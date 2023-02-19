package io.github._4drian3d.simplejumppads.listener;

import io.github._4drian3d.simplejumppads.SimpleJumpPads;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public final class JumpPadInteractListener implements Listener {
    private final SimpleJumpPads plugin;

    public JumpPadInteractListener(final SimpleJumpPads plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) {
            return;
        }

        final Player player = event.getPlayer();
        if (!player.hasPermission("simplejumppads.jump")) {
            return;
        }

        final Location location = copy(player.getLocation());
        final Material type = Objects.requireNonNull(event.getClickedBlock()).getType();

        for (final var section : plugin.getConfiguration().getSections()) {
            if (section.getMaterial() == type) {
                player.setVelocity(
                        location.getDirection()
                                .multiply(section.getMultiplyX())
                                .setY(section.getMultiplyY())
                );
                return;
            }
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private Location copy(final Location original) {
        return new Location(
                original.getWorld(),
                original.blockX(),
                original.blockY(),
                original.blockZ(),
                original.getYaw(),
                original.getPitch()
        );
    }
}
