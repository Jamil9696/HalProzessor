import java.util.List;

public class HalProcessor {


    private final ProgramMemory programMemory;
    private final Accumulator accumulator;

    public HalProcessor(ProgramMemory programMemory, Accumulator accumulator) {
        this.programMemory = programMemory;
        this.accumulator = accumulator;
    }

    public void initProgramMemory(List<String[]> params){
        params.forEach(param -> initProgramMemory(param[0], param[1]));
    }

    public void initProgramMemory(String instruction, String instructionParam) throws IllegalArgumentException{
        programMemory.push(new Instruction(InstructionSet.valueOf(instruction), instructionParam));
    }

    public void printMemory(){
        programMemory.printMemory();
    }

}
