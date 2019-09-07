@SuppressWarnings("unchecked")
public class ArrayBSTree<T extends Comparable<T>> {
	private ArrayBSTNode<T>[] tree;
	private boolean removeSuccess;
	private int maxSize = 5; 	// �ʱ� �Ҵ�� �迭�� ũ��, ó���� 5�� �Ѵ�.
	private int size = 0;	// ���� �迭�� ����� ������ ��

	public ArrayBSTree(){
		tree = new ArrayBSTNode[maxSize];
	}
	public boolean isEmpty(){
		return size==0;
	}
	
	public void resize(){ // size�� maxSize�� �Ǹ� �迭 ũ�⸦ 2�� Ȯ��
		ArrayBSTNode<T>[] tmp = new ArrayBSTNode[2*tree.length];
		for(int i=0;i<tree.length;i++)
			tmp[i]=tree[i];
		tree = tmp;
		maxSize=size*2;
	}
	
	public boolean insert(T data){	// ����
		ArrayBSTNode<T> newNode = new ArrayBSTNode<T>(data);	//���ο� ��� ����
		int index=0;
		if(size == maxSize)	//�迭�� �� ���� 2��� �÷��ش�
			resize();
		
		if(isEmpty()){	//�� tree���
			tree[0] = newNode;
			size++;
			return true;
		}
		else{
			int preIndex = 0;
			boolean vector = true;	//�����϶� true, �������϶� false
			while(tree[index].item != null){
				preIndex = index;
				if(data.compareTo(tree[index].item) < 0){
					index = tree[index].left;
					vector = true;
					if(index==-1)
						break;
				}
				else if(data.compareTo(tree[index].item) > 0){
					index = tree[index].right;
					vector = false;
					if(index==-1)
						break;
				}
				else	//tree�ȿ� ���� item�� ���� ���
					return false;
			}
			tree[size]=newNode;	//�迭�� ����
			//�θ� ����� ����,�����ʿ� �ڽ� ��� index ����
			if(vector)
				tree[preIndex].left = size;
			else
				tree[preIndex].right = size;
		}
		size++;
		return true;
	}
	
	public boolean remove(T data) {	//����� ������� ����
		remove(0, data);
		return removeSuccess;
	}
	private int remove(int index, T data) {
		if (isEmpty()) {	//�� ���
			removeSuccess = false;
			return index;
		}
		else if (data.compareTo(tree[index].item) < 0) {	//������ �����Ͱ� ���� ��ġ ���� ���� ��
			if(tree[index].left == -1){	//���� ��ġ�� -1�̶�� ã�� ���ߴ�.
				removeSuccess = false;
				return index;
			}
			tree[index].left = remove(tree[index].left, data);
		}
		else if (data.compareTo(tree[index].item) > 0) {	//������ �����Ͱ� ���� ��ġ ���� ���� ��
			if(tree[index].right == -1){
				removeSuccess = false;
				return index;
			}
			tree[index].right = remove(tree[index].right, data);
		}
		else {	//ã��
			removeSuccess = true;
			if (tree[index].left == -1)		//������ �������
				return tree[index].right; 
			
			else if (tree[index].right == -1)	//�������� ����� ��
				return tree[index].left;
			
			else {
				ArrayBSTNode<T> maxNode = tree[tree[index].left];
				while (maxNode.right != -1)
					maxNode = tree[maxNode.right];	//���� ū ��
				tree[index].item = maxNode.item;
				tree[index].left = remove(tree[index].left,maxNode.item);	//���� ����� �ڽ�Ʈ�� �ּ�
			}
		}
		return index;
	}
	
	public boolean search(T data){	// Ž��
		return search(tree,0,data);
	}
	public boolean search(ArrayBSTNode<T>[] tree,int index, T data){
		if(isEmpty())
			return false;
		
		else if(data.compareTo(tree[index].item) < 0){
			if(tree[index].left == -1)	//���� ��ġ�� ���� ��
				return false;
			return search(tree,tree[index].left,data);
		}
		
		else if(data.compareTo(tree[index].item) > 0){
			if(tree[index].right == -1)
				return false;
			return search(tree,tree[index].right,data);
		}
		
		else	//ã��
			return true;
	}
	
	public void inorderTraverse(){	// ���� ��ȸ
		inorderTraverse(tree,0);
		System.out.println();
	}
	public void inorderTraverse(ArrayBSTNode<T>[] tree,int index){
		if(tree[index].left != -1)
			inorderTraverse(tree,tree[index].left);
		System.out.print(tree[index].item+" ");
		if (tree[index].right !=-1)
			inorderTraverse(tree,tree[index].right);
	}
	
	public void display() {
		display(tree,0,0);
		System.out.println();
	}
	public void display(ArrayBSTNode<T>[] tree,int index,int count){
		if (tree[index].right !=-1) 
			display(tree,tree[index].right,count+1);
		
		for(int i=0;i<count;i++)
			System.out.print("\t");
		System.out.print(tree[index].item);
		if(tree[index].left != -1)
			System.out.print(",L");
		if(tree[index].right != -1)
			System.out.print(",R");
		System.out.println();
		
		if(tree[index].left != -1)
			display(tree,tree[index].left,count+1);
	}

	public void print(){ // �迭�� ����� ������ ���
		for(int i=0;i<size;i++){
			System.out.print("[ "+i+" ]\t");
			System.out.println("["+tree[i].item+", "+
			tree[i].left+", "+tree[i].right+"]");
		}
	}
}
