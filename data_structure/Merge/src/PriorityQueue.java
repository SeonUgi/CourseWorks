@SuppressWarnings("unchecked")
class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
	private T[] items;
	private int size;
	private int maxSize;

	public PriorityQueue(int max){ 	// ������
		maxSize = max;
		items = (T[]) new Comparable[maxSize];
		size = 0;

	}
	public PriorityQueue(){		// ����Ʈ ������
		this(5);
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	private void swap(int x, int y){
		T tmp = items[x];
		items[x] = items[y];
		items[y] = tmp;
	}
	
	public T peek(){
		if (size == 0)
			throw new java.util.NoSuchElementException(
						"peek(): queue empty");
		return items[0];

	}
	public int size(){
		return size;
	}
	private void resize(){
		T[] newItems = (T[]) new Comparable[2*items.length];
		for (int i = 0; i < items.length; i++)
			newItems[i] = items[i];
		items = newItems;
		maxSize = 2*maxSize;

	}
	
	public void enqueue(T item){
		if (size == items.length)
			resize();
		items[size++] = item;
		moveUp(0, size-1);

	}
	private void moveUp(int first, int last){
		int parent;
		while(last>first){
			parent = (last-1)/2;
			if(items[parent].compareTo(items[last])>0){
				swap(parent, last);
				last=parent;
			}
			else
				break;
		}
	}

	
	public void moveDown(int first, int last){
		int minChild, rightChild, leftChild;
		leftChild = 2*first+1;
		
		while(leftChild<=last){
			//�ڽ� ��� �߿��� ū ���� ���� ����� �ε��� numChild�� ����
			if(leftChild == last)
				minChild = leftChild;
			else{   //2���� �ڽĳ��
				rightChild = 2*first+2;
				if(items[leftChild].compareTo(items[rightChild])>=0)
					minChild = rightChild;
				else
					minChild = leftChild;
			}
			if(items[first].compareTo(items[minChild])>0){
				swap(first, minChild);
	            first = minChild;
	            leftChild = 2*first+1;
			}
			else
				break;
		}
	}

	public T dequeue(){
		if (size == 0)
			throw new java.util.NoSuchElementException("dequeue(): queue empty");
		T item = items[0];
		items[0] = items[--size];
		moveDown(0, size-1);
		return item;
	}
	
	public void print(){
		for (int i = 0; i < size; i++)
			System.out.print(items[i] + " ");
		System.out.println();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size-1; i++)
			sb.append(items[i].toString() + " ");
		sb.append(items[size-1].toString());
		return sb.toString();
	}
}

