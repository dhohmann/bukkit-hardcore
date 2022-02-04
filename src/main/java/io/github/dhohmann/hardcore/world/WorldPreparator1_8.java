package io.github.dhohmann.hardcore.world;

import org.bukkit.Difficulty;
import org.bukkit.World;

public class WorldPreparator1_8 implements WorldPreparator {

	@Override
	public void setupWorld(World world) {
		world.setSpawnFlags(true, world.getAllowAnimals());
		world.setDifficulty(Difficulty.HARD);		
	}

}
