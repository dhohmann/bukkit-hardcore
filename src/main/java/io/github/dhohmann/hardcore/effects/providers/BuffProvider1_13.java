package io.github.dhohmann.hardcore.effects.providers;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class BuffProvider1_13 extends BuffProvider1_12{

	protected void applyDrowned(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.drowned.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.drowned.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.drowned.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.drowned.glowing", 0));
	}
	protected void applyPhantom(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.phantom.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.phantom.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.phantom.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.phantom.glowing", 0));
	};
}
