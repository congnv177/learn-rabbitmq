package cong.completablefuture;

import java.util.function.Supplier;

public class Calculator implements Supplier<Integer>{

	private int a;
	private int b;
	
	public Calculator(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Integer get() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a + b;
	}

}
