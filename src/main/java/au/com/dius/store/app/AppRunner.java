package au.com.dius.store.app;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import au.com.dius.store.cache.Products;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.pricing.impl.PricingRulesEngineImpl;
import au.com.dius.store.product.AppleTV;
import au.com.dius.store.product.IPad;
import au.com.dius.store.product.Item;
import au.com.dius.store.product.MacBookPro;
import au.com.dius.store.product.VGAAdapter;

/*
 * Entry Point to the application
 * 
 * As per the requirements, skus will be provided in a comma separated list as the only
 * argument in the command line.
 * 
 * basic validation will be done against the SKUs provided. 
 * If Any invalid SKUs provided, app will not proceed.
 */

public class AppRunner {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Invalid Usage. At least one SKU must be provided.");
			System.exit(1);
		}
		
		CheckoutApp app = new CheckoutApp();
		app.loadProductData();
		app.checkout(args[0]);
	}

}

class CheckoutApp {
	private Checkout co;
	private PricingRulesEngine pricingRules;

	public void checkout(String args) {
		try {
			List<String> listOfSku = getSkuList(args);

			pricingRules = new PricingRulesEngineImpl();
			co = new Checkout(pricingRules);

			listOfSku.forEach(sku -> {
				Item item = Products.INSTANCE.get(sku);
				co.scan(item);
			});

			co.total();

			printResult(listOfSku, co.getTotalCost());

		} catch (IllegalArgumentException ex) {
			System.out.println("Application Terminating:" + ex.getMessage());
			System.exit(1);
		}
	}

	private void printResult(List<String> listOfSku, double total) {
		StringBuffer sb = new StringBuffer("SKUs Scanned: ");
		listOfSku.forEach(sku -> {
			sb.append(sku);
			sb.append(", ");
		});

		String result = sb.toString();
		System.out.println(result.substring(0, result.length() - 2) + " Total expected: $" + total);
	}

	/*
	 * breaks the comma separated list into individual List items
	 * 
	 * Validates each entry prior to adding to List
	 */
	List<String> getSkuList(String listOfSKU) throws IllegalArgumentException {
		StringTokenizer st = new StringTokenizer(listOfSKU, ",");
		List<String> skuList = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String tkn = st.nextToken();
			if (Products.INSTANCE.get(tkn) == null) {
				throw new IllegalArgumentException("Invalid SKU: " + tkn + "\nValid SKUs are: ipd/mbp/atv/vga");
			}
			skuList.add(tkn);
		}
		return skuList;
	}

	/*
	 * Initializes the Application by loading Product data
	 */
	void loadProductData() {
		Item ipad = new IPad("ipd", "Super iPad", 549.99);
		Item mac = new MacBookPro("mbp", "MacBook Pro", 1399.99);
		Item atv = new AppleTV("atv", "Apple TV", 109.5);
		Item vga = new VGAAdapter("vga", "VGA adapter", 30.0);

		Products.INSTANCE.add(ipad);
		Products.INSTANCE.add(mac);
		Products.INSTANCE.add(atv);
		Products.INSTANCE.add(vga);

		return;
	}
}
