import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "LoginEmail")
	private WebElement email;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(id = "loginLink")
	private WebElement loginButton;
	
	@FindBy(className = "header-icon-label")
	private WebElement name;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	//Login formunu doldurmak için 
	public void enterLoginForm(String email, String password) {
		this.email.clear();
		this.email.sendKeys(email);

		this.password.clear();
		this.password.sendKeys(password);
	}

	//Login olmak için button click  
	public void submit() {
		loginButton.click(); 
	}

	 public String getName(){
			return name.getText();
	 }
}
