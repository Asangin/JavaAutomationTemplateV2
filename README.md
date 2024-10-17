# Java Automation Template version 2

## Run tests

```bash
./gradlew clean test
```

```bash
./gradlew test --tests "com.skryl.suite.SmokeAPISuite"
```

## Run with Report portal

Configuration for report portal can be set in [ddd](src/test/resources/reportportal.properties)

### Run for different launches

```bash
export RP_LAUNCH="Smoke launch"
./gradlew clean test 
```

## View allure report
```bash
allure serve build/allure-results
```