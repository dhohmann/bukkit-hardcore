package io.github.dhohmann.hardcore.effects.entity;

import org.bukkit.event.entity.CreatureSpawnEvent;

import io.github.dhohmann.hardcore.effects.Effect;

public interface SpawnEffect extends Effect {

	/**
	 * Called when an entity that should be enhanced spawns.
	 * 
	 * @param event Spawning event
	 */
	public void onSpawn(CreatureSpawnEvent event);
}
