package io.github._4drian3d.simplejumppads;

import io.github._4drian3d.simplejumppads.commands.JumpPadCommand;
import io.github._4drian3d.simplejumppads.configuration.Configuration;
import io.github._4drian3d.simplejumppads.configuration.Loader;
import io.github._4drian3d.simplejumppads.listener.JumpPadInteractListener;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.permissions.DefaultPermissions;

import java.nio.file.Path;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@SuppressWarnings("unused")
public final class SimpleJumpPads extends JavaPlugin {
    private Configuration configuration;
    private final Path pluginpath;

    SimpleJumpPads(Path pluginPath) {
        this.pluginpath = pluginPath;
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
            reloadPlugin();
        } catch(Exception e) {
            logger.info(miniMessage().deserialize("<red>Cannot load configuration"), e);
            pluginmanager.disablePlugin(this);
            return;
        }

        pluginmanager.registerEvents(new JumpPadInteractListener(this), this);
        getServer().getCommandMap().register("simplejumppads",new JumpPadCommand(this));

        logger.info(miniMessage().deserialize("<gradient:#233329:#63D471>Correctly Started"));
    }

    public void reloadPlugin() throws Exception {
        this.configuration = Loader.loadConfiguration(pluginpath);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
