package com.automation.mobile.listener;

import com.automation.mobile.entities.FileLocations;

/**
 * An enum which holds the properties to be set for extent reporter
 */
public enum ExtentProperties {
    INSTANCE;
    private String reportPath;
    private String extentXServerUrl;
    private String projectName;

    ExtentProperties() {
        //this.reportPath = "output" + File.separator + "Run_" + System.currentTimeMillis() + File.separator
        //        + "report.html";
        this.reportPath = FileLocations.REPORT_DIRECTORY + "/MobileAutomationTestReport.html";
        this.projectName = "default";
    }

    /**
     * Gets the report path
     * @return The report path
     */
    public String getReportPath() {
        return reportPath;
    }

    /**
     * Sets the report path
     * @param reportPath The report path value
     */
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    /**
     * Gets the ExtentX server URL
     * @return The ExtentX server URL
     */
    @Deprecated
    public String getExtentXServerUrl() {
        return extentXServerUrl;
    }

    /**
     * Sets the ExtentX server URL
     * @param extentXServerUrl The ExtentX server URL
     */
    @Deprecated
    public void setExtentXServerUrl(String extentXServerUrl) {
        this.extentXServerUrl = extentXServerUrl;
    }

    /**
     * Gets the project name
     * @return The project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name
     * @param projectName The project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
