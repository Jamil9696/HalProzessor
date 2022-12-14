
public class Instruction {

    private final InstructionSet instruction;
    private String parameter= "";

    public Instruction(InstructionSet instruction, String instructionParam) {
        this.instruction = instruction;
        if(!instruction.toString().equals(instructionParam)) //if equal -> no params given
            this.parameter = instructionParam;    //if not -> set params
    }

    public InstructionSet getInstructionTyp() {
        return instruction;
    }

    public String registerNumber() {
        return parameter;
    }
}
