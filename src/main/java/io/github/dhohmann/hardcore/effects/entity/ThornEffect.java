package io.github.dhohmann.hardcore.effects.entity;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.dhohmann.hardcore.effects.Effect;

/**
 * When an entity gets hit by another entity then this effect applies.
 */
public interface ThornEffect extends Effect {

	/**
	 * Called when the entity with this effect receives a hit.
	 * 
	 * Use {@link EntityDamageByEntityEvent#getEntity()} to get the entity with this effect.
	 * Use {@link EntityDamageByEntityEvent#getDamager()} to get the entity that made the hit.
	 * 
	 * @param event Event containing the information
	 */
	public void onReceiveHit(EntityDamageByEntityEvent event);

}
