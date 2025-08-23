package com.TODO.TODO.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Title cannot be blank")
    @Size(max=25,message="Title cannot exceed more than 25 characters")
    private String title;
    @NotBlank(message="description Cannot be blank")
    @Size(max=100,message="Description cannot exceed more than 100 characters")
    private String description;

    private boolean completed;
    @JsonFormat(pattern = "dd-MM-yyyy   HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy   HH:mm:ss")
    private LocalDateTime updatedAt;
}

