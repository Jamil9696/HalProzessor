class Buffer {
    private boolean available = false;
    private float data;
    Logger logger = Logger.getInstance();


    public synchronized void put(float x) {
        while(available) {
            try {
                logger.debugInfo(Thread.currentThread().getName() + ": I'm waiting with puting value: " + x);
                wait();
            }
            catch(InterruptedException e) {}
        }
        data = x;
        available = true;
        logger.debugInfo(Thread.currentThread().getName() + ": I've put value: " + x);
        notify();
    }

    public synchronized float get() {
        while(!available) {
            try {
                logger.debugInfo(Thread.currentThread().getName() + ": I'm waiting because no value ");
                wait();
            }
            catch(InterruptedException e) {}
        }
        available = false;
        logger.debugInfo(Thread.currentThread().getName() + ": Buffer empty now. received: " + data );
        notify();
        return data;
    }
}
