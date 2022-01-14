package WebHadir.glue;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import WebHadir.config.AutomationFrameworkConfiguration;
import WebHadir.driver.DriverSingleton;
import WebHadir.pages.Login;
import WebHadir.pages.ReportingKaryawan;
import WebHadir.utils.ConfigurationProperties;
import WebHadir.utils.Constants;
import WebHadir.utils.TestCases;
import WebHadir.utils.Utils;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {

	private WebDriver driver;
	private Login login;
	private ReportingKaryawan reportingKaryawan;
	ExtentTest extentTest;
	static ExtentReports report = new ExtentReports("src/main/resources/Report.html");
	
	@Autowired
	ConfigurationProperties configurationProperties;
	
	@Before
	public void initializeObjects() {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		login = new Login();
		reportingKaryawan = new ReportingKaryawan();
		TestCases[] tests = TestCases.values();
		extentTest = report.startTest(tests[Utils.testCount].getTestName());
		Utils.testCount++;
	}
	
	///////////// LOGIN /////////////////////
	
	@Given("User go to the website")
	public void User_go_to_the_website() {
		driver = DriverSingleton.getDriver();
		driver.get(Constants.URL);
		extentTest.log(LogStatus.PASS, "Navigating to "+ Constants.URL);
	}	
	
	@When("User input username password and click SignIn")
	public void User_input_username_password_and_click_SignIn() {
		login.inputLogin(configurationProperties.getUsernameLogin(), configurationProperties.getPasswordLogin());
		extentTest.log(LogStatus.PASS, "User input username password and click login");
	}
	
	
	///////////// SEARCH BY /////////////////////
	
	@Given("User go to the reporting karyawan page")
	public void User_go_to_the_reporting_karyawan_page() {
		reportingKaryawan.gotoReportingKaryawan();
		extentTest.log(LogStatus.PASS, "User go to the reporting karyawan page");
	}
	
	@When("User click dropdown list for NIK")
	public void User_click_dropdown_list_for_NIK() {
		reportingKaryawan.klikDropdownList();
		extentTest.log(LogStatus.PASS, "User click dropdown list for NIK");
	}
	
	@And("User click list NIK")
	public void User_click_list_NIK() {
		reportingKaryawan.klikListNik();
		extentTest.log(LogStatus.PASS, "User click list NIK");
	}
	
	@And("User input NIK in form search and click button search")
	public void User_input_NIK_in_form_search_and_click_button_search() throws InterruptedException {
		reportingKaryawan.searchByNik(configurationProperties.getInNIK());
		extentTest.log(LogStatus.PASS, "User input NIK in form search and click button search");
	}
	
	@When("User click dropdown list for Name")
	public void User_click_dropdown_list_for_Name() {
		reportingKaryawan.klikDropdownList();
		extentTest.log(LogStatus.PASS, "User click dropdown list for Name");
	}
	
	@And("User click list name")
	public void User_click_list_name() {
		reportingKaryawan.klikListName();
		extentTest.log(LogStatus.PASS, "User click list name");
	}
	
	@And("User input name in form search and click button search")
	public void User_input_name_in_form_search_and_click_button_search() throws InterruptedException {
		reportingKaryawan.searchByName(configurationProperties.getInName());
		extentTest.log(LogStatus.PASS, "User input name in form search and click button search");
	}
	
	@When("User click dropdown list for Leader")
	public void User_click_dropdown_list_for_Leader() {
		reportingKaryawan.klikDropdownList();
		extentTest.log(LogStatus.PASS, "User click dropdown list for Leader");
	}
	
	@And("User click list leader")
	public void User_click_list_leader() {
		reportingKaryawan.klikListName();
		extentTest.log(LogStatus.PASS, "User click list leader");
	}
	
	@And("User input leader in form search and click button search")
	public void User_input_leader_in_form_search_and_click_button_search() throws InterruptedException {
		reportingKaryawan.searchByName(configurationProperties.getInLeader());
		extentTest.log(LogStatus.PASS, "User input leader in form search and click button search");
	}
	
	
	@After
	public void closeObject() {
		report.endTest(extentTest);
		report.flush();
	}
	
}
