package cong.waitnotify;

import java.util.Vector;

public class Consumer extends Thread {
	Producer producer;
	 
    Consumer(Producer p) {
        producer = p;
    }
    
    private Vector messages = new Vector();
//    private String msg;
    
    @Override
    public void run() {
        try {
            while (true) {
                String message = producer.getMessage(messages);
                System.out.println("Got message: " + message);
                sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
        Producer producer = new Producer();
        producer.start();
        new Consumer(producer).start();
    }
}
