package io.melakuera.springvue.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "teachers")
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "teacher_teaching_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "teaching_subject")
    private Set<String> teachingSubjects; // Возможно потом будет enum
}
