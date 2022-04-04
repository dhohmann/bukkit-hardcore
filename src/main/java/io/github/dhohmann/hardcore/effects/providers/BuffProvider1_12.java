package io.github.dhohmann.hardcore.effects.providers;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class BuffProvider1_12 extends BuffProvider1_11 {

	protected void applyIllusioner(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.illusioner.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.illusioner.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.illusioner.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.illusioner.glowing", 0));
	}

}
