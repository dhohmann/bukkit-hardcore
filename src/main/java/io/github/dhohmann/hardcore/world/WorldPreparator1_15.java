package io.github.dhohmann.hardcore.world;

import org.bukkit.World;

public class WorldPreparator1_15 extends WorldPreparator1_8 {

	@Override
	public void setupWorld(World world) {
		super.setupWorld(world);
		world.setHardcore(true);
	}

}
