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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.repository.JobpostRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/jobposts")
public class JobpostController {
    private final JobpostRepository jobpostRepository;

    private final JobPostService jobPostService;

    @GetMapping({"", "/"})
    public String showJobPostList (Model model) {
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

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseTemplate<JobPostResponse>> updateJobPost(
        @PathVariable Long id,
        @RequestBody
        @Valid JobPostRequest request
    ) {
        ResponseTemplate<JobPostResponse> updatedJobPost = jobPostService.updateJobPost(id,request);
        return ResponseEntity.ok(updatedJobPost);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate<JobPostResponse>> deleteJobPost(@PathVariable Long id){
        ResponseTemplate<JobPostResponse> response = jobPostService.deleteJobPost(id);
        return ResponseEntity.ok(response);
    }

}
