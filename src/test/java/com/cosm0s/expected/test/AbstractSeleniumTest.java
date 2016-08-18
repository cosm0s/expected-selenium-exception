package com.cosm0s.expected.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractSeleniumTest extends AbstractTomcatEmbedded {

    protected WebDriver driver;

    protected void initializeDriver() {
        this.driver = new FirefoxDriver();
    }

}
