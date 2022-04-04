package io.github.dhohmann.hardcore.entity.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent;

import io.github.dhohmann.hardcore.effects.EnhancedEffects;

public class EntityEnhanceEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private EnhancedEffects effects;
	private boolean canceled;
	private CreatureSpawnEvent event;

	public EntityEnhanceEvent(CreatureSpawnEvent event, EnhancedEffects effects) {
		this.event = event;
		this.effects = effects;
	}

	public EnhancedEffects getEffects() {
		return effects;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return canceled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.canceled = cancel;
	}

	public LivingEntity getEntity() {
		return event.getEntity();
	}

	public CreatureSpawnEvent getSpawnEvent() {
		return event;
	}

}
