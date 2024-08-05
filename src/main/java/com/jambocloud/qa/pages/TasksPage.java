package com.jambocloud.qa.pages;

import com.jambocloud.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TasksPage extends TestBase {

    //Page Objects
    @FindBy(id = "keyword")
    WebElement keyword;

    @FindBy(xpath = "//div[contains(text(), 'Due Date')]/label[contains(text(), 'All')]/input[@type='radio']")
    WebElement dueDateAll;

    @FindBy(xpath = "//div[contains(text(), 'Due Date')]/label[contains(text(), 'Today')]/input[@type='radio']")
    WebElement dueDateToday;

    @FindBy(xpath = "//div[contains(text(), 'Due Date')]/label[contains(text(), 'This Week')]/input[@type='radio']")
    WebElement dueDateThisWeek;

    @FindBy(xpath = "//div[contains(text(), 'Due Date')]/label[contains(text(), 'This Month')]/input[@type='radio']")
    WebElement dueDateThisMonth;

    @FindBy(xpath = "//div[contains(text(), 'Due Date')]/label[contains(text(), 'Custom')]/input[@type='radio']")
    WebElement dueDateCustom;

    @FindBy(id = "always-show-overdue-tasks")
    WebElement alwaysShowOverdueTasks;

    @FindBy(xpath = "//div[contains(text(), 'Assigned To')]/label[contains(text(), 'All')]/input[@type='radio']")
    WebElement assignedToAll;

    @FindBy(xpath = "//div[contains(text(), 'Assigned To')]/label[contains(text(), 'Me')]/input[@type='radio']")
    WebElement assignedToMe;

    @FindBy(xpath = "//div[contains(text(), 'Assigned To')]/label[contains(text(), 'Unassigned')]/input[@type='radio']")
    WebElement assignedToUnassigned;

    @FindBy(xpath = "//div[contains(text(), 'Assigned To')]/label[contains(text(), 'Custom')]/input[@type='radio']")
    WebElement assignedToCustom;

    @FindBy(id = "priority-low")
    WebElement priorityLow;

    @FindBy(id = "priority-medium")
    WebElement priorityMedium;

    @FindBy(id = "priority-high")
    WebElement priorityHigh;

    @FindBy(xpath = "//div[contains(text(), 'Status')]/label[contains(text(), 'Not Complete')]/input[@type='radio']")
    WebElement statusNotComplete;

    @FindBy(xpath = "//div[contains(text(), 'Status')]/label[contains(text(), 'Complete')]/input[@type='radio']")
    WebElement statusComplete;

    @FindBy(xpath = "//div[contains(text(), 'Status')]/label[contains(text(), 'All')]/input[@type='radio']")
    WebElement statusAll;

    @FindBy(xpath = "//div[contains(text(), 'Records to be Returned')]/label[contains(text(), 'Active')]/input[@type='radio']")
    WebElement recordsToBeReturnedActive;

    @FindBy(xpath = "//div[contains(text(), 'Records to be Returned')]/label[contains(text(), 'Deleted')]/input[@type='radio']")
    WebElement recordsToBeReturnedDeleted;

    @FindBy(xpath = "//div[contains(text(), 'Records to be Returned')]/label[contains(text(), 'All')]/input[@type='radio']")
    WebElement recordsToBeReturnedAll;

    @FindBy(id = "tasks-grid")
    WebElement tasksGrid;

    @FindBy(id = "selected-task-view")
    WebElement selectedTaskView;


    //Initializing the page objects
    public TasksPage() {
        PageFactory.initElements(driver, this);
    }

    //Methods
    public WebElement getTasksGrid() {
        return tasksGrid;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void enterKeyword(String taskName) {
        keyword.sendKeys(taskName);
    }

    public void selectDueDate(String option) {
        switch (option) {
            case "All":
                dueDateAll.click();
                break;
            case "Today":
                dueDateToday.click();
                break;
            case "This Week":
                dueDateThisWeek.click();
                break;
            case "This Month":
                dueDateThisMonth.click();
                break;
            case "Custom":
                dueDateCustom.click();
                break;
        }
    }

    public void selectAlwaysShowOverdueTasks() {
        alwaysShowOverdueTasks.click();
    }

    public void selectAssignedTo(String option) {
        switch (option) {
            case "All":
                assignedToAll.click();
                break;
            case "Me":
                assignedToMe.click();
                break;
            case "Unassigned":
                assignedToUnassigned.click();
                break;
            case "Custom":
                assignedToCustom.click();
                break;
        }
    }

    public void selectLowPriority() {
        priorityLow.click();
    }

    public void selectMediumPriority() {
        priorityMedium.click();
    }

    public void selectHighPriority() {
        priorityHigh.click();
    }

    public void selectStatus(String option) {
        switch (option) {
            case "Not Complete":
                statusNotComplete.click();
                break;
            case "Complete":
                statusComplete.click();
                break;
            case "All":
                statusAll.click();
                break;
        }
    }

    public void selectRecordsToBeReturned(String option) {
        switch (option) {
            case "Active":
                recordsToBeReturnedActive.click();
                break;
            case "Deleted":
                recordsToBeReturnedDeleted.click();
                break;
            case "All":
                recordsToBeReturnedAll.click();
                break;
        }
    }

    /**
     * Obtain an array of task ids from the result grid
     *
     * @return array of task ids
     */
    public String[] tasksGridResultTaskIds() {
        //TODO: Return result as a list of object for further verification. Right now it returns an array of task ids.
        List<WebElement> tableRows = tasksGrid.findElements(By.xpath("//table/tr"));
        return (String[]) tableRows.stream().map(a -> a.findElement(By.className("task-id")).getText()).toArray();
    }

    /**
     * Click on the first result of the task grid
     */
    public void clickOnFirstResultOfTheTasksGrid() {
        List<WebElement> tableRows = tasksGrid.findElements(By.xpath("//table/tr"));
        tableRows.get(0).click();
    }

    /**
     * Get result grid row count
     *
     * @return row count
     */
    public int tasksGridResultsCount() {
        List<WebElement> tableRows = tasksGrid.findElements(By.xpath("//table/tr"));
        return tableRows.size();
    }

    /**
     * Checks if task view contains expected values
     *
     * @param taskId     task id
     * @param taskName   task name
     * @param assignedTo assigned to
     * @param priority   priority
     * @param dueDate    due data
     * @param createdBy  created by
     * @return true if all the data contains in the task detail view (right side panel)
     */
    public boolean selectedTaskResultContainsTaskData(String taskId, String taskName, String assignedTo, String priority, String dueDate, String createdBy) {
        //TODO: Return result as an object for further verification
        return selectedTaskView.getText().contains(taskId)
                && selectedTaskView.getText().contains(taskName)
                && selectedTaskView.getText().contains(assignedTo)
                && selectedTaskView.getText().contains(priority)
                && selectedTaskView.getText().contains(dueDate)
                && selectedTaskView.getText().contains(createdBy);
    }


}
