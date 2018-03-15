package cong.waitnotify;

public class Sender implements Runnable {
	Message message;

	public Sender(Message msg) {
		this.message = msg;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		synchronized (message) {
			message.setContent("Xin chào các bạn!");
			message.notifyAll();
		}
	}

}
