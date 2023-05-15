package io.github._4drian3d.simplejumppads.listener;

import io.github._4drian3d.simplejumppads.SimpleJumpPads;
import net.kyori.adventure.sound.Sound;
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
            if (Objects.equals(section.getMaterial(), type)) {
                player.setVelocity(
                        location.getDirection()
                                .multiply(section.getMultiplyX())
                                .setY(section.getMultiplyY())
                );
                if (section.isEnableParticle()) {
                    location.getWorld().spawnParticle(section.getParticle(), location, 1);
                }
                if (section.isEnableSound()) {
                    player.playSound(section.getSound(), Sound.Emitter.self());
                }
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
