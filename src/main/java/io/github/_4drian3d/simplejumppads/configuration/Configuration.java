package io.github._4drian3d.simplejumppads.configuration;

import org.bukkit.Material;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "CanBeFinal"})
@ConfigSerializable
public class Configuration {
    @Comment("Configure which types of JumpPads you want to use")
    private Section[] sections = { new Section() };

    public Section[] getSections() {
        return sections;
    }

    @ConfigSerializable
    public static class Section {
        @Comment("Sets the material of this JumpPad")
        private Material material = Material.HEAVY_WEIGHTED_PRESSURE_PLATE;
        @Comment("Sets the horizontal multiplier of this PumpPad")
        private double multiplyX = 1.0;
        @Comment("Sets the vertical multiplier of this JumpPad")
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

        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof final Section section)) {
                return false;
            }
            return section.material == this.material;
        }
    }
}
