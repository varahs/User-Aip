# Documentation

# Database Project

### Description
This Project is to manage the Database change requests and to track each query, which is executed by a user during the release for enhancements / defects. This process will help us to manage the detail of intake/defect/enhancement and to track database changes & help us to maintain the information, which can be helpful in future.

* Database Project Spring Boot Application - Completed
* Angular Frontend - Completed
* .SQL to database Bash Script - Completed

### Pre requisites
* Spring Boot
* Java 8
  

###  API documentation
#### 1. Code Documentation

* **Config Package**

> ApproverMailsConfig.java

gets List of approvers from *approvers.properties*

---

> FileNameConfig.java

gets file location of *csv*, *sql* and *updated sql* file from *application.properties*

---

> MailConfig.java

gets mail configuration properties from *approvers.properties*

----
 * **Controller Package**

> ReadController.java

Functions to read data from csv file

     public List<User> read()
Read List of Requests from request.csv file

    public User readById(@PathVariable String requestId)
Get Request by Id (Ticket number) from request.csv file.


---
> UpdateController.java

update request.csv file after approver approves or rejects request.

    public Map<String,String> update(@RequestBody User user)

Update user in request.csv file

    public String updateSql(User user)
Insert Approved Request in Queries.sql file.

---

> UploadFile.java

update request.csv file after approver approves or rejects request.

    public void uploadFile(@RequestParam("file") MultipartFile file)

Upload file into file system


---

> ViewController.java 

update request.csv file after approver approves or rejects request.

       public ModelAndView homepage()

Return Home page

---
> WriteController.java

Write new request in request.csv file.

    public HashMap<String, String> writer(@RequestBody User user)

write to request.csv

---
> DownloadReportController.java

Downloads excel file

    public void download(HttpServletResponse response)

convert request.csv file to Excel file

---
> DownloadFileController.java

Downloads File uploaded by user

    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName)

download file to user from given path

---
 * **Entity Package**

> User.java

User entity class

---

 * **Service Package**
 
> ServiceProvider.java

    public void uploadFile(MultipartFile file)

store file in filesystem

    public void add(User user)

Add request to request.csv

    public List<User> readcsv()

Read list of requests from request.csv

    public void update(User user)
Update request in request.csv by using temporary file updated.csv


    public User readcsvId(String requestId)
Read csv by id (Ticket Number) from request.csv

    public void updateSql(User user)

Add approved request to Queries.sql.

    public ByteArrayInputStream download(HttpServletResponse response)
Download report in Excel format.


---

 * **Properties File**

>application.properties

It contains

 - Mail Configuration
 - File locations on filesystem
 ---
>approvers.properties

It contains list of approvers seperated by  comma [ , ] .

 ---
 
 #### 2. REST API Documentation
 

| Route | Parameter |Function|
|--|--|--|
| /read | -|return List of requests  |
| /read/[ID] |Parameter - Ticket Number | return request using ticket number  |
| /read/approvers | - | return List of approvers  |
| /update | Form Body - Format (User Class) |updates Request in request.csv  |
| /upload | Request Param - file (multipart file)|Uploads file in file system  |
| /view |- |Routes to homepage  |
| /write| Form Body - Format (User Class) |Write to request.csv  |
| /download| - | Download Report in Excel Format  |
| /download/file/[Filename]| Parameter - Filename | Download File |

   

### Contributing

 

 -  Suyash Wagh
 - Ashish Chavan
- Trupti Bhandari