package learning.AppiumFramwork;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DependencyPage {
	
	public DependencyPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id = "android:id/checkbox")
	public WebElement PrefCheckbox;
	
	
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	public WebElement Layoutclick;
	
	@AndroidFindBy(id = "android:id/edit")
	private WebElement Hellotext;
	
	@AndroidFindBy(className ="android.widget.Button")
	public List<WebElement> ClickButton;
	
	
	//if we want to put any restrictions/ log
	public WebElement getHellotext() {
		
		System.out.println("to send the text");
		return Hellotext;
	}

}
