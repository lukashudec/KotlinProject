package jUnitE2ETests.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class GeekSearchResultPage(driver: WebDriver) : BoardGameBasePage(driver) {
    fun getGameLink(input: String?): List<WebElement> {
        return driver.findElements(By.linkText(input))
    }

    fun getGameImage(input: String): List<WebElement> {
        return driver.findElements(By.xpath("//img[@alt='Board Game: $input']"))
    }
}