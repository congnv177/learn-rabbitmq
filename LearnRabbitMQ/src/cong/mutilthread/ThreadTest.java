package cong.mutilthread;

public class ThreadTest {

	public static void main(String[] args) {
		Thread threadA = new Thread(new ThreadA());
		Thread threadB = new Thread(new ThreadB());
		threadA.start();
		threadB.start();
	}

}
