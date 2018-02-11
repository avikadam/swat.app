package org.sits.custom;

import java.util.Stack;

public class CustomeQueueUsingStacks<E> {
	Stack<E> st1;
	Stack<E> st2;

	public CustomeQueueUsingStacks() {
		st1 = new Stack<>();
		st2 = new Stack<>();
	}

	public void enqueue(E e) {
		st1.push(e);
	}

	public E dequeue() {
		if (st2.isEmpty()) {
			while (!st1.isEmpty()) {
				st2.push(st1.pop());
			}
		}
		return st2.pop();
	}

	public static void main(String[] args) {
		CustomeQueueUsingStacks<Integer> st = new CustomeQueueUsingStacks<>();
		st.enqueue(1);
		st.enqueue(2);
		st.enqueue(3);

		System.out.println(st.dequeue());
		System.out.println(st.dequeue());
		System.out.println(st.dequeue());
	}
}
