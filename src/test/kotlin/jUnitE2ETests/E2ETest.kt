package jUnitE2ETests

import jUnitE2ETests.pages.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class E2ETest {
    lateinit var driver: WebDriver

    @BeforeEach
    fun beforeStep() {
        System.setProperty(
            "webdriver.chrome.driver",
            "C:/Users/lenovo/Downloads/chromedriver_win32_89/chromedriver.exe"
        )
        driver = ChromeDriver()
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS)
    }

    @AfterEach
    fun afterStep() {
        driver.quit()
    }

    @Test
    fun searchForGame() {
/*
    Given I am on the home page
    When I enter search term: Prophecy
    Then Search results for link: Prophecy should appear
    And Search results for image: Prophecy should appear */
        val mainPage = MainPage(driver).visit()
        val searchResultPage = mainPage.searchGames("Prophecy")
        assertNotEquals(0, searchResultPage.getGameImage("Prophecy").size)
        assertNotEquals(0, searchResultPage.getGameLink("Prophecy").size)
    }

    @Test
    fun testSignIn() {
/*
    Given I am on the main page
    When I click on Sign in button
    Then popup is shown
    And it contains field username
    And it contains field password
    When I enter name and pass */
        MainPage(driver)
            .visit()
            .clickOnSignIn()
            .isDisplayed()
            .passwordDisplayed()
            .usernameDisplayed()
            .signIn("name", "pass")
    }

    @Test
    fun testHelpSearach() {
/*
    Given I am on the FAQ page
    Then search box is present
    And BoardGameGeek FAQ article is present
    When I search for API
    Then List of results with BGG_XML_API2 is shown */
        val faqPage = FaqPage(driver).visit()
        assertTrue(faqPage.searchDisplayed())
        assertTrue(faqPage.articleDisplayed())
        faqPage.search("API")
        assertNotNull(faqPage.checkResultTable("BGG_XML_API2"))
    }

}