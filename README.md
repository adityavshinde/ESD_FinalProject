
# **Student Billing Management System**

A backend system for managing student billing and payment processing, with seamless integration for student functionalities.

---

## **Table of Contents**

- [Project Overview](#project-overview)  
- [Features](#features)  
- [API Endpoints](#api-endpoints)  
  - [Student APIs](#student-apis)  
  - [Billing and Payments APIs](#billing-and-payments-apis)  
- [Setup Instructions](#setup-instructions)  
  - [Backend](#backend)  
  - [Frontend](#frontend)  
- [Postman Collection](#postman-collection)  

---

## **Project Overview**

The **Student Billing Management System** simplifies the billing and payment workflows for students. It includes APIs for creating and managing student and admin users, generating bills, and processing payments.

---

## **Features**
 
- Student login.  
- View all students and their billing information.  
- Generate and fetch bills.  
- Process and view payment history.  

---

## **API Endpoints**

### **Student APIs**

- **POST** `/students`  
  Create a new student account.  

  **Request Body (JSON):**
  ```json
  {
    "rollNumber": "MT010",
    "firstName": "Aditya",
    "lastName": "Shinde",
    "email": "Aditya.Shinde@iiitb.ac.in",
    "password": "pass1234"
  }
  ```

- **GET** `/student/login`  
  Student login.  

- **GET** `/student-bills/student/{id}`  
  Fetch all bills of a specific student.  
  **Headers:**  
  ```json
  {
    "email":"Aditya.Shinde@iiitb.ac.in",
    "password":"pass1234"
}
  ```

---

### **Billing and Payments APIs**

- **POST** `/bills`  
  Create a bill.

- **POST** `/payments`  
  Process a payment.  

- **GET** `/payments`  
  Fetch payment history of a specific student.  
  **Headers:**  
  ```json
  {
    "description":"tuition fees",
    "amount":"358000",
    "bill_date":"2025-06-01",
    "deadline":"2025-06-01",
    "studentId": 1
}
  ```

---

## **Setup Instructions**

### **Backend**

1. Open the project in IntelliJ IDEA.  
2. Customize the `application.properties` file to match your database configuration.  
3. Run the project.  
4. The server will be available at:  
   `http://localhost:8080`

### **Frontend**

1. Navigate to the `ESD_frontend` repo.  
2. Open the project in Visual Studio Code (VSCode).  
3. In the terminal, run:  
   ```bash
   npm start
   ```
4. The frontend will run at the default React port.

---

## **Postman Collection**

1. Use [Postman](https://www.postman.com/) to test the APIs.  
2. Configure the following endpoints in Postman:  
   - **Student Creation**: `POST /students`  
   - **Login**: `GET /student/login`
   - Additional endpoints listed above.  
3. Ensure to pass appropriate headers for endpoints requiring `studentId`.  

---

Enjoy building with the **Student Billing Management System**! 🚀
