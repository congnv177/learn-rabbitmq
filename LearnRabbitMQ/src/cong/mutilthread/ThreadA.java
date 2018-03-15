package cong.mutilthread;

public class ThreadA implements Runnable {
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println("Vao thread A");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
