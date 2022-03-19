package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;   //The name

    @NotNull
    @NotBlank
    @Column(name = "text")
    private String text;    //The question

    @NotEmpty
    @Column(name = "options")
    @Size(min = 2)
    @ElementCollection(targetClass = String.class)
    private List<String> options;

    @Column(name = "answer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private User user;  // User creator

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    @JsonProperty("answer")
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

}
