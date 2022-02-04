package io.github.dhohmann.hardcore.entity.enhancement;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import io.github.dhohmann.hardcore.enhancement.DeathEffect;
import io.github.dhohmann.hardcore.enhancement.Utils;

public class Wise implements DeathEffect {

	private static final String MODIFIER = "wise";

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void execute(LivingEntity dying, LivingEntity killer) {
		if (killer instanceof Player) {
			Player p = (Player) killer;
			p.giveExpLevels(1);
		}
	}

}
