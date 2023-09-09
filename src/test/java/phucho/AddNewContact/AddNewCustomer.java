package phucho.AddNewContact;

import org.testng.asserts.SoftAssert;
import phucho.common.BaseTest;
import phucho.locators.LocatorCRM;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewCustomer extends BaseTest {
    String COMPANY_NAME = "Selenium 04";
    public void loginCRM(){
        driver.findElement(By.xpath(LocatorCRM.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LocatorCRM.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LocatorCRM.buttonLogin)).click();
        sleep(1);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.menuDashboard)).isDisplayed(),"Not Dasboard Page");

    }

    @Test
    public void testAddNewCustomer(){
//        String COMPANY_NAME = "Selenium 04";

        driver.get("https://crm.anhtester.com/admin/authentication");
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerLoginPage)).isDisplayed(),"Not Login Page");

        loginCRM();

        driver.findElement(By.xpath(LocatorCRM.menuCustomers)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerCustomersPage)).isDisplayed(),"Not Customer Page");
        Assert.assertEquals(driver.findElement(By.xpath(LocatorCRM.headerCustomersPage)).getText(),"Customers Summary", "Not match header name" );

        driver.findElement(By.xpath(LocatorCRM.buttonAddNewCustomer)).click();
        driver.findElement(By.xpath(LocatorCRM.inputCompanyName)).sendKeys(COMPANY_NAME);
        driver.findElement(By.xpath(LocatorCRM.inputVatNumber)).sendKeys("10");
        driver.findElement(By.xpath(LocatorCRM.inputPhone)).sendKeys("123456");
        driver.findElement(By.xpath(LocatorCRM.inputWebsite)).sendKeys("anhtester.com");
        driver.findElement(By.xpath(LocatorCRM.dropdownGroups)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchGroup)).sendKeys("VIP");
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchGroup)).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(LocatorCRM.dropdownGroups)).click();
        driver.findElement(By.xpath(LocatorCRM.inputAddress)).sendKeys("Sai Gon");
        driver.findElement(By.xpath(LocatorCRM.inputCity)).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath(LocatorCRM.inputState)).sendKeys("Sai Gon");
        driver.findElement(By.xpath(LocatorCRM.inputZipCode)).sendKeys("70000");
        driver.findElement(By.xpath(LocatorCRM.buttonCountry)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchCountry)).sendKeys("Vietnam");
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchCountry)).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(LocatorCRM.buttonSaveCustomer)).click();
        sleep(3);
        //Search lại customer vừa add new
        driver.findElement(By.xpath(LocatorCRM.menuCustomers)).click();
        driver.findElement(By.xpath(LocatorCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.firstItemCustomerOnTable)).isDisplayed(), "Customer is not found");


        //Kiểm tra giá trị sau khi add new
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath(LocatorCRM.firstItemCustomerOnTable)).click();
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputCompanyName)).getAttribute("value"),COMPANY_NAME,"Company name is not matched");
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputVatNumber)).getAttribute("value"),"10","VAT is not matched");
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputPhone)).getAttribute("value"),"123456","Phone is not matched");
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputWebsite)).getAttribute("value"),"anhtester.com","Website name is not matched");



        softAssert.assertAll();

    }
    @Test
    public void testAddNewContactForCustomer(){
        String number = "9";
        String firstNameContact = "A" + number;
        String lastNameContact = "B";
        String emailContact = number + "abc@gmail.com";

        driver.get("https://crm.anhtester.com/admin/authentication");
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerLoginPage)).isDisplayed(),"Not Login Page");
        loginCRM();
        driver.findElement(By.xpath(LocatorCRM.menuCustomers)).click();
        sleep(1);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerCustomersPage)).isDisplayed(),"Not Customer Page");
        Assert.assertEquals(driver.findElement(By.xpath(LocatorCRM.headerCustomersPage)).getText(),"Customers Summary", "Not match header name" );
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.menuCustomers)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.firstItemCustomerOnTable)).isDisplayed(), "Customer is not found");
        driver.findElement(By.xpath(LocatorCRM.firstItemCustomerOnTable)).click();
        driver.findElement(By.xpath(LocatorCRM.menuContact)).click();
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerContactPage)).isDisplayed(), "Contact Page is not found");
        driver.findElement(By.xpath(LocatorCRM.buttonAddNewContact)).click();
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCRM.headerAddNewContactDialog)).isDisplayed(), "Add New Contact Dialog is not found");
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputProfileImage)).sendKeys(System.getProperty("user.dir") + "/src/test/resources/datatest/profile_image.jpg");
        sleep(2);
        driver.findElement(By.xpath(LocatorCRM.inputFirstNameContact)).sendKeys(firstNameContact);
        driver.findElement(By.xpath(LocatorCRM.inputLastNameContact)).sendKeys(lastNameContact);
        driver.findElement(By.xpath(LocatorCRM.inputEmailContact)).sendKeys(emailContact);
        driver.findElement(By.xpath(LocatorCRM.inputPositionContact)).sendKeys("VIP");
        driver.findElement(By.xpath(LocatorCRM.inputPhoneContact)).sendKeys("123456");
        driver.findElement(By.xpath(LocatorCRM.buttonGeneratePassword)).click();
        driver.findElement(By.xpath(LocatorCRM.buttonShowPassword)).click();
        driver.findElement(By.xpath(LocatorCRM.checkboxDoNotSendEmail)).click();
        driver.findElement(By.xpath(LocatorCRM.buttonSaveContact)).click();
        SoftAssert softAssert = new SoftAssert();
//        sleep(1);
//        softAssert.assertTrue(driver.findElement(By.xpath(LocatorCRM.alertSaveContactSuccess)).isDisplayed(),"Alert Save Contact Success is not shown");
        sleep(1);
        driver.findElement(By.xpath(LocatorCRM.inputSearchContact)).sendKeys(firstNameContact + " " + lastNameContact);
        sleep(1);
        softAssert.assertTrue(driver.findElement(By.xpath(LocatorCRM.firstContactOnTable)).isDisplayed(),"No Contact were found");
        driver.findElement(By.xpath(LocatorCRM.firstContactOnTable)).click();
        sleep(1);
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputFirstNameContact)).getAttribute("value"), firstNameContact, "First Name is not matched");
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputLastNameContact)).getAttribute("value"), lastNameContact, "Last Name is not matched");
        softAssert.assertEquals(driver.findElement(By.xpath(LocatorCRM.inputEmailContact)).getAttribute("value"), emailContact, "Email is not matched");

        softAssert.assertAll();



    }

}

