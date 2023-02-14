package io.github._4drian3d.simplejumppads.configuration;

import org.bukkit.Material;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "CanBeFinal"})
@ConfigSerializable
public class Configuration {
    private List<Section> sections = List.of(new Section());

    public List<Section> getSections() {
        return sections;
    }

    @ConfigSerializable
    public static class Section {
        private Material material = Material.HEAVY_WEIGHTED_PRESSURE_PLATE;
        private double multiplyX = 1.0;
        private double multiplyY = 1.0;

        public Material getMaterial() {
            return material;
        }

        public double getMultiplyX() {
            return multiplyX;
        }

        public double getMultiplyY() {
            return multiplyY;
        }
    }
}
