# REST API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication
Currently, the API uses simple email-based authentication. In production, implement JWT tokens.

---

## User Endpoints

### Register User
**POST** `/users/register`

Register a new user account.

**Request Body:**
```json
{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "secure123",
  "firstName": "John",
  "lastName": "Doe",
  "age": 28,
  "height": 180.5,
  "weight": 75.5,
  "fitnessGoal": "Muscle Gain"
}
```

**Response (201 Created):**
```json
{
  "message": "User registered successfully",
  "user": {
    "id": "507f1f77bcf86cd799439011",
    "username": "johndoe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "age": 28,
    "height": 180.5,
    "weight": 75.5,
    "fitnessGoal": "Muscle Gain",
    "createdAt": "2024-03-16T10:30:00",
    "updatedAt": "2024-03-16T10:30:00"
  }
}
```

### Get User by ID
**GET** `/users/{id}`

Retrieve user information by user ID.

**Response (200 OK):**
```json
{
  "id": "507f1f77bcf86cd799439011",
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "age": 28,
  "height": 180.5,
  "weight": 75.5,
  "fitnessGoal": "Muscle Gain",
  "createdAt": "2024-03-16T10:30:00",
  "updatedAt": "2024-03-16T10:30:00"
}
```

### Get User by Email
**GET** `/users/email/{email}`

Retrieve user by email address.

### Get User by Username
**GET** `/users/username/{username}`

Retrieve user by username.

### Update User
**PUT** `/users/{id}`

Update user profile information.

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "age": 29,
  "height": 181.0,
  "weight": 76.0,
  "fitnessGoal": "Weight Loss"
}
```

### Get All Users
**GET** `/users`

Retrieve list of all users.

### Delete User
**DELETE** `/users/{id}`

Delete a user account and all associated data.

---

## Activity Endpoints

### Create Activity
**POST** `/activities`

Log a new activity.

**Request Body:**
```json
{
  "userId": "507f1f77bcf86cd799439011",
  "activityType": "Running",
  "duration": 45,
  "distance": 7.5,
  "activityDate": "2024-03-16T07:00:00",
  "notes": "Morning run in the park",
  "caloriesBurned": 0
}
```

**Response (201 Created):**
```json
{
  "message": "Activity created successfully",
  "activity": {
    "id": "507f1f77bcf86cd799439012",
    "userId": "507f1f77bcf86cd799439011",
    "activityType": "Running",
    "duration": 45,
    "distance": 7.5,
    "caloriesBurned": 431.25,
    "activityDate": "2024-03-16T07:00:00",
    "notes": "Morning run in the park",
    "createdAt": "2024-03-16T10:30:00"
  }
}
```

### Get Activity by ID
**GET** `/activities/{id}`

Retrieve a specific activity.

### Get User Activities
**GET** `/activities/user/{userId}`

Retrieve all activities for a user.

### Get Activities by Type
**GET** `/activities/user/{userId}/type/{activityType}`

Filter activities by type (Running, Swimming, Yoga, etc.).

### Get Activities by Date Range
**GET** `/activities/user/{userId}/daterange?startDate=2024-03-01T00:00:00&endDate=2024-03-31T23:59:59`

Retrieve activities within a date range.

**Response:**
```json
{
  "activities": [...],
  "count": 15
}
```

### Get Activity Statistics
**GET** `/activities/user/{userId}/stats?startDate=2024-03-01T00:00:00&endDate=2024-03-31T23:59:59`

Get aggregated statistics for a date range.

**Response:**
```json
{
  "totalCalories": 18450.75,
  "totalDistance": 125.5,
  "activities": [...],
  "startDate": "2024-03-01T00:00:00",
  "endDate": "2024-03-31T23:59:59"
}
```

### Update Activity
**PUT** `/activities/{id}`

Update activity details.

### Delete Activity
**DELETE** `/activities/{id}`

Remove an activity record.

---

## Exercise Endpoints

### Create Exercise
**POST** `/exercises`

Add a new exercise to the library.

**Request Body:**
```json
{
  "name": "Barbell Squat",
  "description": "Full body compound exercise",
  "targetMuscles": "Quads, Hamstrings, Glutes, Core",
  "difficulty": "Intermediate",
  "instructions": "Position bar on shoulders...",
  "imageUrl": "https://example.com/image.jpg"
}
```

### Get Exercise by ID
**GET** `/exercises/{id}`

Retrieve exercise details.

### Get All Exercises
**GET** `/exercises`

Retrieve all exercises in the library.

### Get Exercises by Difficulty
**GET** `/exercises/difficulty/{difficulty}`

Filter by difficulty level (Beginner, Intermediate, Advanced).

### Get Exercises by Target Muscles
**GET** `/exercises/muscles/{targetMuscles}`

Filter exercises by target muscle groups.

### Update Exercise
**PUT** `/exercises/{id}`

Update exercise information.

### Delete Exercise
**DELETE** `/exercises/{id}`

Remove an exercise from the library.

### Initialize Default Exercises
**POST** `/exercises/initialize`

Populate database with default exercises.

---

## Workout Routine Endpoints

### Create Workout Routine
**POST** `/workout-routines`

Create a custom workout routine.

**Request Body:**
```json
{
  "userId": "507f1f77bcf86cd799439011",
  "name": "Leg Day Workout",
  "description": "Intensive lower body training",
  "sets": [
    {
      "exerciseName": "Barbell Squat",
      "reps": 8,
      "sets": 4,
      "weight": 100,
      "restSeconds": 90
    },
    {
      "exerciseName": "Leg Press",
      "reps": 10,
      "sets": 3,
      "weight": 150,
      "restSeconds": 60
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "message": "Workout routine created successfully",
  "routine": {
    "id": "507f1f77bcf86cd799439013",
    "userId": "507f1f77bcf86cd799439011",
    "name": "Leg Day Workout",
    "description": "Intensive lower body training",
    "sets": [...],
    "createdAt": "2024-03-16T10:30:00",
    "updatedAt": "2024-03-16T10:30:00"
  }
}
```

### Get Workout Routine by ID
**GET** `/workout-routines/{id}`

Retrieve a specific routine.

### Get User Workout Routines
**GET** `/workout-routines/user/{userId}`

Retrieve all routines for a user.

### Update Workout Routine
**PUT** `/workout-routines/{id}`

Update routine details and exercises.

### Delete Workout Routine
**DELETE** `/workout-routines/{id}`

Remove a routine.

---

## Workout Recommendation Endpoints

### Generate Recommendation
**POST** `/recommendations/{userId}/{fitnessGoal}`

Generate personalized workout recommendations.

**Path Parameters:**
- `userId`: User ID
- `fitnessGoal`: "Weight Loss", "Muscle Gain", "Endurance", or "Flexibility"

**Response (201 Created):**
```json
{
  "message": "Recommendation generated successfully",
  "recommendation": {
    "id": "507f1f77bcf86cd799439014",
    "userId": "507f1f77bcf86cd799439011",
    "fitnessGoal": "Weight Loss",
    "recommendedExerciseIds": [...],
    "recommendedActivities": ["Running", "Swimming", "Cycling"],
    "recommendationReason": "High intensity cardio activities are ideal for weight loss",
    "createdAt": "2024-03-16T10:30:00"
  }
}
```

### Get Recommendation for User
**GET** `/recommendations/user/{userId}`

Retrieve the latest recommendation for a user.

### Update Recommendation
**PUT** `/recommendations/{id}`

Update recommendation details.

### Delete Recommendation
**DELETE** `/recommendations/{id}`

Remove a recommendation.

---

## Error Responses

### 400 Bad Request
```json
{
  "error": "Invalid request data"
}
```

### 404 Not Found
```json
{
  "error": "User not found"
}
```

### 500 Internal Server Error
```json
{
  "error": "Internal server error"
}
```

---

## Rate Limiting

Currently not implemented. To be added in production.

## CORS

The API allows requests from all origins. In production, restrict to specific domains.

## Pagination

Not currently implemented. To be added for large datasets.

---

## Example Usage (JavaScript/Fetch)

### Register User
```javascript
const user = {
  username: "johndoe",
  email: "john@example.com",
  password: "secure123",
  firstName: "John",
  lastName: "Doe",
  age: 28,
  height: 180.5,
  weight: 75.5,
  fitnessGoal: "Muscle Gain"
};

fetch('http://localhost:8080/api/users/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(user)
})
.then(res => res.json())
.then(data => console.log(data));
```

### Log Activity
```javascript
const activity = {
  userId: "507f1f77bcf86cd799439011",
  activityType: "Running",
  duration: 45,
  distance: 7.5,
  activityDate: new Date().toISOString(),
  notes: "Morning run"
};

fetch('http://localhost:8080/api/activities', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(activity)
})
.then(res => res.json())
.then(data => console.log(data));
```

### Get User Activities
```javascript
const userId = "507f1f77bcf86cd799439011";

fetch(`http://localhost:8080/api/activities/user/${userId}`)
.then(res => res.json())
.then(data => console.log(data));
```

---

Last Updated: March 16, 2024
