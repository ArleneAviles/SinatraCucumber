package sinatra.automation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SinatraStepDefs {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUpTest(){
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Given("I navigate to Songs By Sinatra Page")
    public void iNavigateToSongsBySinatraPage() {
        driver.get(" https://evening-bastion-49392.herokuapp.com/");

        //encontrar el mensaje de bienvenida
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section > p"))).isDisplayed();

        //esperar que aparezca la Imagen de Frank Sinatra
        WebElement imagenSinatra = driver.findElement(By.cssSelector("[src='/images/sinatra.jpg']"));
        assertTrue(imagenSinatra.isDisplayed());
        if(imagenSinatra.isDisplayed()){
            System.out.println("Se encontro la imagen de Frank Sinatra");
        }else {
            System.out.println("No se encontro la imagen de Frank");
            System.exit(-1);
        }

        //darle click al link de Login
        driver.findElement(By.linkText("log in")).click();

    }

    @When("I enter valid {word} and {word}")
    public void iEnterValidUserAndPassword(String userName, String password) {
        //verificar que aparezca el link de login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("log in")));

        //verificar que aparezca el campo de Username y password
        driver.findElement(By.id("username")).sendKeys(userName);
        System.out.println("Si registro el username");
        driver.findElement(By.id("password")).sendKeys(password);
        System.out.println("Si registro el password");

        //darle click al boton de login
        driver.findElement(By.cssSelector("[value='Log In']")).click();
    }

    @Then("I can see Songs By Sinatra Main Page")
    public void iCanSeeSongsBySinatraMainPage() {
        //esperar a que aparezca el mensaje de Bienvenida
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        //Validar que aparezca el link de log out
        WebElement logoutLink = driver.findElement(By.cssSelector("[href='/logout']"));
        assertTrue(logoutLink.isDisplayed());
        if (logoutLink.isDisplayed()){
            System.out.println("Si aparece el link de logout");
        } else {
            System.out.println("No se desplego el link de logout");
            System.exit(-1);
        }
    }
}
