# Webform Automation for DigitalUnite Practice Webform

## Problem Statement:
Automate the submission process of the webform located at [DigitalUnite Practice Webform](https://www.digitalunite.com/practice-webform-learners). The automation script will:
1. Input all required fields.
2. Upload any file (with a maximum file size limit of 2MB).
3. Submit the form.
4. Assert that the expected message `"Thank you for your submission!"` is displayed after a successful form submission.

## Tools:
- **IDE:** IntelliJ IDEA
- **Language:** Java
- **Build Tool:** Gradle
- **Test Framework:** JUnit 5
- **Automation Library:** Selenium WebDriver

## How to Run the Project:

1. **Download the Project:**
   - Download the entire folder: `Junit_Class_Project`.

2. **Open in IntelliJ IDEA:**
   - Launch IntelliJ IDEA.
   - Navigate to `File > Open`.
   - Select the `Project_Source_Folder` that contains the `build.gradle` file.
   - Click on the `OK` button and open it as a project.

3. **Build the Project:**
   - IntelliJ will automatically build the necessary dependencies and packages.
   - Wait for the project to sync and build properly.

4. **Run the Project via Terminal:**
   - Open a terminal inside IntelliJ IDEA or any other terminal (ensure your `GRADLE_HOME` is properly configured).
   - Run the following command to clean and test the project:
     ```bash
     gradle clean test
     ```
     **Reminder:** Make sure `GRADLE_HOME` is set up to execute this command from any terminal.


