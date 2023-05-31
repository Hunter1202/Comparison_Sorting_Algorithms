// Constants
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
const width = canvas.width;
const height = canvas.height;
const barWidth = 10;
const barSpacing = 5;
const animationSpeed = 50; // Delay in milliseconds between animation frames

// Global variables
let array = []; // Array to be sorted
let isSorting = false; // Flag to control the animation loop
let animationId = null; // ID of the animation frame

// Indices of the columns being swapped
let maxSwappedIndex = null;
let minSwappedIndex = null;

// Event listeners for buttons
document.getElementById('startButton').addEventListener('click', startAnimation);
document.getElementById('pauseButton').addEventListener('click', pauseAnimation);
document.getElementById('resetButton').addEventListener('click', resetAnimation);

// Initialize the array with random values
function initArray() {
    array = [];
    for (let i = 0; i < width / (barWidth + barSpacing); i++) {
        array.push(Math.floor(Math.random() * height));
    }
}

// Render the array on the canvas
function renderArray() {
    ctx.clearRect(0, 0, width, height);

    for (let i = 0; i < array.length; i++) {
        const x = i * (barWidth + barSpacing);
        const y = height - array[i];
        const color = isSorting ? '#ff0000' : '#ffdc0e';

        ctx.fillStyle = color;

        if (i === maxSwappedIndex || i === minSwappedIndex) {
            // Highlight the columns being swapped with a different color
            ctx.fillStyle = '#00ff00'; // Change the color as needed
        }

        ctx.fillRect(x, y, barWidth, array[i]);
    }
}

// Bubble Sort algorithm
async function bubbleSort() {
    const n = array.length;
    let swapped = false; // Flag to check if any swap occurred

    for (let i = 0; i < n - 1; i++) {
        swapped = false;

        for (let j = 0; j < n - i - 1; j++) {
            if (array[j] > array[j + 1]) {
                // Swap array[j] and array[j + 1]
                [array[j], array[j + 1]] = [array[j + 1], array[j]];
                swapped = true;
                maxSwappedIndex = j + 1; // Update the index of the maximum value being swapped
                minSwappedIndex = j; // Update the index of the minimum value being swapped

                renderArray(); // Render the array after each swap
                await sleep(animationSpeed); // Delay the execution to visualize the swap
            }
        }

        if (!swapped) {
            // If no swap occurred in the inner loop, the array is already sorted
            break;
        }
    }
}

// Animation loop
async function animate() {
    renderArray();
    if (isSorting) {
        await bubbleSort(); // Replace with your desired sorting algorithm
        isSorting = false; // Sorting completed, stop the animation
    }
    animationId = requestAnimationFrame(animate);
}

// Start the animation
function startAnimation() {
    if (!isSorting) {
        isSorting = true;
        initArray();
        animate();
    }
}

// Pause the animation
function pauseAnimation() {
    isSorting = false;
    cancelAnimationFrame(animationId);
}

// Reset the animation
function resetAnimation() {
    pauseAnimation();
    initArray();
    renderArray();
}

// Sleep function to introduce a delay
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// Initialize the canvas and array
function init() {
    canvas.width = window.innerWidth - 40; // Adjust canvas width as needed
    canvas.height = 400; // Adjust canvas height as needed
    initArray();
    renderArray();
}

// Initialize the application
init();
