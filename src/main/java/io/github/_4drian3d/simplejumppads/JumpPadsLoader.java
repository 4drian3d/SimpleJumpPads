package io.github._4drian3d.simplejumppads;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class JumpPadsLoader implements PluginLoader {
    @Override
    public void classloader(final @NotNull PluginClasspathBuilder classpathBuilder) {
        final MavenLibraryResolver resolver = new MavenLibraryResolver();

        final RemoteRepository mavenCentral = new RemoteRepository
                .Builder("central", "default", "https://repo1.maven.org/maven2/")
                .build();
        final Dependency configurateHocon = new Dependency(
                new DefaultArtifact("org.spongepowered:configurate-hocon:4.1.2"),
                null
        );
        final Dependency configurateAdventure = new Dependency(
                new DefaultArtifact("net.kyori:adventure-serializer-configurate4:4.18.0"),
                null
        );

        resolver.addRepository(mavenCentral);
        resolver.addDependency(configurateHocon);
        resolver.addDependency(configurateAdventure);

        classpathBuilder.addLibrary(resolver);
    }
}
