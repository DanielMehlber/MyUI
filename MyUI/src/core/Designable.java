package core;

public abstract interface Designable {

	public MyMaterialDesign design = MyMaterialDesign.DEFAULT;
	/**
	 * Applies the current design on an object, but not its children
	 */
	public abstract void applyDesign();
	/**
	 * Sets the design of an object
	 * @param d
	 */
	public abstract void setDesign(MyMaterialDesign d);

}
