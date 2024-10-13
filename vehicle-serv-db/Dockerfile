# Use the official SQL Server image
FROM mcr.microsoft.com/mssql/server:2019-latest

# Set environment variables
ENV ACCEPT_EULA=Y                      
ENV SA_PASSWORD=vehicle_serv_db_Pass123
ENV MSSQL_PID=Express                  

# Create a directory for the CSV file
RUN mkdir -p /var/opt/mssql/data

# Copy the CSV file into the image
COPY vehicles.csv /var/opt/mssql/data/vehicles.csv
COPY bookings.csv /var/opt/mssql/data/bookings.csv

# Script to import data
COPY import-data.sh /usr/src/app/import-data.sh

# Start SQL Server and run the import script
#CMD /bin/bash -c "/opt/mssql/bin/sqlservr & sleep 30; /usr/src/app/import-data.sh"
#CMD /bin/bash -c "sleep 30; /usr/src/app/import-data.sh && /opt/mssql/bin/sqlservr"
CMD ["./opt/mssql/bin/sqlservr"]

