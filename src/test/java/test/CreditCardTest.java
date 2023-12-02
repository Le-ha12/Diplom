package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DbClass;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void deleteTable() {
        DbClass.deleteTable();

        open("http://localhost:8080");
    }

    //1. Валидная покупка в кредит
    @Test
    void shouldBeStatusApprovedCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
        var creditStatus = DbClass.statusCreditCard();
        assertEquals("APPROVED", creditStatus);
    }

    //2. Отправка формы "Кредит по данным карты" с вводом невалидного номера карты.
    @Test
    void shouldBeStatusDeclinedCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardDeclined();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.errorCard();
//проверка записи в бд
    }

    //3. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (ввод букв, цифр, символов)
    @Test
    void inputWrongFormatSymbolInFieldCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardSymbol();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //4. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (количество символов меньше)
    @Test
    void inputWrongFormatLessQuantityInFieldCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardLessQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //5. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (количество символов больше)
    @Test
    void inputWrongFormatMoreQuantityInFieldCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardMoreQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.errorCard();
    }

    //6. Отправка формы "Кредит по данным карты" с истекшим сроком карты по месяцу.
    @Test
    void shouldBeExpiredMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthNoVal();
        var setYear = DataHelper.getYearNow();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //7. Отправка формы "Кредит по данным карты" с истекшим сроком карты по году.
    @Test
    void shouldBeExpiredYearCreditTest () {
        DbClass.deleteTable();
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearNoVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //8. Отправка формы "Кредит по данным карты" с неверным форматом месяца (ввод нулей)
    @Test
    void inputZeroInFieldMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthZero();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //9. Отправка формы "Кредит по данным карты" с неверным форматом года (ввод нулей)
    @Test
    void inputZeroInFieldYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearZero();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //10. Отправка формы "Кредит по данным карты" с пустым полем "Номер карты"
    @Test
    void shouldBeEmptyFieldNumberCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardEmpty();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //11. Отправка формы "Кредит по данным карты" с пустым полем "Месяц"
    @Test
    void shouldBeEmptyFieldMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthEmpty();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //12. Отправка формы "Кредит по данным карты" с пустым полем "Год"
    @Test
    void shouldBeEmptyFieldYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearEmpty();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //13. Отправка формы "Кредит по данным карты" с пустым полем "Владелец"
    @Test
    void shouldBeEmptyFieldCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerEmpty();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //14. Отправка формы "Кредит по данным карты" с пустым полем "CVC/CVV"
    @Test
    void shouldBeEmptyFieldCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcEmpty();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //15. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (ввод букв, цифр, символов)
    @Test
    void inputWrongFormatSymbolInFieldMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthSymbol();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //16. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (ввод букв, цифр, символов)
    @Test
    void inputWrongFormatSymbolInFieldYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearSymbol();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //17. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (ввод букв, цифр, символов)
    @Test
    void inputWrongFormatSymbolInFieldCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerSymbol();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //18. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (ввод букв, цифр, символов)
    @Test
    void inputWrongFormatSymbolInFieldCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcSymbol();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //19. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (количество символов меньше)
    @Test
    void inputWrongFormatLessQuantityInFieldMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthLessQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //20. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (количество символов меньше)
    @Test
    void inputWrongFormatLessQuantityInFieldYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearLessQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //21. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (количество символов меньше)
    @Test
    void inputWrongFormatLessQuantityInFieldCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerLessQuantity();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //22. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (количество символов меньше)
    @Test
    void inputWrongFormatLessQuantityInFieldCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcLessQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //23. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (количество символов больше)
    @Test
    void inputWrongFormatMoreQuantityInFieldMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthMoreQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //24. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (количество символов больше)
    @Test
    void inputWrongFormatMoreQuantityInFieldYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearMoreQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //25. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (количество символов больше)
    @Test
    void inputWrongFormatMoreQuantityInFieldCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerMoreQuantity();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.numberCardMoreQuantity();
    }

    //26. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (количество символов больше)
    @Test
    void inputWrongFormatMoreQuantityInFieldCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcMoreQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }
}
