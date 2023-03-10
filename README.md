# Pricing  :shopping:

This is a Spring boot application for managing pricing resources. It provides an endpoint for retrieving the valid price of a product for a specific brand at a specific date and time.


## Requirements

To use this controller, you need to have the following:

- Java 11
- Spring Boot 2.4 or higher
- Maven build tool

## Installation

To install and use this controller, follow these steps:
1. Clone the Pricing repository.
2. Open the project in your preferred IDE.
3. Build the project using the following Maven command:
   - mvn clean install
4. Run the following command to execute the project:
   - mvn spring-boot:run

With these steps, you should have the project up and running.

### Useful links
- `H2 Console` : http://localhost:8080/h2-console
- `Postman Collection` : src/main/resources/PricingLocalhost.postman_collection
- `jacoco report` : target/site/jacoco/index.html

## API

The following api is provided by this endPoint:

### `GET /pricing/date/{date}/brand/{brandId}/product/{productId}`
Local example: http://localhost:8080/pricing/date/2020-06-15-10-30/brand/1/product/35455

Response:`
{
"productId": 35455,
"brandId": 1,
"priceList": 3,
"startDate": "2020-06-15-00-00",
"endDate": "2020-06-15-11-00",
"price": 30.50
}`

This endpoint retrieves the valid price of a product for a specific brand at a specific date and time. The `date` parameter should be in the format `yyyy-MM-dd-HH-mm`. The `brandId` and `productId` parameters should be integers.

#### Request Parameters

| Parameter | Type   | Required | Description                                |
| --------- | ------ | -------- | ------------------------------------------ |
| date      | String | Yes      | The date and time to retrieve the price for |
| brandId   | int    | Yes      | The ID of the brand                        |
| productId | int    | Yes      | The ID of the product                      |

#### Response

If the request is successful, the endpoint returns a `200 OK` status code and a JSON object representing the valid price of the product. The JSON object contains the following fields:

| Field      | Type   | Description                           |
| ---------- | ------ | ------------------------------------- |
| id         | int    | The ID of the pricing record           |
| startDate  | String | The start date and time of the pricing |
| endDate    | String | The end date and time of the pricing   |
| price      | double | The price of the product               |
| currency   | String | The currency of the price              |
| brandId    | int    | The ID of the brand                    |
| productId  | int    | The ID of the product                  |

If the requested price is not found, the endpoint returns a `404 Not Found` status code and an error message in the response body. If an internal server error occurs, the endpoint returns a `500 Internal Server Error` status code and an error message in the response body.

## Credits

This controller was created by [Ignacio.A](https://github.com/Aris033/pricing).
