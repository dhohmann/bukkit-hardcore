package io.github.dhohmann.hardcore.entity.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.github.dhohmann.hardcore.enhancement.EnhancedEffects;

public class EntityEnhanceEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private EnhancedEffects effects;
	private boolean canceled;
	private LivingEntity entity;

	public EntityEnhanceEvent(LivingEntity what, EnhancedEffects effects) {
		this.entity = what;
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
		return entity;
	}

}
