# 🚧 Cypress — Civic Issue Reporting System

Cypress is a citizen-facing CLI-based platform that allows Toronto residents to report, track, and follow up on local public infrastructure issues such as potholes, graffiti, or streetlight outages. The system is designed to streamline communication between citizens and city officials and replace the manual complaint process with an efficient digital workflow.

---

## 🧠 Features

- **User Sign-up/Login**  
  Citizens and admins can securely register and authenticate.

- **Submit Reports**  
  Citizens can report public issues with descriptions, location coordinates, and a date.

- **Spam & Duplicate Detection**  
  Cypress uses smart rules to block spam reports and near-duplicate issues within 100 meters.

- **View My Reports**  
  Logged-in citizens can view their own report history.

- **Admin Dashboard**  
  Admins can:
  - View all submitted reports
  - Filter reports by **type** (e.g. pothole, graffiti)
  - Filter reports by **status** (NEW, IN_PROGRESS, CLOSED)
  - Update report status to track progress

---

## 🏗️ Tech Stack

- Java  
- CLI-based Interface  
- Object-Oriented Design Principles

---

## 👥 Contributors

| Name            | 
|-----------------|
| Faryal Siddiqui | 
| Nehal Goel      |
| Aneela Chaudhry |
| Amira Adan      | 

---

## 📦 Getting Started

To run the application:

```bash
javac -d out $(find . -name "*.java")
java -cp out cypress.Main
