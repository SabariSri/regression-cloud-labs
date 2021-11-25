package com.org;

import org.openqa.selenium.JavascriptExecutor;

public class Utils {

    public static void markTestStatus(JavascriptExecutor jse, String status, String reason) {
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}",status,reason);
    }
}
