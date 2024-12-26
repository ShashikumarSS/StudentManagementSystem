package com.cts.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "Course name is mandatory")
    private String courseName;

    @Min(value = 1, message = "Credit hours should be at least 1")
    @Max(value = 6, message = "Credit hours should not be more than 6")
    private int creditHours;
}
