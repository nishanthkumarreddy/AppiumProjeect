package learning.AppiumFramwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	
	public static AppiumDriverLocalService service;
	
	
	public AppiumDriverLocalService startServer() {
		
		boolean flg = chekIfServerisRunning(4723);
		if(!flg) {
		service =  AppiumDriverLocalService.buildDefaultService();
		service.start();
		}
		
		return service;
	}
	
	public static void emulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+ "/src/main/java/learning/AppiumFramwork/emulator.bat");
		Thread.sleep(6000);
	}
	
	public static boolean chekIfServerisRunning(int port) {
		
		boolean isServerRunning =false;
		ServerSocket serversocket;
		
		try {
			serversocket = new ServerSocket(port);
			serversocket.close();			
		}catch(IOException e) {
			isServerRunning =true;
		}finally {
			serversocket=null;
		}		
		
		return isServerRunning;
		
	}

	public static AndroidDriver<AndroidElement> Capabilities(String ApiName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		FileInputStream fisn =  new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/learning/AppiumFramwork/global.properties");
		Properties prop = new Properties();
		prop.load(fisn);
		
		File Apidir = new File("src/main/java");
		File fs = new File(Apidir, (String) prop.get(ApiName));
				
		DesiredCapabilities dc = new DesiredCapabilities();
		//if we hvae virtual devices/emulators, we have give specific name of that emulator
		String emulat = (String) prop.get("device");
		
		if(emulat.equalsIgnoreCase("NishanthEmulator2")){
			//start emulator
			emulator();
		}
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, emulat);
		
		//if we connect to real device using USB cable/ any third party apps then we need to write below code
		//we can run the same scripts in any device after connecting if it's emulator/real device	
			//dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		
		
		
		
		//this is the new code which we need to mention to run android devices - uiautomator2
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");//new step
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		AndroidDriver<AndroidElement> driver = 
				new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		
		return driver;
		

	}

}
