package com.example.project.controller;

import com.example.project.dto.post.OfficeLocationDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.services.OfficeLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/office-location")
@RequiredArgsConstructor
public class OfficeLocationController {
    private final OfficeLocationService officeLocationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<OfficeLocationDto>> createOfficeLocation(
        @RequestBody @Valid OfficeLocationDto officeLocationDto
    ) {
        ResponseTemplate<OfficeLocationDto> response = officeLocationService
            .createOfficeLocation(officeLocationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseTemplate<OfficeLocationDto>> updateOfficeLocation(
            @PathVariable Long id,
            @RequestBody @Valid OfficeLocationDto officeLocationDto
    ){
        ResponseTemplate<OfficeLocationDto> updatedOfficeLocation = officeLocationService.updateOfficeLocation(id, officeLocationDto);
        return ResponseEntity.ok(updatedOfficeLocation);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate<OfficeLocationDto>> deleteOfficeLocation(@PathVariable Long id){
        ResponseTemplate<OfficeLocationDto> deletedOfficeLocation = officeLocationService.deleteOfficeLocation(id);
        return ResponseEntity.ok(deletedOfficeLocation);
    }
}
