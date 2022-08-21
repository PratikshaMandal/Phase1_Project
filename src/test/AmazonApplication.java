package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Initialize driver

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		 //Launch Amazon.in site
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
        
        //Getting Parent Window Handler
        String ParentWindow = driver.getWindowHandle();
        
        //Search for Samsung product
        WebElement SearchInput = driver.findElement(By.id("twotabsearchtextbox"));
        SearchInput.sendKeys("Samsung");

        //Click on the Search Button
        WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
        SearchButton.click();

        //Print Product Details and Price

        List<WebElement> ProductList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
        System.out.println("Total Number of Products are " + ProductList.size());
        List<WebElement> ProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

        for(int index=0;index<ProductList.size();index++) {

            System.out.print(ProductList.get(index).getText() + "Price : Rupees ");
            System.out.println(ProductPrice.get(index).getText());
        }

        // Click On The First Product Link
        
        ProductList.get(0).click();
        String toValidate = ProductList.get(0).getText();
        System.out.println(toValidate);

        //Validation on parent and child windows

        Set<String> AllWindowHandles = driver.getWindowHandles();
        System.out.println("Before Clicking Tab Button The Window Is " + ParentWindow);

        for(String window:AllWindowHandles){
            if(!window.equals(ParentWindow)){
                driver.switchTo().window(window);
            }
        }
        WebElement HeadingOfNewTab = driver.findElement(By.xpath("//div[@id='title_feature_div']//span"));
        String HeaderString = HeadingOfNewTab.getText();
        System.out.println(HeaderString);

        if(HeaderString.equals(toValidate)){
            System.out.println("Title Match With Expected");
        }else {
            System.out.println("Title Not Match With Expected");

		}
		driver.quit();

	}


}
