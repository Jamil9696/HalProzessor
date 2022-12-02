import java.util.ArrayList;
import java.util.List;

public class OutputInterface {


    private List<Buffer> buffers = new ArrayList<>();



    public void print(Float out) {
        System.out.println("OUT: " + out);

    }

    void addBuffer(Buffer buffer, int i){
        if(i >= buffers.size()){
            for(int j = i; j > buffers.size(); --j){
              buffers.add(new Buffer());
            }
        }


        buffers.add(buffer);
        // Logger.getInstance().debugInfo("OutputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size()-1) + " hinzugefügt");


    }

    Buffer getBuffer(int i){
        if(i >= buffers.size()){
            for(int j = i; j > buffers.size(); --j){
                buffers.add(new Buffer());
            }
        }
        // Logger.getInstance().debugInfo("OutputBuffer: " + buffers.get(buffers.size()-1).toString() +" an der Stelle: " + (buffers.size()-1)+ " hinzugefügt");

        return buffers.get(i);
    }


    public void send(Float out, Integer index) {

        getBuffer(index).put(out);
    }
}
