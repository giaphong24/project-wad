package com.example.project.mapper;

import com.example.project.dto.post.JobPostRequest;
import com.example.project.dto.post.JobPostResponse;
import com.example.project.model.Jobpost;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = {TagMapper.class, OfficeLocationMapper.class}
)
public interface JobPostMapper {
    JobPostMapper INSTANCE = Mappers.getMapper(JobPostMapper.class);
    Jobpost toJobPostEntity(JobPostRequest request);
    JobPostRequest toJobPostToRequest(Jobpost jobpost);
    JobPostResponse toJobPostToResponse(Jobpost jobpost);
    List<JobPostResponse> toJobPostToResponseList(List<Jobpost> jobpostList);

}
