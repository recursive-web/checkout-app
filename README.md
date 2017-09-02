<h2>Usage</h2>
<p>
This repo contains all source and config files needed to run this applications. you need to build from source in order to run this.
</p>

<h3>Pre-Requisites:</h3>
<p>
<ul>
<li>Java 8</li>
<li>Maven 3.3.x</li>
</ul>
</p>

<h3>Building:</h3>
<p>
<ul>
<li>Clone or download the project as referred</li>
<li>Once on your computer, change directory to the <strong>checkout-app</strong> folder on a terminal/commandline.</li>
<li>Build by running: mvn clean package</li>
<li>Maven will run the unit tests and build the app. The build artefact <strong>checkout-app.jar</strong> is created in the newly created target folder</li>
<li>Build is now complete.</li>
</ul>
</p>

<h3>Running:</h3>
<p>
<ul>
<li>Change directory to <strong>target</strong></li>
<li>Run app: <i>java -jar checkout-app.jar &lt;comma,separated,list,of,SKUs&gt;</i></li>
<li>App accepts the following SKUs: ipd,mbp,atv,vga</li>
<li>Note that there should NOT be a space between SKUs or after a comma</li>
<li>Invalid SKUs will result in the app terminating.</li>
<li>Same SKU can be repeated and will result in the quantity increasing for that SKU. </li>
<li>Order is not important</li>
<li>Once input, the application will calculate the cost and print out: <i>SKUs Scanned: &lt;comma,separated,list,of,SKUs&gt; Total expected: &lt;calculated total cost&gt;</li>
</ul>
</p>

<h2>Solution Description</h2>
<ul>
<li>The requirements regarding how promotional prices are applied have all been realised</li>
<li>As required, the system has been designed with SOLID principles in mind and future promotional changes can be easily done.</li>
<li>Each promotional change has been encapsulated in its own class so side effects are contained.</li>
<li>Each Product has a corresponding implementation of a ProductPromotion (au.com.dius.store.promotion.ProductPromotion) interface which handles the application of promotions (or not as is the case in VGAs)</li>
<li>The pricing rules engine(au.com.dius.store.pricing.impl.PricingRulesEngineImpl) (injected to the Checkout object at instantiation) delegates the calculation of the costs to the inidividual products</li>
<li>The pricing rules engine is not couple with the Products - product line changes will not break this class</li>
<li>The pricing rules engine acquires Product specific promotional classed via a Factory class (au.com.dius.store.promotion.ProductPromotionFactory). This is the only class that needs updating if the product line changes.</li>
<li>The product master data is loaded on to a cache (au.com.dius.store.cache.Products) which would probably be in persistent storage in an actual solution. It has been kept in a central location so it can be accessed by different classes. This reduces the chance for errors as well.</li>
<li>Scanned Items are added to a Shopping Cart till the total is tallied.</li>
</ul>

