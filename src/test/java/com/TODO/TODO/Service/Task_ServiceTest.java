package com.TODO.TODO.Service;

import com.TODO.TODO.Entity.Task;
import com.TODO.TODO.Entity.Todo_input;
import com.TODO.TODO.Exception.ResourseNotFoundException;
import com.TODO.TODO.Repository.Todo_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

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

    @Test
    void testUpdateTask()
    {
        Task existingTask=new Task();
        existingTask.setId(1L);
        existingTask.setTitle("work1");

        Task UpdatingTask=new Task();
        UpdatingTask.setTitle("work2");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(todoRepository.save(any(Task.class))).thenReturn(existingTask);

        Task updated=taskService.update_task(1L,UpdatingTask);

        assertEquals(1L,updated.getId());
        assertEquals("work2",updated.getTitle());
        assertNotEquals("work1",updated.getTitle());
        assertTrue(updated.isCompleted());
    }

    @Test
    void testDeleteTask()
    {
        Task existingTask=new Task();
        existingTask.setId(1L);
        existingTask.setTitle("apple");
        existingTask.setDescription("an apple a keeps a doctor away");
        existingTask.setCompleted(false);


        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        doNothing().when(todoRepository).deleteById(1L);

        taskService.delete(existingTask.getId());

        verify(todoRepository, times(1)).deleteById(1L);
        verify(todoRepository,never()).findById(1L);

    }
    @Test
    void testDeleteTask_NotFound() {
        Long id = 1L;

        // Arrange: no task found
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourseNotFoundException.class, () -> taskService.delete(id));

        // Verify interactions
        verify(todoRepository, times(1)).findById(id);
        verify(todoRepository, never()).deleteById(anyLong());
    }

}
