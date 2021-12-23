package com.browserstack.appium_flutter;

import io.appium.java_client.MobileElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.Test;

/**
 * Please note this is a sample boilerplate code.
 * Replace this file with your test files.
 * Test below should work with the starter flutter app
 */

public class SampleTest extends BaseTestClass {

    @Test
    public void CounterTest() {
        String buttonFinderKey = "increment";
        MobileElement counterTextFinder = find.byValueKey("counter");
        assertEquals(counterTextFinder.getText(), "0");
        clickToElement(buttonFinderKey);
        clickToElement(buttonFinderKey);
        assertEquals(counterTextFinder.getText(), "2");
    }

    private MobileElement waitFor(String locator) {
        return (MobileElement) driver.executeScript("flutter:waitFor", find.byValueKey(locator), 30);
    }

    private void clickToElement(String locator) {
        MobileElement el = waitFor(locator);
        el.click();
    }
}
