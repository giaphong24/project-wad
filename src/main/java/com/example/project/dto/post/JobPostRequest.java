package com.example.project.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostRequest implements Serializable {
    @NotBlank(message = "Job tile is required")
    private String jobTitle;

    private String position;

    private String requirements;

    private String typeOfEmployment;

    private int salaryRange;

    private String contactInformation;

    private String jobDescription;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or future")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    private List<Long> tagId;



}
