import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GitHubTest {
    @Test
    void openGit() {
        //Запустить браузер Google и в нем открыть сайт github
        Selenide.open("https://github.com/");
        Configuration.holdBrowserOpen = true;

        //Открыть страницу Selenide в Github.com
        $("[type=text]").setValue("selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();

        //Перейти в раздел Wiki проекта
        $("#wiki-tab").click();

        //Убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        //Открыть страницу SoftAssertions, проверить что внутри есть пример кода для JUnit5
        $$(byText("SoftAssertions")).find(visible).click();
        $("#wiki-content").shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}
