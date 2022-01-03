package io.github.dhohmann.hardcore.enhancement;

import org.bukkit.entity.LivingEntity;

public interface OnHitEffect extends Effect {

	/**
	 * Applies the on hit effect to the hit.
	 * 
	 * @param damager
	 * @param damage
	 * @return modified damage value
	 */
	public double applyHitEffect(LivingEntity damager, double damage);

}
