import org.openqa.selenium.By;

public class AppiumTestsSelectors {
    public static final By submitButtonSelector = new By.ByCssSelector("#search-form > fieldset > button");
    public static final By searchInputMainPageSelector = new By.ById("searchInput");
    public static final By readWikiInAnotherLanguageButtonSelector = new By.ByCssSelector("#js-lang-list-button > i.sprite.svg-arrow-down-blue");
    public static final By languageListsByClassSelector = new By.ByCssSelector(".langlist.langlist-large.hlist");
}
