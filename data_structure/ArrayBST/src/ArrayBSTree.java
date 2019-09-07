@SuppressWarnings("unchecked")
public class ArrayBSTree<T extends Comparable<T>> {
	private ArrayBSTNode<T>[] tree;
	private boolean removeSuccess;
	private int maxSize = 5; 	// 초기 할당된 배열의 크기, 처음에 5로 한다.
	private int size = 0;	// 현재 배열에 저장된 데이터 수

	public ArrayBSTree(){
		tree = new ArrayBSTNode[maxSize];
	}
	public boolean isEmpty(){
		return size==0;
	}
	
	public void resize(){ // size가 maxSize가 되면 배열 크기를 2배 확장
		ArrayBSTNode<T>[] tmp = new ArrayBSTNode[2*tree.length];
		for(int i=0;i<tree.length;i++)
			tmp[i]=tree[i];
		tree = tmp;
		maxSize=size*2;
	}
	
	public boolean insert(T data){	// 삽입
		ArrayBSTNode<T> newNode = new ArrayBSTNode<T>(data);	//새로운 노드 생성
		int index=0;
		if(size == maxSize)	//배열이 꽉 차면 2배로 늘려준다
			resize();
		
		if(isEmpty()){	//빈 tree경우
			tree[0] = newNode;
			size++;
			return true;
		}
		else{
			int preIndex = 0;
			boolean vector = true;	//왼쪽일때 true, 오른쪽일때 false
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
				else	//tree안에 같은 item이 있을 경우
					return false;
			}
			tree[size]=newNode;	//배열에 삽입
			//부모 노드의 왼쪽,오른쪽에 자식 노드 index 삽입
			if(vector)
				tree[preIndex].left = size;
			else
				tree[preIndex].right = size;
		}
		size++;
		return true;
	}
	
	public boolean remove(T data) {	//재귀적 방법으로 삭제
		remove(0, data);
		return removeSuccess;
	}
	private int remove(int index, T data) {
		if (isEmpty()) {	//빈 경우
			removeSuccess = false;
			return index;
		}
		else if (data.compareTo(tree[index].item) < 0) {	//삭제할 데이터가 현재 위치 보다 작을 때
			if(tree[index].left == -1){	//다음 위치가 -1이라면 찾지 못했다.
				removeSuccess = false;
				return index;
			}
			tree[index].left = remove(tree[index].left, data);
		}
		else if (data.compareTo(tree[index].item) > 0) {	//삭제할 데이터가 현재 위치 보다 작을 때
			if(tree[index].right == -1){
				removeSuccess = false;
				return index;
			}
			tree[index].right = remove(tree[index].right, data);
		}
		else {	//찾음
			removeSuccess = true;
			if (tree[index].left == -1)		//왼쪽이 비었을때
				return tree[index].right; 
			
			else if (tree[index].right == -1)	//오른쪽이 비었을 때
				return tree[index].left;
			
			else {
				ArrayBSTNode<T> maxNode = tree[tree[index].left];
				while (maxNode.right != -1)
					maxNode = tree[maxNode.right];	//가장 큰 값
				tree[index].item = maxNode.item;
				tree[index].left = remove(tree[index].left,maxNode.item);	//지운 노드의 자식트리 주소
			}
		}
		return index;
	}
	
	public boolean search(T data){	// 탐색
		return search(tree,0,data);
	}
	public boolean search(ArrayBSTNode<T>[] tree,int index, T data){
		if(isEmpty())
			return false;
		
		else if(data.compareTo(tree[index].item) < 0){
			if(tree[index].left == -1)	//다음 위치가 없을 때
				return false;
			return search(tree,tree[index].left,data);
		}
		
		else if(data.compareTo(tree[index].item) > 0){
			if(tree[index].right == -1)
				return false;
			return search(tree,tree[index].right,data);
		}
		
		else	//찾음
			return true;
	}
	
	public void inorderTraverse(){	// 중위 순회
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

	public void print(){ // 배열에 저장된 데이터 출력
		for(int i=0;i<size;i++){
			System.out.print("[ "+i+" ]\t");
			System.out.println("["+tree[i].item+", "+
			tree[i].left+", "+tree[i].right+"]");
		}
	}
}
