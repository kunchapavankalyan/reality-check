package com.TODO.TODO.Controller;

import com.TODO.TODO.Entity.Task;
import com.TODO.TODO.Entity.Todo_input;
import com.TODO.TODO.Exception.ResourseNotFoundException;
import com.TODO.TODO.Service.Task_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Todo_Controller {

    private final Task_Service taskService;

    @Autowired
    public Todo_Controller(Task_Service taskService)
    {
        this.taskService = taskService;
    }

    // 1. Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.get_all_task();
    }

    // 2. Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task= taskService.get_task(id)
                .orElseThrow(() -> new ResourseNotFoundException("The Task which you are looking for does not exist:" +id));
        return ResponseEntity.ok(task);
    }

    // 3. Add a new task
    @PostMapping
    public Task addTask(@Valid @RequestBody Todo_input todoInput)
    {
        return taskService.add_task(todoInput);
    }

    // 4. Update an existing task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        updatedTask.setId(id); // Ensure correct ID
        return taskService.update_task(id,updatedTask);
    }

    // 5. Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "Task with ID " + id + " deleted successfully!";
    }
    @GetMapping("/pagesort")
    public Page<Task> GetPaginationAndSorting
            (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String SortBy,
            @RequestParam(defaultValue = "asc") String sortDir
            )
    {
        return taskService.GetTaskWithPagingAndSorting(page,size,SortBy,sortDir);
    }

}
