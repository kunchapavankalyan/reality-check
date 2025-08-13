package com.TODO.TODO.Service;

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
    public void add_task(Todo_input todo_input)
    {
        Task task=new Task();
        task.setTitle(todo_input.getTitle());
        task.setDescription(todo_input.getDescription());
        task.setUpdatedAt(LocalDateTime.now());
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        todo_repository.save(task);
    }
    public void Update_task(Task task)
    {
        task.setUpdatedAt(LocalDateTime.now());
        todo_repository.save(task);
    }
    public Optional<Task> get_task(Long id)
    {
       return todo_repository.findById(id);
    }
    public void delete(Long id)
    {
        todo_repository.deleteById(id);
    }
}
