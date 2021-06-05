package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC_001_AccountRegistration extends BaseClass {
	
	@Test(groups = {"regression","master"})
	public void test_account_Registration()
	{   
		
		try {
		logger.info("Starting TC_001_AccountRegistration");
		
		driver.get(rb.getString("appURL"));
		logger.info("Home Page displayed");
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked in MyAccount");
		hp.clickRegister();
		logger.info("Clicked Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName("John");
		logger.info("Provided first name");
		
		regpage.setLastName("Canedy");
		logger.info("Provided Last name");
		
		regpage.setEmail(randomestring()+"@gmail.com");
		logger.info("Provided Email address");
		
		regpage.setTelephone("65656565");
		logger.info("Provided Telephone");
		
		regpage.setPassword("abcxyz");
		logger.info("Provided Password");
		
		regpage.setConfirmPassword("abcxyz");
		logger.info("Provided Confirm Password");
		
		regpage.setPrivacyPolicy();
		logger.info("Checked Privacy policy");
		
		regpage.clickContinue();
		logger.info("Clicked Continue button");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{	
			logger.info("Account Creation Successful");
			Assert.assertTrue(true);
			
		}
		else
		{	
			logger.info("Account creation failed");
			captureScreen(driver,"test_account_Registration");
			Assert.assertTrue(false);
			
		}
		}
		catch(Exception e)
		{
			logger.info("Account creation failed");
			Assert.fail();
			
		}
		logger.info("Finished TC_001_AccountRegistration");
	}
		
	
}