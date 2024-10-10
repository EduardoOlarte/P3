package uptc.edu.co.model;

public class Item {
	private int code;
	private String name;
	private int supply;
	private double unitValue;

	public Item(int code, String name, int supply, double unitValue) {
		this.code = code;
		this.name = name;
		this.supply = supply;
		this.unitValue = unitValue;
	}

	public int getCode(){ 
		return code;
	}

	public String getName() {
		return name;
	}

	public int getSupply() {
		return supply;
	}

	public double getUnitValue() {
		return unitValue;
	}

	public void setSupply(int supply) {
		this.supply = supply;
	}
}
