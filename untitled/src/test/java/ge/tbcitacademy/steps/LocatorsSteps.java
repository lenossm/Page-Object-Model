package ge.tbcitacademy.steps;

import ge.tbcitacademy.pages.LocatorsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

// Step კლასი — აქ ვწერთ ქმედებებს ელემენტებზე, ერთი მოქმედება თითო მეთოდად
public class LocatorsSteps {
    private final LocatorsPage page;

    // კონსტრუქტორი იღებს WebDriver-ს და ინიციალიზებს Page კლასს
    public LocatorsSteps(WebDriver driver) {
        this.page = new LocatorsPage(driver);
    }

    // slider გვერდზე გადასვლა
    public LocatorsSteps openSliderPage() {
        page.openSliderPage();
        return this;
    }

    // ფილტრავს და ბეჭდავს მხოლოდ იმ ბმულებს, რომლებიც შეიცავენ "o"-ს და არ შეიცავენ "animate"
    public LocatorsSteps printFilteredLinks() {
        List<WebElement> filteredList = page.getListItems().stream()
                .filter(e -> e.getText().contains("o")) // მხოლოდ ისინი, სადაც ტექსტში "o" წერია
                .collect(Collectors.toList());

        filteredList.parallelStream().forEach(item -> {
            WebElement link = item.findElement(org.openqa.selenium.By.tagName("a")); // მოიძებნოს <a>
            String href = link.getAttribute("href"); // აიღოს href

            if (!href.contains("animate")) { // გამოტოვოს animate
                System.out.println(href); // დაბეჭდოს
            }
        });

        return this;
    }

    // Buttons გვერდზე გადასვლა
    public LocatorsSteps openButtonsPage() {
        page.openButtonsPage();
        return this;
    }

    // "Add Element" ღილაკზე რამდენჯერმე დაკლიკება
    public LocatorsSteps clickAddButtonMultipleTimes(int times) {
        for (int i = 0; i < times; i++) {
            page.addElementButton.click(); // დაქლიკება ელემენტზე
        }
        return this;
    }

    // ამოწმებს ბოლო Delete ღილაკის class ატრიბუტს
    public LocatorsSteps validateLastDeleteButtonClass() {
        WebElement lastDeleteButton = page.getLastDeleteButton();
        Assert.assertEquals(lastDeleteButton.getAttribute("class"), "added-manually");
        return this;
    }

    // ამოწმებს ბოლო Delete ღილაკის onclick ატრიბუტს
    public LocatorsSteps validateLastOnClickAttribute() {
        List<WebElement> deleteButtons = page.getDeleteButtons();
        WebElement lastButton = deleteButtons.get(deleteButtons.size() - 1); // ბოლო ელემენტი
        Assert.assertEquals(lastButton.getAttribute("onclick"), "deleteElement()");
        return this;
    }

    // Challenging DOM გვერდზე გადასვლა
    public LocatorsSteps openChallengingDomPage() {
        page.openChallengingDomPage();
        return this;
    }

    // ბეჭდავს Apeirian9-ის გვერდით მდებარე მნიშვნელობას
    public LocatorsSteps printLoremValue() {
        String text = page.getLoremValueForApeirian9().getText();
        System.out.println("Lorem value for Apeirian9 is: " + text);
        return this;
    }
}
