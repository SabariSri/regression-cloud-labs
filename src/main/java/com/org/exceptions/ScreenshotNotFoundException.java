package com.org.exceptions;

public class ScreenshotNotFoundException extends Exception{
  public ScreenshotNotFoundException(String screenshot){
      super(screenshot + "Not taken");
  }
}
