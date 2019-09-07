import java.util.Iterator;

class Node<T> {
	public T item;
	public Node<T> next;
	public Node(T item, Node<T> next) {
		this.item = item;
		this.next = next;
	}
}

class CircularIterator<T> implements Iterator<T>{	//�������ᱸ�� �ݺ��� Ŭ����
	private Node<T> tail;
	private Node<T> scanPos;
	
	public CircularIterator (Node<T> tail) {
		this.tail=tail;
		scanPos=tail.next;
	}
	
	
	public boolean hasNext(){	//�ٽ� ó�� ��忡 �������� �� true
		return scanPos==tail.next;
	}
	
	public T next(){	//���� ��� Ž��
		T item = scanPos.item;
		scanPos = scanPos.next;
		return item;
	}
}

class CircularLinkedList<T> {
	public Node<T> tail;
	private int size;
	
	public boolean isEmpty() {
		return size == 0;
	}
	public void insert(T item) {
		if (isEmpty()) {
			tail = new Node<T>(item, null);
			tail.next = tail;
		}
		else {
			Node<T> tmp = new Node<T>(item, tail.next);
			tail.next = tmp;
			tail = tmp;
		}
		size++;
	}
	public String toString() {
		if (isEmpty())
			return "";
		Node<T> tmp = tail.next;
		StringBuilder sb = new StringBuilder(tmp.item + " ");
		while (tmp != tail) {
			tmp = tmp.next;
			sb.append(tmp.item + " ");
		}
		return sb.toString();
	}
	public int size() {
		return size;
	}
	
	public CircularIterator<T> iterator() {				// CircularIterator�� ����� �޼ҵ�
		return new CircularIterator<T>(tail);
	}
	// ���� �ؾ� �� �޼ҵ� 
	public boolean remove(T item){
		Node<T> prev =null;
		Node<T> curr =tail.next;			//����Ʈ�� ó��
		
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		if(curr.item.equals(item)){			//����Ʈ�� ����ó������ ã�� ��
			tail.next =curr.next;
			--size;
			return true;
		}

		while(curr != tail){							
			if(curr.item.equals(item))
				break;
			else{
				prev = curr;			
				curr = curr.next;
			}
		}
		
		if(curr==tail){					//����Ʈ �� ����
			if(curr.item.equals(item)){
				tail = prev;
				tail.next = curr.next;
				--size;
				return true;
			}
			
			else 
				return false;
		}
		
		prev.next=curr.next;
		--size;
		return true;
	}
	
	public boolean search(T item){
		Node<T> tmp = tail.next;	//����Ʈ�� ���� ó��
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		while(tmp!=tail){	//Ž��
			if(tmp.item.equals(item))
				return true;
			else
				tmp=tmp.next;
		}
		
		if(tmp==tail){					//����Ʈ �� ����
			if(tmp.item.equals(item))
				return true;
		}
		
		return false;
	}
}