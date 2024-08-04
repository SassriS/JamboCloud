package com.jambocloud.qa.tests;

import com.jambocloud.qa.base.TestBase;
import com.jambocloud.qa.pages.TasksPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TasksTest extends TestBase {

    TasksPage tasksPage;

    @BeforeMethod
    public void setUp(){
        initialization();
        tasksPage = new TasksPage();
    }

    @Test(priority=1)
    public void loginPageTitleTest(){
        String title = tasksPage.getTitle();
        Assert.assertEquals(title, "Tasks");
    }

    @Test(priority=2)
    public void filterPanelTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30 seconds timeout

        tasksPage.enterKeyword("T-2");
        //TODO: Complete rest of the actions
        tasksPage.selectDueDate("This Month");
        tasksPage.selectAlwaysShowOverdueTasks();
        tasksPage.selectAssignedTo("Unassigned");
        tasksPage.selectHighPriority();
        tasksPage.selectStatus("Not Complete");
        tasksPage.selectRecordsToBeReturned("Active");
        //TODO: Add a wait
        // Wait until the task grid is loaded and the specific task is present
        wait.until(ExpectedConditions.visibilityOf(tasksPage.getTasksGrid()));

        boolean taskId = tasksPage.tasksGridResultContainsTaskByTaskId("T-2");
        Assert.assertTrue(taskId, "Incorrect task ID found");

        int resultCount = tasksPage.tasksGridResultsCount();
        Assert.assertEquals(resultCount, 1);

        tasksPage.clickOnFirstResultOfTheTasksGrid();

        boolean containsData = tasksPage.selectedTaskResultContainsTaskData("T-2", "Prepare Monthly Report", "John", "High", "10-08-2024", "Alice");
        Assert.assertTrue(containsData, "Incorrect task details found");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



}
