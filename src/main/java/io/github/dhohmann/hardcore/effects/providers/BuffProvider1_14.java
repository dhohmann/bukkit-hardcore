package io.github.dhohmann.hardcore.effects.providers;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class BuffProvider1_14 extends BuffProvider1_13 {

	protected void applyPillager(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.pillager.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.pillager.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.pillager.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.pillager.glowing", 0));
	}

	protected void applyRavager(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.ravager.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.ravager.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.ravager.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.ravager.glowing", 0));
	}
}
