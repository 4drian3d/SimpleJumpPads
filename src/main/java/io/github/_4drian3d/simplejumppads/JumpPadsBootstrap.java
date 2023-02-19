package io.github._4drian3d.simplejumppads;

import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class JumpPadsBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(@NotNull PluginProviderContext context) {
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new SimpleJumpPads(context.getDataDirectory());
    }
}
