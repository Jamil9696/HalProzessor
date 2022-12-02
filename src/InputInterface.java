import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputInterface {

    private List<Buffer> buffers = new ArrayList<>();

    public Float input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("IN: ");
        Float input = scanner.nextFloat();

        return input;
    }

    void addBuffer(Buffer buffer, int i){
        if(i > buffers.size()){
            for(int j = i; j > buffers.size(); --j){
                buffers.add(new Buffer());
                Logger.getInstance().debugInfo("InputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size() - 1) + " hinzugefügt");
            }
        }

        buffers.add(buffer);
        Logger.getInstance().debugInfo("InputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size() - 1) + " hinzugefügt");


    }

    Buffer getBuffer(int i){
        if(i >= buffers.size()){
            for(int j = buffers.size(); j <= i; j++){
                buffers.add(new Buffer());
            }
        }

        return buffers.get(i);
    }

    float read(int i){

        return getBuffer(i).get();
    }

}
