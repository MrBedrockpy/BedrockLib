package ru.mrbedrockpy.bedrocklib.config.variable.bukkit;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class MaterialVariable extends ConfigVariable<Material> {

    public MaterialVariable(String path, Material defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public Class<Material> getType() {
        return Material.class;
    }

    @Override
    public void load(FileConfiguration config) {
        String materialString = config.getString(getPath());
        if (materialString == null) {
            set(getDefaultValue());
            return;
        }
        Material material = Material.getMaterial(materialString);
        set(material != null ? material : getDefaultValue());
    }

    @Override
    public void save(FileConfiguration config) {
        config.set(getPath(), get().toString());
    }
}
