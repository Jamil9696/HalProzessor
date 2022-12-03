import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HalThreadSystem {

    private final HalConfigReader configReader = new HalConfigReader();
    List<HalRunnable> halThreads = new ArrayList<>();

    Logger logger = Logger.getInstance();


    public void setUp(String filePath){
        configReader.readConfigurationFile(filePath);

        List<Thread> threads = new ArrayList<>();

        createHalThreads();
        createConnections();
        startThreads();
    }

    public void createHalThreads(){
        Map<String, List<String[]>> map = configReader.getInstructionParams();
        // instantiate HalThreads for each hal-program, each Thread will access the same data storage
        DataStorage sharedStorage = new DataStorage(100);
        for (Map.Entry<String, List<String[]>> entry : map.entrySet()) {
            HalRunnable halThread = new HalThread(sharedStorage, entry.getKey(), entry.getValue());
            halThreads.add(halThread);
        }

    }

    public void createConnections(){

        // create connections
        List<String[]> connections = configReader.getEdges();
        for(int i = 0; i < connections.size(); i++){

            String[] sender = connections.get(i)[0].split(":");
            String[] receiver = connections.get(i)[2].split(":");

            int senderAt = Integer.parseInt(sender[0]);
            int sendFrom = Integer.parseInt(sender[1]);
            int receiverAt = Integer.parseInt(receiver[0]);
            int receiveFrom = Integer.parseInt(receiver[1]);


            if(senderAt >= halThreads.size() || receiverAt >= halThreads.size()){
                System.out.println("connection " + senderAt + " to " + receiverAt + " can't be created");
                System.exit(1);
            }
            logger.debugInfo("========== CREATE CONNECTION ========");
            logger.debugInfo("SENDER: from " + sender[0] + " port: " + sender[1]);
            logger.debugInfo("RECEIVER: from " + receiver[0] + " port: " + receiver[1]);

            if(connections.get(i)[1].equals(">")){
                logger.debugInfo("ConnectionTyp: " + ">\n");
                Buffer buf = halThreads.get(receiverAt).getBuffer(receiveFrom, true);
                logger.debugInfo("");
                halThreads.get(senderAt).addBuffer(buf, sendFrom, false);

            }else{
                logger.debugInfo("ConnectionTyp: " + "<\n");
                Buffer buf = halThreads.get(senderAt).getBuffer(sendFrom, true);
                halThreads.get(receiverAt).addBuffer(buf, receiveFrom, false);

            }
            logger.debugInfo("");
        }

    }


    public void startThreads(){
        logger.debugInfo("===================    THREADS    ===========================");
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < halThreads.size(); i++){
            threads.add(new Thread(halThreads.get(i),"p"+i));
        }

       for (int i = 0; i < halThreads.size(); i++){
            threads.get(i).start();
        }
       /*
        for (int i = halThreads.size()-1; i >= 0; i--){
            threads.get(i).start();
        }*/





        while (true);


    }





}
