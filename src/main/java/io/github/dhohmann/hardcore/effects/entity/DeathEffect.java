package io.github.dhohmann.hardcore.effects.entity;

import org.bukkit.event.entity.EntityDeathEvent;

import io.github.dhohmann.hardcore.effects.Effect;

public interface DeathEffect extends Effect {

	public void onDeath(EntityDeathEvent event);
}
