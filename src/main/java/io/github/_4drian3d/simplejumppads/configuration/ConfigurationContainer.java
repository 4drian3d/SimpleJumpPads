package io.github._4drian3d.simplejumppads.configuration;

import io.github._4drian3d.simplejumppads.configuration.serializer.MaterialSerializer;
import io.github._4drian3d.simplejumppads.configuration.serializer.ParticleSerializer;
import net.kyori.adventure.serializer.configurate4.ConfigurateComponentSerializer;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.requireNonNull;

public final class ConfigurationContainer {
  private final AtomicReference<Configuration> configuration = new AtomicReference<>();
  private final Path filePath;

  private ConfigurationContainer(Path path) {
    this.filePath = path;
  }

  public static ConfigurationContainer fromFile(Path path) {
    return new ConfigurationContainer(path);
  }


  public boolean hasLoaded() {
    return configuration.get() != null;
  }

  public void load() throws ConfigurateException {
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
            .path(filePath.resolve("config.conf"))
            .build();

    final var node = loader.load();
    final Configuration configuration = node.get(Configuration.class);
    node.set(Configuration.class, configuration);
    loader.save(node);
    this.configuration.set(configuration);
  }

  public Configuration get() {
    return requireNonNull(configuration.get());
  }
}
