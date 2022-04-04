package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.OnHitEffect;
import io.github.dhohmann.hardcore.effects.entity.SpawnEffect;

/**
 * Spawns an entity with fire effect. If this entity gets attacked, it sets the
 * attacker for {@value #FIRE_DURATION} seconds on fire.
 */
public class Burning implements SpawnEffect, OnHitEffect {

	private static final String MODIFIER = "burning";
	private static final int FIRE_DURATION = 3;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void onHit(EntityDamageByEntityEvent event) {
		event.getEntity().setFireTicks(FIRE_DURATION * TICKS_PER_SECOND);
		
	}

	@Override
	public void onSpawn(CreatureSpawnEvent event) {
		event.getEntity().setVisualFire(true);
		
	}

}
