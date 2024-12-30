Цей код — це Spring Boot застосунок, який виконує дві паралельні задачі:

Лічильник: Задача збільшує значення змінної counter кожні 2 секунди. Коли counter досягає 10, задача зупиняється.
Час: Ще одна задача виводить поточний час у консоль кожні 5 секунд, поки лічильник активний.

Код використовує @SpringBootApplication для запуску програми, @EnableScheduling для задач за розкладом, і ScheduledExecutorService для управління лічильником. Завершення роботи планувальника викликається автоматично,
коли лічильник доходить до 10.