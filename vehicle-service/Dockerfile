# Tomcat image
FROM tomcat:9.0

# Working directory on container
WORKDIR /usr/local/tomcat/webapps

# Copy war file into webapps (container)
COPY ./target/vehicle-service.war ./vehicle-service.war

# Container's port expose
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
