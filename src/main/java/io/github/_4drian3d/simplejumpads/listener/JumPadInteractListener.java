package io.github._4drian3d.simplejumpads.listener;

import io.github._4drian3d.simplejumpads.SimpleJumPads;
import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.Position;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("UnstableApiUsage")
public final class JumPadInteractListener implements Listener {
    private final SimpleJumPads plugin;

    public JumPadInteractListener(final SimpleJumPads plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) {
            return;
        }

        final Player player = event.getPlayer();
        if (player.hasPermission("simplejumpads.jump")) {
            return;
        }

        final Location location = player.getLocation();
        final BlockPosition blockPosition = Position.block(location)
                .offset(0, -1, 0);
        final Location blockLocation = blockPosition.toLocation(location.getWorld());
        final Block block = blockLocation.getBlock();

        for (final var section : plugin.getConfiguration().getSections()) {
            if (section.getMaterial() == block.getType()) {
                player.setVelocity(
                        blockLocation.getDirection()
                                .multiply(section.getMultiplyX())
                                .setY(section.getMultiplyY())
                );
                return;
            }
        }
    }
}
