package com.example.project.controller;

import com.example.project.dto.post.JobPostRequest;
import com.example.project.dto.post.JobPostResponse;
import com.example.project.dto.template.ResponseTemplate;
import com.example.project.model.Jobpost;
import com.example.project.services.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.repository.JobpostRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
@RequestMapping("/jobposts")
public class JobpostController {
    private final JobpostRepository jobpostRepository;

    private final JobPostService jobPostService;

    @GetMapping({"", "/"})
    public String showProductList (Model model) {
        List<Jobpost> jobPosts = jobpostRepository.findAll();
        model.addAttribute("jobPosts", jobPosts);
        return "jobposts/index";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<JobPostResponse>> createJobPost(
        @RequestBody @Valid JobPostRequest request
    ) {
        ResponseTemplate<JobPostResponse> jobPostResponseDto = jobPostService.createJobPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPostResponseDto);
    }

}
