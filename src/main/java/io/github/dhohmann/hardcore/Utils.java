package io.github.dhohmann.hardcore;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bukkit.entity.LivingEntity;

import io.github.dhohmann.hardcore.entity.EnhancedEffects;

public class Utils {

	private static ResourceBundle bundle;

	public static ResourceBundle getBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("enhancements");
		}
		return bundle;
	}

	public static EnhancedEffects getEnhancements(LivingEntity entity) {
		return null;
	}

	public static String getTranslation(String text) {
		try {
			return bundle.getString(text);
		} catch (MissingResourceException | ClassCastException e) {
			return text;
		}
	}

}
