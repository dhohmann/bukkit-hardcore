package io.github.dhohmann.hardcore.effects.providers;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class BuffProvider1_10 extends BuffProvider1_9 {
	protected void applyHusk(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.husk.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.husk.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.husk.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.husk.glowing", 0));
	}
}
