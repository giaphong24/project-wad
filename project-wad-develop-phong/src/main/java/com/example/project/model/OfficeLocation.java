package com.example.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "office_location")
public class OfficeLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "office_name")
    private String officeName;

    private String street;
    private String district;
    private String city;
    private String country;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "officeLocation")
    private List<Jobpost> jobposts;

    @Override
    public int hashCode() {
        return Objects.hash(id, officeName, street, district, city, country, createdAt, updatedAt,
            jobposts);
    }

    @Override
    public String toString() {
        return "OfficeLocation{" +
            "id=" + id +
            ", officeName='" + officeName + '\'' +
            ", street='" + street + '\'' +
            ", district='" + district + '\'' +
            ", city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", jobPosts=" + jobposts +
            '}';
    }

}
