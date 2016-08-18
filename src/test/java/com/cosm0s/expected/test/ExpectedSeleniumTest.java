package com.cosm0s.expected.test;

import com.cosm0s.rules.ExpectedSeleniumException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;

public class ExpectedSeleniumTest extends AbstractSeleniumTest {

    @Rule
    public ExpectedSeleniumException thrown = ExpectedSeleniumException.none();

    @Before
    public void setUp(){
        initializeDriver();
        thrown.setWebDriver(driver);
    }

    @Test
    public void test_tomcat_embedded() {
        driver.get("http://localhost:8080/showcase-6.0/");
        WebElement element = driver.findElement(By.id("LOGOTEXTSIDE")).findElement(By.className("logoBlueText"));
        assertTrue("Showcase is down", element.getText().equals("NOPE"));
        driver.quit();
    }

}

