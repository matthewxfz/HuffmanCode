package edu.iit.xfz;


public class CS401ArrayImpl<E>{
	private E[] elements;
	private int num_elements;
	private int capacity;

	@SuppressWarnings("unchecked")
	public CS401ArrayImpl(int size) {
		elements = (E[]) new Object[size];
		num_elements = 0;
		capacity = size;
	}

	@SuppressWarnings("unchecked")
	public CS401ArrayImpl() {
		/**
		 * Call the c'tor that takes the 'size' parameter.
		 **/
		this(5);
	}

	/**
	 * Methods inherited from CS401CollectionInterface
	 */
	public boolean is_full() {
		if (num_elements == capacity)
			return true;
		return false;
	}

	public boolean is_empty() {
		if (num_elements == 0)
			return true;
		return false;
	}

	public int size() {
		return num_elements;
	}

	public boolean add(E e) {
		add(Where.BACK, e); // Add at the end
		return true;
	}

	/*
	 * Remove element at index i. If the element exists in the collection,
	 * return that element back to the user. If the index is out of bounds,
	 * return null.
	 */
	public E remove(int i) {
		if (i < 0 || i > size() - 1) {
			return null;
		} else {
			E e = elements[i];
			for (int index = i; index < size(); index++)
				elements[index] = elements[index + 1];
			num_elements--;

			return e;
		}
	}

	/*
	 * Return true if e is in the collection class, false otherwise.
	 */
	public E contains(E e) {

		for (int i = 0; i < size(); i++) {
			if (((Comparable<E>) elements[i]).compareTo(e) == 0) {
				return elements[i];
			}
		}
		
		return null;
	}

	/**
	 * ---- Methods defined by this class
	 * ---------------------------------------------------------- Methods that
	 * are added by this class and not in the CS401CollectionInterface
	 */

	/**
	 * Add element in middle. Preconditions: where == MIDDLE index <=
	 * num_elements - 1
	 */
	public boolean add(Where where, int index, E e) {

		/*
		 * If there is no space to add the new element, grow the array.
		 */
		if (is_full()) {
			grow();
		}

		if (index < 0) {
			return false;
		} else {
			num_elements++;

			for (int i = size() - 1; i > index; i--) {
				elements[i] = elements[i - 1];
			}
			elements[index] = e;
		}
		return true;
	}

	/**
	 * Add element in front or at the end, as dictated by where. Preconditions:
	 * where != MIDDLE
	 */
	public boolean add(Where where, E e) {

		/*
		 * If there is no space to add the new element, grow the array.
		 */
		if (is_full()) {
			grow();
		}

		if (where == Where.BACK) {
//			System.out.println("Inserting element at index " + num_elements);
			elements[num_elements] = e;
			num_elements++;
		} else if (where == Where.FRONT) {
			// System.out.println("Inserting element at index 0");
			// System.out.println("Compacting storage");
			num_elements++;

			for (int i = size() - 1; i > 0; i--) {
				elements[i] = elements[i - 1];
			}
			elements[0] = e;

		}

		return true;
	}

	/*
	 * Gets the element at index i (0 <= i <= num_elements-1)
	 */
	public E get(int i) {

		if (i < 0 && i > num_elements)
			return null;

		return (elements[i]);
	}

	/**
	 * ----------- Private methods
	 */
	/*
	 * Grows elements array to hold more elements. Copies old (existing)
	 * elements in the new array.
	 * 
	 * Postcondition: (a) elements must contain the contents of the old array
	 * (b) elements must now have twice as much capacity as before
	 */
	@SuppressWarnings("unchecked")
	private boolean grow() {
		capacity *= 2;
		E[] newElements = (E[]) new Object[capacity];
		for (int i = 0; i < size(); i++) {
			newElements[i] = elements[i];
		}

		elements = newElements;

//		System.out.println("Capacity reached.  Increasing storage...");
		// System.out.println("New capacity is " + capacity + " elements");

		return true;
	}
}
