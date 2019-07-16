package com.danielmehlber.myui;

public interface Designable {

	/**
	 * Applies the current design on an object, but not its children
	 */
	void applyDesign();

	MyDesign getDesign();

	/**
	 * Sets the design of an object
	 *
	 * @param d
	 */
	void setDesign(MyDesign d);

	/**
	 * Resets the Designable Component to its design
	 */
	void reset();

}
