const toggleButton = document.getElementById('toggleNavbarForm');
const form = document.getElementById('form');
const navbar = document.querySelector('.navbar');
const addMealButton = document.getElementById('add');

// Function to show the form and hide the navbar
toggleButton.addEventListener('click', () => {
    navbar.style.display = 'none'; // Hide navbar
    form.classList.remove('hidden'); // Show form
});

// Function to handle form submission and toggle back
addMealButton.addEventListener('click', (event) => {
    event.preventDefault(); // Prevent form submission from refreshing the page
    form.classList.add('hidden'); // Hide form
    navbar.style.display = 'flex'; // Show navbar again
});