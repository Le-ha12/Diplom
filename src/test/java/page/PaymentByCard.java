package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static data.DataHelper.*;

@Data
public class PaymentByCard {
    private SelenideElement formPayment = $(byText("Оплата по карте"));
    private SelenideElement numberCard = $(By.cssSelector("input[placeholder='0000 0000 0000 0000']"));
    private SelenideElement month = $(By.cssSelector("input[placeholder='08']"));
    private SelenideElement year = $(By.cssSelector("input[placeholder='22']"));
    private SelenideElement cardowner = $(By.xpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvc = $(By.cssSelector("input[placeholder='999']"));
    private SelenideElement button = $(byText("Продолжить"));
    private SelenideElement approved = $(".notification_status_ok");
    private SelenideElement errorWindow = $(".notification_status_error");

    public PaymentByCard() {
        formPayment.shouldBe(Condition.visible);
    }

    public void fillingFormPayment(DataHelper.NumberCardClass setNumberCard, String setMonth, String setYear, String setCardowner, String setCvc) {
        numberCard.setValue(setNumberCard.getNumberCard());
        month.setValue(setMonth);
        year.setValue(setYear);
        cardowner.setValue(setCardowner);
        cvc.setValue(setCvc);
        button.click();
    }

    public void approved() {
        approved.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
    public void errorCard() {
        errorWindow.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void numberCardMoreQuantity () {
        $(".input__sub").shouldHave(exactText("Поле не должно превышать 21 символ"));
    }

    public void expiredDate () {
        $(".input__sub").shouldHave(exactText("Истёк срок действия карты"));
    }

    public void wrongFormat () {
        $(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void obligatory () {
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
