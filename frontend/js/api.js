/* API Service Functions */

const API_BASE_URL = 'http://localhost:8080/api';

async function apiCall(endpoint, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        }
    };

    if (data) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, options);
        if (!response.ok) {
            throw new Error(`API Error: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error('API Call Error:', error);
        throw error;
    }
}

// User API Functions
const UserAPI = {
    register: (user) => apiCall('/users/register', 'POST', user),
    getById: (id) => apiCall(`/users/${id}`),
    getByEmail: (email) => apiCall(`/users/email/${email}`),
    getByUsername: (username) => apiCall(`/users/username/${username}`),
    update: (id, user) => apiCall(`/users/${id}`, 'PUT', user),
    getAll: () => apiCall('/users'),
    delete: (id) => apiCall(`/users/${id}`, 'DELETE')
};

// Activity API Functions
const ActivityAPI = {
    create: (activity) => apiCall('/activities', 'POST', activity),
    getById: (id) => apiCall(`/activities/${id}`),
    getUserActivities: (userId) => apiCall(`/activities/user/${userId}`),
    getByType: (userId, type) => apiCall(`/activities/user/${userId}/type/${type}`),
    getByDateRange: (userId, startDate, endDate) => 
        apiCall(`/activities/user/${userId}/daterange?startDate=${startDate}&endDate=${endDate}`),
    update: (id, activity) => apiCall(`/activities/${id}`, 'PUT', activity),
    delete: (id) => apiCall(`/activities/${id}`, 'DELETE'),
    getStats: (userId, startDate, endDate) => 
        apiCall(`/activities/user/${userId}/stats?startDate=${startDate}&endDate=${endDate}`)
};

// Exercise API Functions
const ExerciseAPI = {
    create: (exercise) => apiCall('/exercises', 'POST', exercise),
    getById: (id) => apiCall(`/exercises/${id}`),
    getAll: () => apiCall('/exercises'),
    getByDifficulty: (difficulty) => apiCall(`/exercises/difficulty/${difficulty}`),
    getByTargetMuscles: (muscles) => apiCall(`/exercises/muscles/${muscles}`),
    update: (id, exercise) => apiCall(`/exercises/${id}`, 'PUT', exercise),
    delete: (id) => apiCall(`/exercises/${id}`, 'DELETE'),
    initialize: () => apiCall('/exercises/initialize', 'POST')
};

// Workout Routine API Functions
const WorkoutRoutineAPI = {
    create: (routine) => apiCall('/workout-routines', 'POST', routine),
    getById: (id) => apiCall(`/workout-routines/${id}`),
    getUserRoutines: (userId) => apiCall(`/workout-routines/user/${userId}`),
    update: (id, routine) => apiCall(`/workout-routines/${id}`, 'PUT', routine),
    delete: (id) => apiCall(`/workout-routines/${id}`, 'DELETE')
};

// Workout Recommendation API Functions
const RecommendationAPI = {
    generate: (userId, fitnessGoal) => apiCall(`/recommendations/${userId}/${fitnessGoal}`, 'POST'),
    getForUser: (userId) => apiCall(`/recommendations/user/${userId}`),
    update: (id, recommendation) => apiCall(`/recommendations/${id}`, 'PUT', recommendation),
    delete: (id) => apiCall(`/recommendations/${id}`, 'DELETE')
};
