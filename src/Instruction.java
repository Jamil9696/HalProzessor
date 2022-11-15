import java.util.Optional;

public class Instruction {

    private final String instruction;
    private String parameter= "";

    public Instruction(String instruction, String instructionParam) {
        this.instruction = instruction;
        if(!instruction.equals(instructionParam)) //if equal -> no params given
            this.parameter = instructionParam;    //if not -> set params
    }

    public String getInstructionName() {
        return instruction;
    }

    public String getParameter() {
        return parameter;
    }
}
