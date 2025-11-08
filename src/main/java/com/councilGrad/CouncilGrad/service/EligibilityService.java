package com.councilGrad.CouncilGrad.service;

import com.councilGrad.CouncilGrad.model.College;
import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EligibilityService {

    private final CollegeRepository collegeRepo;
    private final AIService aiService;

    public EligibilityService(CollegeRepository collegeRepo, AIService aiService) {
        this.collegeRepo = collegeRepo;
        this.aiService = aiService;
    }

    public String getEligibleColleges(Student student) {
        // Step 1: Get relevant colleges (same course)
        List<College> colleges = collegeRepo.findByCourse(student.getCourse());

        // Step 2: Prepare prompt for GenAI
        String prompt = buildPrompt(student, colleges);

        // Step 3: Send to AI service and return response
        return aiService.getAIResponse(prompt);
    }

    private String buildPrompt(Student s, List<College> colleges) {
        StringBuilder builder = new StringBuilder();
        builder.append("You are a college recommendation AI.\n\n");
        builder.append("Student details:\n");
        builder.append(String.format("- 10th Marks: %.2f\n- 12th Marks: %.2f\n- Category: %s\n- Budget: %d\n- Course: %s\n\n",
                s.getMarks10(), s.getMarks12(), s.getCategory(), s.getBudget(), s.getCourse()));
        builder.append("College data:\n");

        colleges.forEach(c -> builder.append(
                String.format("{name: '%s', min_10th: %.2f, min_12th: %.2f, category: %s, fees: %d}\n",
                        c.getCollegeName(), c.getMinMarks10(), c.getMinMarks12(), c.getCategory(), c.getFees())));

        builder.append("\nTask: Return a JSON array of eligible colleges with reasoning. Example:\n");
        builder.append("[{\"college\":\"ABC College\", \"reason\":\"Eligible based on marks and budget\"}]\n");

        return builder.toString();
    }
}

