package org.sits.design.pattern.creational.builder;

public class RowHouse implements Builder {

	private House house = new House();

	@Override
	public void buildBasement() {
		house.setBasement("basement");
	}

	@Override
	public void buildStructure() {
		house.setStructure("structure");
	}

	@Override
	public void bulidRoof() {
		house.setRoof("roof");
	}

	@Override
	public void buildInterior() {
		house.setInterior("interior");
	}

	@Override
	public House getHouse() {
		return house;
	}

}
