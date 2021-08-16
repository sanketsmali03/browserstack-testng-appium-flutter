package com.browserstack.appium_flutter;

import io.appium.java_client.MobileElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTestClass {
    @Test
    public void basicTest () {
        //Start Writing Test Case Here
        //driver & find is available using inheritance

        // driver.get("BrowserStack"); => the driver instance can be used in this way, if needed
        Assert.assertTrue(1==1); // Your test assertion would come here
    }

    @Test
    public void dummyTest () {
        //Start Writing Test Case Here
        //driver & find is available using inheritance

        // driver.get("BrowserStack"); => the driver instance can be used in this way, if needed
        Assert.assertTrue(1<2); // Your test assertion would come here
    }
}
