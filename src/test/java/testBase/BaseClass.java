package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.XLUtility;

public class BaseClass {

	public WebDriver driver;
	public Logger logger; //for logging Log4j
	public ResourceBundle rb;
	@BeforeClass(groups = {"master","sanity","regression"}) 
	@Parameters({"browser"})
	public void setup(String br)
	{	
		logger = LogManager.getLogger(this.getClass());
		
		rb = ResourceBundle.getBundle("config");
		
		
		//Drivers
		
		if(br.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		logger.info("Launched chrome browser");
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			logger.info("Launched edge browser");
			
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			logger.info("Launched edge browser");
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown()
	{
	driver.close();
	}
	
	
	public String randomestring() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	public int randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString2));
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+".png");
		FileUtils.copyFile(source, target);
	}
	
	
	public String[][] getDataDTT(String path) throws IOException
	{
		
		
		XLUtility xlutil = new XLUtility(path); //its constructor which is accepting path of ur excel file
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1); 
		
		String Arraydata[][] = new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) //i Starts from 1st row as 0th row is having heading
		{
			for(int j=0;j<totalcols;j++)
			{
				Arraydata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1st row 0th col of excel is placed in 0th and 1st position of 2d array
			}
		}
		return Arraydata;
	}
}