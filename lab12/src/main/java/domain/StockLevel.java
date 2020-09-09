package domain;

public class StockLevel {

	private Double currentStockLevel;
	private Double reorderThreshold;
	private Double reorderAmount;
	private String supplier;
	private String units;

	public StockLevel() {
	}

	public StockLevel(Double currentStockLevel, Double reorderThreshold, Double reorderAmount, String supplier, String units) {
		this.currentStockLevel = currentStockLevel;
		this.reorderThreshold = reorderThreshold;
		this.reorderAmount = reorderAmount;
		this.supplier = supplier;
		this.units = units;
	}

	public Double getCurrentStockLevel() {
		return currentStockLevel;
	}

	public void setCurrentStockLevel(Double currentStockLevel) {
		this.currentStockLevel = currentStockLevel;
	}

	public Double getReorderThreshold() {
		return reorderThreshold;
	}

	public void setReorderThreshold(Double reorderThreshold) {
		this.reorderThreshold = reorderThreshold;
	}

	public Double getReorderAmount() {
		return reorderAmount;
	}

	public void setReorderAmount(Double reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

}
