package com.councilgrad.councilgrad.service;

import com.councilgrad.councilgrad.dto.CollegeCourseDto;
import com.councilgrad.councilgrad.dto.CourseCollegeDto;
import com.councilgrad.councilgrad.model.College;
import com.councilgrad.councilgrad.model.CollegeCourse;
import com.councilgrad.councilgrad.repository.CollegeCourseRepository;
import com.councilgrad.councilgrad.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;
    private final CollegeCourseRepository collegeCourseRepository;

    public CollegeService(CollegeRepository collegeRepository,
                          CollegeCourseRepository collegeCourseRepository) {
        this.collegeRepository = collegeRepository;
        this.collegeCourseRepository = collegeCourseRepository;
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public Optional<College> getCollegeById(Long id) {
        return collegeRepository.findById(id);
    }

    public College addCollege(College college) {
        return collegeRepository.save(college);
    }

    public College updateCollege(Long id, College updatedCollege) {
        return collegeRepository.findById(id)
                .map(c -> {
                    c.setName(updatedCollege.getName());
                    c.setLocation(updatedCollege.getLocation());
                    c.setWebsite(updatedCollege.getWebsite());
                    c.setDescription(updatedCollege.getDescription());
                    return collegeRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("College not found"));
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

    public List<CollegeCourseDto> getCoursesForCollege(Long collegeId) {
        List<CollegeCourse> ccList = collegeCourseRepository.findByCollegeId(collegeId);

        return ccList.stream()
                .map(cc -> CollegeCourseDto.builder()
                        .collegeCourseId(cc.getId())
                        .courseId(cc.getCourse().getId())
                        .programName(cc.getCourse().getProgram().getName())
                        .courseName(cc.getCourse().getName())
                        .feePerYear(cc.getFeePerYear())
                        .durationYears(cc.getDurationYears())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CourseCollegeDto> getCollegesForCourse(Long courseId) {
        List<CollegeCourse> ccList = collegeCourseRepository.findByCourseId(courseId);

        return ccList.stream()
                .map(cc -> CourseCollegeDto.builder()
                        .collegeCourseId(cc.getId())
                        .collegeId(cc.getCollege().getId())
                        .collegeName(cc.getCollege().getName())
                        .location(cc.getCollege().getLocation())
                        .feePerYear(cc.getFeePerYear())
                        .durationYears(cc.getDurationYears())
                        .build())
                .collect(Collectors.toList());
    }
}
