package org.sits.thread.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSearcher extends RecursiveTask<Boolean> {
	private static final long serialVersionUID = 6353766657947100898L;
	int[] arr;
	int searchableElement;

	ForkJoinSearcher(int[] arr, int search) {
		this.arr = arr;
		this.searchableElement = search;
	}

	@Override
	protected Boolean compute() {
		int mid = (0 + arr.length) / 2;
		if (arr[mid] == searchableElement) {
			return true;
		} else if (mid == 1 || mid == arr.length) {
			return false;
		} else if (searchableElement < arr[mid]) {
			System.out.println(Thread.currentThread().getName() + " says :: Doing Left-search");
			int[] left = Arrays.copyOfRange(arr, 0, mid);
			ForkJoinSearcher forkTask = new ForkJoinSearcher(left, searchableElement);
			forkTask.fork();
			return forkTask.join();
		} else if (searchableElement > arr[mid]) {
			System.out.println(Thread.currentThread().getName() + " says :: Doing Right-search");
			int[] right = Arrays.copyOfRange(arr, mid, arr.length);
			ForkJoinSearcher forkTask = new ForkJoinSearcher(right, searchableElement);
			forkTask.fork();
			return forkTask.join();
		}
		return false;
	}
}
