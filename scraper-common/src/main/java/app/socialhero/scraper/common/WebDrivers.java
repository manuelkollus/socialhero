package app.socialhero.scraper.common;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDrivers {
  private WebDrivers() {}

  public static void setupChromeDriver() {
    WebDriverManager.chromedriver().setup();
  }
}
