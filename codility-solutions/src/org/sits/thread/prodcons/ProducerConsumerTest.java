package org.sits.thread.prodcons;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

public class ProducerConsumerTest {

	@Test
	public void testProdConsum(String[] args) {
		BlockingQueue<Integer> sharedQ = new LinkedBlockingQueue<Integer>();
		Producer p = new Producer(sharedQ);
		Consumer c = new Consumer(sharedQ);
		p.start();
		c.start();
	}

}
