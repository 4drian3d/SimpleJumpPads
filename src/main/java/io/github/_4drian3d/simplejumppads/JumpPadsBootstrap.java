package io.github._4drian3d.simplejumppads;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github._4drian3d.simplejumppads.configuration.ConfigurationContainer;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public final class JumpPadsBootstrap implements PluginBootstrap {
    private ConfigurationContainer configurationContainer;

    @Override
    public void bootstrap(final @NotNull BootstrapContext context) {
        this.configurationContainer = ConfigurationContainer.fromFile(context.getDataDirectory().resolve("config.conf"));
        context.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                event -> event.registrar()
                        .register(context.getPluginMeta(), commandFromContext(context), "SimpleJumpPads Command", List.of("sj")));
    }

    @Override
    public @NotNull JavaPlugin createPlugin(final @NotNull PluginProviderContext context) {
        return new SimpleJumpPads(this.configurationContainer);
    }

    private LiteralCommandNode<CommandSourceStack> commandFromContext(BootstrapContext context) {
      return Commands.literal("simplejumppads")
              .executes(ctx -> {
                ctx.getSource().getSender().sendRichMessage("<rainbow>SimpleJumpPads | by 4drian3d");
                return Command.SINGLE_SUCCESS;
              })
              .requires(ctx -> ctx.getSender().hasPermission("simplejumppads.command"))
              .then(Commands.literal("reload")
                      .executes(ctx -> {
                        try {
                          configurationContainer.load();
                          ctx.getSource().getSender().sendRichMessage("<green>Correctly reloaded");
                        } catch (Exception e) {
                          ctx.getSource().getSender().sendRichMessage("<red>An error occurred on configuration reload, check console");
                          context.getLogger().error("An error occurred loading configuration", e);
                        }
                        return Command.SINGLE_SUCCESS;
                      })
              )
              .build();
    }
}
