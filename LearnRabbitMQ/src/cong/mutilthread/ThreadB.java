package cong.mutilthread;

public class ThreadB implements Runnable {
	public ThreadB() {
		
	}
	@Override
	public void run() {
		try {
			Thread.sleep(100);
			System.out.println("Vao thread B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
