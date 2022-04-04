package io.github.dhohmann.hardcore.effects.entity;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.dhohmann.hardcore.effects.Effect;

/**
 * When an entity hits another entity then this effect applies.
 */
public interface OnHitEffect extends Effect {

	public void onHit(EntityDamageByEntityEvent event);

}
