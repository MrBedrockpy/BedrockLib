package ru.mrbedrockpy.bedrocklib.config;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.config.comment.Comment;
import ru.mrbedrockpy.bedrocklib.config.comment.CommentType;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;
import ru.mrbedrockpy.bedrocklib.manager.Manager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Getter
public abstract class AbstractConfig<P extends BedrockPlugin> extends Manager<P> {

    private final YamlConfiguration config;
    private final File configFile;
    private final String name;
    private boolean createdNow = false;

    private final List<ConfigVariable<?>> variables = new ArrayList<>();
    private final Map<String, Comment> commentMap = new LinkedHashMap<>();
    private final List<String> topComments = new ArrayList<>();

    public AbstractConfig(P plugin, String configName) {
        super(plugin);
        this.name = configName;
        this.configFile = new File(getPlugin().getDataFolder(), configName);
        if (!configFile.exists()) {
            createdNow = true;
            configFile.getParentFile().mkdirs();
            try {
                if (!configFile.createNewFile()) throw new RuntimeException("Failed to create config file: " + configName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            getPlugin().getLogger().warning("Error loading config: " + e);
        }
        registerFields();
        if (createdNow) save();
        else load();
    }

    private void registerFields() {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(this);

                if (fieldValue instanceof ConfigVariable) {
                    ConfigVariable<?> variable = (ConfigVariable<?>) fieldValue;
                    variables.add(variable);
                    Comment comment = field.getAnnotation(Comment.class);
                    if (comment != null) {
                        if (comment.type() == CommentType.TOP) topComments.add("# " + comment.value().replace("\n", "\n# "));
                        else commentMap.put(variable.getPath(), comment);
                    }
                }
            } catch (IllegalAccessException e) {
                getPlugin().getLogger().warning("Failed to register config field: " + field.getName());
            }
        }
    }

    public void load() {
        variables.forEach(variable -> variable.load(config));
    }

    public void save() {
        variables.forEach(variable -> variable.save(config));
        try {
            String yamlContent = config.saveToString();
            String commentedContent = addCommentsToYaml(yamlContent);
            Files.write(
                    configFile.toPath(),
                    commentedContent.getBytes(),
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to save config: " + name, e);
        }
    }

    private String addCommentsToYaml(String yamlContent) {
        StringBuilder result = new StringBuilder();
        if (!topComments.isEmpty()) {
            for (String comment : topComments) result.append(comment).append("\n");
            result.append("\n");
        }
        String[] lines = yamlContent.split("\n");
        for (String line : lines) {
            if (result.isEmpty() && line.trim().isEmpty()) continue;
            result.append(line);
            if (!line.trim().isEmpty() && line.contains(":")) {
                String key = line.split(":")[0].trim();
                Comment comment = commentMap.get(key);
                if (comment != null && comment.type() == CommentType.INLINE) result.append(" # ").append(comment.value());
            }
            result.append("\n");
        }
        return result.toString();
    }
}
