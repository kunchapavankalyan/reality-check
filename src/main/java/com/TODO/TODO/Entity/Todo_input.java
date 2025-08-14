package com.TODO.TODO.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo_input
{
    @NotBlank(message="Title cannot be blank")
    @Size(max=25, message="title cannot exceed more than 100 characters")
    private String title;
    @NotBlank(message="description cannot be blank")
    @Size(max=25,message="description cannot exceed more than 100 characters")
    private String description;
}
