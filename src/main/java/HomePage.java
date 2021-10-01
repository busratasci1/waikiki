import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  {
		
	  private static String webUrl="https://www.lcwaikiki.com/tr-TR/TR";
	 
	  
	  @FindBy(id = "header-user-section")
	  private WebElement btnSignIn;
	  
	  @FindBy(id = "loginLink")
	  private  WebElement  loginButton;
	  
	  public HomePage(WebDriver driver) {
	        super(driver);
	  }
	 
	 public void getLoginPage(){
		 btnSignIn.click();
		 wait.until(elementClickableById(loginButton));
	 }
	 public String getUrl(){
			return webUrl;
	 }
	
	  
}
