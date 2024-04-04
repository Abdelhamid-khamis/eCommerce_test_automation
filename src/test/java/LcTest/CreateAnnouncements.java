package LcTest;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class CreateAnnouncements {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    @Test
    public void test() {
//        1. Launch browser -- Launched earlier
//        2. Navigate to url 'http://automationexercise.com'
        String baseUrl = "https://testing-lc.azurewebsites.net/Announcement/Index";
        driver.browser().navigateToURL(baseUrl);

        //        3. Verify that home page is visible successfully
        driver.verifyThat().browser().title().isEqualTo("Log in - WinjiGo").perform();


        By emailField = By.xpath("//*[@class=\"form-control lms-xs-placeholder text-box single-line\"]");
        driver.element().type(emailField, testData.getTestData("email"));

        By passwordField = By.id("inputPassword");
        driver.element().type(passwordField, testData.getTestData("password"));

        driver.element().click(By.id("btnLogin"));
        driver.element().click(By.id("lspnannounceCounter"));
        driver.element().click(By.xpath("//*[@class=\"btn btn-link lms-xs-no-padding-start lms-font-meduim lms-padding-end-0\"]"));

//        Announcement form
//        dropdown segments
        driver.element().click(By.xpath("//*[@class=\"input\"]"));
//        driver.element().click(By.xpath("lms-tree-name lms-nowrap"));
        driver.element().click(By.id("school_71787c91-2e3b-4672-a28b-af0064a12aef"));
        driver.element().click(By.xpath("//*[@class=\"input\"]"));

        driver.element().click(By.xpath("//*[@class=\"btn btn-default btn-block dropdown-toggle\"]"));
        driver.element().click(By.xpath("(//*[@class=\"item-unselected truncate-full-width display-block\"])[4]"));
        driver.element().click(By.xpath("//*[@class=\"btn btn-default btn-block dropdown-toggle\"]"));

//        Title & Msg
driver.element().type(By.id("txtannouncementTitle"), testData.getTestData("announcementTitle"));
//driver.element().type(By.id("txtAnnouncementMessage_contents"), testData.getTestData("announcementTitle"));

        By iFrameLocator = By.xpath("//*[@class=\"cke_wysiwyg_frame cke_reset\"]");
        driver.element().switchToIframe(iFrameLocator );
        driver.element().type(By.xpath("//*[@class=\"cke_editable cke_editable_themed cke_contents_ltr cke_show_borders\"]"), testData.getTestData("announcementMsg"));
        driver.element().switchToDefaultContent();



        driver.element().click(By.id("btnAddAnnouncementApply"));

    }

    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("lcJSON.json");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        driver.quit();
    }
}

