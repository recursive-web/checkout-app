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
<li>Run app: <i>java -jar checkout-app.jar <comma,separated,list,of,SKUs></i></li>
<li>App accepts the following SKUs: ipd,mbp,atv,vga</li>
<li>Invalid SKUs will result in the app terminating.</li>
<li>Same SKU can be repeated and will result in the quantity increasing for that SKU. </li>
<li>Order is not important</li>
<li>Once input, the application will calculate the cost and print out: <i>SKUs Scanned: <comma,separated,list,of,SKUs> Total expected: <calculated total cost></li>

</ul>
</p>


