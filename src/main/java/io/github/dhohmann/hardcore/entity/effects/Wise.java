package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.DeathEffect;

/**
 * When the entity dies to a player, the player receives {@value #EXP_LEVELS}
 * level of XP.
 */
public class Wise implements DeathEffect {

	private static final String MODIFIER = "wise";
	private static final int EXP_LEVELS = 1;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void onDeath(EntityDeathEvent event) {
		Player p = event.getEntity().getKiller();
		if (p != null) {
			p.giveExpLevels(EXP_LEVELS);
		}
	}

}
