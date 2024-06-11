package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobpost")
public class Jobpost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", nullable = false, columnDefinition = "TEXT")
    private String jobTitle;

    @Column(name = "position", nullable = false, columnDefinition = "TEXT")
    private String position;

    @Column(name = "requirements", nullable = false, columnDefinition = "TEXT")
    private String requirements;

    @Column(name = "type_of_employment", nullable = false, columnDefinition = "TEXT")
    private String typeOfEmployment;

    @Column(name = "salary_range", nullable = false)
    private int salaryRange;

    @Column(name = "contact_information", nullable = false, columnDefinition = "TEXT")
    private String contactInformation;

    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
    private String jobDescription;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
        name = "job_post_tag",
        joinColumns = @JoinColumn(name = "job_post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id",
            foreignKey = @ForeignKey(name = "post_tag_fk"))
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Tag> tags = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, position, requirements, typeOfEmployment, salaryRange,
            contactInformation, jobDescription, startDate, createdAt, updatedAt, tags);
    }

    public String toString() {
        return "JobPost{" +
            "id=" + id +
            ", jobTitle=" + jobTitle +
            ", position=" + position +
            ", requirements=" + requirements +
            ", typeOfEmployment=" + typeOfEmployment +
            ", salaryRange=" + salaryRange +
            ", contactInformation=" + contactInformation +
            ", jobDescription=" + jobDescription +
            ", startDate=" + startDate +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", tags=" + tags +
            "}";
    }
}
