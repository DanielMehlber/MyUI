package core;

public abstract interface Designable {

	/**
	 * Applies the current design on an object, but not its children
	 */
	public abstract void applyDesign();
	/**
	 * Sets the design of an object
	 * @param d
	 */
	public abstract void setDesign(MyMaterialDesign d);
	public abstract MyMaterialDesign getDesign();
	/**
	 * Resets the Designable Component to its design
	 */
	public abstract void reset();

}
