package au.com.dius.store.product;

public abstract class Item {
	protected String sku;
    protected String name;
    protected double unitPrice;
	
    protected Item(String sku, String name, double unitPrice) {
    	this.sku = sku;
		this.name = name;
		this.unitPrice = unitPrice;
    }
    
    @Override
	public String toString() {
		return "IPad [sku=" + sku + ", name=" + name + ", unitPrice=" + unitPrice + "]";
	}
    
    public abstract Item get();
    
    public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
    
    
}
