package io.github.dhohmann.hardcore.enhancement.providers;

import io.github.dhohmann.hardcore.enhancement.EnhancedEffectType;
import io.github.dhohmann.hardcore.enhancement.EnhancedEffects;
import io.github.dhohmann.hardcore.enhancement.Utils;

public class BuffProvider1_16 extends BuffProvider1_14 {

	protected void applyPiglin(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.piglin.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.piglin.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.piglin.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.piglin.glowing", 0));
	}

	protected void applyPiglinBrute(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.piglinbrute.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.piglinbrute.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.piglinbrute.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.piglinbrute.glowing", 0));
	}

	protected void applyHoglin(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.hoglin.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.hoglin.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.hoglin.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.hoglin.glowing", 0));
	}
}
