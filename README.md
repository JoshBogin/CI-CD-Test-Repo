# CI-CD-Test-Repo

## Background
This repository is a plaground used to learn how to implement a CI/DC pipeline that runs scans and tests on a project in Github Actions!

I created a hello world app that includes simple unit tests and UI tests that will be utilized by the pipeline workflows.

## Building the Project
- **Download** Android Studio if you don't already have it and **clone** the repository
   - `git clone git@github.com:JoshBogin/CI-CD-Test-Repo.git`

## PR Workflow
The pr workflow accomplishes a few different tasks. It builds the repository and runs the detekt linter on it, it runs the unit tests, and creates a debug apk of the app.
- If any one of these steps fail, the workflow will fail and it will send an email notification of this

## Nightly Action
The nightly flow simply builds and runs the UI test suite
- This will also send a notification on failure

## Upload to Playstore
Lastly, I have created a workflow to build a signed app bundle and upload it to the Google Playstore!
- To execute this, go to the **Actions** tab
- Click on `Create & Release Bundle to PlayStore` workflow
- Select the `Run Workflow` dropdown and then click `Run Workflow` again in the menu
