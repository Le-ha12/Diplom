package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Data
public class WebService {
    private SelenideElement headingH2 = $("h2");
    private SelenideElement paymentButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public WebService() {
        headingH2.shouldBe(Condition.visible);
    }

    public PaymentByCard paymentByCard() {
        paymentButton.click();
        return new PaymentByCard();
    }

    public CreditCard creditCard() {
        creditButton.click();
        return new CreditCard();
    }
}

