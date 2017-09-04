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
<li>At least one SKU is required</li>
<li>App accepts the following SKUs: ipd,mbp,atv,vga</li>
<li>Note that there should NOT be a space between SKUs or after a comma</li>
<li>Invalid SKUs will result in the app terminating.</li>
<li>Same SKU can be repeated and will result in the quantity increasing for that SKU. </li>
<li>Order of SKUs is not important. They will be scanned in the order they are input.</li>
<li>Once input, the application will calculate the cost and print out: <i>SKUs Scanned: &lt;comma,separated,list,of,SKUs&gt; Total expected: &lt;calculated total cost&gt;</i></li>
</ul>
</p>

<h2>Solution Description</h2>
<ul>
<li>The requirements regarding how promotional prices are applied have all been realised</li>
<li>As required, the system has been designed with SOLID principles in mind and future promotional changes can be easily applied.</li>
<li>Each promotional change has been encapsulated in its own class so side effects are contained.</li>
<li>Each Product has a corresponding implementation of a ProductPromotion (au.com.dius.store.promotion.ProductPromotion) interface which handles the application of promotions (or not as is the case in VGAs).</li>
<li>At runtime the logic to apply promotions is changed dynamically based on the product - this is an example of the Strategy Pattern.</li>
<li>The above points address the Sales Manager's requirement of flexibility as changes are isolated to a single class.</li>
<li>The pricing rules engine(au.com.dius.store.pricing.impl.PricingRulesEngineImpl) (injected to the Checkout object at instantiation) delegates the calculation of the costs to the individual products</li>
<li>The pricing rules engine is not coupled with the Products - product line changes will not break this class</li>
<li>The pricing rules engine acquires Product specific promotional classed via a simple Factory class (au.com.dius.store.promotion.ProductPromotionFactory). This is the only class that needs updating if products are added/removed or updated.</li>
<li>The product master data is loaded on to a cache (au.com.dius.store.cache.Products) which would probably be in persistent storage in an actual solution. It has been kept in a central location so it can be accessed by different classes. This enables the product data to be used for reference purposes.</li>
<li>Scanned Items are added to a Shopping Cart till the total is tallied.</li>
<li>There is comprehensive Unit Testing provided.</li>
<li>The only frameworks used are Maven and JUnit.</li>
</ul>

<h2>Assumptions</h2>
<ul>
<li>It is assumed that each checkout will run in its own JVM - hence the design of the shopping cart is for a single customer at one time.</li>
<li>Following on from this, it is also assumed that there is no concurrent activity happening</li>
<li>Applying promotions is contained within the XXXProductPromotion class (where XXX denotes an Item such as VGA). It would also have been possible to add this functionality to the XXXProduct pojo (domain class). However, doing so would move away from the Single Responsibility model as the domain object can exist without promotions. Also, as this would interact with Other objects, testability of the PoJo becomes more complex.</li>
</ul>

