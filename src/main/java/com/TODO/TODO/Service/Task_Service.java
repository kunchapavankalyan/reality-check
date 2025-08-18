package com.TODO.TODO.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.TODO.TODO.Entity.Task;
import com.TODO.TODO.Entity.Todo_input;
import com.TODO.TODO.Repository.Todo_Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class Task_Service
{
    public final Todo_Repository todo_repository;
    @Autowired
    public Task_Service(Todo_Repository todo_repository)
    {
       this.todo_repository=todo_repository;
    }
    public List<Task> get_all_task()
    {
        return  todo_repository.findAll();
    }
    public Task add_task(Todo_input todo_input)
    {
        Task task=new Task();
        task.setTitle(todo_input.getTitle());
        task.setDescription(todo_input.getDescription());
        task.setUpdatedAt(LocalDateTime.now());
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        return todo_repository.save(task);
    }
    public Task update_task(Long id, Task updatedTask) {
        Task existingTask = todo_repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Update only the fields you send
        if (updatedTask.getTitle() != null)
        {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null)
        {
            existingTask.setDescription(updatedTask.getDescription());
        }
        existingTask.setCompleted(true);

        // Keep original createdAt
        existingTask.setCreatedAt(existingTask.getCreatedAt());

        // Update the updatedAt to now
        existingTask.setUpdatedAt(LocalDateTime.now());

        return todo_repository.save(existingTask);
    }

    public Optional<Task> get_task(Long id)
    {
       return todo_repository.findById(id);
    }
    public void delete(Long id)
    {
        todo_repository.deleteById(id);
    }

    public Page<Task> GetTaskWithPagingAndSorting(int page,int size,String sortby,String sortdir)
    {
        Sort sort=sortdir.equalsIgnoreCase("asc")? Sort.by(sortby).ascending():Sort.by(sortby).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return todo_repository.findAll(pageable);
    }
}
