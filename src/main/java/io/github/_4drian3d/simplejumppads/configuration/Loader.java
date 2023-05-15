package io.github._4drian3d.simplejumppads.configuration;

import io.github._4drian3d.simplejumppads.configuration.serializer.MaterialSerializer;
import io.github._4drian3d.simplejumppads.configuration.serializer.ParticleSerializer;
import net.kyori.adventure.serializer.configurate4.ConfigurateComponentSerializer;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;

public final class Loader {
    public static Configuration loadConfiguration(final Path path) throws ConfigurateException {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .defaultOptions(a ->
                    a.header("""
                            SimpleJumpPads | by 4drian3d
                            
                            Available Materials for JumpPads https://github.com/4drian3d/SimpleJumpPads/wiki/Materials
                            Available Particles https://github.com/4drian3d/SimpleJumpPads/wiki/Particles""")
                            .shouldCopyDefaults(true)
                            .serializers(
                                    builder -> builder.registerAll(ConfigurateComponentSerializer.configurate()
                                            .serializers())
                                            .register(new MaterialSerializer())
                                            .register(new ParticleSerializer())
                            )
                )
                .path(path.resolve("config.conf"))
                .build();

        final var node = loader.load();
        final Configuration configuration = node.get(Configuration.class);
        node.set(Configuration.class, configuration);
        loader.save(node);
        return configuration;
    }

    private Loader() {}
}
