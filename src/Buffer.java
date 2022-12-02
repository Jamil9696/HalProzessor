class Buffer {
    private boolean available = false;
    private float data;


    public synchronized void put(float x) {
        while(available) {
            try {
                System.out.println("I'm waiting with puting value: " + x);
                wait();
            }
            catch(InterruptedException e) {}
        }
        data = x;
        available = true;
        System.out.println("I've put value: " + x);
        notify();
    }

    public synchronized float get() {
        while(!available) {
            try {
                System.out.println("I'm waiting because no value ");
                wait();
            }
            catch(InterruptedException e) {}
        }
        available = false;
        System.out.println("Is empty now. received: " + data );
        notify();
        return data;
    }
}
