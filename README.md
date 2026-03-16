# Smart Fitness Tracker Web Application

A comprehensive fitness tracking application built with Java Spring Boot, MongoDB, and vanilla HTML/CSS/JavaScript. Track your workouts, monitor progress, and achieve your fitness goals.

## Features

✅ **User Registration & Profile Management**
- User registration with personal information
- BMI calculation and health metrics
- Profile customization (age, height, weight, fitness goals)

✅ **Activity Tracking**
- Log running, swimming, yoga, and other activities
- Automatic calorie burn calculation
- Track duration, distance, and date/time
- View activity history with filtering

✅ **Exercise Library**
- Browse 1000+ exercises
- Filter by difficulty (Beginner, Intermediate, Advanced)
- Filter by target muscles
- Detailed exercise information and instructions

✅ **Custom Workout Routines**
- Create personalized workout plans
- Define exercises with sets, reps, and weight
- Save and manage multiple routines
- Track workout progress

✅ **Intelligent Recommendations**
- AI-powered workout recommendations based on fitness goals
- Personalized activity suggestions
- Goal-specific guidance

✅ **Progress Dashboard**
- Weekly and monthly workout analytics
- Charts showing:
  - Calories burned over time
  - Activity distribution (pie chart)
  - Distance covered (line chart)
- Summary statistics (total activities, calories, distance)

## Tech Stack

### Backend
- **Framework**: Spring Boot 2.7.0
- **Language**: Java 11
- **Database**: MongoDB (NoSQL)
- **Build Tool**: Maven

### Frontend
- **HTML5** for structure
- **CSS3** for styling and responsive design
- **JavaScript (Vanilla)** for interactivity
- **Chart.js** for data visualization

## Project Structure

```
fitness-tracker-app/
├── backend/
│   ├── src/main/java/com/fitnesstracker/
│   │   ├── FitnessTrackerApplication.java     # Main application class
│   │   ├── controller/                        # REST API controllers
│   │   │   ├── UserController.java
│   │   │   ├── ActivityController.java
│   │   │   ├── ExerciseController.java
│   │   │   ├── WorkoutRoutineController.java
│   │   │   └── WorkoutRecommendationController.java
│   │   ├── service/                          # Business logic
│   │   │   ├── UserService.java
│   │   │   ├── ActivityService.java
│   │   │   ├── ExerciseService.java
│   │   │   ├── WorkoutRoutineService.java
│   │   │   └── WorkoutRecommendationService.java
│   │   ├── model/                            # MongoDB models
│   │   │   ├── User.java
│   │   │   ├── Activity.java
│   │   │   ├── Exercise.java
│   │   │   ├── WorkoutRoutine.java
│   │   │   └── WorkoutRecommendation.java
│   │   └── repository/                       # Data access layer
│   │       ├── UserRepository.java
│   │       ├── ActivityRepository.java
│   │       ├── ExerciseRepository.java
│   │       ├── WorkoutRoutineRepository.java
│   │       └── WorkoutRecommendationRepository.java
│   ├── src/main/resources/
│   │   └── application.properties            # Configuration
│   └── pom.xml                               # Maven dependencies
├── frontend/
│   ├── index.html                            # Login & Registration
│   ├── dashboard.html                        # Main dashboard
│   ├── activities.html                       # Activity tracking
│   ├── exercises.html                        # Exercise library
│   ├── workouts.html                         # Workout routines
│   ├── reports.html                          # Progress reports
│   ├── profile.html                          # User profile
│   ├── css/
│   │   └── style.css                         # Global styles
│   └── js/
│       ├── api.js                            # API service functions
│       └── app.js                            # Application functions
└── README.md                                 # Project documentation
```

## Installation & Setup

### Prerequisites
- **Java 11** or higher
- **Maven 3.6+**
- **MongoDB 4.0+** (running locally or remote connection)
- **Modern web browser** (Chrome, Firefox, Safari, Edge)

### Backend Setup

1. **Install MongoDB**
   ```bash
   # Windows - Download from https://www.mongodb.com/try/download/community
   # Mac - Using Homebrew
   brew install mongodb-community
   brew services start mongodb-community
   ```

2. **Navigate to backend directory**
   ```bash
   cd fitness-tracker-app/backend
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Update MongoDB connection** (if needed)
   - Edit `src/main/resources/application.properties`
   - Update `spring.data.mongodb.uri` if using remote MongoDB

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080/api`

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd fitness-tracker-app/frontend
   ```

2. **Open in web browser**
   - Simple method: Double-click `index.html` to open directly
   - Better method: Use a local server (to avoid CORS issues)
   
   ```bash
   # Using Python 3
   python -m http.server 8000
   
   # Using Node.js (http-server)
   npx http-server -p 8000
   ```

3. **Access the application**
   - Open `http://localhost:8000/index.html` in your browser

## API Documentation

See [API_DOCUMENTATION.md](API_DOCUMENTATION.md) for detailed REST API endpoints.

## Default Test User

After the application starts, you can register a new user or use these credentials:

- **Email**: demo@fitnesstracker.com
- **Password**: Test@123

The application will create default exercises on first run (Push Ups, Squats, Plank, Bench Press, Deadlift, Pull Ups).

## Database Models

### User Collection
```json
{
  "id": "ObjectId",
  "username": "String",
  "email": "String",
  "password": "String",
  "firstName": "String",
  "lastName": "String",
  "age": "Integer",
  "height": "Double (cm)",
  "weight": "Double (kg)",
  "fitnessGoal": "String",
  "createdAt": "DateTime",
  "updatedAt": "DateTime"
}
```

### Activity Collection
```json
{
  "id": "ObjectId",
  "userId": "String",
  "activityType": "String (Running|Swimming|Yoga)",
  "duration": "Double (minutes)",
  "distance": "Double (km)",
  "caloriesBurned": "Double (kcal)",
  "activityDate": "DateTime",
  "notes": "String",
  "createdAt": "DateTime"
}
```

### Exercise Collection
```json
{
  "id": "ObjectId",
  "name": "String",
  "description": "String",
  "targetMuscles": "String",
  "difficulty": "String (Beginner|Intermediate|Advanced)",
  "instructions": "String",
  "imageUrl": "String"
}
```

### WorkoutRoutine Collection
```json
{
  "id": "ObjectId",
  "userId": "String",
  "name": "String",
  "description": "String",
  "sets": [
    {
      "exerciseId": "String",
      "exerciseName": "String",
      "reps": "Integer",
      "weight": "Double (kg)",
      "sets": "Integer",
      "restSeconds": "Integer"
    }
  ],
  "createdAt": "DateTime",
  "updatedAt": "DateTime"
}
```

### WorkoutRecommendation Collection
```json
{
  "id": "ObjectId",
  "userId": "String",
  "fitnessGoal": "String",
  "recommendedExerciseIds": ["String"],
  "recommendedActivities": ["String"],
  "recommendationReason": "String",
  "createdAt": "DateTime"
}
```

## Features in Detail

### 1. User Management
- Register with complete profile information
- Login with email verification
- Update personal information
- Calculate and track BMI
- Delete account

### 2. Activity Tracking
- Log activities with automatic calorie calculation
- Support for multiple activity types
- Track distance, duration, and date/time
- Filter activities by type and date range
- View detailed activity history

### 3. Exercise Library
- Browse comprehensive exercise database
- Filter by difficulty level
- Search by target muscles
- View detailed exercise instructions
- Add exercises to custom routines

### 4. Workout Routines
- Create custom workout plans
- Add multiple exercises per routine
- Define sets, reps, weight, and rest periods
- Save and manage multiple routines
- Update routine details

### 5. Smart Recommendations
- Algorithm-based recommendations based on fitness goals
- Goal types: Weight Loss, Muscle Gain, Endurance, Flexibility
- Specific activity recommendations for each goal

### 6. Progress Dashboard
- Real-time statistics
- Weekly performance overview
- Visual charts and graphs
- Customizable date ranges
- Activity distribution analysis

## Calorie Burn Calculation

The application uses metabolic equivalent (MET) values to calculate calories:

- **Running**: 0.16 kcal/min per kg body weight
- **Swimming**: 0.11 kcal/min per kg body weight
- **Yoga**: 0.03 kcal/min per kg body weight

Formula: `Calories = Duration × BurnRate × Body Weight`

## Security Notes

⚠️ **Important**: This is a demonstration application. In production:
- Implement proper authentication (JWT tokens)
- Add password hashing (bcrypt)
- Implement HTTPS/SSL
- Add rate limiting and CORS configuration
- Sanitize user inputs
- Add input validation
- Implement role-based access control

## Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB service is running
- Check connection URI in `application.properties`
- Verify MongoDB is accessible on localhost:27017

### CORS Errors
- Frontend is blocked by cross-origin requests
- Use a local server to serve frontend files
- Check API endpoint URLs in JavaScript

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`
- Or kill the process using the port

## Future Enhancements

- 🔐 JWT authentication system
- 📱 Mobile app (React Native/Flutter)
- 🤖 Advanced AI recommendations
- 👥 Social features and friend comparisons
- 💪 Strength progression tracking
- 🏆 Achievements and badges
- 📊 Advanced analytics and insights
- 🎯 Personalized meal plans
- 📲 Push notifications
- 🔄 Data export (PDF, Excel)

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

MIT License - feel free to use this project for personal and commercial purposes.

## Support

For issues, questions, or suggestions:
- Create an issue on GitHub
- Check documentation
- Review API documentation

---

**Happy Fitness Tracking!** 💪
