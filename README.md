# Clinical Management System 

A full-stack, MVC-patterned web application designed to manage complex clinical data, patient admissions, and physician rosters. 

Built as a portfolio project to explore enterprise Java development, this application bridges real-world medical data constraints with modern software engineering principles. I leverage my experience in Medicine and Medical Science, as well as work in the South African Public Health Sector to identify features that integrate well within a healthcare context. It features a highly normalized relational database schema, handling everything from South African National ID validation to ICD-10 differential diagnoses tracking.


## 🚀 Key Features

* **Relational Data Modeling:** Implements robust `@OneToMany` and `@ManyToMany` JPA relationships linking Patients, Physicians, Roles, Specialties, and Diagnoses.
* **Custom Cross-Field Validation:** Utilizes custom Spring Validation annotations (e.g., verifying that a patient's Date of Birth perfectly matches the first six digits of their 13-digit South African RSA ID).
* **Automated Data Generation:** Employs JPA `@PrePersist` lifecycle hooks to dynamically generate and format professional display names and unique HPCSA identification numbers. (future expansion of this project could see potential implementation of real HPCSA Numbers and validation; TBD.)
* **Dynamic Search & Filtering:** Spring Data JPA query methods allow users to search the clinical database by patient name, diagnosis, ID, or treating physician.
* **Responsive UI:** Server-side rendering with Thymeleaf and Bootstrap 5 for a clean, professional, and responsive user interface.


## 🛠️ Tech Stack

* **Language:** Java (JDK 25)
* **Framework:** Spring Boot 4.0.3 (Spring Web, Spring Data JPA, Spring Validation)
* **Database:** Microsoft SQL Server
* **Frontend:** Thymeleaf (Template Engine), HTML5, Bootstrap 5
* **Infrastructure:** Docker (Azure SQL Edge image)
* **Build Tool:** Maven


## 🗄️ Database Architecture

The system relies on a normalized SQL database with the following core entities:
* **Patients:** Tracks demographics, admission/discharge dates, and links to treating physicians.
* **Physicians:** Tracks practitioner details, roles, and specialties.
* **Diagnoses (Reference):** A lookup table of ICD-10 codes and descriptions.
* **Specialties & Roles (Reference):** Lookup tables standardizing clinical roles and departments.


## ⚙️ Getting Started

### Prerequisites
To run this project locally, you will need:
* Java Development Kit (JDK) 25 or higher
* Maven
* Docker Desktop
* An IDE (IntelliJ IDEA, Rider, or VS Code)


### Local Installation

1. **Clone the repository:**
   
   ```git clone https://github.com/TrstnDev/Clinic-Patient-Directory.git```


2. **Spin up the Database via Docker:**
   
   *Note for Apple Silicon users; This project uses the ```azure-sql-edge``` image for native ARM64 compatibility.*
   
    ```docker run -e "ACCEPT_EULA=Y" -e "MSSQL_<yourpreferredusername>_PASSWORD=<yourpassword123!>" -p <yourpreferredport>:<yourpreferredport> --name <yourpreferredcontainername> -d mcr.microsoft.com/azure-sql-edge```


3. **Configure Application Properties:**
   
   Ensure your ```src/main/resources/application.yaml``` matches your specific Docker credentials.


4. **Run the Application**
   
   Execute the maven wrapper command to start the Spring Boot application:
   
   ```./mvnw spring-boot:run```

   The application will be available at:

   ```http://localhost:8080```


## Author

**T. Kriel**

Developed to showcase the combination of my deep academic and practical background in medicine and medical science with modern software engineering and cloud development practices.

:) 
