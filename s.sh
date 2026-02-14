#!/usr/bin/env bash
set -euo pipefail

PROJECT="assignment8-saucedemo"

# Create root
mkdir -p "$PROJECT"

# Create dirs
mkdir -p "$PROJECT/src/test/java/com/saucedemo/base"
mkdir -p "$PROJECT/src/test/java/com/saucedemo/pages"
mkdir -p "$PROJECT/src/test/java/com/saucedemo/tests"
mkdir -p "$PROJECT/src/test/java/com/saucedemo/utils"
mkdir -p "$PROJECT/src/test/resources"

# Create empty files
touch "$PROJECT/pom.xml"
touch "$PROJECT/testng.xml"
touch "$PROJECT/README.md"

touch "$PROJECT/src/test/java/com/saucedemo/base/BaseTest.java"
touch "$PROJECT/src/test/java/com/saucedemo/base/DriverFactory.java"

touch "$PROJECT/src/test/java/com/saucedemo/pages/CartPage.java"
touch "$PROJECT/src/test/java/com/saucedemo/pages/CheckoutCompletePage.java"
touch "$PROJECT/src/test/java/com/saucedemo/pages/CheckoutOverviewPage.java"
touch "$PROJECT/src/test/java/com/saucedemo/pages/CheckoutStepOnePage.java"
touch "$PROJECT/src/test/java/com/saucedemo/pages/LoginPage.java"
touch "$PROJECT/src/test/java/com/saucedemo/pages/ProductsPage.java"

touch "$PROJECT/src/test/java/com/saucedemo/tests/NegativeTests.java"
touch "$PROJECT/src/test/java/com/saucedemo/tests/RegressionTests.java"
touch "$PROJECT/src/test/java/com/saucedemo/tests/SmokeTests.java"

touch "$PROJECT/src/test/java/com/saucedemo/utils/ScreenshotUtil.java"
touch "$PROJECT/src/test/java/com/saucedemo/utils/Waits.java"

touch "$PROJECT/src/test/resources/testng-listeners.xml"

echo "✅ Created project skeleton: $PROJECT"
echo "Next: paste code into files, then run: cd $PROJECT && mvn clean test"
