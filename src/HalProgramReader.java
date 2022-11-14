import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


public class HalProgramReader {

    private File halProgram;
    private final HalProcessor halProzessor;


    public HalProgramReader(HalProcessor halProzessor) {

        this.halProzessor = halProzessor;



    }


    public void read_file(String filePath){

        if(!Files.exists(Path.of(filePath))){
            System.out.println("Invalid file path!");
            return;
        }

        halProgram = new File(filePath);
        try (final BufferedReader buffRead = new BufferedReader(new FileReader(halProgram))) {

            String line;
            while ((line = buffRead.readLine()) != null && !Objects.equals(line, "")) {
                String[] instructionParams = line.split(" ");
                createInstruction(instructionParams);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createInstruction(String[] instructionParams){

        if(instructionParams[0].chars().allMatch(Character::isDigit)){
            halProzessor.initProgramMemory(instructionParams[1], instructionParams[instructionParams.length-1]);
        }else{
            halProzessor.initProgramMemory(instructionParams[0], instructionParams[instructionParams.length-1]);
        }

    }


}
