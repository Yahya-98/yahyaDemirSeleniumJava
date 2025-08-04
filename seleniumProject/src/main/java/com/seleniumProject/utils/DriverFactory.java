package com.seleniumProject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver setDriver(String browser, String isBrowserHeadless) {
        if (driverThreadLocal.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--acceptInsecureCerts");
                    options.addArguments("disable-infobars");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--lang=en_US");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--allowed-ips");
                    options.addArguments("--start-fullscreen");
                    if (Boolean.parseBoolean(isBrowserHeadless)) {
                        options.addArguments("--headless");
                    }
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driverThreadLocal.set(new ChromeDriver(options));

                    break;
                case "firefox":

                    FirefoxProfile profile1 = new FirefoxProfile();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setProfile(profile1);

                    firefoxOptions.addArguments("--ignore-certificate-errors");
                    firefoxOptions.addArguments("--acceptInsecureCerts");
                    firefoxOptions.addArguments("--disable-notifications");
                    firefoxOptions.addArguments("--no-sandbox");
                    firefoxOptions.addArguments("--disable-gpu");
                    firefoxOptions.addArguments("--disable-popup-blocking");
                    firefoxOptions.addArguments("--disable-dev-shm-usage");
                    firefoxOptions.addArguments("--allowed-ips");
                    if (Boolean.parseBoolean(isBrowserHeadless)) {
                        firefoxOptions.addArguments("--headless");
                    }

                    WebDriverManager.firefoxdriver().setup();
                    FirefoxDriver driverForWebFirefox = new FirefoxDriver(firefoxOptions);
                    driverThreadLocal.set(driverForWebFirefox);

                    driverThreadLocal.get().manage().window().setSize(new Dimension(1792, 1080));
                    break;


                case "mwfirefox":

                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("general.useragent.override", "Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-G973U) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36");

                    FirefoxOptions firefoxOptions1 = new FirefoxOptions();
                    firefoxOptions1.setProfile(profile);

                    firefoxOptions1.addArguments("--ignore-certificate-errors");
                    firefoxOptions1.addArguments("--acceptInsecureCerts");
                    firefoxOptions1.addArguments("--disable-notifications");
                    firefoxOptions1.addArguments("--no-sandbox");
                    firefoxOptions1.addArguments("--disable-gpu");
                    firefoxOptions1.addArguments("--disable-popup-blocking");
                    firefoxOptions1.addArguments("--disable-dev-shm-usage");
                    firefoxOptions1.addArguments("--allowed-ips");
                    firefoxOptions1.addPreference("dom.webnotifications.enabled", false);
                    firefoxOptions1.addPreference("dom.push.enabled", false);

                    if (Boolean.parseBoolean(isBrowserHeadless)) {
                        firefoxOptions1.addArguments("--headless");
                    }
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxDriver driverForMobileWebFirefox = new FirefoxDriver(firefoxOptions1);
                    // WebDriverManager ile driver'ı kur ve oluştur
                    driverThreadLocal.set(driverForMobileWebFirefox);

                    // Mobil görünüm için tarayıcı boyutunu ayarla
                    Dimension dimension = new Dimension(360, 800); // Örnek bir mobil cihaz boyutu
                    driverThreadLocal.get().manage().window().setSize(dimension);
                    driverForMobileWebFirefox.installExtension(new File("uBlock/ublock_origin-1.59.0.xpi").toPath());

                    break;

                case "mwchrome":

                    Map<String, String> mobileEmulation1 = new HashMap<>();
                    mobileEmulation1.put("deviceName", "Pixel 7");

                    ChromeOptions mobileOptions = new ChromeOptions();
                    mobileOptions.addArguments("--ignore-certificate-errors");
                    mobileOptions.addArguments("--allow-insecure-localhost");
                    mobileOptions.addArguments("--acceptInsecureCerts");
                    mobileOptions.addArguments("disable-infobars");
                    mobileOptions.addArguments("--no-sandbox");
                    mobileOptions.addArguments("--disable-gpu");
                    mobileOptions.addArguments("--lang=en_US");
                    mobileOptions.addArguments("--remote-allow-origins=*");
                    mobileOptions.addArguments("--disable-popup-blocking");
                    mobileOptions.addArguments("--disable-notifications");
                    mobileOptions.addArguments("--disable-dev-shm-usage");
                    mobileOptions.addArguments("--disable-web-security");
                    mobileOptions.addArguments("--disable-features=IsolateOrigins,site-per-process");
                    mobileOptions.addArguments("--disable-site-isolation-trials");
                    mobileOptions.addArguments("--disable-browser-side-navigation");
                    mobileOptions.addArguments("--disable-infobars");
                    mobileOptions.addArguments("--disable-gpu");
                    mobileOptions.addArguments("--allowed-ips");

                    mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation1);


                    if (Boolean.parseBoolean(isBrowserHeadless)) {
                        mobileOptions.addArguments("--headless");
                    }

                    WebDriverManager.chromedriver().setup();
                    driverThreadLocal.set(new ChromeDriver(mobileOptions));
                    Dimension dimension1 = new Dimension(360, 800);
                    driverThreadLocal.get().manage().window().setSize(dimension1);
                    break;
                default:
                    driverThreadLocal.set(new ChromeDriver());
            }

        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();

    }


}
