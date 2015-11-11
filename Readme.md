Tip Calculator
==============

My friend Shane was taking an introductory Java class and showed me his tip calculator. It looked pretty neat, and I
haven't done any Swing work in a while, so I decided to write one of my own. This tip calculator is loosely based on
Google's tip calculator, you'll see it if you type "tip calculator" into Google's search.

Building
--------

This project uses Maven, if you don't already have Maven installed, you can download and install it from [the Maven
website](https://maven.apache.org/download.cgi).

Once you have Maven installed, you can tell Maven to build and package the application.

    mvn clean package

Maven will clean the project, make a fresh build and then package it all up with the
[Shade plugin](https://maven.apache.org/plugins/maven-shade-plugin/).

Usage
-----

You can launch the application by double-clicking the `top-calculator-X.X-SNAPSHOT.jar`, or you can invoke it from the
command line.

    java -jar top-calculator-X.X-SNAPSHOT.jar

Once launched, fill in the amount of your bill, the percentage you'd like to tip and the number of people who will be
splitting the bill. The application will calculate the tip per person as well as the total size of the bill.