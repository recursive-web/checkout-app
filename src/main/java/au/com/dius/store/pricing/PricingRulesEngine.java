package au.com.dius.store.pricing;

/*
 * Interface for the pricing Rules Engine
 */
public interface PricingRulesEngine {
	/*
	 * Calculates total of the shopping cart.
	 */
	public double calculateTotal();
}
