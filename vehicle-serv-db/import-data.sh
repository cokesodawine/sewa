#!/bin/bash
# Wait for SQL Server to start
#sleep 30

# pass and sql query definition
password="vehicle_serv_db_Pass123" # database SA password
create_serv_db="create database vehicle_database;"
create_vehicles_tb="
create table vehicles (
vehicle_plat varchar(6) primary key,
vehicle_avail bit,
vehicle_brand varchar(15),
vehicle_model varchar(15),
vehicle_type varchar(15),
vehicle_color varchar(15),
fee_prhr float 
);"
create_bookings_tb="
create table bookings (
vehicle_plat varchar(6),
user_id varchar(10),
start_date date,
end_date date,
foreign key (vehicle_plat) references vehicles(vehicle_plat) 
);"

# create database and tables
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$password" -d master -Q "$create_serv_db" -C
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$password" -d vehicle_database -Q "$create_vehicles_tb" -C
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$password" -d vehicle_database -Q "$create_bookings_tb" -C

# Import data from CSV into the SQL Server table
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$password" -d  vehicle_database -Q "BULK INSERT dbo.vehicles FROM '/var/opt/mssql/data/vehicles.csv' WITH (FIELDTERMINATOR = ',', ROWTERMINATOR = '\n', FIRSTROW = 3);" -C    
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$password" -d  vehicle_database -Q "BULK INSERT dbo.bookings FROM '/var/opt/mssql/data/bookings.csv' WITH (FIELDTERMINATOR = ',', ROWTERMINATOR = '\n', FIRSTROW = 3);" -C  