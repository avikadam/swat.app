package org.sits.design.pattern.creational.builder;

public class Engineer {
	private Builder builder;

	public Engineer(Builder builder) {
		this.builder = builder;
	}

	public House getHouse() {
		return this.builder.getHouse();
	}

	public Engineer constructHouse() {
		this.builder.buildBasement();
		this.builder.buildStructure();
		this.builder.bulidRoof();
		this.builder.buildInterior();
		return this;
	}
}
