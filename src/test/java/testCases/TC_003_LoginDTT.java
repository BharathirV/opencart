package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDTT extends BaseClass {
	
	
	@Test(dataProvider="LoginData")
	public void test_LoginDTT(String email,String pwd,String exp)
	{
		logger.info("Starting TC_003_LoginDTT");
		
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
			
			lp.setEmail(email);
			logger.info("Provided Email");
			
			lp.setPassword(pwd);
			logger.info("Provided Password");
			
			lp.clickButton();
			logger.info("Clicked Login Button");
			
			boolean targetpage = lp.isMyAccountPageExists();
			
			if(exp.equals("Valid"))
			{
				if(targetpage == true)
				{
					logger.info("Login Success");
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
				else //When data is Valid target page is false then else part will execute
				{
					logger.error("Login Failed");
					Assert.assertTrue(false);
				}
			
			}
			if (exp.equals("Invalid"))
			{
				if (targetpage == true) //when passed invalid data my targetpage should be false but it is true so it should return false
				{
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				}
				else //when invalid data is given targetpage should be false hence else will return true
				{
					logger.error("Login Failed");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{	
			logger.fatal("Login Failed");
			Assert.fail(); 
		}
	logger.info("Finished TC_003_LoginDTT");	
	}

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		XLUtility xlutil = new XLUtility(path); //its constructor which is accepting path of ur excel file
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1); 
		
		String logindata[][] = new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) //i Starts from 1st row as 0th row is having heading
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1st row 0th col of excel is placed in 0th and 1st position of 2d array
			}
		}
		return logindata;
	}
		
		
		
	}
	

