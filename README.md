### Для запуска тестов используйте следующую команду:
#### gradlew clean test -DthreadCount={count} -Dbrowser={browser}
#### где count - количество потоков, browser - браузер, в котором запускаем тесты
#### Пример: gradlew clean test -DthreadCount=3 -Dbrowser=chrome

### Для построения отчетов используйте следующую команду:
#### allure serve build/allure-results
#### Отчет строить нужно после того, как тесты будут завершены