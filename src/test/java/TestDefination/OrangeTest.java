package TestDefination;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrangeTest {
	static WebDriver driver;
	public static String empId;
	
	@Given("user open a browser")
	public void user_open_a_browser() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user enters valid username and valid password")
	public void user_enters_valid_username_and_valid_password() {
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
	}

	@Then("user click on login button")
	public void user_click_on_login_button() {
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
	}

	@Then("user validte home page title")
	public void user_validte_home_page_title() {
		String Title = driver.getTitle();
		Assertions.assertEquals(Title, "OrangeHRM");
	}

	@Then("user validate home page url")
	public void user_validate_home_page_url() {
		String Url = driver.getCurrentUrl();
		Assertions.assertEquals(Url.contains("orangehrm"), true);
	}

	@Then("user validate home page logo")
	public void user_validate_home_page_logo() {
		boolean actualLogo = driver.findElement(By.xpath("//div[@class='oxd-brand-banner']")).isDisplayed();
		Assertions.assertEquals(actualLogo, true);
	}

	@Given("user click on PIM link")
	public void user_click_on_pim_link() {
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
	}

	@When("user click on add employee button")
	public void user_click_on_add_employee_button() {
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

	}

	@When("user enter firstName as {string} , middleName as {string} , lastName as {string} and capture the employee id")
	public void user_enter_first_name_as_middle_name_as_last_name_as_and_capture_the_employee_id(String firstname, String middlename, String lastname) {
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("middleName")).sendKeys(middlename);
		driver.findElement(By.name("lastName")).sendKeys(lastname);
	
		empId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/parent::div/descendant::input[@class='oxd-input oxd-input--active']")).getAttribute("value");
	
	}

	@Then("user click on save button")
	public void user_click_on_save_button() throws InterruptedException {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
	

	
	}
	
	@When("user select gender as {string}")
	public void user_select_gender_as(String gender) throws InterruptedException {
		WebElement wb = driver.findElement(By.xpath("//label[text()='Employee Id']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",wb);
		Thread.sleep(3000);
		
	List<WebElement>gen=driver.findElements(By.xpath("//div[@class='--gender-grouped-field']/descendant::input[@type='radio']"));
	for(WebElement abc:gen) {
		String p = abc.getText();
		if(p.equalsIgnoreCase(gender)) {
			abc.click();
			Thread.sleep(3000);
			break;
			
		}
	}
	
	}

	@When("user enter the employee id")
	public void user_enter_the_employee_id() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/parent::div/descendant::input[@class='oxd-input oxd-input--active']")).sendKeys(empId);
		Thread.sleep(3000);
	}

	@Then("user click on search button")
	public void user_click_on_search_button() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
		
		
	}

	@Then("user delete employee and confirm delete")
	public void user_delete_employee_and_confirm_delete() throws InterruptedException {
		//WebElement wb1 = driver.findElement(By.xpath("//span[text()=' (179) Records Found']"));
		//JavascriptExecutor js1 = (JavascriptExecutor)driver;
		//js1.executeScript("arguments[0].scrollIntoView();",wb1);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();
	}

	@When("user click on profile icon")
	public void user_click_on_profile_icon() {
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
	}

	@Then("user click on logout")
	public void user_click_on_logout() {
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
}
