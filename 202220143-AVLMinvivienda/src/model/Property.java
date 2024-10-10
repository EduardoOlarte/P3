package model;

import java.util.Objects;

public class Property {
	private String numPredial;
	private String direction;
	private String economicDestiny;
	private String landArea;
	private String builtArea;
	private String previousNumPredial;

	public Property(String numPredial, String direction, String economicDestiny, String landArea, String builtArea,
			String previousNumPredial) {
		super();
		this.numPredial = numPredial;
		this.direction = direction;
		this.economicDestiny = economicDestiny;
		this.landArea = landArea;
		this.builtArea = builtArea;
		this.previousNumPredial = previousNumPredial;
	}

	public String getNumPredial() {
		return numPredial;
	}

	public void setNumPredial(String numPredial) {
		this.numPredial = numPredial;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getEconomicDestiny() {
		return economicDestiny;
	}

	public void setEconomicDestiny(String economicDestiny) {
		this.economicDestiny = economicDestiny;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getBuiltArea() {
		return builtArea;
	}

	public void setBuiltArea(String builtArea) {
		this.builtArea = builtArea;
	}

	public String getPreviousNumPredial() {
		return previousNumPredial;
	}

	public void setPreviousNumPredial(String previousNumPredial) {
		this.previousNumPredial = previousNumPredial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numPredial);
	}

	@Override
	public String toString() {
		return "Predio{" + "numeroPredial='" + numPredial + '\'' + ", direccion='" + direction + '\''
				+ ", destinoEconomico='" + economicDestiny + '\'' + ", areaTerreno=" + landArea + ", areaConstruida="
				+ builtArea + ", numeroPredialAnterior='" + numPredial + '\'' + '}';
	}
}
