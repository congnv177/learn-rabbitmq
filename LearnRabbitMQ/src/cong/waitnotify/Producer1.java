package cong.waitnotify;

import java.util.Vector;

public class Producer1 extends Thread {
	static final int MAXQUEUE = 5;
	@SuppressWarnings("rawtypes")
	private Vector messages = new Vector();

	@Override
	public void run() {
		try {
			while (true) {
				putMessage();}
		} catch (InterruptedException e) {
		}
	}

	@SuppressWarnings("unchecked")
	private synchronized void putMessage() throws InterruptedException {
		System.out.println("a");
		while (messages.size() == MAXQUEUE) {
			System.out.println("a1");
			wait();
		}
		System.out.println("b");
		messages.addElement(new java.util.Date().toString());
		System.out.println("put message");
		notify();
		System.out.println("e");
		//Sau khi event put message được xảy ra, hàm notify() đươc gọi để đánh thức-kích hoạt lại thread getMessage tiếp tục hoạt động.
	}

	// Called by Consumer
	public synchronized String getMessage() throws InterruptedException {
		System.out.println("f");
		notify();
		System.out.println("msg="+messages.size());
		while (messages.size() == 0)
			wait();//Gọi hàm wait() để đồng bộ hoá đoạn code sau, Thread hiện tại sẽ tạm dừng, 
		   // rơi vào trạng thái nằm chờ đến khi method notify được gọi.
		String message = (String) messages.firstElement();
//		System.out.println("str msg="+message);
		messages.removeElement(message);
		return message;
	}
}
