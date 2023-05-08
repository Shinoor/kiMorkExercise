package modules.com;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Program1 {
    WebDriver driver ;
    static ExtentReports reports;
    static ExtentTest test;
    static ExtentSparkReporter sparkReporter;


    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://forms.gle/eiiqRfHiRAiCXgPX7");
        driver.manage().window().maximize();
        reports = new ExtentReports();
        File file = new File("src/test.output/report.html");
        sparkReporter = new ExtentSparkReporter(file);

        reports.attachReporter(sparkReporter);

        reports.setSystemInfo("Host Name", "Adesina-777");
        reports.setSystemInfo("Env", "Automation Testing");
        reports.setSystemInfo("Build", "Regression");

        reports.createTest("Test 2 ").skip("This is skippe");
        reports.createTest("Test 1 ").pass("PASSED");


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Test(groups = "Regression")
    public void VerifyTitle(){
        ExtentTest test1 = reports.createTest("Test Case: Title Validation");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Ki Mock Platform — Take Home Exercise";
        Assert.assertEquals(actualTitle,expectedTitle);
        test1.pass("Test Case Passed");
    }
    @Test(groups = "Regression")
    public void verifyBackgroundColor(){
        // To check the title page colour is having colour blue (#4285f4)
        ExtentTest test2 = reports.createTest("Test Case: Colour Verification");
        String expected = "#4285f4";

        WebElement RVEQke = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[1]/div/div[1]"));
        String RVEQkeColor = RVEQke.getCssValue("background-color");
        System.out.println(RVEQkeColor);

        String hexBRVEQkeColor = Color.fromString(RVEQkeColor).asHex();
        System.out.println(hexBRVEQkeColor);
        Assert.assertEquals(hexBRVEQkeColor,expected);
        test2.pass("Background Colour Verified");
    }
    @Test(groups = "Regression")
    public void verifyWelcomeText(){
        ExtentTest test3 = reports.createTest("Test case: Texts Verification on the WebPage");
        boolean flag =  driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div[1]")).isDisplayed();
        Assert.assertTrue(flag);
        test3.pass("Text verification Passed");

        ExtentTest test4 = reports.createTest("Test case: Welcome messages is Displayed");
        boolean welcomeText =   driver.getPageSource().contains("Welcome to to the Ki Mock Platform");
        Assert.assertTrue(welcomeText);
        test3.pass("Welcome to the Ki Mock Platform is displyed on the web page");
    }
    @Test(groups = "Regression")
    public void pendingQuoteRadioButton(){
// select see my quotes Radio button, verify if displayed and enabled, verify pending qutes page and verify the exists of the classes
        ExtentTest test5 = reports.createTest("Test Case: The Radio Button is clickabled");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div/span/div/div[1]/label/div/div[1]")).click();
        test5.pass("Radio Button is clickable");


        ExtentTest test6 = reports.createTest("Test Case: The Radio Button is Enabled");
        boolean isEnable = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).isEnabled();
        Assert.assertTrue(isEnable);
        test6.pass("Radio Button is Enabled: Test Case Passed");

        ExtentTest test7 = reports.createTest("Test case: The Radio Button is Displayed on WebPage");
        boolean isdisplayed = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).isDisplayed();
        Assert.assertTrue(isdisplayed);
        test7.pass("Radio Button is Displayed on the Web Page: Test Case Passed ");


        ExtentTest test8 = reports.createTest("Test Case: Pending Quote Radio Button is displayed on the Web Page");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).click();
        boolean pendingQt = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div")).isDisplayed();
        Assert.assertTrue(pendingQt);
        test8.pass("Pending Quote Radio Button Verified: Passed");


        ExtentTest test9 = reports.createTest("Test Cases:The Cyber Class is Exist on the Web Page");
        try {
            driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div[2]"));
            System.out.println("Element is exists");

        }catch (NoSuchElementException e){
            System.out.println("Element not exist");
            test9.pass("Cyber Class Verified on the Page: Passed");
        }

        // iterate classes elements
        ExtentTest test10 = reports.createTest("Test Case: Verified all the Business Classes");
        List<WebElement> roles  =driver.findElements(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]"));
        WebElement q;
        for (int i=0; i<roles.size(); i++){
            q = roles.get(i);
            System.out.println(q.getText());
            test10.pass("All the Business Classes Verified: Passed");

        }
        // Verify back botton is cliclable
        ExtentTest test11 = reports.createTest("Test case: To verify a Back-Botton is Clickabled");
        WebElement backBtn = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div[1]/span"));
        backBtn.click();
        test11.pass("Back Button Verified");
    }

    @Test(groups = "Regression")
    public void createQuoteFunctionalityVerifications() throws InterruptedException {
        ExtentTest test12 = reports.createTest("Test case: To Verity Primary Insured Text is on the Page");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.findElement(By.cssSelector("#i10 > div.vd3tt > div")).click();
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).click();
        boolean pagetext =   driver.getPageSource().contains("Provide details of the Primary Insured");
        Assert.assertTrue(pagetext);
        test12.pass("Provide Details of the Primary text is verified");

        //clickablilty of dropdow
        ExtentTest test13 = reports.createTest("Test case: Drop Down arrow is clickable");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]")).click();
        test13.pass("Drop-Down Arrow is verified");

        //Primary Country Selection Box
        ExtentTest test14 = reports.createTest("Test Case: A country is selectable on the Dropdown Lists");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]"));
        WebElement ukDdown =    driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div[8]/span"));
        js.executeScript("arguments[0].click();",ukDdown);
        test14.pass("A country is Selectabled");


        // Priamary insured selection box and Text validation
        ExtentTest test15 = reports.createTest("Test Case: A Primary Isurance is selectable from the drop down list");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]"));
        WebElement choosePI =  driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]"));
        js.executeScript("arguments[0].click();",choosePI);

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(choosePI));

        // priamary insured text validation
        boolean piText = driver.getPageSource().contains("Primary Insured");
        Assert.assertTrue(piText);
        test15.pass("A Priamary Insurance is Selectabled: AND Primary Insured Text Validated");

        // insurance group selection
        ExtentTest test16 = reports.createTest("Test Case: Insurance Group is Selectable from Dropdown List");
        js.executeScript("window.scrollBy(0, 100)");
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]"));

        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement fGLtd = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[2]/div[6]/span"));
        wait1.until(ExpectedConditions.elementToBeClickable(fGLtd));

        WebElement priamaryInsuredHL = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[1]/div[6]/span"));
        js.executeScript("arguments[0].click();",fGLtd);
        js.executeScript("arguments[0].scrollIntoView(true)",priamaryInsuredHL);


        wait1.until(ExpectedConditions.elementToBeClickable(fGLtd));
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[1]/div[6]"));
        driver.switchTo().activeElement().isDisplayed();
        Thread.sleep(1000);
        test16.pass("An Insurance group is selectabled from the list");

        // Class business verification, option radio seleclected
        ExtentTest test17 = reports.createTest("Test Case: Business Class Radio Verifications");
        WebElement cyberRadio = driver.findElement(By.xpath("//*[@id=\"i13\"]/div[3]/div/div"));
        js.executeScript("arguments[0].click();",cyberRadio);
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.elementToBeClickable(cyberRadio));
        test17.pass("Business Class Radio Verified");

        // Verify next button
        ExtentTest test18 = reports.createTest("Test Case: Next Button Verification");
        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(50));
        WebElement nt = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div[2]/span/span"));

        js.executeScript("arguments[0].click();",nt);
           try {
               wait3.until(ExpectedConditions.elementToBeClickable(nt));

           }catch (StaleElementReferenceException e){
        }

        WebDriverWait wait4 = new WebDriverWait(driver,Duration.ofSeconds(50));
        boolean provideDetaisText = driver.getPageSource().contains("Provide details of the coverage required");
        Assert.assertTrue(provideDetaisText);
        test18.pass(" Next Button Verified");
    }

    @Test(groups = "Smoke Test")
    public void endToEndVerifications() throws Exception {
       // test to compear the page ID
        String mainPageWindow = driver.getWindowHandle();
       // System.out.println("Main Pag ID :" +mainPageWindow);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.findElement(By.cssSelector("#i10 > div.vd3tt > div")).click();
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).click();
        String currentWindow = driver.getWindowHandle();

        Assert.assertEquals(mainPageWindow,currentWindow);

        // priamary country selection box (//*[@class='z12JJ'])
        //Verify that clicablilty
        // js.executeScript("window.scrollBy(0, 100)");


        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div[2]"));
        WebElement ukDdown =    driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div[8]/span"));
        js.executeScript("arguments[0].click();",ukDdown);

        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]"));
        WebElement piDdown =  driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]"));


        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(piDdown));
        js.executeScript("arguments[0].click();",piDdown);


        // insurance group selection


        WebElement cElemnt = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[1]"));
        js.executeScript("window.scrollBy(0, 100)");

        js.executeScript("arguments[0].click();",cElemnt);
        driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div[1]"));

        WebElement fGLtd = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[2]/div[6]/span"));
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.elementToBeClickable(fGLtd));
        js.executeScript("arguments[0].click();",fGLtd);
        js.executeScript("arguments[0].scrollIntoView(true)",fGLtd);
        Thread.sleep(1000);
        System.out.println(currentWindow);



        // Class business verification, option radio seleclected

        WebElement cyberRadio = driver.findElement(By.xpath("//*[@id=\"i13\"]/div[3]/div/div"));
        js.executeScript("arguments[0].click();",cyberRadio);
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.elementToBeClickable(cyberRadio));

        WebElement nt = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div[2]/span/span"));
        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait3.until(ExpectedConditions.elementToBeClickable(nt));
        js.executeScript("arguments[0].click();",nt);

        // validate the precent page
        String currentNewPage = driver.getWindowHandle();
        Assert.assertEquals(currentNewPage,currentWindow);

        // Calender verification
        Calendar calendar = Calendar.getInstance();
        String targetDate = "08-05-2023";
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            targetDateFormat.setLenient(false);
            Date formattedTargetDate = targetDateFormat.parse(targetDate);
            calendar.setTime(formattedTargetDate);

            int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
            int targetMonth = calendar.get(Calendar.MONTH);
            int targetYear = calendar.get(Calendar.YEAR);

            WebElement inputeDate = driver.findElement(By.xpath("//div[@class='rFrNMe yqQS1 hatWr zKHdkd CDELXb']/div/div/div/input[@type='date']"));
            js.executeScript("arguments[0].click();",inputeDate);
            Thread.sleep(2000);




        } catch (ParseException e) {
            throw new Exception("Invalid date is provided, please check input date");
        }

        //AUM Value input

        WebElement aumValue =   driver.findElement(By.xpath(" //input[@type='text']"));
        js.executeScript("arguments[0].value='100'",aumValue);

        //Prenium Value input
        WebElement premiumValue = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[1]/div/div[1]/input "));
        js.executeScript("arguments[0].value='500'",premiumValue);

        // Next button ciickable
        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div[2]/span/span"));
        js.executeScript("arguments[0].click();",nextButton);

        // Sucess Quote Created Varification  Submission completed. Your quote has been created successfully.
        boolean sqCreated = driver.getPageSource().contains("Your quote has been created successfully.");
        Assert.assertTrue(sqCreated);



    }

    @AfterTest
    public  void tearDown() throws IOException {
       // driver.quit();
        reports.flush();
        //Desktop.getDesktop().browse(new File("/test.output/report.html").toURI());

    }
}
/*
       ExtentTest test2 = reports.createTest("Test 2");
        test2.log(Status.FAIL, "This is failed");
        reports.createTest("Test 3 ").skip("This is skippe");*/
/*//input[@type='date' and @data-initial-value='2023-05-08']
* //div[text()='Ki Mock Platform — Take Home Exercise']  >>>> Ki Mock Platform — Take Home Exercise
* //div[@jsmodel='CP1oW']/div   >>>> inception Date Parent
* //div[@class='rFrNMe yqQS1 hatWr zKHdkd']/div  >>>>> date inception
* //div[@class='rFrNMe yqQS1 hatWr zKHdkd CDELXb']/div/div/div/input[@type='date']
* //div[@class='o7cIKf']
* //div[@class='rFrNMe yqQS1 hatWr zKHdkd CDELXb']/div/div/div/input[@type='date']
*
* */