package com.jambocloud.qa.tests;

import com.jambocloud.qa.base.TestBase;
import com.jambocloud.qa.pages.TasksPage;
import com.jambocloud.qa.utils.ExcelUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TasksTest extends TestBase {

    TasksPage tasksPage;

    private ExcelUtil excelUtil;

    @BeforeClass
    public void setupClass() {
        excelUtil = new ExcelUtil("test-data/JamboCloud-FilterPanel-TestData.xlsx", "Sheet1");
    }

    @BeforeMethod
    public void setupMethod() {
        initialization();
        tasksPage = new TasksPage();
    }

    @Test(priority = 1)
    public void taskPageTitleTest() {
        String title = tasksPage.getTitle();
        Assert.assertEquals(title, "Tasks");
    }

    @Test(priority = 2, dataProvider = "filterTestData")
    public void filterPanelTest(
            String keyword,
            String dueDate,
            String alwaysShowOverdueTasks,
            String assignedTo,
            String priorityLow,
            String priorityMedium,
            String priorityHigh,
            String status,
            String recordsToBeReturned,
            String assertTaskIds,
            String testCaseId
    ) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30 seconds timeout

        if (!keyword.isEmpty()) {
            tasksPage.enterKeyword(keyword);
        }

        if (!dueDate.isEmpty()) {
            tasksPage.selectDueDate(dueDate);
        }

        if (alwaysShowOverdueTasks.equals("1")) {
            tasksPage.selectAlwaysShowOverdueTasks();
        }

        if (!assignedTo.isEmpty()) {
            tasksPage.selectAssignedTo(assignedTo);
        }

        if (priorityLow.equals("1")) {
            tasksPage.selectLowPriority();
        }

        if (priorityMedium.equals("1")) {
            tasksPage.selectMediumPriority();
        }

        if (priorityHigh.equals("1")) {
            tasksPage.selectHighPriority();
        }

        if (!status.isEmpty()) {
            tasksPage.selectStatus(status);
        }

        if (!recordsToBeReturned.isEmpty()) {
            tasksPage.selectRecordsToBeReturned(recordsToBeReturned);
        }

        // Wait until the task grid is loaded and the specific task is present
        wait.until(ExpectedConditions.visibilityOf(tasksPage.getTasksGrid()));

        int resultCount = tasksPage.tasksGridResultsCount();
        Assert.assertEquals(resultCount, assertTaskIds.length(), "Result grid task count not matched");

        String[] actualTaskIds = tasksPage.tasksGridResultTaskIds();
        Assert.assertEquals(actualTaskIds, assertTaskIds.split(","), "Expected tasks not matched with result grid");
    }

    @DataProvider(name = "filterTestData")
    public Object[][] getData() {
        int rowCount = excelUtil.getRowCount();
        Object[][] data = new Object[rowCount - 1][11];

        for (int i = 1; i < rowCount; i++) { // Skipping the header row
            data[i - 1][0] = excelUtil.getCellData(i, 1); // keyword
            data[i - 1][1] = excelUtil.getCellData(i, 2); // dueDate
            data[i - 1][2] = excelUtil.getCellData(i, 3); // alwaysShowOverdueTasks
            data[i - 1][3] = excelUtil.getCellData(i, 4); // assignedTo
            data[i - 1][4] = excelUtil.getCellData(i, 5); // priorityLow
            data[i - 1][5] = excelUtil.getCellData(i, 6); // priorityMedium
            data[i - 1][6] = excelUtil.getCellData(i, 7); // priorityHigh
            data[i - 1][7] = excelUtil.getCellData(i, 8); // status
            data[i - 1][8] = excelUtil.getCellData(i, 9); // recordsToBeReturned
            data[i - 1][9] = excelUtil.getCellData(i, 10); // assertTaskIds
            data[i - 1][10] = excelUtil.getCellData(i, 0); // test_case_id
        }

        return data;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
