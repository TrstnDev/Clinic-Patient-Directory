# MediManager 🏥

MediManager is a robust, clinical-grade patient and physician management system built to modern healthcare IT standards. Designed to handle relational clinical data securely, this application separates administrative tracking from backend database architecture using enterprise best practices.

## 💻 Tech Stack
* **Backend:** Java 25, Spring Boot 4.0.3 (Web, Data JPA, Validation)
* **Frontend:** Thymeleaf, HTML5, Bootstrap 5.3.2
* **Database:** PostgreSQL (Containerized via OrbStack/Docker)
* **Build Tool:** Maven

## ✨ Key Features

### 1. Clinical Record Management
* **Patient Admission & Directory:** Securely onboard patients with strict data validation, including custom cross-field validation ensuring South African RSA ID numbers match the provided Date of Birth.
* **Physician Roster:** Manage hospital staff, linking doctors to specific clinical roles (e.g., Medical Officer, Registrar) and specialties (e.g., Neurology, Cardiology).
* **Decoupled Reference Data:** Utilizes global clinical standards like ICD-10 codes for diagnoses, designed to withstand future medical coding updates without breaking database relationships.

### 2. Robust Search Architecture
Built with specialized Spring Data JPA derived queries, the system allows hospital staff to search records via:
* **Patients:** Name, Surname, Medical File Number, RSA ID, Diagnosis, or Treating Physician.
* **Physicians:** Name, Surname, HPCSA Registration Number, RSA ID, or Specialty.

### 3. Automated Clinical Identifiers
To streamline front-desk operations and testing, the system automatically generates authentic-looking clinical identifiers upon record creation (e.g., HPCSA numbers like `MP123456` and sequential Medical Record Numbers) using JPA `@PrePersist` lifecycle hooks.

## 🏗️ Database Architecture
MediManager avoids the use of **Natural Keys**. 

The system utilizes secure, immutable **UUIDs** (Surrogate Keys) for all table relationships. 
* **Benefit:** If a medical board updates a specialty code or a patient's ID requires correction, the underlying database relationships remain intact, preventing cascading migration failures.

## 🚀 Getting Started

### Prerequisites
* Java 25 or higher
* Docker (or OrbStack)
* Maven

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/trstndev/medimanager.git](https://github.com/trstndev/medimanager.git)
   cd medimanager

2. **Configure Database Credentials:**
   Create a ```.env``` file in the root directory to securely pass your credentials to the Docker
   container:
   ```bash
   DB_USERNAME=clinic-admin
   DB_PASSWORD=your_secure_password

3. **Start the PostgreSQl Database:**
   ```bash
   docker compose up -d

4. **Run the Application:**
   Start the application via your IDE or the command line. Spring Boot will automatically wait for
   Hibernate to generate the UUID-based tables, and then execute ```data.sql``` to populate the
   reference data (Roles, Specialities, and Diagnoses).
   ```bash
   ./mvnw spring-boot:run

5. **Access the Sytem:**
   Navigate to ```http://localhost:8080/patients``` in your web browser.

## 🛣️ Future Roadmap ##
* **Service Layer Refactoring:** Abstracting business logic out of controllers and into dedicated ```@Service``` components.
* **Soft Deletion:** Upgrading the current hard-delete functionality to comply with clinical data retention policies (e.g., ```isActive = false```).
* **Security Authentication:** Implementing Spring Security with Role-Based Access Control (BRAC) to separate Admin, Doctor, and Receptionist permissions.
