package com.sits.java_sample;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

interface IA {
	void m();
}

interface IB {
	void m();
}

interface IC extends IA, IB {
	// void m();
}

public class TestBBD implements IC {

	ReadWriteLock l = new ReentrantReadWriteLock();

	@Override
	public void m() {
		// l.writeLock().
		System.out.println('a');
	}

	public static void main(String[] args) {
		IA a = new TestBBD();
		a.m();
		IB b = new TestBBD();
		b.m();
	}
}

class TestA implements IC {

	@Override
	public void m() {
		//
	}

	public static void main(String[] args) {
		IA a = new TestA();
		a.m();
		IB b = new TestA();
		b.m();
	}
	// TestBBD d = new TestBBD.Builder().
}
