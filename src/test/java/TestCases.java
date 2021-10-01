
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases  extends  BaseTest{


	@Test
	public void test_1_1_homePageControl(){ 
		HomePage homePage=new HomePage(driver);
		driver.get(homePage.getUrl());
		homePage.waitForPageLoad();
		System.out.println("title ="+driver.getTitle());
		Assert.assertTrue(driver.getTitle().equals("LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki"));
		System.out.println("Lc Waikiki Alışveriş sitesi başarılı bir şekilde açıldı");
	}
	
	/**
	 * Login ekranini acip, bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 */
	@Test
	public void test_1_2_loginPage(){ 
		HomePage homePage=new HomePage(driver);
		homePage.getLoginPage();
		Assert.assertTrue(driver.getTitle().equals("Üye Girişi - LC Waikiki"));
		System.out.println("Kullanıcı girişi için sayfa başarılı bir şekilde açıldı...");
	}
	
	/**
	 * Bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 * Burada kullanıcı adını ve şifreyi değiştiribilirsiniz.
	 */
	@Test
	public void test_1_3_signIn(){  
		LoginPage loginPage =new LoginPage(driver);
		loginPage.enterLoginForm("busra-9234@hotmail.com", "Busra123");
		loginPage.submit();
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage.getName().equals("Hesabım"));
		System.out.println("Kullanıcı Girişi Başarılı ...");
	 
	}
	
	/**
	 * Ekranin ustundeki Search alanina 'samsung' yazip Ara butonuna tiklayacak 
	 * Gelen sayfada pantolan icin sonuc bulundugunu onaylayacak
	 * @throws InterruptedException
	 */
	@Test
	public void test_1_4_dataSearch () throws InterruptedException{  
		SearchPage searchPage =new SearchPage(driver);
		searchPage.enterDataForSearch("pantolan");
		searchPage.submitForSearch();
		Thread.sleep(1000);
		searchPage.scrollToTheElement();//Minumum bekleme ile arada oluşabilecek hata önlenmektedir.
		Thread.sleep(1000);
		searchPage.clickSeeMore();
	}


	/**
	 * Ustten 3. urunun icindeki 'favorilere ekle' butonuna tiklayacak 
	 */
	@Test
	public void test_1_5_selectRandomProductAndAddToChart(){
		SearchPage searchPage =new SearchPage(driver);
		searchPage.selectThirdProduct();
		searchPage.addFavoriteProduct();
		System.out.println("Sepete Eklenen Ürün   : "+searchPage.getSelectedFavoriteProduct());
	}
	 
	/**
	 * Ekranin en ustundeki pop-up ile 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_7_clickMyFavorites () {  
		FavouritePage favouritePage =new FavouritePage(driver);
		favouritePage.clickPopupMyFavorite();
		System.out.println("Favoriler için hesap sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Yeni açılan ekranda en ustundeki 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_8_openMyFavorites () {  
		FavouritePage favouritePage =new FavouritePage(driver);
		favouritePage.myFavoriteListShow(); 
		System.out.println("Favorilerim sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Acilan sayfada bir onceki sayfada izlemeye alinmis urunun bulundugunu onaylayacak
	 */
	@Test
	public void test_1_9_findFavoriteProduct () {  
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) { 
			String watchesProduct=productTitle.getText(); 
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) {
				System.out.println("Favoriye Eklenen Ürün Onaylandı.Ürünün Başlığı :"+watchesProduct+"\n"); 
				Assert.assertTrue(watchesProduct.equals(favouritePage.getSelectedFavoriteProduct()));
			}
		} 
	}
	
	/**
	 * Favorilere alinan bu urunun yanindaki 'Kaldir' butonuna basarak, favorilerimden cikaracak
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_1_webSiteDeleteProduct () throws InterruptedException { 
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) {  
			String watchesProduct=productTitle.getText(); 
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) { 
				favouritePage.clickLinkDeleteProduct();
				 Thread.sleep(3000); 
				 Assert.assertTrue(favouritePage.getMessage().equals("Ürününüz listeden silindi.")); 
				 favouritePage.closeContent();  
				System.out.println("Ürününüz listeden başarılı bir şekilde silindi....");

			}
		}  
	}
	
	/**
	 * Sayfada bu urunun artik favorilere alinmadigini onaylayacak. 
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_2_deleteConfirmationForProduct () {
		boolean controlValue=true;
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) {   
			String watchesProduct=productTitle.getText(); 			
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) 
				controlValue=false;
		}  
		Assert.assertFalse(controlValue);
		System.out.println("Ürününüz favorilerde yer almıyor....");
 
	}

}
