package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;     //logging.log4j.core package need to import
    public Properties prop;



    @BeforeClass(groups = "Sanity")
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws IOException {
        //logger
        //logger= (Logger) LogManager.getLogger(this.getClass());

        //Reading properties file
        FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
        prop=new Properties();
        prop.load(file);


        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities desiredCapabilities=new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){
                desiredCapabilities.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("MAC")){
                desiredCapabilities.setPlatform(Platform.MAC);
            }
            else if(os.equalsIgnoreCase("Linux")){
                desiredCapabilities.setPlatform(Platform.LINUX);
            }
            else{
                System.out.println("OS is not correct");
            }
            switch (br.toLowerCase()){
                case "chrome": desiredCapabilities.setBrowserName("chrome"); break;
                case "firefox": desiredCapabilities.setBrowserName("firefox");break;
                case "edge": desiredCapabilities.setBrowserName("MicrosoftEdge");break;
                default:
                    System.out.println("Browser is not correct"); return;
            }
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);

        }
        if(prop.getProperty("execution_env").equalsIgnoreCase("local")){

            switch (br.toLowerCase()){
                case "chrome":driver=new ChromeDriver(); break;
                case "edge":driver=new EdgeDriver(); break;
                case "firefox":driver=new FirefoxDriver(); break;
                default:
                    System.out.println("Invalid Browser"); return;
            }

        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //driver.get("https://tutorialsninja.com/demo");
        driver.get(prop.getProperty("URL"));

        driver.manage().window().maximize();

    }
    @AfterClass(groups = "Sanity")
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber(){
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumber(){
        String generatedNumber= RandomStringUtils.randomNumeric(3);
        String generatedString= RandomStringUtils.randomAlphabetic(3);
        return (generatedString+"@"+generatedNumber);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
