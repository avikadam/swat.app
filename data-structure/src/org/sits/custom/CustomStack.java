package org.sits.custom;

import java.util.EmptyStackException;

public class CustomStack<E> {
	private int stackSize;
	private Object[] stackArr;

	/**
	 * constructor to create stack with size
	 * 
	 * @param size
	 */
	public CustomStack(int size) {
		this.stackSize = 0;
		this.stackArr = new Object[size];
	}

	/**
	 * This method adds new entry to the top of the stack
	 * 
	 * @param entry
	 * @throws Exception
	 */
	public void push(E entry) {
		if (this.isStackFull()) {
			increaseStackCapacity();
			// throw new Exception("Stack is already full. Can not add
			// element.");
		}
		System.out.println("Adding: " + entry);
		this.stackArr[++stackSize] = entry;
	}

	/**
	 * This method removes an entry from the top of the stack.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public E pop() {
		if (this.isStackEmpty()) {
			throw new EmptyStackException();
		}
		E entry = (E) this.stackArr[stackSize--];
		System.out.println("Removed entry: " + entry);
		return entry;
	}

	/**
	 * This method returns top of the stack without removing it.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E peek() {
		return (E) stackArr[stackSize];
	}

	/**
	 * This method returns true if the stack is empty
	 * 
	 * @return
	 */
	public boolean isStackEmpty() {
		return (stackSize == -1);
	}

	/**
	 * This method returns true if the stack is full
	 * 
	 * @return
	 */
	public boolean isStackFull() {
		return (stackSize == stackArr.length - 1);
	}

	private void increaseStackCapacity() {
		Object[] newStack = new Object[this.stackSize * 2];
		for (int i = 0; i < stackSize; i++) {
			newStack[i] = this.stackArr[i];
		}
		this.stackArr = newStack;
		this.stackSize = this.stackSize * 2;
	}

	public static void main(String[] args) {
		CustomStack<Integer> stack = new CustomStack<>(5);
		try {
			stack.push(4);
			stack.push(8);
			stack.push(3);
			stack.push(89);
			stack.pop();
			stack.push(34);
			stack.push(45);
			stack.push(78);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
