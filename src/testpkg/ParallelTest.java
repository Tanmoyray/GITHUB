package testpkg;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ParallelTest {
	
	//public  AndroidDriver<MobileElement> driver;
	public  AppiumDriver<MobileElement> driver;
	
	public static DesiredCapabilities cap;
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"port","device"})
	public void testsetup(String port,String device) throws MalformedURLException, InterruptedException{
				
		String path = "/Users/tanmoyray/Documents/AndroidApk/Parking/veiculu.apk";
		File file = new File(path);
		
		cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		//cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
							
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		
		cap.setCapability(MobileCapabilityType.APP, file);
		
		driver = new AndroidDriver<>(new URL("http://localhost:"+port+"/wd/hub"), cap);
		
		System.out.println("session id is---"+driver.getSessionId());
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void ValidLoginTest() throws InterruptedException{
		
        driver.findElementById("com.senrysa.parkingplace:id/LoginUserName").sendKeys("9126324813");

        Thread.sleep(5000L);
		driver.findElementById("com.senrysa.parkingplace:id/LoginPassword").sendKeys("123");
		
		driver.tap(1, driver.findElementById("com.senrysa.parkingplace:id/btn_Login"), 1);
		Thread.sleep(9000L);
		
		System.out.println("Test Login");
		
	}
	
	@AfterTest
	public void quit(){
		driver.quit();
	}
	
	
	}
