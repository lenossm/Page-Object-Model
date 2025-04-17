package ge.tbcitacademy.tests;

import ge.tbcitacademy.steps.LocatorsSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

// ეს არის მთავარი ტესტ კლასი — მოიძახებს მხოლოდ Step მეთოდებს
public class LocatorsTest {
    private WebDriver driver;
    private LocatorsSteps steps;

    // ტესტის დაწყების წინ — ვაბრუნებთ ბრაუზერს, ვქოლავთ Step კლასს
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver(); // ბრაუზერის გაშვება
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // ელემენტების მოლოდინი
        driver.manage().window().maximize(); // ფანჯრის მაქსიმალიზაცია
        steps = new LocatorsSteps(driver); // Step კლასის ინიციალიზაცია
    }

    // ტესტის შემდეგ — ბრაუზერის დახურვა
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // პირველი ტესტი — slider გვერდზე გადადის და ბეჭდავს გაფილტრულ ბმულებს
    @Test
    public void unorderedListTest() {
        steps
                .openSliderPage()
                .printFilteredLinks();
    }

    // მეორე ტესტი — add/delete ღილაკებზე მუშაობს და ვალიდაციას აკეთებს
    @Test
    public void buttonsTest() {
        steps
                .openButtonsPage()
                .clickAddButtonMultipleTimes(3)
                .validateLastDeleteButtonClass()
                .validateLastOnClickAttribute();
    }

    // მესამე ტესტი — challenging DOM გვერდზე გადადის და ეძებს მონაცემს
    @Test
    public void challengingDomTest() {
        steps
                .openChallengingDomPage()
                .printLoremValue();
    }
}
