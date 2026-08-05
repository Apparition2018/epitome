# JSON 函数

---
## [MySQL](https://dev.mysql.com/doc/refman/8.2/en/json-function-reference.html)
1. `->` 相当于 `JSON_EXTRACT()`
    ```mysql
    SELECT JSON_EXTRACT('{"id": "3", "name": "Barney"}', '$.name'); -- "Barney"
    ```
2. `->>` 相当于 `JSON_UNQUOTE(JSON_EXTRACT())`，类似 `JSON_VALUE()`
    ```mysql
    SELECT JSON_VALUE('{"id": "3", "name": "Barney"}', '$.name');   -- Barney
    ```
---