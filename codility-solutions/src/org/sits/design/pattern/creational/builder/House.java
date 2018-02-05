/**
 * 
 */
package org.sits.design.pattern.creational.builder;

/**
 * @author admin
 *
 */
public class House implements Plan {

	private String basement;
	private String structure;
	private String roof;
	private String interior;

	@Override
	public void setBasement(String basement) {
		this.basement = basement;
	}

	@Override
	public void setStructure(String structure) {
		this.structure = structure;
	}

	@Override
	public void setRoof(String roof) {
		this.roof = roof;
	}

	@Override
	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getBasement() {
		return basement;
	}

	public String getStructure() {
		return structure;
	}

	public String getRoof() {
		return roof;
	}

	public String getInterior() {
		return interior;
	}

}
