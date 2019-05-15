package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LogarCorretamente {
    /*
        O driver chromedriver.exe em questão suporta apenas o Google Chrome na versão 74.0.3729.6, em caso de erro buscar
         versão correspondente ao seu navegador no link http://chromedriver.chromium.org/downloads
        */
    private String chromedriver_path = System.getProperty("user.dir") + "\\chrome-driver\\chromedriver.exe";
    private String url = "https://www.phptravels.net/login";

    @Test
    public void testFazerLoginComSucesso() {
        String email = "user@phptravels.com";
        String senha = "demouser";
        System.setProperty("webdriver.chrome.driver", chromedriver_path);

        WebDriver navegador = new ChromeDriver();
        navegador.get(url);

        navegador.findElement(By.name("username")).sendKeys(email);
        navegador.findElement(By.name("password")).sendKeys(senha);
        navegador.findElement(By.className("loginbtn")).click();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.findElement(By.cssSelector("div[class*='RTL']"));
        assertEquals("https://www.phptravels.net/account/",navegador.getCurrentUrl());
        navegador.close();
    }
}
