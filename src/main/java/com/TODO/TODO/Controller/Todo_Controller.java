package com.TODO.TODO.Controller;

import com.TODO.TODO.Entity.Task;
import com.TODO.TODO.Entity.Todo_input;
import com.TODO.TODO.Service.Task_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class Todo_Controller {

    private final Task_Service taskService;

    @Autowired
    public Todo_Controller(Task_Service taskService) {
        this.taskService = taskService;
    }

    // 1. Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.get_all_task();
    }

    // 2. Get a task by ID
    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable Long id) {
        return taskService.get_task(id);
    }

    // 3. Add a new task
    @PostMapping
    public Task addTask(@RequestBody Todo_input todoInput) {
        return taskService.add_Task(todoInput);
    }

    // 4. Update an existing task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        updatedTask.setId(id); // Ensure correct ID
        return taskService.updateTask(updatedTask);
    }

    // 5. Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task with ID " + id + " deleted successfully!";
    }
}
