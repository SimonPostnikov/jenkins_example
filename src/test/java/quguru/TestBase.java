package quguru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Credentials;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import helpers.Attach;
import static config.Credentials.credentials;
import static java.lang.String.format;


public class TestBase {
    private static Object Credentials;

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        //Набор ключей-значений, которые передаются драйверу
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        //Макс браузер
        Configuration.startMaximized = true;
        //Запуск браузера в селеноиде
        String login = credentials.login();
        String password =credentials.password();
        Configuration.remote = format("https://%s:%s@",login,password) + System.getProperty("url");
    };

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }
}