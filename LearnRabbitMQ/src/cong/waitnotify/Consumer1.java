package cong.waitnotify;

public class Consumer1 extends Thread {
	Producer1 producer;
	 
    Consumer1(Producer1 p) {
        producer = p;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                String message = producer.getMessage();
                System.out.println("Got message: " + message);
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
        Producer1 producer = new Producer1();
        producer.start();
        new Consumer1(producer).start();
    }
}
