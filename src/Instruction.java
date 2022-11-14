import java.util.Optional;

public class Instruction {

    private final String instructionName;
    private String parameter;

    public Instruction(String instructionName) {
        this.instructionName = instructionName;
    }

    public Instruction(String instructionName, String instructionParam) {
        this.instructionName = instructionName;
        this.parameter = instructionParam;
    }

    public String getInstructionName() {
        return instructionName;
    }

    public String getParameter() {
        return Optional.of(parameter).orElse("");
    }
}
