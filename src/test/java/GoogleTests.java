import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Epic("QA.GURU automation course")
@Story("Google tests with video")
@Tag("google")
class GoogleTests {

    @BeforeEach
    void beforeEach(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        if(System.getProperty("selenoid_url") != null) {
            Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub";
        }
    }

    @Test
    @DisplayName("успешный поиск в Google")
    @Description("Positive test")

    void successfulSearch() {
        step("Открываем поисковик",() -> {
            open("http://google.com");
        });
        step("Вводим поисковый запрос", () -> {
            $(byName("q")).val("lepra").pressEnter();
        });
        step("Находим результат", () -> {
            $("html").shouldHave(text("Лепра — Википедия"));});

    }

    @Test
    @DisplayName("неудачный поиск в Google")
    @Description("Negative test")

    void unSuccessfulSearch() {
        step("Открываем поисковик",() -> {
            open("http://google.com");
        });
        step("Вводим поисковый запрос", () -> {
            $(byName("q")).val("lepra").pressEnter();
        });
        step("Находим результат", () ->{
            $("html").shouldHave(text("Липра — Википедия"));});

    }
}