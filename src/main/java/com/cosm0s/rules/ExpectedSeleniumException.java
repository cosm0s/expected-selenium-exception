package com.cosm0s.rules;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ExpectedSeleniumException implements TestRule {

    private WebDriver driver;

    private static final String basePath = "." + File.separator + "src" + File.separator + "test" + File.separator + "resources";
    private static final String extension = ".png";

    public static ExpectedSeleniumException none() {
        return new ExpectedSeleniumException();
    }

    public void setWebDriver(WebDriver driver){
        this.driver = driver;
    }
    public Statement apply(Statement base, Description description) {
        return new ExpectedSeleniumExceptionStatement(base, description);
    }

    private class ExpectedSeleniumExceptionStatement extends Statement {

        private final Statement next;
        private final Description description;

        public ExpectedSeleniumExceptionStatement(Statement base, Description description) {
            next = base;
            this.description = description;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                next.evaluate();
            } catch (Throwable e) {
                takeScreenshot(description);
                throw e;
            }
            driver.quit();

        }
    }

    private void takeScreenshot(Description description) throws IOException {
        String className = description.getClassName().substring(description.getClassName().lastIndexOf(".") + 1);
        String path = basePath + File.separator + className + File.separator + description.getMethodName() + extension;
        File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(path));
        driver.quit();
    }

}

