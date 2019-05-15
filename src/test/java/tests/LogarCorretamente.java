package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class LogarCorretamente {
    /*  O driver chromedriver.exe em questão suporta apenas o Google Chrome na versão 74.0.3729.6, em caso de erro buscar
         versão correspondente ao seu navegador no link http://chromedriver.chromium.org/downloads */

    //Endereço do chromedriver do projeto
    private String chromedriver_path = System.getProperty("user.dir") + "\\chrome-driver\\chromedriver.exe";

    //URL da página de login
    private String url = "https://www.phptravels.net/login";

    @Test
    public void testFazerLoginComSucesso() {
        //Dados de acesso corretos
        String email = "user@phptravels.com";
        String senha = "demouser";

        //Configura-se a utilização do chromedriver do projeto
        System.setProperty("webdriver.chrome.driver", chromedriver_path);

        WebDriver navegador = new ChromeDriver();
        navegador.get(url);

        //Encontra os campos de login e senha
        navegador.findElement(By.name("username")).sendKeys(email);
        navegador.findElement(By.name("password")).sendKeys(senha);

        //Confirma selecionando o botão de Login
        navegador.findElement(By.className("loginbtn")).click();

        //Aguarda o carregamento de todos os elementos da página
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Verifica o painel de boas vindas ao usuário
        navegador.findElement(By.cssSelector("div[class*='RTL']"));

        //Se a página atual for a da conta então o teste foi bem sucedido
        assertEquals("https://www.phptravels.net/account/",navegador.getCurrentUrl());

        //Fecha o navegador para economia de RAM
        navegador.quit();
    }
}
