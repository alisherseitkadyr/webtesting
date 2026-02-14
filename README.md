# Assignment 8 - SauceDemo Full Web Application Testing (Automation)

## Tech
- Java 17
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager

## Run
### Default (Chrome)
mvn clean test

### Firefox
mvn clean test -Dbrowser=firefox

### Headless
mvn clean test -Dheadless=true

## Reports
- TestNG HTML report:
  - test-output/index.html
- Screenshots on failure:
  - target/screenshots/

## Base URL
Default: https://www.saucedemo.com/
Override:
mvn clean test -DbaseUrl=https://www.saucedemo.com/
