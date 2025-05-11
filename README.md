# Email Buddy

A full-stack application for generating and managing email content using AI.

## 📁 Project Structure

email-buddy/
│
├── email-generator-backend/ # Java Spring Boot backend (Maven project)
│ └── email-generator/
│
├── email-generator-react/ # React frontend (Vite-based)
│ └── email-generator-react/
│
├── email-generator-ext/ chrome extension


---

## 🔧 Tech Stack

- **Backend**: Java, Spring Boot, Maven
- **Frontend**: React.js, JavaScript, Vite
- **Others**: Git, GitHub

---

## 🚀 Getting Started

### Backend

```bash
cd email-generator-backend/email-generator
./mvnw spring-boot:run

###Frontend
cd email-generator-react/email-generator-react
npm install
npm run dev

###Project excludes:

node_modules/

target/

.classpath, .project, .settings

.env, .log, .DS_Store

### How to run this project:

Make sure to run both backend and frontend in parallel.

Backend on localhost:8080

Frontend on localhost: 5173 (Vite default)




