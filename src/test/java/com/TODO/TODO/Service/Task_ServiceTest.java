package com.TODO.TODO.Service;

import com.TODO.TODO.Entity.Task;
import com.TODO.TODO.Entity.Todo_input;
import com.TODO.TODO.Repository.Todo_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Task_ServiceTest {

    @Mock
    private Todo_Repository todoRepository;

    @InjectMocks
    private Task_Service taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask() {
        Todo_input input = new Todo_input();
        input.setTitle("Test Title");
        input.setDescription("Test Description");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Test Title");
        savedTask.setDescription("Test Description");

        when(todoRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.add_task(input);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        verify(todoRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.get_task(1L);

        assertTrue(result.isPresent());
        assertEquals("Sample", result.get().getTitle());
        verify(todoRepository, times(1)).findById(1L);
    }
}
