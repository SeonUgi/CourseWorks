import java.util.Scanner;

class BSTNode<T> {
	public T item;
	public BSTNode<T> left;
	public BSTNode<T> right;
	public BSTNode(T item) {  // 생성자
		this.item = item;
		left = null;
		right = null;
	}
}

class BSTree<T extends Comparable <T>> {
	private BSTNode<T> root;
	private boolean insertSuccess;
	private boolean removeSuccess;
	
	public BSTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean insert(T data) {	//반복적 방법으로 삽입
		BSTNode<T> newNode = new BSTNode<T>(data);	
		if (root == null) {
			root = newNode;
			return true;
		}
		else {	
			BSTNode<T> parent = null;
			BSTNode<T> curr = root;
			while(curr != null) {
				parent = curr;
				if (data.compareTo(curr.item) < 0)
					curr = curr.left;
				else if (data.compareTo(curr.item) > 0)
					curr = curr.right;
				else	// 삽입할 데이터가 이미 있으면 false 반환
					return false;
			}
			if (parent.item.compareTo(data) > 0)
				parent.left = newNode;
			else
				parent.right = newNode;
			return true;
		}
	}
	
	public boolean insertByRecursion(T data) {	//재귀적 방법으로 삽입
		root = insertByRecursion(root, data);
		return insertSuccess;
	}
	private BSTNode<T> insertByRecursion(BSTNode<T> tree, T data) {
		if (tree == null) {
			insertSuccess = true;
			tree = new BSTNode<T>(data);
		}
		else if (data.compareTo(tree.item) < 0)
			tree.left = insertByRecursion(tree.left, data);
		else if (data.compareTo(tree.item) > 0)
			tree.right = insertByRecursion(tree.right, data);
		else
			insertSuccess = false;
		return tree;
	}
	
	public boolean remove(T data) { // 반복적 방법으로 삭제
		BSTNode<T> parent = null;
		BSTNode<T> curr = root;

		if(curr == null)
			return false;

		while(curr != null) {
			if(data.compareTo(curr.item) < 0) { // 왼쪽으로 이동
				parent = curr;
				curr = curr.left;
			}
			else if(data.compareTo(curr.item) > 0) { // 오른쪽으로 이동
				parent = curr;
				curr = curr.right;
			} 
			else{ // 찾았을 떄
				if(curr.left == null || curr.right == null) {
					if(parent == null) {
						if(curr.right != null)
							root = curr.right;
						else
							root = curr.left;
					} 
					else if(parent.left == curr) {
						if (curr.left != null)
							parent.left = curr.left;
						else
							parent.left = curr.right;
					} 
					else if(parent.right == curr) {
						if (curr.right != null)
							parent.right = curr.right;
						else
							parent.right = curr.left;
					}
				} 
				else if (curr.left != null && curr.right != null) {
					BSTNode<T> maxParentNode = null;
					BSTNode<T> maxNode = curr.left;
					while(maxNode.right != null) {
						maxParentNode = maxNode;
						maxNode = maxNode.right;
					}
					curr.item = maxNode.item;
					if(maxParentNode == null)
						curr.left = maxNode.left;
					else{
						curr.left = maxParentNode;
						maxParentNode.right = null;
					}
				}
				return true;
			}
		}
		return false; // 노드 끝까지 찾아도 없을 시 false반환
	}
	
	public boolean removeByRecursion(T data) {	//재귀적 방법으로 삭제
		root = removeByRecursion(root, data);
		return removeSuccess;
	}
	private BSTNode<T> removeByRecursion(BSTNode<T> tree, T data) {
		if (tree == null) {
			removeSuccess = false;
		}
		else if (data.compareTo(tree.item) < 0) {
			tree.left = removeByRecursion(tree.left, data);
		}
		else if (data.compareTo(tree.item) > 0) {
			tree.right = removeByRecursion(tree.right, data);
		}
		else {
			removeSuccess = true;
			if (tree.left == null) {
				tree = tree.right;
			}
			else if (tree.right == null) {
				tree = tree.left;
			}
			else {
				BSTNode<T> maxNode = tree.left;
				while (maxNode.right != null)
					maxNode = maxNode.right;
				tree.item = maxNode.item;
				tree.left = removeByRecursion(tree.left,maxNode.item);
			}
		}
		return tree;
	}
	
	public boolean search(T data) {	//반복적 방법으로 탐색
		BSTNode<T> tmp = root;
		if(tmp == null)
			return false;
		
		while(tmp != null){
			if(tmp.item == data)
				return true;
			else if(data.compareTo(tmp.item) < 0)
				tmp =tmp.left;
			else if(data.compareTo(tmp.item) > 0)
				tmp =tmp.right;
		}
		return false;
	}
	
	public boolean searchByRecursion(T data) {	//재귀적 방법으로 탐색
		return searchByRecursion(root, data);
	}
	private boolean searchByRecursion(BSTNode<T> tree, T data) {	//재귀적 방법으로 탐색
		if(tree == null)
			return false;
		if(tree.item == data)
			return true;
		
		else if(data.compareTo(tree.item) < 0)
			return searchByRecursion(tree.left,data);
		
		else if(data.compareTo(tree.item) > 0)
			return searchByRecursion(tree.right,data);
		
		return false;
	}
	
	public void inorderTraverse() {
		inorderTraverse(root);
		System.out.println();
	}
	private void inorderTraverse(BSTNode<T> tree) {
		if (tree != null) {
			inorderTraverse(tree.left);
			System.out.print(tree.item .toString()+ " ");
			inorderTraverse(tree.right);
		}
	}
	
	public void print() {
		print(root,0);
		System.out.println();
	}
	private void print(BSTNode<T> tree,int count) {
		if (tree != null) {
			print(tree.right,++count);
			for(int i=0;i<count-1;i++)
				System.out.print("\t");
			System.out.print(tree.item .toString());
			if(tree.left != null)
				System.out.print(",L");
			if(tree.right != null)
				System.out.print(",R");
			System.out.println();
			print(tree.left,count++);
			
		}
		count--;
	}

}

public class TestBST {
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>();
		String command;
		int data;
	
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a command: i(insert), ir(insert by recurion), r(emove), rr(remove by recursion)");
		System.out.println("s(earch), sr(search by recursion), inorder(traverse), p(rint), or q(uit)");

		while (true) {
			System.out.print("> ");
			command = input.next();
			if (command.equals("i")) {
				data = input.nextInt();
				if (tree.insert(data)) 
					System.out.println(data + " inserted.");
				else
					System.out.println(data + " is in the tree.");				
			}
			if (command.equals("ir")) {
				data = input.nextInt();
				if (tree.insertByRecursion(data)) 
					System.out.println(data + " inserted.");
				else
					System.out.println(data + " is in the tree.");				
			}
			else if (command.equals("rr")) {
				data = input.nextInt();
				if (tree.removeByRecursion(data))
					System.out.println(data + " removed.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("r")) {
				data = input.nextInt();
				if (tree.remove(data))
					System.out.println(data + " removed.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("s")) {
				data = input.nextInt();
				if (tree.search(data))
					System.out.println(data + " is in the tree.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("sr")) {
				data = input.nextInt();
				if (tree.searchByRecursion(data))
					System.out.println(data + " is in the tree.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("inorder"))
				tree.inorderTraverse();
			else if (command.equals("p"))
				tree.print();
			else if (command.equals("q")) {
				System.out.println("Commands terminated.");
				break;
			}
		}
		input.close();
	}
}