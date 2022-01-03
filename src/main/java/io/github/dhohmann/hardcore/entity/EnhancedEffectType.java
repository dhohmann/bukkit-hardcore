package io.github.dhohmann.hardcore.entity;

import io.github.dhohmann.hardcore.enhancement.Effect;
import io.github.dhohmann.hardcore.entity.enhancement.Berserker;
import io.github.dhohmann.hardcore.entity.enhancement.Exploding;
import io.github.dhohmann.hardcore.entity.enhancement.Glowing;

public enum EnhancedEffectType {

	/**
	 * Adds a glowing effect to an entity.
	 */
	GLOWING(Glowing.class),
	/**
	 * After the entity dies, it explodes after delay of {@value Exploding#DELAY}
	 * seconds.
	 * 
	 * @see Exploding#DELAY
	 */
	EXPLODING(Exploding.class),
	/**
	 * When an entity hits another entity, the damage gets multiplied by a factor of
	 * {@value Berserker#DAMAGE_MOFIDIER}.
	 * 
	 * @see Berserker#DAMAGE_MOFIDIER
	 */
	BERSERKER(Berserker.class);

	private Class<? extends Effect> effect;

	EnhancedEffectType(Class<? extends Effect> handler) {
		this.effect = handler;
	}

	public Effect getHandler() {
		try {
			return effect.getConstructor().newInstance();
		} catch (Throwable e) {
			throw new RuntimeException("Enhance handler is invalid", e);
		}
	}

}
