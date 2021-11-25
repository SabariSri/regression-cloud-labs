## BrowserStack execution - 
mvn test -P browserstack -DbrowserStack_user_name=YOUR_BROWSERSTACK_USERNAME -DbrowserStack_access_key=BROWSERSTACK_ACCESS_KEY <br />

## LamdaTest execution  - 
mvn test -P lamdatest -DLT_USERNAME=YOUR_LAMDATEST_USERNAME -DLT_ACCESS_KEY=LAMDATEST_ACCESS_KEY <br />

## SauceLabs execution - 
mvn test -P saucelabs -DsauceLabs_username=YOUR_SAUCE_LABS_USERNAME -DsauceLabs_accesskey=SAUCELABS_ACCESS_KEY <br />

## Zalenium execution - 
mvn test -P zalenium

