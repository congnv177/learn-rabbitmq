package cong.waitnotify;

public class Receiver implements Runnable {
	Message message;

	public Receiver(Message msg) { // phuong thuc khoi tao
		this.message = msg;
	}

	@Override
	public void run() {
		synchronized (message) {
			System.out.println("Đang đợi tin nhắn...");
			try {
				message.wait(); //o che do cho
				System.out.println("Đã nhận được tin nhắn với  nội dung: "+ message.getContent());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
