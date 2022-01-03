package io.github.dhohmann.hardcore.enhancement;

import org.bukkit.entity.LivingEntity;

public interface SpawnEffect extends Effect  {

	public void applyOn(LivingEntity entity);
}
