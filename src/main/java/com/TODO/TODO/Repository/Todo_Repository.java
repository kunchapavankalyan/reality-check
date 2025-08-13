package com.TODO.TODO.Repository;

import com.TODO.TODO.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Todo_Repository extends JpaRepository<Task,Long>
{
}
