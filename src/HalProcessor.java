public class HalProcessor {


    private final ProgramMemory programMemory;
    private final Accumulator accumulator;

    public HalProcessor(ProgramMemory programMemory, Accumulator accumulator) {
        this.programMemory = programMemory;
        this.accumulator = accumulator;
    }

    public void initProgramMemory(String instruction, String instructionParam) {
        programMemory.push(new Instruction(instruction, instructionParam));
    }
}
