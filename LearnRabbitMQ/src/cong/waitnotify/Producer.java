package cong.waitnotify;

import java.util.Vector;

public class Producer extends Thread {
	static final int MAXQUEUE = 5;

	private Vector msg = new Vector();

	public void run() {
		try {
			while (true)
				putMessage();
		} catch (InterruptedException e) {
		}
	}

	private synchronized void putMessage() throws InterruptedException {
		while (msg.size() == MAXQUEUE)
			wait();
		msg.addElement(new java.util.Date().toString());
		System.out.println("put message");
		notify();
		// Sau khi event put message được xảy ra, hàm notify() đươc gọi để đánh
		// thức-kích hoạt lại thread getMessage tiếp tục hoạt động.
	}

	// Called by Consumer
	public synchronized String getMessage(Vector messages) throws InterruptedException {
		notify();
		while (msg.size() == 0)
			wait();// Gọi hàm wait() để đồng bộ hoá đoạn code sau, Thread hiện tại sẽ tạm dừng,
					// rơi vào trạng thái nằm chờ đến khi method notify được gọi.
		String message = (String) msg.firstElement();
		msg.removeElement(message);

		while (msg.size() == MAXQUEUE)
			wait();
		msg.addElement(new java.util.Date().toString());
		System.out.println("put message");
		notify();
		return message;
	}
}
