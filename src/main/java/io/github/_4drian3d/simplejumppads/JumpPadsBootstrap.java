package io.github._4drian3d.simplejumppads;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class JumpPadsBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(final @NotNull BootstrapContext context) {
    }

    @Override
    public @NotNull JavaPlugin createPlugin(final @NotNull PluginProviderContext context) {
        return new SimpleJumpPads(context.getDataDirectory());
    }
}
