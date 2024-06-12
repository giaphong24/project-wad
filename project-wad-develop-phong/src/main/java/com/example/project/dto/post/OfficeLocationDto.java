package com.example.project.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeLocationDto implements Serializable {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    private String officeName;
    private String street;
    private String district;
    private String city;
    private String country;

}
