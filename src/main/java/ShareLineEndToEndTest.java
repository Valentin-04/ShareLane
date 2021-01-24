import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLineEndToEndTest {
    @Test
    public void signUpTestZipCodeFailed(){
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("1");
        $(By.cssSelector("[value=Continue]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");
    }
    @Test
    public void signUpTestZipCodePassed(){
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue]")).click();
        String result = "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111";
        Assert.assertEquals(result, "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
    }
    @Test
    public void sugnUpTestFormFailed(){
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        $(By.name("first_name")).sendKeys("Ted");
        $(By.name("email")).sendKeys("sss@sss");
        $(By.name("password1")).sendKeys("1111");
        $(By.name("password2")).sendKeys("1111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
    @Test
    public void sugnUpTestFormPassed(){
        open("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        $(By.name("first_name")).sendKeys("ted");
        $(By.name("email")).sendKeys("sss@sss.ss");
        $(By.name("password1")).sendKeys("1111");
        $(By.name("password2")).sendKeys("1111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }
}
