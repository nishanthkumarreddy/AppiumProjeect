package learning.AppiumFramwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemoApp extends base{
	
	
	@BeforeTest
	public void checktheServer() throws IOException, InterruptedException {
		//Kill if any servers are running
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
		
	}
	
	
	@Test(dataProvider="inputdata", dataProviderClass=Testdataprovider.class)
	public void apiDemoPract(String input) throws IOException, InterruptedException {
		service = startServer();
		
		AndroidDriver<AndroidElement> instancedriver = Capabilities("APIappName");
		instancedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		HomePage h = new HomePage(instancedriver);
		h.Preferences.click();
		
		
		PreferencePage ps = new PreferencePage(instancedriver);
		ps.Preferences.click();
		
		
		DependencyPage dp = new DependencyPage(instancedriver);
		dp.PrefCheckbox.click();
		dp.Layoutclick.click();
		dp.getHellotext().sendKeys(input);
		dp.ClickButton.get(1).click();
		
		service.stop();
		
	}

}
