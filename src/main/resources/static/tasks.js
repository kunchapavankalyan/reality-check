const API_URL = "/api/tasks";  // Backend endpoint

document.addEventListener("DOMContentLoaded", () => {
    fetchTasks();
    document.getElementById("taskForm").addEventListener("submit", addTask);
});

// Fetch all tasks from backend
async function fetchTasks() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Failed to fetch tasks");

        const tasks = await response.json();
        const taskList = document.getElementById("taskList");
        taskList.innerHTML = "";

        tasks.forEach(task => {
            const li = document.createElement("li");
            li.innerHTML = `${task.title} - ${task.priority}
                <button onclick="deleteTask(${task.id})">Delete</button>`;
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error("Error fetching tasks:", error);
    }
}

// Add a new task
async function addTask(event) {
    event.preventDefault();
    const title = document.getElementById("taskTitle").value;
    const priority = document.getElementById("taskPriority").value;

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ title, priority, status: "PENDING" })  // Ensure status is passed
        });

        if (!response.ok) throw new Error("Failed to add task");

        document.getElementById("taskTitle").value = "";
        fetchTasks();  // Refresh task list
    } catch (error) {
        console.error("Error adding task:", error);
    }
}

// Delete a task
async function deleteTask(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Failed to delete task");
        fetchTasks();  // Refresh task list
    } catch (error) {
        console.error("Error deleting task:", error);
    }
}
