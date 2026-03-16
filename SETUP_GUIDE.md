# Smart Fitness Tracker - Setup & Installation Guide

## Table of Contents
1. [System Requirements](#system-requirements)
2. [MongoDB Setup](#mongodb-setup)
3. [Backend Setup](#backend-setup)
4. [Frontend Setup](#frontend-setup)
5. [Running the Application](#running-the-application)
6. [Testing the Application](#testing-the-application)
7. [Troubleshooting](#troubleshooting)

---

## System Requirements

### Minimum Requirements
- **RAM**: 4GB
- **Disk Space**: 2GB
- **OS**: Windows, macOS, or Linux

### Required Software

#### Java
- **Java Development Kit (JDK) 11 or higher**
  - Download from: https://adoptopenjdk.net/
  - Verify installation:
    ```bash
    java -version
    javac -version
    ```

#### Maven
- **Apache Maven 3.6.0 or higher**
  - Download from: https://maven.apache.org/download.cgi
  - Verify installation:
    ```bash
    mvn -version
    ```
  - Add Maven to PATH if not already done

#### MongoDB
- **MongoDB 4.0 or higher**
  - Community Edition: https://www.mongodb.com/try/download/community
  - Enterprise Edition: https://www.mongodb.com/try/download/enterprise

#### Git (Optional)
- For cloning the repository
- Download from: https://git-scm.com/

---

## MongoDB Setup

### Windows Installation

1. **Download MongoDB Community Server**
   - Visit: https://www.mongodb.com/try/download/community
   - Select "Windows" and click "DOWNLOAD"
   - Run the installer (.msi file)

2. **Follow Installation Wizard**
   - Accept license agreement
   - Choose Complete Setup
   - Select "Run MongoDB as a Service" (recommended)
   - Install MongoDB Compass (optional but recommended)

3. **Verify Installation**
   ```bash
   mongod --version
   ```

4. **Start MongoDB Service** (if not auto-started)
   ```bash
   # Windows Command Prompt (Admin)
   net start MongoDB
   
   # Or using PowerShell
   Start-Service MongoDB
   ```

5. **Verify MongoDB is Running**
   ```bash
   mongo --version
   mongo # Should connect to local MongoDB
   ```

### macOS Installation

```bash
# Using Homebrew (recommended)
brew tap mongodb/brew
brew install mongodb-community

# Start MongoDB service
brew services start mongodb-community

# Verify installation
mongod --version

# Connect to MongoDB
mongo
```

### Linux Installation (Ubuntu/Debian)

```bash
# Import GPG key
wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | sudo apt-key add -

# Add MongoDB repository
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/5.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-5.0.list

# Update package cache
sudo apt-get update

# Install MongoDB
sudo apt-get install -y mongodb-org

# Start MongoDB service
sudo systemctl start mongod
sudo systemctl enable mongod

# Verify
mongo --version
mongo
```

### Create Sample Database (Optional)

```bash
# Connect to MongoDB
mongo

# Create database and collection
use fitness_tracker_db
db.createCollection("users")

# Insert sample data (optional)
db.users.insertOne({
  "email": "demo@fitnesstracker.com",
  "firstName": "Demo",
  "lastName": "User"
})

# Exit
exit
```

---

## Backend Setup

### Step 1: Verify Java Installation

```bash
java -version
# Should output: version 11 or higher

javac -version
# Should output: version 11 or higher
```

### Step 2: Verify Maven Installation

```bash
mvn -version
# Should output Maven 3.6.0 or higher
```

### Step 3: Navigate to Backend Directory

```bash
cd fitness-tracker-app/backend
```

### Step 4: Configure MongoDB Connection (if needed)

**File**: `src/main/resources/application.properties`

Default configuration:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/fitness_tracker_db
spring.data.mongodb.database=fitness_tracker_db
```

**For Remote MongoDB:**
```properties
spring.data.mongodb.uri=mongodb://username:password@host:port/database
```

**For MongoDB Atlas Cloud:**
```properties
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/database?retryWrites=true&w=majority
```

### Step 5: Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile Java code
- Run tests (if any)
- Package the application

### Step 6: Resolve Issues (if any)

**If you encounter build errors:**

```bash
# Clear Maven cache
mvn clean

# Download fresh dependencies
mvn dependency:resolve

# Try building again
mvn clean install -DskipTests
```

---

## Frontend Setup

### Option 1: Direct File Opening (Simplest)

1. **Navigate to Frontend Directory**
   ```bash
   cd fitness-tracker-app/frontend
   ```

2. **Open in Browser**
   - Double-click `index.html`
   - Or right-click → Open with → Browser

**Note**: This method may have CORS issues with API calls.

### Option 2: Python Local Server (Recommended)

#### Python 3:
```bash
cd fitness-tracker-app/frontend

# Python 3.x
python -m http.server 8000

# Then open: http://localhost:8000/index.html
```

#### Python 2 (if Python 3 not available):
```bash
python -m SimpleHTTPServer 8000
```

### Option 3: Node.js Local Server

```bash
cd fitness-tracker-app/frontend

# Install http-server (one-time)
npm install -g http-server

# Start server
http-server -p 8000

# Then open: http://localhost:8000/index.html
```

### Option 4: Live Server Extension (VS Code)

1. Install "Live Server" extension in VS Code
2. Right-click `index.html` → "Open with Live Server"
3. Automatically opens in browser at `http://localhost:5500`

---

## Running the Application

### Step 1: Start Backend

```bash
cd fitness-tracker-app/backend

mvn spring-boot:run
```

**Expected Output:**
```
2024-03-16 10:30:00 - Starting FitnessTrackerApplication
...
2024-03-16 10:30:05 - Started FitnessTrackerApplication in 5.234 seconds
```

**Backend URL**: `http://localhost:8080/api`

### Step 2: Start Frontend

**In a new terminal:**

```bash
cd fitness-tracker-app/frontend

# Using Python 3
python -m http.server 8000

# Or npm
http-server -p 8000
```

**Frontend URL**: `http://localhost:8000/index.html` (or `http://localhost:5500` for Live Server)

### Step 3: Open Application

1. Navigate to `http://localhost:8000/index.html`
2. You should see the login/registration page
3. Create a new account or use example credentials

---

## Testing the Application

### Test Scenario 1: User Registration & Login

1. **Register New User**
   - Click "Register" tab
   - Fill in all required fields
   - Select a fitness goal
   - Click "Register"
   - Should redirect to dashboard

2. **Login**
   - Go back to index.html
   - Enter registered email
   - Login should work

### Test Scenario 2: Log Activity

1. **From Dashboard**
   - Select an activity type (Running, Swimming, Yoga)
   - Enter duration and distance
   - Click "Add Activity"
   - Should appear in "Recent Activities"

2. **From Activities Page**
   - Click "Activities" in sidebar
   - Fill complete activity form
   - Should see activity in the list

### Test Scenario 3: Browse Exercises

1. **Go to Exercises Page**
   - Click "Exercises" in sidebar
   - Should see default exercises loaded
   - Filter by difficulty
   - Click exercise to view details

### Test Scenario 4: Create Workout Routine

1. **Go to Workout Plans**
   - Click "Workout Plans" in sidebar
   - Fill in routine name
   - Add exercise sets
   - Click "Create Workout Plan"
   - Should appear in the list

### Test Scenario 5: View Reports

1. **Go to Reports**
   - Click "Reports" in sidebar
   - Should display charts with data
   - Modify date range
   - Charts should update

### Test Scenario 6: View Profile

1. **Go to Profile**
   - Click "Profile" in sidebar
   - View calculated BMI
   - Update profile information
   - Changes should be saved

---

## Troubleshooting

### Backend Won't Start

**Problem**: Port 8080 already in use

**Solution**:
```bash
# Change port in application.properties
# server.port=8081

# Or kill the process using port 8080
# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Mac/Linux:
lsof -i :8080
kill -9 <PID>
```

**Problem**: MongoDB connection failed

**Solution**:
```bash
# Verify MongoDB is running
# Windows:
Get-Service MongoDB

# Mac:
brew services list

# Linux:
sudo systemctl status mongod

# Check connection in application.properties
# Default: mongodb://localhost:27017/fitness_tracker_db
```

**Problem**: Build fails with dependency errors

**Solution**:
```bash
# Clear Maven cache
mvn clean

# Force update dependencies
mvn dependency:resolve -U

# Build with skip tests
mvn clean install -DskipTests
```

### Frontend Won't Load

**Problem**: Blank page or JavaScript console errors

**Solution**:
1. Check browser console (F12)
2. Ensure backend is running on localhost:8080
3. Check if using `http://` not `file://`
4. Clear browser cache (Ctrl+Shift+Delete)

**Problem**: Cannot log in or API calls failing

**Solution**:
- Verify backend is running: `http://localhost:8080/api/users`
- Check browser console for CORS errors
- Ensure using local server (not `file://`)
- Verify MongoDB is running

### MongoDB Issues

**Problem**: "connect ECONNREFUSED 127.0.0.1:27017"

**Solution**:
```bash
# Start MongoDB service
# Windows:
net start MongoDB

# Mac:
brew services start mongodb-community

# Linux:
sudo systemctl start mongod
```

**Problem**: Database not found

**Solution**:
```bash
# MongoDB creates databases automatically
# Run one API call to trigger creation
# Or manually create in Mongo shell:
mongo
use fitness_tracker_db
exit
```

### Performance Issues

**Problem**: Application runs slowly

**Solution**:
1. Close unused applications
2. Check system resources (RAM, CPU)
3. Enable Spring Boot debug logging
4. Check MongoDB collection indexes

---

## Environment Variables (Optional)

Create a `.env` file in the backend directory:

```properties
MONGODB_URI=mongodb://localhost:27017/fitness_tracker_db
SERVER_PORT=8080
LOG_LEVEL=INFO
```

Update `application.properties` to read from environment:

```properties
spring.data.mongodb.uri=${MONGODB_URI}
server.port=${SERVER_PORT}
logging.level.root=${LOG_LEVEL}
```

---

## Next Steps

1. ✅ Complete the setup
2. 📖 Read API_DOCUMENTATION.md for endpoint details
3. 🧪 Run test scenarios above
4. 🚀 Customize based on requirements
5. 📦 Deploy to production (see deployment guide)

---

## Support & Resources

- **Official Documentation**:
  - Spring Boot: https://spring.io/projects/spring-boot
  - MongoDB: https://docs.mongodb.com/
  - Chart.js: https://www.chartjs.org/docs/latest/

- **Tutorials**:
  - Spring Boot REST API: https://spring.io/guides
  - JavaScript Async/Await: https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous

- **Community**:
  - Stack Overflow
  - GitHub Issues
  - Spring Boot Forums

---

**Setup Complete!** 🎉

You're ready to use the Smart Fitness Tracker application.
