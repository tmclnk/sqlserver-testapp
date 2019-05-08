# SQL Server Integrated Auth JDBC Test App
This is a standalone Spring Boot (Java) app which can be used to test the `integratedSecurity=true` with the Microsoft JDBC Driver. It assumes that your SQL Server database is configured to work with Windows Authentication. If you use SQL Server Management Studio (SSMS) for development and log in with your windows credentials rather than a service account in the database, this will help you ascertain if you can 

## Why do this?
The short answer is "policy." Identity and Access Management (IAM) policies for your organization, as well as security policies for your domain (PCI-DSS, SCSEM, STIG, etc) may dictate things like password complexity, If you are already doing this in Active Directory for your _users_, then it may make sense to be doing this for your applications as well. Furthermore, using integrated auth may make it easier to deal with database usernames and passwords in your code repositories (namely, there are none; enterprise IAM handles all of this).

## Requirements
- Java 8 on your $PATH
- `sqljdbc_auth.dll` on your $PATH (see below)

## Configuration
### sqljdbc_auth.dll
The DLL file for the JDBC driver isn't included in this build, but you can freely obtain a copy of sqljdbc_auth.dll and place it in a directory on your $PATH. You can download a tarball from [microsoft](https://www.microsoft.com/en-us/download/details.aspx?id=11774).

Note that for normal JDBC operation, you don't need the DLL file; this is only if you are using more advanced features of the driver (namely integrated authentication).

### application.properties
Modify `src/main/resources/application.properties` to point to _your_ database.

```
spring.datasource.url=jdbc:sqlserver://YOUR_DATABASE;integratedSecurity=true
```

## Running 
You can use the included maven wrapper scripts 

** bash **

```bash
./mvnw spring-boot:run
```

** Windows Command Prompt**

```cmd
mvnw.bat spring-boot:run
```

# Related Links
- [Microsoft JDBC Driver](https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-2017)
- [Microsoft JDBC 6.0 Download](https://www.microsoft.com/en-us/download/details.aspx?id=11774) 
- [Kerberos Integrated Authentication with SQL Server](https://docs.microsoft.com/en-us/sql/connect/jdbc/using-kerberos-integrated-authentication-to-connect-to-sql-server?view=sql-server-2017)
- [Maven Wrapper Plugin](https://github.com/takari/takari-maven-plugin)