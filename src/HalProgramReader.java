import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HalProgramReader {

    private final List<String[]> instructionParams;

    public HalProgramReader() {
        instructionParams = new ArrayList<>();

    }


    public void read_file(String filePath) throws IOException {

        if(!Files.exists(Path.of(filePath))){       //check if file is valid
            System.out.println("Invalid file path!");
            return;
        }

        File halProgram = new File(filePath);
        try (final BufferedReader buffRead = new BufferedReader(new FileReader(halProgram))) {
            String line;
            while ((line = buffRead.readLine()) != null && !Objects.equals(line, "")) {
                String[] argv = line.split(" ");
                convertToInstruction(argv);
            }
        }

    }

    private void convertToInstruction(String[] args){

        if(args[0].chars().allMatch(Character::isDigit)){
            instructionParams.add(new String[]{args[1], args[args.length-1]});
        }else{
            instructionParams.add(new String[]{args[0], args[args.length-1]});
        }
    }

    public List<String[]> getInstructionParams(){
        return instructionParams;
    }


}
