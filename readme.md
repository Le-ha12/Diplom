# Тестирование веб-сервиса "Путешествие дня"
Тестирование приложения, которое предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте;
2. Выдача кредита по данным банковской карты.

# Начало работы
Перед началом работы удостоверьтесь, что у вас есть необходимый набор ПО, описанный в разделе Prerequisites. Для получения копии проекта необходимо клонировать репозиторий проект себе на ПК для последующего запуска и тестирования https://github.com/Le-ha12/Diplom.git;

# Prerequisites
Для работы вам потребуются следующие ПО:

1. Средство для программирования - Idea;
2. Система контроля версий - Git;
3. Браузер - Google Chrome;
4. Средство для развертывания проектов - Docker-compose.

# Установка и запуск
1. Необходимо клонировать репозиторий https://github.com/Le-ha12/Diplom.git;
2. Открыть IntelliJ IDEA с данным проектом;
3. Создать контейнеры в скопированном проекте docker-compose up;
4. Запустить контейнеры командой docker-compose start;
5. Запустить jar-файл с базой данных MySQL командой
   java "-Dspring.datasource.url=jdbc:mysql:/localhost:3306" -jar artifacts/aqa-shop.jar;
6. Открыть терминал и запустить тесты командой
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app";
7. Для запуска jar-файла с базой данных PostgreSQL необходимо перейти в виртуальную машину и воспользоваться командой
 java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar;
8. Открыть терминал и запустить тесты командой
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app";
9. Сгенерировать отчет с помощью фреймворка Allure:
   1. ./gradlew allureReport
   2. ./gradlew allureServe;
10. Удалить контейнеры командой docker-compose down.


