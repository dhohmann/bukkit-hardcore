package io.github.dhohmann.hardcore.effects.entity;

import org.bukkit.event.entity.CreatureSpawnEvent;

import io.github.dhohmann.hardcore.effects.Effect;

public interface SpawnEffect extends Effect  {

	public void onSpawn(CreatureSpawnEvent event);
}
