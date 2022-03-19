package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "passed_quizzes")
public class PassedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private long quizId;

    @JsonIgnore
    private String username;

    @JsonProperty(value = "completedAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp timestamp;

    public PassedQuiz(Long quizId, String username, long completedAt) {
        this.quizId = quizId;
        this.username = username;
        this.timestamp = new Timestamp(completedAt);
    }
    public PassedQuiz() {}
}
