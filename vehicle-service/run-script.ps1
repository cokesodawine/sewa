# Define the paths
$sourcePath = ".\target\vehicle-service.war"
$destinationPath = "C:\Program Files\apache-tomcat-9.0.93\webapps"
$tomcatBinPath = "C:\Program Files\apache-tomcat-9.0.93\bin"
$mavenProjectPath = "D:\software\The Greatest Project\sewa-refinement\sewa-microservice\vehicle-service"

# Navigate to the Maven project directory
Set-Location -Path $mavenProjectPath

# Run 'mvn clean install' to build the project
Start-Process "mvn" -ArgumentList "clean install" -NoNewWindow -Wait

# Copy the WAR file to the Tomcat webapps directory
Copy-Item -Path $sourcePath -Destination $destinationPath -Force

# Save the current location
$originalLocation = Get-Location

# Change directory to the Tomcat bin folder
Set-Location -Path $tomcatBinPath

# Run the catalina.bat file to start Tomcat
Start-Process "cmd.exe" -ArgumentList "/c catalina.bat run" -NoNewWindow -Wait
