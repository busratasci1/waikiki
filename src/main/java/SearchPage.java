import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPage extends BasePage {

	@FindBy(className = "search")
	private WebElement searchData;
	
	@FindBy(className = "search-button")
	private WebElement searchBtn;

	@FindBy(className = "lazy-load-button")
	private WebElement seeMoreButton;
	
	//Arama sonucu
	@FindBy(xpath = "//div[@class='row c-items']//*")
	private WebElement countResult;
	
	//Arama sonuçlarından listedeki 3. ürün
	@FindBy(xpath = "//li[3]/div/div[2]/span[2]")
	private WebElement thirdProductInList;
	
	//3.ürün isimi
	@FindBy(xpath = "//li[3]/div/div/a/h3")
	private WebElement thirdProductName;

	//3.ürünün favori ekle buttonu
	@FindBy(xpath = "//li[3]/div/div[2]/span[2]")
	private WebElement favoriAddButton;
			
	public SearchPage(WebDriver driver) {
		super(driver); 
	}
	
	//Arama kısmına veri girmek için
	public void enterDataForSearch(String data) {
		this.searchData.clear();
		this.searchData.sendKeys(data);
	}
	
	//Arama yapmak için
	public void submitForSearch() {
		searchBtn.click(); 
	}

	public void clickSeeMore() {
		seeMoreButton.click();
	}

	public void scrollToTheElement()
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(false);", seeMoreButton);
	}
	public void scrollDownToThePage(){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	//Arama sonucu
	public String getCountResult()
	{
		return countResult.getText();
	}

	//3.Ürünü seç 
	 public void selectThirdProduct(){
		 wait.until(elementClickableByXpad(thirdProductInList));
		 setSelectedFavoriteProduct(thirdProductName.getText()); 
		 favoriAddButton.click(); 
	 }
	
	//Favorilere ekle
	 public void addFavoriteProduct(){ 
		 favoriAddButton.click(); 
	 }
}
