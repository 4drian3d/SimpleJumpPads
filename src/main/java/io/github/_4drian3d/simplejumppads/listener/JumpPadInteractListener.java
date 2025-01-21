package io.github._4drian3d.simplejumppads.listener;

import io.github._4drian3d.simplejumppads.SimpleJumpPads;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record JumpPadInteractListener(SimpleJumpPads plugin) implements EventExecutor, Listener {

    @Override
    public void execute(final @NotNull Listener listener, final @NotNull Event event) {
        final PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;
        if (interactEvent.getAction() != Action.PHYSICAL) {
            return;
        }

        final Player player = interactEvent.getPlayer();
        if (!player.hasPermission("simplejumppads.jump")) {
            return;
        }

        final Location location = copy(player.getLocation());
        final Material type = Objects.requireNonNull(interactEvent.getClickedBlock()).getType();

        for (final var section : plugin.getConfiguration().get().getSections()) {
            if (Objects.equals(section.getMaterial(), type)) {
                player.setVelocity(
                        location.getDirection()
                                .multiply(section.getMultiplyX())
                                .setY(section.getMultiplyY())
                );
                if (section.isEnableParticle()) {
                    location.getWorld().spawnParticle(section.getParticle(), location, Math.max(section.getParticleAmount(), 1));
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

    public void register() {
        plugin.getServer().getPluginManager()
                .registerEvent(PlayerInteractEvent.class, this, EventPriority.NORMAL, this, plugin, true);
    }
}
