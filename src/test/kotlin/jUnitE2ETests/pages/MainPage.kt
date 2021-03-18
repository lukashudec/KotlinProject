package jUnitE2ETests.pages

import org.openqa.selenium.WebDriver

class MainPage(driver: WebDriver) : BoardGameBasePage(driver) {
    init {
        this.root = "https://www.boardgamegeek.com/"
    }
}