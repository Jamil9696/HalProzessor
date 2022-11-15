import java.util.ArrayList;
import java.util.List;

public class ProgramMemory {

    private final List<Instruction> instructionList = new ArrayList<>();
    int programCounter = 0;

    public void push(Instruction instruction){
        instructionList.add(instruction);
    }

    public void pop(){
        if(!instructionList.isEmpty()){
            instructionList.remove(instructionList.size() -1);
        }
    }

    public void printMemory(){
        instructionList.forEach(instruction ->
                System.out.println(instruction.getInstructionName() + " " + instruction.getParameter() )
        );
    }







}
