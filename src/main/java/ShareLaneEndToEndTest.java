import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLaneEndToEndTest {
    public static final String URL = "https://www.sharelane.com/cgi-bin/register.py";
    public static final String NUMBER = "11111";
    public static final Object REGISTER = By.cssSelector("[value=Register]");

    @Test
    public void signUpTestZipCodeFailed() {
        open(URL);
        $(By.name("zip_code")).sendKeys("1");
        $(By.cssSelector("[value=Continue]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");
    }
    @Test
    public void signUpTestZipCodePassed() {
        open(URL);
        $(By.name("zip_code")).sendKeys(NUMBER);
        $(By.cssSelector("[value=Continue]")).click();
        Object result = REGISTER;
        Assert.assertEquals(result, REGISTER);
    }
    @Test
    public void signUpTestFormFailed() {
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        $(By.name("first_name")).sendKeys("Ted");
        $(By.name("email")).sendKeys("sss@sss");
        $(By.name("password1")).sendKeys(NUMBER);
        $(By.name("password2")).sendKeys(NUMBER);
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
    @Test
    public void signUpTestFormPassed() {
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        $(By.name("first_name")).sendKeys("ted");
        $(By.name("email")).sendKeys("sss@sss.ss");
        $(By.name("password1")).sendKeys(NUMBER);
        $(By.name("password2")).sendKeys(NUMBER);
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }
}