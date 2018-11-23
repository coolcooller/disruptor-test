package com.my.test;

public class FalseSharing implements Runnable {

	public final static long ITERATIONS = 500L * 1000L * 1000L;

	private static int NUM_THREADS = 2;

	private final int arrayIndex;

	private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

	static {
		for (int i = 0; i < longs.length; i++){
			longs[i] = new VolatileLong();
		}
	}

	public FalseSharing(final int arrayIndex){
		this.arrayIndex = arrayIndex;
	}

	public static void main(final String[] args) throws Exception {
		final long start = System.nanoTime();
		runTest();
		System.out.println("duration = " + (System.nanoTime() - start) / 1000000000);
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing(i));
		}
		
		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {

		public volatile long value = 0L;

		// 注释掉，会产生伪共享，影响性能。 NUM_THREADS 线程数越大越影响
		// http://ifeve.com/falsesharing/
		public long p1, p2, p3, p4, p5, p6; // comment out

	}
}
