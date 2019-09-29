# Microservice workshop by REWE digital

[![Build Status](https://travis-ci.org/caze73/inova-workshop-rd.svg?branch=master)](https://travis-ci.org/caze73/inova-workshop-rd)

This project is based on the REWE digital project: [Integration Patterns](https://github.com/rewe-digital/integration-patterns)

## Current state
This repository contains an example for integrating microservices:
* Integration of frontend components via a micro-frontend approach
* Integration of backend data via Apache Kafka based events 

The example consists of the following services:
* composer - a basic frontend composition engine
* product-detail-page - a simple product detail page
* header-footer - a service serving a header and a footer
* product-information - a backend for managing product data

Additionally there is a prepared service skeleton for a microservice covered by this workshop:
* product-stock - a service providing stock information for products

To run the example, each project can be build using the latest maven version:
```
mvn clean install
```
All projects can be build via `./build.sh` - this will build jars and docker images for all services.

All services together can be started via `docker-compose up -d`.

Locally, the services will run on the following ports:
* composer: `8000`
* product-detail-page: `8080`
* header-footer: `8081`
* product-information: `8082`
* product-stock: `8083`

When started via docker-compose, the services run on
* composer: `9000`
* product-detail-page: `9080`
* header-footer: `9081`
* product-information: `9082`
* product-stock: `9083`

To test the setup, go to `http://localhost:9000/p/2670536` to see a product detail page including a header and a footer. To acess the product detail page directly, go to `http://localhost:9080/products/2670536`.

## Goal

During the workshop the product-stock service should be enriched with functionality:
1. Providing a frontend component which renders the current stock for a given product number
2. Offering a REST API to set the stock value for a product
3. Store the current product stock in a database

## Background

The product-detail service already contains a placeholder for an include providing stock information:
```
<rewe-digital-include th:path="${stockSrc + '/stock/' + product.productNumber}">n/a</rewe-digital-include>
```
The `stockSrc` is already configured to point to the new product-stock service. If the service provides 
the stock information like `http://localhost:8083/stock/2670536` in a proper way, it will be included in the product detail page.
Therefor the stock must be rendered in a `<rewe-digital-content>` tag:
```
<rewe-digital-content>10</rewe-digital-content>
```

## License

The MIT License (MIT) Copyright © 2018 REWE digital GmbH, https://www.rewe-digital.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.