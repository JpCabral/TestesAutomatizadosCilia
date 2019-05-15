package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;

public class BuscarVooPrimeiraClasseComSucesso {

    /* O driver chromedriver.exe em questão suporta apenas o Google Chrome na versão 74.0.3729.6, em caso de erro buscar
     versão correspondente ao seu navegador no link http://chromedriver.chromium.org/downloads  */

    //Endereço do chromedriver do projeto
    private String chromedriver_path = System.getProperty("user.dir") + "\\chrome-driver\\chromedriver.exe";

    //URL da página de login
    private String url = "https://www.phptravels.net/login";


    @Test
    public void testFazerBuscaDeVoosComSucesso() {
        //Dados de acesso corretos
        String email = "user@phptravels.com";
        String senha = "demouser";
        String cidade_partida = "GYN";
        String cidade_chegada = "GRU";

        //Configura-se a utilização do chromedriver do projeto
        System.setProperty("webdriver.chrome.driver", chromedriver_path);

        WebDriver navegador = new ChromeDriver();

        //Acessa a URL
        navegador.get(url);

        //Inicializa executor de código javascript
        JavascriptExecutor jse = (JavascriptExecutor)navegador;

        //Encontra os campos de login e senha
        navegador.findElement(By.name("username")).sendKeys(email);
        navegador.findElement(By.name("password")).sendKeys(senha);

        //Confirma selecionando o botão de Login
        navegador.findElement(By.className("loginbtn")).click();

        //Aguarda o carregamento de todos os elementos da página
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Verifica o painel de boas vindas ao usuário
        navegador.findElement(By.cssSelector("div[class*='RTL']"));

        //Seleciona a aba FLIGHTS
        navegador.findElement(By.cssSelector("a[href*='flights']")).click();

        navegador.findElement(By.name("origin"));

        jse.executeScript("document.getElementsByName('origin')[0].setAttribute('value', '{\"code\":\"GYN\",\"label\":\"Santa Genoveva\"}');");
        jse.executeScript("document.getElementsByName('destination')[0].setAttribute('value', '{\"code\":\"GRU\",\"label\":\"Guarulhos Arpt\"}');");

        assertEquals("https://www.phptravels.net/m-flights",navegador.getCurrentUrl());

        //Fecha o navegador para economia de RAM
        navegador.quit();
    }

}
