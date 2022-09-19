import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class AppiumWebTest {
    String WikiPageStart = "https://www.wikipedia.org/";

    private static Stream<Arguments> listOfLanguages() {
        return Stream.of(
                Arguments.of("js-link-box-fr", "https://fr.m.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal"),
                Arguments.of("js-link-box-ru", "https://ru.m.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0"),
                Arguments.of("js-link-box-ja", "https://ja.m.wikipedia.org/wiki/%E3%83%A1%E3%82%A4%E3%83%B3%E3%83%9A%E3%83%BC%E3%82%B8"),
                Arguments.of("js-link-box-es", "https://es.m.wikipedia.org/wiki/Wikipedia:Portada"),
                Arguments.of("js-link-box-pt", "https://pt.m.wikipedia.org/wiki/Wikip%C3%A9dia:P%C3%A1gina_principal"),
                Arguments.of("js-link-box-de", "https://de.m.wikipedia.org/wiki/Wikipedia:Hauptseite"),
                Arguments.of("js-link-box-it", "https://it.m.wikipedia.org/wiki/Pagina_principale"),
                Arguments.of("js-link-box-zh", "https://zh.m.wikipedia.org/wiki/Wikipedia:%E9%A6%96%E9%A1%B5"),
                Arguments.of("js-link-box-ar", "https://ar.m.wikipedia.org/wiki/%D8%A7%D9%84%D8%B5%D9%81%D8%AD%D8%A9_%D8%A7%D9%84%D8%B1%D8%A6%D9%8A%D8%B3%D9%8A%D8%A9")

        );
    }

    @Test
    public void openWikiSearch() throws MalformedURLException, InterruptedException {
        URL serverURL = new URL("http://0.0.0.0:4723/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");
        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");

        AppiumDriver driver = new IOSDriver(serverURL, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(WikiPageStart);
        driver.findElement(AppiumTestsSelectors.searchInputMainPageSelector).sendKeys("meadow");
        WebElement someElement = driver.findElement(AppiumTestsSelectors.submitButtonSelector);
        someElement.click();
        Assertions.assertTrue(driver.getCurrentUrl().equals("https://en.m.wikipedia.org/wiki/Meadow"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("listOfLanguages")
    public void switchLanguages(String languageSetting, String resultingPageWiki) throws MalformedURLException {
        URL serverURL = new URL("http://0.0.0.0:4723/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");
        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");

        AppiumDriver driver = new IOSDriver(serverURL, capabilities);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get(WikiPageStart);
        driver.findElement(By.id(languageSetting)).click();
        Assertions.assertTrue(driver.getCurrentUrl().equals(resultingPageWiki));
    }

    @Test
    public void readWikiInAnotherLanguage() throws MalformedURLException {
        URL serverURL = new URL("http://0.0.0.0:4723/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");
        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");
        AppiumDriver driver = new IOSDriver(serverURL, capabilities);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.get(WikiPageStart);
        driver.findElement(AppiumTestsSelectors.readWikiInAnotherLanguageButtonSelector).click();
        List<WebElement> languageElementList = driver.findElements(AppiumTestsSelectors.languageListsByClassSelector);
        for (int i = 0; i < languageElementList.size(); i++) {
           Assertions.assertTrue(languageElementList.get(i).isDisplayed());
        }

        driver.quit();
    }
}
