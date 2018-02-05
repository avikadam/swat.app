package org.sits.design.pattern.creational.builder;

public interface Builder {
	public void buildBasement();

	public void buildStructure();

	public void bulidRoof();

	public void buildInterior();

	public House getHouse();
}
