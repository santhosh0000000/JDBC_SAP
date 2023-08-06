# JDBC_SAP
The Java code is designed to connect to an SAP HANA database, execute a SQL query, and write the results to a CSV file using the OpenCSV library.

1. Importing Libraries
The code imports necessary classes for working with file writing, JDBC (Java Database Connectivity), and OpenCSV for writing CSV files.

2. Main Class Definition
The main class Main is defined, containing the main method.

3. Defining Connection Parameters
The connection parameters for the SAP HANA database are defined, including the host, port, username, password, schema, and table name. The JDBC URL is formed using the host and port.

4. Building SQL Query
A SQL query is constructed to select all records from the specified schema and table in the SAP HANA database.

5. Connecting to the Database
A try-with-resources block is used to establish a connection to the SAP HANA database using the DriverManager.getConnection method. If successful, a message is printed to the console.

6. Executing SQL Query
A Statement object is created, and the SQL query is executed, retrieving a ResultSet containing the query results.

7. Checking for Empty Results
If the ResultSet is empty (i.e., no data is returned), a message is printed, and the program exits early.

8. Writing Results to a CSV File
A CSVWriter object is created to write the results to a CSV file located at /root/output.csv. The column names and data rows are written to the file as follows:

Column Names: The column names are retrieved from the ResultSet metadata and written as the first row in the CSV file.
Data Rows: The data rows are iterated through and written to the CSV file, one row at a time.
9. Error Handling
Various catch blocks are used to handle exceptions that may occur during execution, such as:

SQLException: Handles errors related to the database connection or query execution.
IOException: Handles errors related to file writing.
