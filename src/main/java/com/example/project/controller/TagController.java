package com.example.project.controller;

import com.example.project.dto.TagDto;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ResponseTemplate<TagDto>> createTag(
        @RequestBody @Valid TagDto tagDto
    ) {
        ResponseTemplate<TagDto> response = tagService.createTag(tagDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate<TagDto>> deleteTag(@PathVariable Long id) {
        ResponseTemplate<TagDto> deletedTag = tagService.deleteTag(id);
        return ResponseEntity.ok(deletedTag);
    }

}
