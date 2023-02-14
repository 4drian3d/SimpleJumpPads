package io.github._4drian3d.simplejumppads.commands;

import io.github._4drian3d.simplejumppads.SimpleJumpPads;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JumpPadCommand implements CommandExecutor, TabCompleter {
    private final SimpleJumpPads plugin;

    public JumpPadCommand(final SimpleJumpPads plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            final @NotNull CommandSender sender,
            final @NotNull Command command,
            final @NotNull String label,
            final @NotNull String @NotNull [] args
    ) {
        if (args.length == 0) {
            sender.sendRichMessage("<rainbow>SimpleJumpPads | by 4drian3d");
            return true;
        }
        if ("reload".equalsIgnoreCase(args[0])) {
            try {
                plugin.reloadPlugin();
                sender.sendRichMessage("<green>Correctly reloaded");
            } catch (Exception e) {
                sender.sendRichMessage("<red>An error occurred on configuration reload, check console");
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    private static final List<String> SUGGESTIONS = List.of("reload");

    @Override
    public @Nullable List<String> onTabComplete(
            final @NotNull CommandSender sender,
            final @NotNull Command command,
            final @NotNull String label,
            final @NotNull String @NotNull [] args
    ) {
        if (args.length == 0 || "reload".startsWith(args[0])) {
            return SUGGESTIONS;
        }

        return null;
    }
}
