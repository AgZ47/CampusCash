



# <p align="center">Campus Cash</p>


 
  <p align="center">
     <a href="#"><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript"></a>
  <a href="#"><img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java"></a>
  <a href="#"><img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white" alt="Python"></a>
  <a href="#"><img src="https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=node.js&logoColor=white" alt="Node.js"></a>
  <a href="#"><img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black" alt="React"></a>
  <a href="#"><img src="https://img.shields.io/badge/Express.js-000000?style=for-the-badge&logo=express&logoColor=white" alt="Express.js"></a>
  <a href="#"><img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white" alt="MongoDB"></a>
  <a href="#"><img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase"></a>

</p>

## Introduction

Campus Cash is a cashless payment system designed to streamline transactions within a campus environment. It enables students to make purchases for food, stationery, and other items without using physical cash.  Outstanding dues are seamlessly integrated into their college fees, providing a hassle-free payment experience.  The system features both a modern web interface (React) for students and a desktop application (Python) for staff management.

## Table of Contents

1.  [Key Features](#key-features)
2.  [Installation Guide](#installation-guide)
3.  [Usage](#usage)
4.  [Environment Variables](#environment-variables)
5.  [Project Structure](#project-structure)
6.  [Technologies Used](#technologies-used)
7.  [License](#license)

## Key Features

*   **Cashless Transactions:** Facilitates purchases without the need for physical cash.
*   **Due Management:**  Tracks student dues and integrates them into college fee structures.
*   **User Authentication:** Secure login system for both students and staff.
*   **React Frontend:** Modern and intuitive web interface for students to view their dues.
*   **Python Desktop Application:** Feature-rich application for staff to manage dues (add, filter, delete).
*   **Role-Based Access Control:**  Different access levels for store personnel (add dues) and staff (delete dues).
*   **Centralized Data Storage:**  Uses MongoDB to store user and due information.
*   **JWT Authentication:** Securely authenticates users via JSON Web Tokens.

## Installation Guide

### Backend (Node.js)

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd CampusCashBE
    ```

2.  **Install dependencies:**
    ```bash
    npm install
    ```

3.  **Configure Environment Variables:** Create a `.env` file in the `CampusCashBE` directory and set the following variables (see [Environment Variables](#environment-variables) section for details):

    ```
    MONGO_URI=your_mongodb_connection_string
    JWT_SECRET=your_jwt_secret_key
    PORT=5000
    ```

4.  **Run the server:**
    ```bash
    npm start
    ```

### Frontend (React)

1.  **Navigate to the frontend directory:**
    ```bash
    cd ../CampusCash
    ```

2.  **Install dependencies:**
    ```bash
    npm install
    ```

3.  **Run the application:**
    ```bash
    npm start
    ```

### Desktop App (Python)

1.  **Navigate to the python application directory:**
    ```bash
    cd ../CampusCashPy
    ```

2.  **Install dependencies:**
    ```bash
    pip install -r requirements.txt # Create requirements.txt file if needed with dependencies like tkinter, ttkbootstrap, Pillow, pymongo
    ```

3.  **Run the application:**
    ```bash
    python CampusCash.py
    ```

## Usage

### Frontend (React)

*   **Registration:**  Navigate to the `/register` route to create a new user account.
*   **Login:**  Navigate to the `/login` route to log in with your student ID and password.
*   **User Profile:** After logging in, you will be redirected to your user profile (`/user`), where you can view your total dues.
*   **Due Details:** Access detailed information about your dues by navigating to the `dues/:studentId` route.

### Backend (Node.js)

The backend provides API endpoints for:

*   User registration and login (`/api/auth/register`, `/api/auth/login`)
*   Fetching user profile data (`/api/auth/user`)
*   Retrieving dues details for a specific student (`/api/auth/dues/:studentId`)

### Desktop App (Python)

*   Run `CampusCash.py` to launch the application.
*   Choose between "Store" or "Staff" login.
*   Enter the appropriate credentials.
*   Store personnel can add new dues by entering student ID, description, and amount.
*   Staff can filter dues by student ID and delete existing dues.

## Environment Variables

*   `MONGO_URI` (Backend): The connection string for your MongoDB database.  This is required for storing user data and dues information.
*   `JWT_SECRET` (Backend): A secret key used to sign and verify JWTs.  This should be a strong, randomly generated string.
*   `PORT` (Backend): The port number on which the backend server will listen (default: 5000).

## Project Structure

```
CampusCash/                   # React Frontend
├── package.json
├── src/
│   ├── App.js                # Main application component and router
│   ├── components/           # React components
│   │   ├── Login.js          # Login component
│   │   ├── Register.js       # Register component
│   │   ├── UserProfile.js    # User profile component
│   │   ├── DueDetails.js     # Due details component
│   └── index.js              # Entry point for the React application
CampusCashBE/                # Node.js Backend
├── package.json
├── server.js               # Main server file
├── routes/
│   └── auth.js             # Authentication routes
├── models/
│   ├── User.js             # User model
│   └── Dues.js             # Dues model
├── config/
│   └── db.js               # Database connection
CampusCashPy/                # Python Desktop Application
├── CampusCash.py          # Main application logic
```

## Technologies Used

*   **Frontend:**
    <p align="left">
        <a href="#"><img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" alt="React"></a>
        <a href="#"><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript"></a>
        <a href="#"><img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3"></a>
    </p>

*   **Backend:**
    <p align="left">
        <a href="#"><img src="https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white" alt="Node.js"></a>
        <a href="#"><img src="https://img.shields.io/badge/Express.js-000000?style=for-the-badge&logo=express&logoColor=white" alt="Express.js"></a>
    </p>

*   **Database:**
    <p align="left">
        <a href="#"><img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white" alt="MongoDB"></a>
    </p>

*   **Desktop Application:**
     <p align="left">
        <a href="#"><img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white" alt="Python"></a>
        <a href="#"><img src="https://img.shields.io/badge/Tkinter-469584?style=for-the-badge&logo=tkinter&logoColor=white" alt="Tkinter"></a>
    </p>

## License

MIT License

<p align="left"> <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="MIT License"></a> </p>
