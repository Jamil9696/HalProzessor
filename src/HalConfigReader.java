import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class HalConfigReader {

    private final Map<String,List<String[]>> instructionLists;
    private final List<String> filePaths;
    private final List<String[]> edges; // of graph


    public HalConfigReader() {
        instructionLists = new TreeMap<>();
        filePaths = new ArrayList<>();
        edges = new ArrayList<>();
    }



    public void readConfigurationFile( String args){

        try{
          precompileFile(args);
          for (String filePath : filePaths) {
               readFile(filePath);
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void precompileFile( String filePath) throws IOException {

        if(!checkFilePath(filePath)) return;


        File halProgram = new File(filePath);
        try (final BufferedReader buffRead = new BufferedReader(new FileReader(halProgram))) {
            String line;
            while ((line = buffRead.readLine()) != null) {

                if(line.contains("#"))
                    line = line.substring(0, line.indexOf("#"));

                if(line.contains("C:")){
                    filePaths.add(line);
                    continue;
                }

                if(line.contains("<") || line.contains(">")){
                    String[] edge = line.split(" ");
                    edges.add(edge);
                }
                    ;
            }
        }
    }


    private void readFile(String filePath) throws IOException {

        if(!checkFilePath(filePath)) return;
        String key = "p" + instructionLists.size();
        instructionLists.put(key, new ArrayList<>());

        File halProgram = new File(filePath);
        try (final BufferedReader buffRead = new BufferedReader(new FileReader(halProgram))) {
            String line;
            while ((line = buffRead.readLine()) != null && !Objects.equals(line, "")) {

                String[] argv = line.split(" ");
                convertToInstruction(key, argv);
            }
        }
    }


    private boolean checkFilePath(String filePath){
        if(Files.exists(Path.of(filePath))) return true;
        System.out.println("Invalid file path!");
        return true;
    }


    private void convertToInstruction(String key, String[] args){

        if(args[0].chars().allMatch(Character::isDigit)){

            instructionLists.get(key).add(new String[]{args[1], args[args.length-1]});
        }else{
            instructionLists.get(key).add(new String[]{args[0], args[args.length-1]});
        }
    }


    public Map<String,List<String[]>> getInstructionParams(){

        return instructionLists;
    }

    public List<String[]> getEdges(){
        return edges;
    }


}
