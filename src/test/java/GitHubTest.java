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
        //��������� ������� Google � � ��� ������� ���� github
        Selenide.open("https://github.com/");
        Configuration.holdBrowserOpen = true;

        //������� �������� Selenide � Github.com
        $("[type=text]").setValue("selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();

        //������� � ������ Wiki �������
        $("#wiki-tab").click();

        //���������, ��� � ������ ������� (Pages) ���� �������� SoftAssertions
        $(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        //������� �������� SoftAssertions, ��������� ��� ������ ���� ������ ���� ��� JUnit5
        $$(byText("SoftAssertions")).find(visible).click();
        $("#wiki-content").shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}
