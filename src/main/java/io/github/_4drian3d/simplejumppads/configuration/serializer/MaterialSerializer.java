package io.github._4drian3d.simplejumppads.configuration.serializer;

import org.bukkit.Material;
import org.spongepowered.configurate.serialize.ScalarSerializer;
import org.spongepowered.configurate.serialize.SerializationException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class MaterialSerializer extends ScalarSerializer<Material> {
    @SuppressWarnings("Convert2MethodRef")
    private static final Map<String, Material> ALLOWED = Arrays.stream(Material.values())
            .filter(material -> material.toString().endsWith("PRESSURE_PLATE"))
            .collect(Collectors.toUnmodifiableMap(material -> material.toString(), Function.identity()));

    public MaterialSerializer() {
        super(Material.class);
    }

    @Override
    public Material deserialize(final Type type, final Object obj) throws SerializationException {
        final Material material = ALLOWED.get(obj.toString());
        if (material == null) {
            throw new SerializationException("Invalid material provided: " + obj);
        }
        return material;
    }

    @Override
    protected Object serialize(Material item, Predicate<Class<?>> typeSupported) {
        return item.toString();
    }
}
