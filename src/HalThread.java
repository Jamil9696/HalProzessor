import java.util.List;

public class HalThread implements HalRunnable{

   private final HalArchitecture halArchitecture;
   private final String name;

    public HalThread(DataStorage storage,String threadName, List<String[]> instructionParam){
       this.halArchitecture =  new HalArchitecture(
                new ProgramMemory(storage),
                new HalProcessor(new Accumulator()),
                new InputInterface(),
                new OutputInterface()
        );
       this.halArchitecture.initProgramMemory(instructionParam);
       this.name = threadName;
    }

    @Override
    public String getThreadName() {
      return name;
    }

    @Override
    public Buffer getBuffer(int i, boolean readOnly) {
        return halArchitecture.getBuffer(i,readOnly);
    }

    @Override
    public void addBuffer(Buffer buffer, int i, boolean readOnly) {
        halArchitecture.addBuffer(buffer, i, readOnly);
    }

    @Override
    public void run() {
      while (halArchitecture.fetch());
    }
}
