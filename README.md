### Hexlet tests and linter status:
[![Actions Status](https://github.com/Textile86/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Textile86/java-project-78/actions)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Textile86_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Textile86_java-project-78)

# Data Validator

Java-библиотека для валидации данных

## Features

- Валидация строк, чисел, Map
- Цепочка вызовов
- Вложенная валидация структур данных

## Usage

```java
Validator v = new Validator();

### Валидация строк
```java
StringSchema schema = v.string()
    .required()
    .minLength(5)
    .contains("hello");

schema.isValid("hello world"); // true
schema.isValid("hi");          // false
schema.isValid(null);          // false

### Валидация чисел
```java
NumberSchema schema = v.number()
    .required()
    .positive()
    .range(1, 100);

schema.isValid(50);   // true
schema.isValid(0);    // false
schema.isValid(null); // false

### Валидация Map с  структурой
```java
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map()
    .required()
    .sizeof(2)
    .shape(schemas);

Map<String, Object> person = Map.of("name", "John", "age", 25);
schema.isValid(person); // true