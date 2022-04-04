package io.github.dhohmann.hardcore.effects;

public interface Effect {

	/**
	 * Provides the translatable modifier that will be used as the custom name.
	 * 
	 * @return 
	 */
	public String getModifier();
	
	static final int TICKS_PER_SECOND = 20;
}
