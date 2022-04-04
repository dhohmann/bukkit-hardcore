package io.github.dhohmann.hardcore;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import io.github.dhohmann.hardcore.effects.BuffProvider;
import io.github.dhohmann.hardcore.effects.EnhancedEffectType;
import io.github.dhohmann.hardcore.effects.EnhancedEffects;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_10;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_11;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_12;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_13;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_14;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_16;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_8;
import io.github.dhohmann.hardcore.effects.providers.BuffProvider1_9;
import io.github.dhohmann.hardcore.world.WorldPreparator;
import io.github.dhohmann.hardcore.world.WorldPreparator1_15;
import io.github.dhohmann.hardcore.world.WorldPreparator1_8;

public class Utils {

	private static ResourceBundle bundle;
	private static WorldPreparator worldPreparator;
	private static BuffProvider buffProvider;

	public static ResourceBundle getBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("enhancements");
		}
		return bundle;
	}

	public static WorldPreparator getWorldPreparator() {
		if (worldPreparator == null) {
			VersionInfo version = new VersionInfo(Bukkit.getBukkitVersion());
			if (version.isBefore("1.15")) {
				worldPreparator = new WorldPreparator1_8();
			} else {
				worldPreparator = new WorldPreparator1_15();
			}
		}
		return worldPreparator;
	}

	public static BuffProvider getBuffProvider() {
		if (buffProvider == null) {
			VersionInfo version = new VersionInfo(Bukkit.getBukkitVersion());
			if (version.isEqual("1.8")) {
				buffProvider = new BuffProvider1_8();
			} else if (version.isEqual("1.9")) {
				buffProvider = new BuffProvider1_9();
			} else if (version.isEqual("1.10")) {
				buffProvider = new BuffProvider1_10();
			} else if (version.isEqual("1.11")) {
				buffProvider = new BuffProvider1_11();
			} else if (version.isEqual("1.12")) {
				buffProvider = new BuffProvider1_12();
			} else if (version.isEqual("1.13")) {
				buffProvider = new BuffProvider1_13();
			} else if (version.isEqual("1.14") || version.isEqual("1.15")) {
				buffProvider = new BuffProvider1_14();
			} else if (version.isEqualOrHigherThan("1.16")) {
				buffProvider = new BuffProvider1_16();
			}
		}
		return buffProvider;
	}

	public static EnhancedEffects getEnhancements(LivingEntity entity) {
		EnhancedEffects effects = getBuffProvider().getBuffs(entity.getType());
		return effects;
	}

	public static void add(EnhancedEffects enhanced, EnhancedEffectType type, double perc) {
		if (Math.random() < perc) {
			enhanced.addEffect(type);
		}
	}

	public static String getTranslation(String text) {
		try {
			return bundle.getString(text);
		} catch (MissingResourceException | ClassCastException e) {
			return text;
		}
	}

}
