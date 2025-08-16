package ru.mrbedrockpy.bedrocklib.config.variable.paper;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class TextColorVariable extends ConfigVariable<TextColor> {

    public TextColorVariable(String path, TextColor defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public Class<TextColor> getType() {
        return TextColor.class;
    }

    @Override
    public void load(FileConfiguration config) {
        String colorString = config.getString(getPath());
        if (colorString == null) {
            set(getDefaultValue());
            return;
        }
        TextColor color = TextColor.fromHexString(colorString);
        set(color != null ? color : getDefaultValue());
    }

    @Override
    public void save(FileConfiguration config) {
        config.set(getPath(), get().asHexString());
    }
}
