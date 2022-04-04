package io.github.dhohmann.hardcore.effects;

import org.bukkit.entity.EntityType;

public interface BuffProvider {

	EnhancedEffects getBuffs(EntityType type);
}
