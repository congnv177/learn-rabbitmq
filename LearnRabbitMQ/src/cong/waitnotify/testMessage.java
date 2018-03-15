package cong.waitnotify;

public class testMessage {

	public static void main(String[] args) {
		Message msg = new Message("Xử lý");
		Thread t1 = new Thread(new Sender(msg));
		Thread t2 = new Thread(new Receiver(msg));
		t1.start();
		t2.start();
	}

}
