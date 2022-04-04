package io.github.dhohmann.hardcore.effects.providers;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class BuffProvider1_11 extends BuffProvider1_10 {

	protected void applyEvoker(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.evoker.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.evoker.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.evoker.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.evoker.glowing", 0));
	}
	protected void applyVex(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.vex.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.vex.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.vex.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.vex.glowing", 0));
	}
	protected void applyZombieVillager(EnhancedEffects effects) {
		Utils.add(effects, EnhancedEffectType.WISE, config.getDouble("enhancement.zombievillager.wise", 0));
		Utils.add(effects, EnhancedEffectType.EXPLODING, config.getDouble("enhancement.zombievillager.exploding", 0));
		Utils.add(effects, EnhancedEffectType.BERSERKER, config.getDouble("enhancement.zombievillager.berserker", 0));
		Utils.add(effects, EnhancedEffectType.GLOWING, config.getDouble("enhancement.zombievillager.glowing", 0));
	}
}
