package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass
{

	@Test(groups = {"sanity","master"})
	public void test_Login()
	{
		logger.info("Starting TC_002_Login");
		
		try
		{
			driver.get(rb.getString("appURL"));
			logger.info("Home Page Displayed");
			
			driver.manage().window().maximize();
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount");
			hp.clickLogin();
			logger.info("Clicked on Login");
			
			LoginPage lp = new LoginPage(driver);
			
			lp.setEmail(rb.getString("email"));
			logger.info("Provided Email");
			
			lp.setPassword(rb.getString("password"));
			logger.info("Provided Password");
			
			lp.clickButton();
			logger.info("Clicked Login Button");
			
			
			boolean targetpage = lp.isMyAccountPageExists();
			
			if (targetpage)
			{
				logger.info("Login Success");
			}
			else
			{
				logger.error("Login Failed");
				captureScreen(driver,"TC_002_LoginPage"); //Capturing Screen shot
				Assert.assertTrue(false);
			}
			
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
			
		}
		
		logger.info("Finished TC_002_Login");
	}
}
