package io.github._4drian3d.simplejumppads.configuration;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;

public final class Loader {
    public static Configuration loadConfiguration(final Path path) throws ConfigurateException {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .defaultOptions(a ->
                    a.header("""
                            SimpleJumpPads | by 4drian3d
                            
                            Available Materials for JumpPads:
                            - ACACIA_PRESSURE_PLATE
                            - BAMBOO_PRESSURE_PLATE
                            - BIRCH_PRESSURE_PLATE
                            - CRIMSON_PRESSURE_PLATE
                            - DARK_OAK_PRESSURE_PLATE
                            - HEAVY_WEIGHTED_PRESSURE_PLATE
                            - JUNGLE_PRESSURE_PLATE
                            - LIGHT_WEIGHTED_PRESSURE_PLATE
                            - MANGROVE_PRESSURE_PLATE
                            - OAK_PRESSURE_PLATE
                            - POLISHED_BLACKSTONE_PRESSURE_PLATE
                            - SPRUCE_PRESSURE_PLATE
                            - STONE_PRESSURE_PLATE
                            - WARPED_PRESSURE_PLATE""")
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
