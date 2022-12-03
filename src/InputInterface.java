import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InputInterface {

    private List<Buffer> buffers = new ArrayList<>();
    private final Logger logger = Logger.getInstance();

    public Float input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(Thread.currentThread().getName()+ ":");
        Float  input = scanner.nextFloat();
        System.out.println(Thread.currentThread().getName() + " IN: " + input + " gelesen");
        return input;

        //return logger.synchronizedInput(Thread.currentThread().getName());
    }

    void addBuffer(Buffer buffer, int i){
        if(i > buffers.size()){
            for(int j = buffers.size(); j < i; j++){
                buffers.add(new Buffer());
                logger.debugInfo("InputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size() - 1) + " hinzugefügt");
            }
        }

        buffers.add(buffer);
        Logger.getInstance().debugInfo("InputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size() - 1) + " hinzugefügt");


    }

    Buffer getBuffer(int i){
        if(i >= buffers.size()){
            for(int j = buffers.size(); j <= i; j++){
                buffers.add(new Buffer());
                Logger.getInstance().debugInfo("InputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size() - 1) + " hinzugefügt");
            }
        }

        return buffers.get(i);
    }

    float read(int i){

        return getBuffer(i).get();
    }

}
