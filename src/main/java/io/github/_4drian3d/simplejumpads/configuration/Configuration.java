package io.github._4drian3d.simplejumpads.configuration;

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
        private int multiplyX = 2;
        private int multiplyY = 2;

        public Material getMaterial() {
            return material;
        }

        public int getMultiplyX() {
            return multiplyX;
        }

        public int getMultiplyY() {
            return multiplyY;
        }
    }
}
