import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class GeneralActivities {
    protected AppiumDriver driver = null;
    Actions action = new Actions(driver);

    public void clickElement(By byName) {
        driver.findElement(byName).click();
    }
}
