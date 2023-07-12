package ru.netology.banklogin.test;

import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = DataHelper.generateRandomUser();
    loginPage.validLogin(authInfo);
    loginPage.verifyErrorNotificationVisiblity();
}
@Test
@DisplayName("Should get error notification if login with exist in base and active user and random verification code")
void shouldgGetErrorNotificationIfLoginWithExistUserAndRandomVerificationCode() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = DataHelper.getAuthInfoWithTestData();
    var verificationPage = loginPage.validLogin(authInfo);
    verificationPage.verifyVerificationPageVisiblity();
    var verificationCode = DataHelper.generateRandomVerificationCode();
    verificationPage.verify(verificationCode.getCode());
    verificationPage.verifyErrorNotificationVisiblity();
}
}
