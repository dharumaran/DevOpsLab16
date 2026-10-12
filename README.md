# Lab Exercise 13 — Git + Maven + Jenkins + Docker

## Included files
- `src/main/java/com/example/App.java`: Java application
- `src/test/java/com/example/AppTest.java`: sample unit test
- `pom.xml`: Maven build and test configuration
- `Dockerfile`: packages the JAR into a Java 17 container
- `Jenkinsfile`: visible Jenkins stages for Checkout, Build, Test, Package, Docker Build, and Verify Docker Image

## Requirements
- Git
- JDK 17
- Maven 3.9+
- Jenkins with a Windows build agent
- Docker Desktop running with Linux containers enabled

The supplied Jenkinsfile uses Windows `bat` steps. If your Jenkins agent is Linux/WSL, replace each `bat 'command'` with `sh 'command'`.

## Test locally (PowerShell, from this folder)
```powershell
git --version
java -version
mvn -version
docker version
mvn clean test package
docker build -t lab13-java-app:latest .
docker run --rm lab13-java-app:latest
```

Expected application output:
`Hello from the Git + Maven + Jenkins + Docker pipeline!`

## Publish to GitHub
Create an empty GitHub repository, then run these commands from this folder:
```powershell
git init
git add .
git commit -m "Add Lab 13 Java pipeline"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```
Replace the URL with your own repository URL.

## Jenkins setup
1. Open Jenkins and select **Manage Jenkins → Tools**. Configure JDK and Maven if they are not already available to the agent. Alternatively, make sure `java` and `mvn` work from the account running the Jenkins agent.
2. Select **New Item**, enter `Lab13-Complete-Build`, choose **Pipeline**, and click **OK**.
3. In the Pipeline section, choose **Pipeline script from SCM**.
4. Set SCM to **Git**, enter your GitHub repository URL, and add credentials if the repository is private.
5. Set Branch Specifier to `*/main` and Script Path to `Jenkinsfile`. Save.
6. Make sure Docker Desktop is running and that the same Windows account/agent that runs Jenkins can execute `docker version`.
7. Select **Build Now**. Open **Stage View** to see the pipeline stages and **Console Output** to diagnose errors.
8. After success, check **Build Artifacts** for the JAR. On the machine where Docker is available, run `docker images` and `docker run --rm lab13-java-app:latest`.

## Troubleshooting
- `mvn is not recognized`: install Maven or configure Maven under Jenkins **Tools**, then ensure the agent can access it.
- `docker is not recognized`: install Docker Desktop, ensure `docker.exe` is on the agent's PATH, and restart the Jenkins agent/service after changing PATH.
- Docker daemon/engine error: start Docker Desktop and wait until `docker version` shows both Client and Server information.
- `Access is denied` or Docker works in PowerShell but not Jenkins: Jenkins may run as a Windows service under a different account. Configure the agent/service account and PATH so it can access Docker; test from the Jenkins agent context.
- Java version error: confirm the build agent uses JDK 17 or newer.
