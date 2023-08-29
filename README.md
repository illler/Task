# Анализ Билетов

Данная программа на языке Java читает файл `tickets.json` и выполняет следующие вычисления:

1. Рассчитывает минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика.
2. Вычисляет разницу между средней ценой и медианой цены для полетов между городами Владивосток и Тель-Авив.

## Подготовка

Перед запуском программы убедитесь, что у вас есть:

- Установленная Java Development Kit (JDK) на вашей системе.
- Файл `tickets.json` с данными о билетах на авиаперелеты.
  
## Результаты

Программа выведет следующую информацию:

- Минимальное время полета между Владивостоком и Тель-Авивом для каждой авиакомпании.
- Разницу между средней ценой и медианной ценой для полетов между Владивостоком и Тель-Авивом.

## JSON Данные

Убедитесь, что файл `tickets.json` находится в той же директории, что и исходный код на Java (`TicketsHandler.java`). Файл JSON должен иметь структуру, аналогичную следующей:

```json
{
  "tickets": [{
      "origin": "VVO",
      "origin_name": "Владивосток",
      "destination": "TLV",
      "destination_name": "Тель-Авив",
      "departure_date": "12.05.18",
      "departure_time": "16:20",
      "arrival_date": "12.05.18",
      "arrival_time": "22:10",
      "carrier": "TK",
      "stops": 3,
      "price": 12400
    }, {
      "origin": "VVO",
      "origin_name": "Владивосток",
      "destination": "TLV",
      "destination_name": "Тель-Авив",
      "departure_date": "12.05.18",
      "departure_time": "17:20",
      "arrival_date": "12.05.18",
      "arrival_time": "23:50",
      "carrier": "S7",
      "stops": 1,
      "price": 13100
    }
```

## Зависимости

Данная программа имеет две внешних зависимости, помимо стандартной библиотеки Java.

```
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.28</version>
            <scope>provided</scope>
        </dependency>
```
## Получение файла `tickets.json`

Скачайте файл `tickets.json` по [этой ссылке](https://disk.yandex.ru/d/oytpde1hp8DBRQ).



