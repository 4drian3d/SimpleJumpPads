package io.github._4drian3d.simplejumppads.configuration.serializer;

import net.kyori.adventure.util.Index;
import org.bukkit.Particle;
import org.spongepowered.configurate.serialize.ScalarSerializer;

import java.lang.reflect.Type;
import java.util.function.Predicate;

public final class ParticleSerializer extends ScalarSerializer<Particle> {
    @SuppressWarnings("Convert2MethodRef")
    private static final Index<String, Particle> INDEX = Index.create(particle -> particle.toString(), Particle.values());

    public ParticleSerializer() {
        super(Particle.class);
    }

    @Override
    public Particle deserialize(Type type, Object obj) {
        return INDEX.valueOrThrow(obj.toString());
    }

    @Override
    protected Object serialize(Particle item, Predicate<Class<?>> typeSupported) {
        return item.toString();
    }
}
