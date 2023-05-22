package adequateshop.api_testing.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AventstackReportManager implements ITestListener {
// to run with, configure as listener in testng.xml file
    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    String reportName;

    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("HH.mm/dd.MM.yyyy").format(new Date());
        reportName = "Test Report " + timestamp + ".html";
        extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        extentSparkReporter.config().setDocumentTitle("Rest-AssuredProject");
        extentSparkReporter.config().setReportName("Adequate Shop API methods");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", "Adequate Shop API methods");
        extentReports.setSystemInfo("Operating system", System.getProperty("os.name"));
        extentReports.setSystemInfo("UserName", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "123");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS, "[nice] Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL, "[bad] Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.SKIP, "[] Test Skipped");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}