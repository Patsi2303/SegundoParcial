import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestsSauceDemo extends BaseTest {

    @Test
    public void logoutTest() throws InterruptedException {

        WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        usernameTextBox.sendKeys("standard_user");

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement sideBarButton = driver.findElement(By.id("react-burger-menu-btn"));
        sideBarButton.click();

        Thread.sleep(2000);

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();

        WebElement loginLogo = driver.findElement(By.className("login_logo"));
        Assertions.assertTrue(loginLogo.isDisplayed());
    }

    @Test
    public void addProductTest() throws InterruptedException {

        WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        usernameTextBox.sendKeys("standard_user");

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        selectProduct("Sauce Labs Onesie");

        WebElement addToCart = driver.findElement(By.id("add-to-cart"));
        addToCart.click();

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String quantity = cartBadge.getText();

        Assertions.assertTrue(cartBadge.isDisplayed());
        Assertions.assertEquals("1", quantity);
    }

    public void selectProduct(String productName){
        WebElement product = driver.findElement(By.xpath("//div[text()='"+productName+"']"));
        product.click();
    }

    @Test
    public void backFromCartTest() throws InterruptedException {

        WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        usernameTextBox.sendKeys("standard_user");

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement continueShoppingButton = driver.findElement(By.id("continue-shopping"));
        continueShoppingButton.click();

        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assertions.assertTrue(product.isDisplayed());
    }

    @Test
    public void cancelCheckoutTest(){

        WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        usernameTextBox.sendKeys("standard_user");

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        selectProduct("Sauce Labs Onesie");

        WebElement addToCart = driver.findElement(By.id("add-to-cart"));
        addToCart.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();

        WebElement cartTitle = driver.findElement(By.className("title"));
        String cartTitleText = cartTitle.getText();
        Assertions.assertTrue(cartTitle.isDisplayed());
        Assertions.assertEquals(cartTitleText, "Your Cart");
    }

    @Test
    public void sucessfulCheckoutTest(){

        WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        usernameTextBox.sendKeys("standard_user");

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        selectProduct("Sauce Labs Onesie");

        WebElement addToCart = driver.findElement(By.id("add-to-cart"));
        addToCart.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Alejandro");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Uriarte");
        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("0000");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        WebElement thanksMessage = driver.findElement(By.className("complete-header"));
        String messageText = thanksMessage.getText();
        Assertions.assertTrue(thanksMessage.isDisplayed());
        Assertions.assertEquals("Thank you for your order!", messageText);
    }
}
