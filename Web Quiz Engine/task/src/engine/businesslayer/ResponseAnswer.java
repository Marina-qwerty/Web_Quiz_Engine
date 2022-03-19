package engine.businesslayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseAnswer {

    private boolean success;
    private String feedback;
    public static final String SUCCESS_MESSAGE = "Congratulations, you're right!";
    public static final String WRONG_MESSAGE = "Wrong answer! Please, try again.";

}
