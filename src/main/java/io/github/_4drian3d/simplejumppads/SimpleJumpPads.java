package io.github._4drian3d.simplejumppads;

import io.github._4drian3d.simplejumppads.configuration.ConfigurationContainer;
import io.github._4drian3d.simplejumppads.listener.JumpPadInteractListener;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.permissions.DefaultPermissions;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@SuppressWarnings("unused")
public final class SimpleJumpPads extends JavaPlugin implements Listener {
    private final ConfigurationContainer configurationContainer;

    SimpleJumpPads(ConfigurationContainer configurationContainer) {
        this.configurationContainer = configurationContainer;
    }

    @Override
    public void onEnable() {
        final Permission commandPermission = new Permission("simplejumppads.command", PermissionDefault.OP);
        final Permission jumpPermission = new Permission("simplejumppads.jump", PermissionDefault.TRUE);
        DefaultPermissions.registerPermission(commandPermission);
        DefaultPermissions.registerPermission(jumpPermission);

        final var logger = this.getComponentLogger();
        final var pluginmanager = getServer().getPluginManager();

        try {
            this.configurationContainer.load();
        } catch(Exception e) {
            logger.info(miniMessage().deserialize("<red>Cannot load configuration"), e);
            pluginmanager.disablePlugin(this);
            return;
        }

        new JumpPadInteractListener(this).register();

        logger.info(miniMessage().deserialize("<gradient:#233329:#63D471>Correctly Started"));
    }

    public ConfigurationContainer getConfiguration() {
        return configurationContainer;
    }
}
