package com.browserstack.appium_flutter;

import java.net.URL;
import java.util.Map;
import java.util.Iterator;
import java.io.FileReader;

import com.testsigma.flutter.FlutterFinder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTestClass {
    protected AndroidDriver<MobileElement> driver;
    protected FlutterFinder find;

    @BeforeMethod(alwaysRun = true)
    @org.testng.annotations.Parameters(value = { "device", "os_version", "os" })
    public void setUp(String device, String os_version, String os) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser
                .parse(new FileReader("src/test/resources/com/browserstack/appium_flutter/sample.conf.json"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        Iterator<Map.Entry<String,String>> it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> pair = (Map.Entry<String,String>) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }
        if (device != null) {
            capabilities.setCapability("device", device);
        }
        if (os_version != null) {
            capabilities.setCapability("os_version", os_version);
        }
        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("username");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("access_key");
        }
        capabilities.setCapability("user", username);
        capabilities.setCapability("key", accessKey);
        String android_app = System.getenv("BROWSERSTACK_ANDROID_APP_ID");
        String ios_app = System.getenv("BROWSERSTACK_IOS_APP_ID");
        if (os.toLowerCase() == "android") {
            if(android_app == null){
                android_app = (String) config.get("app_android");
            }
            capabilities.setCapability("app",android_app);
        }
        if (os.toLowerCase() == "ios") {
            if(ios_app == null){
                ios_app = (String) config.get("app_ios");
            }
            capabilities.setCapability("app",ios_app);
        }

        driver = new AndroidDriver<MobileElement>(
                new URL(config.get("server") + "/wd/hub"),
                capabilities);
        find = new FlutterFinder(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        if (driver == null) {
            return;
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            driver.executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\",\"reason\": \""
                            + result.getThrowable().toString() + "\"}}");
        } else {
            driver.executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\",\"reason\": \"Test Successful!\"}}");
        }
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }
}
