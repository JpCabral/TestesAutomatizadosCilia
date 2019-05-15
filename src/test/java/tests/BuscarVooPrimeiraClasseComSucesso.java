package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class BuscarVooPrimeiraClasseComSucesso {

    /*
    O driver chromedriver.exe em questão suporta apenas o Google Chrome na versão 74.0.3729.6, em caso de erro buscar
     versão correspondente ao seu navegador no link http://chromedriver.chromium.org/downloads
    */
    private String chromedriver_path = System.getProperty("user.dir") + "\\chrome-driver\\chromedriver.exe";
    private String url = "https://www.phptravels.net/login";


    @Test
    public void testFazerBuscaDeVoosComSucesso() {
        String email = "user@phptravels.com";
        String senha = "demouser";
        String cidade_partida = "GYN";
        String cidade_chegada = "GRU";

        System.setProperty("webdriver.chrome.driver", chromedriver_path);

        WebDriver navegador = new ChromeDriver();
        JavascriptExecutor jse = (JavascriptExecutor)navegador;

        navegador.get(url);

        navegador.findElement(By.name("username")).sendKeys(email);
        navegador.findElement(By.name("password")).sendKeys(senha);
        navegador.findElement(By.className("loginbtn")).click();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Confirma se é possível encontrar a mensagem de boas vindas ao usuário
        navegador.findElement(By.cssSelector("div[class*='RTL']"));

        //Seleciona a aba FLIGHTS
        navegador.findElement(By.cssSelector("a[href*='flights']")).click();

//        navegador.findElement(By.cssSelector("div[id*='location_from']")).click();
        navegador.findElement(By.name("origin"));
        jse.executeScript("document.getElementsByName('origin')[0].setAttribute('value', '{\"code\":\"GYN\",\"label\":\"Santa Genoveva\"}');");
        jse.executeScript("document.getElementsByName('destination')[0].setAttribute('value', '{\"code\":\"GRU\",\"label\":\"Guarulhos Arpt\"}');");



//        <input type="text" name="" value="" id="location_from" tabindex="-1" class="select2-offscreen">

//        navegador.findElement(By.id("select2-drop"));
//        navegador.findElement(By.className("select2-search")).sendKeys(cidade_partida);
//        select2-input
//        System.out.println(navegador.findElement(By.className("nav nav-tabs RTL nav-justified")));
//        navegador.findElement(By.partialLinkText("Flights")).click();

        assertEquals("https://www.phptravels.net/m-flights",navegador.getCurrentUrl());
//        navegador.quit();
    }

}
