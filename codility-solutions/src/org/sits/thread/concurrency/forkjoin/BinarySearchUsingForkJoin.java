package org.sits.thread.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class BinarySearchUsingForkJoin {
	int[] arr = new int[100];

	public BinarySearchUsingForkJoin() {
		init();
	}

	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		Arrays.sort(arr);
	}

	public void createForJoinPool(int search) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(50);
		ForkJoinSearcher searcher = new ForkJoinSearcher(this.arr, search);
		Boolean status = forkJoinPool.invoke(searcher);
		System.out.println(" Element ::" + search + " has been found in array? :: " + status);
	}

	public static void main(String[] args) {
		BinarySearchUsingForkJoin search = new BinarySearchUsingForkJoin();
		search.createForJoinPool(10);
		System.out.println("**********************");
		search.createForJoinPool(104);
	}
}
