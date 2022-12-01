import java.util.Optional;

public interface HalRunnable extends Runnable {


    String getThreadName();

    Buffer getBuffer(int i, boolean readOnly);

    void addBuffer(Buffer buffer, int i, boolean readOnly);
}
