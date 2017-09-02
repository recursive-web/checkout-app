package au.com.dius.store.product;

public class VGAAdapter extends Item {

	public VGAAdapter(String sku, String name, double unitPrice) {
		super(sku, name, unitPrice);
	}

	@Override
	public Item get() {
		return new VGAAdapter(sku, name, unitPrice);
	}

}
