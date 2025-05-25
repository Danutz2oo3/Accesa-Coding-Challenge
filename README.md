# Price Comparator - Backend

This is a Java backend application built for the Accesa Internship 2025 coding challenge. The goal of the project is to help users compare prices for grocery products across multiple stores (Lidl, Kaufland, Profi), track discounts, and optimize their shopping basket.

---

## Features Implemented

- **Custom Price Alerts**  
  Users can track products and get notified when prices drop below a target.

- **Best Discounts**  
  Lists products with the highest discounts across all stores.

- **New Discounts**  
  Shows newly added discounts within the last 24 hours.

- **Price History Tracking**  
  Returns historical price data per product (with discount info).

- **Product Substitutes**  
  Suggests better value alternatives based on price per unit.

- **Optimized Shopping Basket**  
  Splits a user's basket into cheapest options across stores (lowest final price).

---

## Tech Stack

- Java 17
- Spring Boot
- Maven
- Spring Data JPA
- H2 in-memory database
- CSV-based data ingestion (no external database)
- Lombok (for DTOs and model simplification)

---

## Project Structure

```bash
src
├── main
│   ├── java
│   │   └── com.accesa.pricecomparator
│   │       ├── controller      # REST APIs
│   │       ├── dto             # Request/response objects
│   │       ├── entity          # Domain models
│   │       ├── repository      # CSV-backed data access
│   │       └── service         # Business logic
│   └── resources
│       └── data/               # Sample CSV data (products & discounts)
```
---

## How to Run

```bash
git clone https://github.com/your-username/price-comparator.git # to download the repo on your machine
cd price-comparator # go to the download location
./mvnw clean install # build the application
./mvnw spring-boot:run #run the application
```

## Example Usage

### `POST /api/basket/optimize`  
**Description**: Calculates the cheapest combination of stores for the selected products based on the given date.

#### Example Request:
```http
POST /api/basket/optimize
Content-Type: application/json

{
  "productIds": ["P001", "P002", "P017"],
  "date": "2025-05-08"
}
```

### `GET /api/basket/substitutes`  
**Description**: Find the best substitutes for a given product on a given date.

#### Example Request:
```http
GET /api/basket/substitutes?productId=P001&date=2025-05-08
```

### `GET /api/discounts/top`  
**Description**: Returns the top discounted products (by percentage) available on a specific date. You can control how many results are returned using the limit parameter.

#### Example Request:
```http
GET /api/discounts/top?date=2025-05-08&limit=5
```

### `GET /api/discounts/new`  
**Description**: Lists discounts that were newly introduced on the given date.

#### Example Request:
```http
GET /api/discounts/new?date=2025-05-08
```

### `POST /api/alerts/register`  
**Description**: Registers a price alert for a specific product. If its price drops below the target on future checks, it will be included in the */check* response.

#### Example Request:
```http
POST /api/alerts/register?productId=P001&targetPrice=9.50
```

### `GET /api/alerts/check`  
**Description**: Checks all registered alerts and returns the products that have dropped below their target price on the given date.

#### Example Request:
```http
GET /api/alerts/check?date=2025-05-08
```

### `GET /api/products/{productId}/price-history`  
**Description**: Returns the full price history of a product, including base and final prices with discount information. You can optionally filter by store, brand, or category.

#### Example Request:
```http
GET /api/products/P001/price-history?store=lidl&brand=Zuzu
```
