package com.example.project.controller;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.services.OfficeLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/office-location")
@RequiredArgsConstructor
public class OfficeLocationController {
    private final OfficeLocationService officeLocationService;

    @PostMapping
    public ResponseEntity<ResponseTemplate<OfficeLocationDto>> createOfficeLocation(
        @RequestBody @Valid OfficeLocationDto officeLocationDto
    ) {
        ResponseTemplate<OfficeLocationDto> response = officeLocationService
            .createOfficeLocation(officeLocationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
