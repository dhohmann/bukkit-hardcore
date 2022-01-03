package io.github.dhohmann.hardcore.enhancement;

import org.bukkit.entity.LivingEntity;

public interface DeathEffect extends Effect {

	public void execute(LivingEntity dying, LivingEntity killer);
}
