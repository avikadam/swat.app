package org.sits.design.pattern.creational.builder;

public class MyDreamHouse {
	public static void main(String[] args) {
		House house = new Engineer(new RowHouse()).constructHouse().getHouse();
		System.out.println("Builder constructed: " + house);
	}
}
