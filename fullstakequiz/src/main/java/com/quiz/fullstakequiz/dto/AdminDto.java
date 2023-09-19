package com.quiz.fullstakequiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String adminName;
    private String token;
}
