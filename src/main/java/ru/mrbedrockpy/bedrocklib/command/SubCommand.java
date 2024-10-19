package ru.mrbedrockpy.bedrocklib.command;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    String getTag();

    default boolean permission(CommandSender sender) {
        return true;
    }

    CommandResult execute(CommandSender sender, String[] args);

}
