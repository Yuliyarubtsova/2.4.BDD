package ru.netology.web.test;

import lombok.val;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @Test
    void shouldTransfer0MoneyFromFirstCardToSecond() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.secondTopUp();
        int amount = 0;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardFirst());
        val balanceFirstCardAfterTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer - amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer + amount), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldTransfer0MoneyFromSecondCardToFirstCard() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.FirstTopUp();
        int amount = 0;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardSecond());
        val balanceFirstCardAfterTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer + amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer - amount), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldTransferValidAmountFromFirstCardToSecondCard() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.secondTopUp();
        int amount = 5000;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardFirst());
        val balanceFirstCardAfterTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer - amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer + amount), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldTransferValidAmountFromSecondCardToFirstCard() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.FirstTopUp();
        int amount = 5000;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardSecond());
        val balanceFirstCardAfterTransfer = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer + amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer - amount), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldTransferInValidAmountFromFirstCardToSecondCard() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val moneyTransferPage = dashboardPage.secondTopUp();
        int amount = 50000;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardFirst());
        moneyTransferPage.errorMessage();
    }

    @Test
    void shouldTransferInValidAmountFromSecondCardToFirstdCard() {
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val moneyTransferPage = dashboardPage.FirstTopUp();
        int amount = 50000;
        moneyTransferPage.transferMoney(amount, DataHelper.Card.getCardFirst());
        moneyTransferPage.errorMessage();
    }

}
