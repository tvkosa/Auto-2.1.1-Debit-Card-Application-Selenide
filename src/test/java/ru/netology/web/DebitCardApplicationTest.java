package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardApplicationTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendRequest() {
        $("[data-test-id=name] input").setValue("Горчицина Варвара");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldSendRequestNameAndSurnameSeparatedDash() {
        $("[data-test-id=name] input").setValue("Горчицина-Метелкина Варвара-Барбара");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldRequestMeaningInName() {
        $("[data-test-id=name] input").setValue("%");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    public void shouldRequestNoRussianLetterInName() {
        $("[data-test-id=name] input").setValue("Varvara");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldRequestNameIsNotFull() {
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));
    }

    @Test
    public void shouldRequestPhoneNumberIsNotFull() {
        $("[data-test-id=name] input").setValue("Горчицина Варвара");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));
    }

    @Test
    public void shouldRequestPhoneNumber10Digits() {
        $("[data-test-id=name] input").setValue("Горчицина Варвара");
        $("[data-test-id=phone] input").setValue("+7901234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldRequestPhoneNumber12Digits() {
        $("[data-test-id=name] input").setValue("Горчицина Варвара");
        $("[data-test-id=phone] input").setValue("+790123456789");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldRequestNoAgreement() {
        $("[data-test-id=name] input").setValue("Горчицина Варвара");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
//        @Test
//    public void shouldSendRequestName() {
//        $("[data-test-id=name] input").setValue("Горчицина-Метёлкина Варвара");
//        $("[data-test-id=phone] input").setValue("+79012345678");
//        $("[data-test-id=agreement]").click();
//        $("button").click();
//        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
//    }

