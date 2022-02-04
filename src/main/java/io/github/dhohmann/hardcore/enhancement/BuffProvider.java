package io.github.dhohmann.hardcore.enhancement;

import org.bukkit.entity.EntityType;

public interface BuffProvider {

	EnhancedEffects getBuffs(EntityType type);
}
