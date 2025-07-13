package com.kidschool.serviceabsence.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String nom;
    private String prenom;
}
