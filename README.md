# browserstack-appium-flutter

This repository demonstrates how to run Appium tests for your [Flutter](https://flutter.dev) apps in [TestNG](http://testng.org) on BrowserStack App Automate.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Setup

### Project Structure
* [android](./android)
    * [testing-examples](./android/testng-examples)
      * [src/test](./android/testing-examples/src/test)  (You can find all the source code here)
        * [java](./android/testing-examples/src/test/java)
          * [com.browserstack.appium_flutter](./android/testing-examples/src/test/java/com/browserstack/appium_flutter) (All your tests would be added in this directory)
            * [BaseTestClass](./android/testng-examples/src/test/java/com/browserstack/appium_flutter/BaseTestClass.java) (Base Class for all your tests)
            * [SampleTest](./android/testng-examples/src/test/java/com/browserstack/appium_flutter/SampleTest.java) (Sample Test class consisting of your test cases)
        * [resources](./android/testing-examples/src/test/resources)
          * [com.browserstack.appium_flutter](./android/testing-examples/src/test/resources/com/browserstack/appium_flutter)
            * [sample.conf.json](./android/testng-examples/src/test/resources/com/browserstack/appium_flutter/sample.conf.json) (Configuration file for your tests with necessary capabilities)
            * [sample.testng.xml](./android/testng-examples/src/test/resources/com/browserstack/appium_flutter/sample.testng.xml) (The TestNG .xml file to run your tests)

### Install the dependencies

To install the dependencies for Android tests, run :
```sh
cd android/testng-examples
mvn clean
```

### Run your first test

1. Upload your app using [BrowserStack's Rest API](https://www.browserstack.com/docs/app-automate/appium/upload-app-from-filesystem)
```sh
curl -u "<YOUR_BROWSERSTACK_USERNAME>:<YOUR_BROWSERSTACK_ACCESS_KEY>" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/app/file/application-debug.apk"
```
A sample response to the above API request is shown below :
```json
{
    "app_url":"bs://f7c874f21852ba57957a3fdc33f47514288c4ba4"
}
```

Please note the value of app_url in the API response (bs://f7c874f21852.... in the above example). This value will be used later to set the app capability (in the [sample.conf.json](./android/testng-examples/src/test/resources/com/browserstack/appium_flutter/sample.conf.json) or as an environment variable => BROWSERSTACK_ANDROID_APP_ID & BROWSERSTACK_IOS_APP_ID) to specify application under test in your Appium test scripts.
```json
...
"capabilities": {
    "app_android": "bs://f7c874f21852ba57957a3fdc33f47514288c4ba4",
    "app_ios":"bs://k7y874f21852ba57956a3fdc33f47514288c43a4"
  }
...
```

**Note**: You can replace the above 'app' capability with a 'custom_id' or 'shareable_id', if you have uploaded the app with a 'custom_id', refer [Specify app section](https://www.browserstack.com/docs/app-automate/appium/set-up-tests/specify-app)

2. You need to export a few environment variables before running your tests
```sh
export BROWSERSTACK_USERNAME
```
```sh
export BROWSERSTACK_ACCESS_KEY
```


Or,

You can set these values in your sample.conf.json
```json
...
"username": "BROWSERSTACK_USERNAME",
"access_key": "BROWSERSTACK_ACCESS_KEY"
...
```


3. Finally, run your [testng.xml file](./android/testng-examples/src/test/resources/com/browserstack/appium_flutter/sample.testng.xml), which  contains the tests that need to be run along with the device and os_version for the devices you want to test on


**Note**: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).