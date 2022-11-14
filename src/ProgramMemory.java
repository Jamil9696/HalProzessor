import java.util.ArrayList;
import java.util.List;

public class ProgramMemory {

    private static final List<Instruction> INSTRUCTION_LIST = new ArrayList<>();
    int programCounter = 0;

    public void push(Instruction instruction){
        INSTRUCTION_LIST.add(instruction);
    }
    public void pop(){
        if(!INSTRUCTION_LIST.isEmpty()){
            INSTRUCTION_LIST.remove(INSTRUCTION_LIST.size() -1);
        }
    }







}
