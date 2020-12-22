package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    Integer id;
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
}
