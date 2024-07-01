Данный проект разработан для проверки отображения витрины с различными комбинациями настроек темы UniTheme2.
Актуальная версия UniTheme2 темы 4.18.1c. Можно установить как саму тему отдельно, так и Пакет UniTheme2 (UltRu).
Используются макеты Light v2, Advanced и Default.

Рекомендуется **запускать** проект через файл TestNG.xml. Но можно каждый тест запускать по отдельности.
В файле data.properties (папка src -- test -- resources) вписать браузер, в котором будет происходить проверка.

**Условия для работоспособности авто-тестов:**
1) Следить за актуальными версиями браузеров и их драйверов в этом проекте (папка src -- test -- resources);
2) Следить за актуальностью библиотек (файл pom.xml) 
3) В класс Constants добавить актуальную ссылку, на которой будут запускаться авто-тесты.


Сайт для chromedriver: https://getwebdriver.com/

Сайт для msedgedriver: https://developer.microsoft.com/ru-ru/microsoft-edge/tools/webdriver/?form=MA13LH

Сайт для geckodriver: https://github.com/mozilla/geckodriver/releases

