import java.util.Optional;

public class Instruction {

    private final String instruction;
    private String parameter;

    public Instruction(String instruction, String instructionParam) {
        this.instruction = instruction;
        if(!instruction.equals(instructionParam))
        this.parameter = instructionParam;
    }

    public String getInstructionName() {
        return instruction;
    }

    public String getParameter() {
        return Optional.ofNullable(parameter).orElse("");
    }
}
