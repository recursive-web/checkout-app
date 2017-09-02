package au.com.dius.store.product;

public class IPad extends Item {

	public IPad(String sku, String name, double unitPrice) {
		super(sku, name, unitPrice);
	}

	@Override
	public Item get() {
		return new IPad(sku, name, unitPrice);
	}
}
