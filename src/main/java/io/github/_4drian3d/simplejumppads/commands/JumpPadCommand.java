package io.github._4drian3d.simplejumppads.commands;

import io.github._4drian3d.simplejumppads.SimpleJumpPads;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class JumpPadCommand extends Command {
    private final SimpleJumpPads plugin;

    public JumpPadCommand(final SimpleJumpPads plugin) {
        super("simplejumppads", "", "", List.of("jumppads"));
        setPermission("simplejumppads.command");
        this.plugin = plugin;
    }

    private static final List<String> SUGGESTIONS = List.of("reload");

    @Override
    public @NotNull List<String> tabComplete(
            final @NotNull CommandSender sender,
            final @NotNull String label,
            final @NotNull String @NotNull [] args,
            final @Nullable Location location
    ) {
        if (args.length == 0 || "reload".startsWith(args[0])) {
            return SUGGESTIONS;
        }

        return List.of();
    }

    @Override
    public boolean execute(
            @NotNull CommandSender sender,
            @NotNull String commandLabel,
            @NotNull String[] args
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
}
