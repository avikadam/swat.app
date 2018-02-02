package org.sits.list;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * adds element at the starting of the linked list
	 * 
	 * @param element
	 */
	public void addFirst(E element) {
		Node<E> tmp = new Node<E>(element, head, null);
		if (head != null) {
			head.setLinkPrev(tmp);
		}
		head = tmp;
		if (tail == null) {
			tail = tmp;
		}
		size++;
		System.out.println("adding: " + element);
	}

	/**
	 * adds element at the end of the linked list
	 * 
	 * @param element
	 */
	public void addLast(E element) {
		Node<E> tmp = new Node<E>(element, null, tail);
		if (tail != null) {
			tail.setLinkNext(tmp);
		}
		tail = tmp;
		if (head == null) {
			head = tmp;
		}
		size++;
		System.out.println("adding: " + element);
	}

	/**
	 * this method walks forward through the linked list
	 */
	public void iterateForward() {

		System.out.println("iterating forward..");
		Node<E> tmp = head;
		while (tmp != null) {
			System.out.println(tmp.getElement());
			tmp = tmp.getLinkNext();
		}
	}

	/**
	 * this method walks backward through the linked list
	 */
	public void iterateBackward() {
		System.out.println("iterating backword..");
		Node<E> tmp = tail;
		while (tmp != null) {
			System.out.println(tmp.getElement());
			tmp = tmp.getLinkPrev();
		}
	}

	/**
	 * this method removes element from the start of the linked list
	 * 
	 * @return
	 */
	public E removeFirst() {
		return removeElement(0);
	}

	/**
	 * this method removes element from the end of the linked list
	 * 
	 * @return
	 */
	public E removeLast() {
		return removeElement(size);
	}

	/**
	 * This method removes element from asked position
	 * 
	 * @param pos
	 */
	public E removeElement(int pos) {
		if (size == 0)
			throw new EmptyListException();

		if (pos == 1) {
			Node<E> tmp = head.getLinkNext();
			head = head.getLinkNext();
			head.setLinkPrev(null);
			size--;
			return tmp.getElement();
		}
		if (pos == size) {
			Node<E> tmp = tail.getLinkPrev();
			tail = tail.getLinkPrev();
			tail.setLinkNext(null);
			size--;
			return tmp.getElement();
		}
		Node<E> ptr = head.getLinkNext();
		for (int i = 2; i < size; i++) {
			if (i == pos) {
				Node<E> p = ptr.getLinkPrev();
				Node<E> n = ptr.getLinkNext();

				p.setLinkNext(n);
				n.setLinkPrev(p);
				size--;
				return ptr.getElement();
			}
			ptr = ptr.getLinkNext();
		}
		throw new NoSuchElementException();
	}

	/**
	 * This method removes element at asked position
	 * 
	 * @param pos
	 */
	public void addElement(E val, int pos) {
		if (pos > size)
			throw new NoSuchElementException();

		Node<E> nptr = new Node<E>(val, null, null);

		if (pos == 1) {
			head.setLinkPrev(nptr);
			nptr.setLinkNext(head);
			head = nptr;
			return;
		}
		if (pos == size) {
			Node<E> tmp = nptr.getLinkPrev();
			nptr.setLinkNext(tail);
			nptr.setLinkPrev(tmp);
			tail.setLinkPrev(nptr);
			return;
		}
		Node<E> ptr = head;
		for (int i = 2; i <= size; i++) {
			if (i == pos) {
				Node<E> tmp = ptr.getLinkPrev();
				nptr.setLinkPrev(tmp);
				nptr.setLinkNext(ptr);
				tmp.setLinkPrev(tmp);
			}
			ptr = ptr.getLinkNext();
		}
		size++;
	}

}

/**
 * This class keeps track of each element information
 */
class Node<E> {
	private E element;
	private Node<E> next;
	private Node<E> prev;

	public Node() {
		this.next = null;
		this.prev = null;
		this.element = null;
	}

	public Node(E element, Node<E> next, Node<E> prev) {
		this.element = element;
		this.next = next;
		this.prev = prev;
	}

	/* Function to set link to next node */
	public void setLinkNext(Node<E> n) {
		next = n;
	}

	/* Function to set link to previous node */
	public void setLinkPrev(Node<E> p) {
		prev = p;
	}

	/* Funtion to get link to next node */
	public Node<E> getLinkNext() {
		return next;
	}

	/* Function to get link to previous node */
	public Node<E> getLinkPrev() {
		return prev;
	}

	/* Function to set data to node */
	public void setData(E d) {
		element = d;
	}

	/* Function to get data from node */
	public E getElement() {
		return element;
	}
}

class EmptyListException extends RuntimeException {
	private static final long serialVersionUID = 8290209645350669558L;

	public EmptyListException() {
		super();
	}

	public EmptyListException(java.lang.String arg0) {
		super(arg0);
	}
}