package au.com.future-airlines.nonfunctional;

/**
 * Created by boses on 18/07/2018.
 */
public class AiOutput {

    private Integer PARTY_ID;
    private String ACTION_ID;
    private String Problem_Sample;
    private String Problem_Mean;
    private String Problem_StandardDeviation;

    public AiOutput() {
    }

    public Integer getPARTY_ID() {
        return PARTY_ID;
    }

    public void setPARTY_ID(Integer PARTY_ID) {
        this.PARTY_ID = PARTY_ID;
    }

    public String getACTION_ID() {
        return ACTION_ID;
    }

    public void setACTION_ID(String ACTION_ID) {
        this.ACTION_ID = ACTION_ID;
    }

    public String getProblem_Sample() {
        return Problem_Sample;
    }

    public void setProblem_Sample(String problem_Sample) {
        Problem_Sample = problem_Sample;
    }

    public String getProblem_Mean() {
        return Problem_Mean;
    }

    public void setProblem_Mean(String problem_Mean) {
        Problem_Mean = problem_Mean;
    }

    public String getProblem_StandardDeviation() {
        return Problem_StandardDeviation;
    }

    public void setProblem_StandardDeviation(String problem_StandardDeviation) {
        Problem_StandardDeviation = problem_StandardDeviation;
    }

    @Override
    public String toString() {
        return PARTY_ID +
                "|" + ACTION_ID +
                "|" + Problem_Sample +
                "|" + Problem_Mean +
                "|" + Problem_StandardDeviation
                ;
    }
}
