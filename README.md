# E-commerce Caching Application

## Overview

This is a Spring Boot application that provides caching functionalities through RESTful endpoints. The application allows you to add, retrieve, and evict cache entries.

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher

## Getting Started

### Building the Project

To build the project, navigate to the project root directory and run:

```sh
mvn clean install
```

### Running the Application

To run the application, use the following command:

```sh
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Add to Cache

- **URL:** `/cache/put`
- **Method:** `POST`
- **Parameters:**
    - `key` (String): The key for the cache entry.
    - `value` (String): The value for the cache entry.
- **Response:** `Added to cache.`

### Retrieve from Cache

- **URL:** `/cache/get`
- **Method:** `GET`
- **Parameters:**
    - `key` (String): The key for the cache entry.
- **Response:** The value associated with the key, or `Cache miss!` if the key is not found.

### Evict from Cache

- **URL:** `/cache/evict`
- **Method:** `DELETE`
- **Parameters:**
    - `key` (String): The key for the cache entry.
- **Response:** `Evicted from cache.`

## Running Tests

To run the tests, use the following command:

```sh
mvn test
```

