# sales-taxes
[![Build Status](https://travis-ci.org/KiuIras/sales-taxes.svg?branch=master)](https://travis-ci.org/KiuIras/sales-taxes)

## Description
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

## How-To
### Prerequisites
To build and run the application you need Maven and the JRE 8+.
### Build
To build the application just clone the project wherever you want and run:
```bash
mvn clean install
```
The command builds the application, runs the tests and produces in the `target` folder a `salestaxes-app.tgz` file.

Copy the file in the folder where you want to install the application and untar it via the command:
```bash
tar -xzf salestaxes-app.tgz
```
### Run
Launch the script to run the application:
```bash
./sales-taxes.sh -i path/to/input/file -o path/to/output/file -c path/to/categories/file
```
If no arguments are given the application use the default values:
* the input file `example/shopping_basket1.example`
* the output file `output/receipt_details.txt`
* the categories file `conf/categories.properties`
#### Input file
The input file should be contains an item for each line.
The line should be composed by:
1. a positive integer for the quantity, 
2. the description (that can contains the "imported" indication),
3. the string " at ",
4. a positive decimal number for the net price.
An example: `3 imported music CD at 12.99`
### Categories file
The categories file should contains the mapping beetwen items and categories expressed as key/value pair.
Each line should contais a key/value pair.
An example: `Pill=MEDICAL`
### Configuration file
It is possible to change tax rate and exempt categories in the configuration file `conf/salestaxes.properties`.

## Notes
1. In the source code there is an alternative implementation for the categories: `WordNetAnalyzer`.
It tries (naïvely) to retrieve the categories of a given item using the [WordNet library](https://wordnet.princeton.edu/).
To use it instead the categories file you can edit the CategoryAnalyzer value in the configuration file.
NOTE: It doesn't work with supplied data!
2. In the `SalesTaxesApplicationTest` there is a test that checks the supplied data. I need to do minor changes to the given output text.
