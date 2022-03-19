package engine.businesslayer;

import java.util.List;

public class UserAnswer {

    private List<Integer> answer;

    public UserAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public UserAnswer() {}

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public boolean isAnswerCorrect(List<Integer> correctAnswer) {
        if ((answer == null) || (correctAnswer == null)) return false;
        return (answer.containsAll(correctAnswer) && correctAnswer.containsAll(answer));
    }
}
