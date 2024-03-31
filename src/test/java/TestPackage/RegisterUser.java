package TestPackage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ecommerce.utils.GoogleAlert;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("User Register")
public class RegisterUser {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    By homePageSignUpButton = By.className("fa-lock");
    By nameField = By.cssSelector("[data-qa=signup-name]");
    By emailField = By.cssSelector("[data-qa=signup-email]");
    By signupButton = By.cssSelector("[data-qa=signup-button]");
    By message = By.xpath("(//*[@class=\"title text-center\"])[1]");


    @Test(description = "Register User Test - GUI")
    @Description("As a new user, upon entering valid data during registration, I should be successfully registered and " +
            "automatically logged into the system.")
    public void test() {
//        1. Launch browser -- Launched earlier
//        2. Navigate to url 'http://automationexercise.com'
        String baseUrl = "https://automationexercise.com/";
        driver.browser().navigateToURL(baseUrl);

//        3. Verify that home page is visible successfully
        driver.verifyThat().browser().title().isEqualTo("Automation Exercise").perform();

//        4. Click on 'Signup / Login' button
        driver.element().click(homePageSignUpButton);

//        5. Verify 'New User Signup!' is visible
        driver.element().verifyThat(By.className("signup-form")).text().isEqualTo("New User Signup!\n" +
                "Signup").perform();

//        6. Enter name and email address
        driver.element().type(nameField, testData.getTestData("name"));
        driver.element().type(emailField, testData.getTestData("email"));

//        7. Click 'Signup' button
        driver.element().click(signupButton);

//        8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        driver.element().verifyThat(message).text().isEqualTo("ENTER ACCOUNT INFORMATION").perform();

//        9. Fill details: Title, Name, Email, Password, Date of birth
        driver.element().click(By.id("id_gender1"));
        driver.element().type(By.cssSelector("[data-qa=password]"), testData.getTestData("password"));
        driver.element().click(By.xpath("//*[@value=20]")); // Days
        driver.element().click(By.xpath("(//*[@value=9])[2]")); // Months
        driver.element().click(By.xpath("(//*[@value=1995])")); //

//        10. Select checkbox 'Sign up for our newsletter!'
        driver.element().click(By.id("newsletter"));

//        11. Select checkbox 'Receive special offers from our partners!'

//        12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        driver.element().type(By.cssSelector("[data-qa=first_name]"), testData.getTestData("firstName"));
        driver.element().type(By.cssSelector("[data-qa=last_name]"), testData.getTestData("lastName"));
        driver.element().type(By.cssSelector("[data-qa=address]"), testData.getTestData("address"));
        driver.element().click(By.xpath("//option[@value=\"Singapore\"]"));
        driver.element().type(By.cssSelector("[data-qa=state]"), testData.getTestData("city"));
        driver.element().type(By.cssSelector("[data-qa=city]"), testData.getTestData("state"));
        driver.element().type(By.cssSelector("[data-qa=zipcode]"), testData.getTestData("zipCode"));
        driver.element().type(By.cssSelector("[data-qa=mobile_number]"), testData.getTestData("mobileNumber"));

//        13. Click 'Create Account button'
        driver.element().click(By.cssSelector("[data-qa=create-account]"));

//        14. Verify that 'ACCOUNT CREATED!' is visible
        GoogleAlert.dismissAlert(driver,By.xpath("By.cssSelector(\"[data-qa=account-created]\")"));
        driver.element().verifyThat(By.cssSelector("[data-qa=account-created]")).text().isEqualTo("ACCOUNT CREATED!").perform();

//        15. Click 'Continue' button
        driver.element().click(By.cssSelector("[data-qa=continue-button]"));


//        17. Click 'Delete Account' button
//        driver.element().click(By.cssSelector("a[href='/delete_account']"));
        GoogleAlert.dismissAlert(driver,By.cssSelector("a[href='/delete_account']"));

//        18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
//        driver.element().verifyThat(By.cssSelector("h2[data-qa=account-deleted] > b")).text().
//                equalsIgnoringCaseSensitivity("ACCOUNT DELETED!").perform();

        driver.element().click(By.cssSelector("[data-qa=continue-button]")) ;


    }

    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        driver.quit();
    }
}
