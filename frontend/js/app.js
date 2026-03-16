/* Main Application Functions */

// Common utilities
function formatDate(date) {
    return new Date(date).toLocaleDateString();
}

function formatDateTime(date) {
    return new Date(date).toLocaleString();
}

function calculateBMI(height, weight) {
    return (weight / ((height / 100) * (height / 100))).toFixed(1);
}

function getBMIStatus(bmi) {
    if (bmi < 18.5) return 'Underweight';
    if (bmi < 25) return 'Normal Weight';
    if (bmi < 30) return 'Overweight';
    return 'Obese';
}

// Check authentication
function checkAuth() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        window.location.href = 'index.html';
    }
    return userId;
}

// Logout function
function logout() {
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    window.location.href = 'index.html';
}

// Show notification
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 15px 20px;
        background: ${type === 'success' ? '#27ae60' : '#e74c3c'};
        color: white;
        border-radius: 5px;
        z-index: 9999;
        animation: slideIn 0.3s ease;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Loading spinner
function showLoading(show = true) {
    let loader = document.getElementById('loader');
    if (!loader) {
        loader = document.createElement('div');
        loader.id = 'loader';
        loader.style.cssText = `
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 40px;
            height: 40px;
            border: 4px solid #f3f3f3;
            border-top: 4px solid #4ECDC4;
            border-radius: 50%;
            animation: spin 1s linear infinite;
            z-index: 9998;
        `;
        document.body.appendChild(loader);
    }
    loader.style.display = show ? 'block' : 'none';
}

// Input validation
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function validatePassword(password) {
    return password.length >= 6;
}

// Format numbers
function formatNumber(num) {
    return Math.round(num * 100) / 100;
}

// Initialize page
function initializePage() {
    const userId = localStorage.getItem('userId');
    const userName = localStorage.getItem('userName');
    
    const userNameDisplay = document.getElementById('userNameDisplay');
    if (userNameDisplay) {
        userNameDisplay.textContent = userName || 'User';
    }

    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', logout);
    }
}

// Set current date/time inputs
function setCurrentDateTime(inputId) {
    const input = document.getElementById(inputId);
    if (input && input.type === 'datetime-local') {
        input.valueAsDate = new Date();
    }
}

// Format calories display
function formatCalories(calories) {
    return Math.round(calories) + ' kcal';
}

// Format distance display
function formatDistance(distance) {
    return distance.toFixed(2) + ' km';
}

// Format duration display
function formatDuration(minutes) {
    const hours = Math.floor(minutes / 60);
    const mins = minutes % 60;
    if (hours > 0) {
        return hours + 'h ' + mins + 'm';
    }
    return mins + 'm';
}
