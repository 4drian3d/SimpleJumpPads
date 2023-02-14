package io.github._4drian3d.simplejumpads.configuration;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;

public final class Loader {
    public static Configuration loadConfiguration(final Path path) throws ConfigurateException {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .defaultOptions(a ->
                    a.header("SimpleJumPads | by 4drian3d")
                            .shouldCopyDefaults(true)
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
