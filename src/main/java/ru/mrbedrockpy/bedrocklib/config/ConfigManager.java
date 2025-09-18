package ru.mrbedrockpy.bedrocklib.config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.config.annotation.Config;
import ru.mrbedrockpy.bedrocklib.config.annotation.ConfigField;
import ru.mrbedrockpy.bedrocklib.config.data.ConfigData;
import ru.mrbedrockpy.bedrocklib.config.data.ConfigFieldData;
import ru.mrbedrockpy.bedrocklib.manager.Manager;
import ru.mrbedrockpy.bedrocklib.serialization.SerializeConfig;
import ru.mrbedrockpy.bedrocklib.serialization.Serializer;
import ru.mrbedrockpy.bedrocklib.util.FileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ConfigManager<P extends BedrockPlugin<P>> extends Manager<P> {

    private final Yaml yaml = new Yaml(getDumperOptions());

    private final File configFolder;
    private final SerializeConfig<P> serializeConfig;

    private final ConfigData[] configs;

    private ConfigManager(P plugin, File configFolder, SerializeConfig<P> serializeConfig, Class<?>... configs) {
        super(plugin);
        this.configFolder = configFolder;
        this.serializeConfig = serializeConfig;
        this.configs = Arrays.stream(configs)
                .map(this::initConfig)
                .filter(Objects::nonNull)
                .toArray(ConfigData[]::new);
        this.loadConfigs();
    }

    public ConfigData initConfig(Class<?> clazz) {
        Config configAnnotation = clazz.getAnnotation(Config.class);
        if (configAnnotation == null) {
            getPlugin().getLogger().warning(clazz.getName() + " is not annotated with @Config");
            return null;
        }
        List<ConfigFieldData<?>> fields = new ArrayList<>();
        for (Field field: clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            ConfigField fieldAnnotation = field.getAnnotation(ConfigField.class);
            if (fieldAnnotation == null) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(null);
                if (value == null) {
                    getPlugin().getLogger().warning("Field cannot be null: " + fieldAnnotation.name());
                    continue;
                }
                fields.add(new ConfigFieldData<>(fieldAnnotation.name(), field.getType(), field));
            } catch (IllegalAccessException e) {
                getPlugin().getLogger().warning(fieldAnnotation.name() + "'s access error : " + e.getMessage());
            }
        }
        return new ConfigData(
                configAnnotation.name(),
                fields.toArray(new ConfigFieldData[0]),
                Arrays.stream(clazz.getDeclaredClasses())
                        .map(this::initConfig)
                        .filter(Objects::nonNull)
                        .toArray(ConfigData[]::new)
        );
    }

    public void loadConfigs() {
        for (ConfigData config : configs) {
            File configFile = new File(configFolder, config.getName());
            String content = FileUtil.getTextFile(configFile);
            Map<String, Object> data;
            if (content == null || content.isEmpty()) data = new HashMap<>();
            else {
                data = yaml.load(content);
                if (data == null) data = new HashMap<>();
            }
            if (data.isEmpty()) saveConfigs();
            else loadConfig(config, data);
        }
    }

    private void loadConfig(ConfigData configData, Map<String, Object> data) {
        for (ConfigFieldData<?> fieldData : configData.getFields()) {
            if (data.containsKey(fieldData.getName())) {
                try {
                    String value = String.valueOf(data.get(fieldData.getName()));
                    Serializer<?> serializer = serializeConfig.getById(fieldData.getType());
                    if (serializer == null) throw new RuntimeException("Serializer not found: " + fieldData.getType().getName());
                    fieldData.setValue(serializer.deserialize(value));
                } catch (Exception e) {
                    getPlugin().getLogger().warning("Failed to set value for field " + fieldData.getName() + ": " + e.getMessage());
                }
            }
        }
        for (ConfigData category : configData.getCategories()) {
            Object categoryData = data.get(category.getName());
            if (categoryData instanceof Map) {
                loadConfig(category, (Map<String, Object>) categoryData);
            }
        }
    }

    public void saveConfigs() {
        for (ConfigData config : configs) {
            Map<String, Object> data = new LinkedHashMap<>();
            saveConfig(config, data);
            try {
                FileUtil.setTextFile(new File(configFolder, config.getName()), yaml.dump(data));
            } catch (Exception e) {
                getPlugin().getLogger().warning("Failed to save config " + config.getName() + ": " + e.getMessage());
            }
        }
    }

    private void saveConfig(ConfigData configData, Map<String, Object> data) {
        for (ConfigFieldData<?> fieldData : configData.getFields()) {
            try {

                Serializer<?> serializer = serializeConfig.getById(fieldData.getValue().getClass());
                if (serializer == null) throw new RuntimeException("Serializer not found: " + fieldData.getType().getName());
                data.put(fieldData.getName(), serialize(serializer, fieldData.getValue()));
            } catch (Exception e) {
                getPlugin().getLogger().warning("Failed to get value for field " + fieldData.getName() + ": " + e.getMessage());
            }
        }
        for (ConfigData category : configData.getCategories()) {
            Map<String, Object> categoryData = new LinkedHashMap<>();
            saveConfig(category, categoryData);
            data.put(category.getName(), categoryData);
        }
    }

    private <F> String serialize(Serializer<F> serializer, Object value) {
        return serializer.serialize((F) value);
    }

    private DumperOptions getDumperOptions() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        options.setPrettyFlow(true);
        options.setIndent(2);
        options.setSplitLines(false);
        return options;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private File pluginFolder;
        private Class<?>[] configs;

        private Builder() {}

        public Builder withPluginFolder(File pluginFolder) {
            this.pluginFolder = pluginFolder;
            return this;
        }

        public Builder withConfigs(Class<?>... configs) {
            this.configs = configs;
            return this;
        }

        public <P extends BedrockPlugin<P>> ConfigManager<P> build(P plugin, SerializeConfig<P> serializeConfig) {
            return new ConfigManager<>(plugin, pluginFolder, serializeConfig, configs);
        }
    }
}
