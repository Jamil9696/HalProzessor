class Buffer {
    private boolean available = false;
    private float data;
    Logger logger = Logger.getInstance();


    public synchronized void put(float x) {
        while(available) {
            try {
                logger.debugInfo(Thread.currentThread().getName() + ": I'm waiting with puting value: " + x + " in " + this);
                wait();
            }
            catch(InterruptedException e) {}
        }
        data = x;
        available = true;
        logger.debugInfo(Thread.currentThread().getName() + ": I've put value: " + x + " in" + this);
        notify();
    }

    public synchronized float get() {
        while(!available) {
            try {
                logger.debugInfo(Thread.currentThread().getName() + ": I'm waiting for "+ this + " because no value ");
                wait();
            }
            catch(InterruptedException e) {}
        }
        available = false;
        logger.debugInfo(Thread.currentThread().getName() + ": " + this + " is empty now. received: " + data );
        notify();
        return data;
    }
}
