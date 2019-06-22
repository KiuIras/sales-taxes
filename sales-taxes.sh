#!/bin/bash

java -Dlog4j.configurationFile=conf/log4j2.xml -cp "*" it.kiuiras.salestaxes.SalesTaxesApplication $@