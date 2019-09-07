import java.io.FileNotFoundException;
import java.util.Scanner;

class TwoFourNode {
	final int ORDER = 4;
	public int numKeys;
	public int[] keys;
	public TwoFourNode[] next;

	public TwoFourNode(int key) {
		keys = new int[ORDER - 1];
		next = new TwoFourNode[ORDER];
		keys[0] = key;
		for (int i = 0; i < ORDER; i++)
			next[i] = null;
		numKeys = 1;
	}

	public boolean isLeaf() {
		return ((next[0] == null) ? true : false);
	}

	public boolean isFull() {
		return ((numKeys == ORDER - 1) ? true : false);
	}
}

class TwoFourTree {
	public static int num2Nodes;
	public static int num3Nodes;
	public static int num4Nodes;
	public static int numLeafNodes;

	private TwoFourNode root;

	public TwoFourTree() {
		root = null;
	}

	public void insert(int key) {
		root = insert(root, key);
	}

	public TwoFourNode insert(TwoFourNode tree, int key) {
		if (tree == null) { // 빈 트리, 첫번째 노드 생성
			tree = new TwoFourNode(key);
		} else {
			TwoFourNode parent = null;
			TwoFourNode curr = tree;
			int currIndex = 0;
			while (curr != null) {
				if (curr.isFull()) {
					parent = splitNode(parent, curr, key);
					if (tree == curr)
						tree = parent;
					curr = findChildNode(parent, key);
				}
				parent = curr;
				currIndex = findKeyPos(curr, key);
				curr = curr.next[currIndex];
			}
			parent = insertKey(parent, currIndex, key); // 리프노드에 키삽입
		}
		return tree;
	}

	public TwoFourNode findChildNode(TwoFourNode tree, int key) {
		if (key < tree.keys[0])
			return tree.next[0];
		else if (tree.numKeys == 1)
			return tree.next[1];
		else if (key > tree.keys[0] && key < tree.keys[1])
			return tree.next[1];
		else if (tree.numKeys == 2)
			return tree.next[2];
		else if (key > tree.keys[1] && key < tree.keys[2])
			return tree.next[2];
		else
			return tree.next[3];
	}

	// 4-node인 child를 분할하여 중간 키를 parent에 삽입하고
	// 분할된 2개의 2-node를 부모노드에 연결한다.
	public TwoFourNode splitNode(TwoFourNode parent, TwoFourNode curr, int key) {
		TwoFourNode rightNode = new TwoFourNode(curr.keys[2]);
		rightNode.next[0] = curr.next[2];
		rightNode.next[1] = curr.next[3];
		int middleKey = curr.keys[1];
		curr.numKeys = 1;
		if (parent == null) { // 루트를 분할하면 새로운 루트 생성, 중간 키를 갖는 노드가 새로운 루트가 됨
			parent = new TwoFourNode(middleKey);
			parent.next[0] = curr;
			parent.next[1] = rightNode;
		} else { // 루트 이외의 노드 분할
			int currIndex = findKeyPos(parent, middleKey); // parent에 삽입할 중간 키
															// 찾음
			parent = insertKey(parent, currIndex, middleKey); // parent에 중간 키 삽입
			parent.next[currIndex] = curr; // 분할된 2개의 2-node를 부모 노드에 연뎔
			parent.next[currIndex + 1] = rightNode;
		}
		return parent;
	}

	public int findKeyPos(TwoFourNode tree, int key) {
		int index = 0;
		if (key < tree.keys[0])
			return index;
		else {
			index = tree.numKeys - 1;
			while (key < tree.keys[index])
				index--;
			return index + 1;
		}
	}

	// 노드에 삽입할 키의 위치는 index. 노드의 키와 포인터 위치를 오른쪽으로 이동한 후 키를 삽입한다.
	public TwoFourNode insertKey(TwoFourNode tree, int index, int key) {
		int endIndex;
		for (endIndex = tree.numKeys; endIndex > index; endIndex--) {
			tree.keys[endIndex] = tree.keys[endIndex - 1];
			tree.next[endIndex + 1] = tree.next[endIndex];
		}
		tree.keys[endIndex] = key;
		tree.numKeys++;
		return tree;
	}

	public void clearCount() {
		num2Nodes = 0;
		num3Nodes = 0;
		num4Nodes = 0;
		numLeafNodes = 0;
	}

	public void print() {
		print(root, 0);
	}

	private void print(TwoFourNode tree, int count) {
		if (tree != null) {
			if (tree.isLeaf()) { // leaf node일때
				for (int i = 0; i < count; i++)
					System.out.print("    ");
				for (int i = 0; i < tree.numKeys; i++)
					System.out.print(" " + tree.keys[i]);
				System.out.println();
			} else {
				int i;
				for (i = 0; i < tree.numKeys; i++) {
					print(tree.next[i], count + 1);
					for (int j = 0; j < count; j++)
						System.out.print("  ");
					System.out.println("  " + tree.keys[i]);
				}
				print(tree.next[i], count + 1);
				System.out.println();
			}
		}
	}

	public void countNodes() {
		countNodes(root);
	}

	private void countNodes(TwoFourNode tree) {
		for (int i = 0; i < tree.numKeys + 1; i++) {
			if (tree.next[i] != null)
				countNodes(tree.next[i]);
		}
		if (tree.isLeaf())
			numLeafNodes++;
		if (tree.numKeys == 1)
			num2Nodes++;
		else if (tree.numKeys == 2)
			num3Nodes++;
		else if (tree.numKeys == 3)
			num4Nodes++;
	}

	public boolean search(int key) {
		return search(root, key);
	}

	private boolean search(TwoFourNode tree, int key) {
		if (tree != null) {
			if (tree.isLeaf()) { // leaf node일때
				for (int i = 0; i < tree.numKeys; i++)
					if (key == tree.keys[i])
						return true;
			} else {
				int i;
				for (i = 0; i < tree.numKeys; i++) {
					if (search(tree.next[i], key))
						return true;
					if (key == tree.keys[i])
						return true;
				}
				return search(tree.next[i], key);
			}
		}
		return false;
	}

	public int height() {
		return height(root, 0);
	}

	private int height(TwoFourNode tree, int height) {
		if (tree.isLeaf())
			return 0;
		else
			return height(tree.next[0], height) + 1;
	}

	public int max() {
		return max(root);
	}

	private int max(TwoFourNode tree) {
		if (tree.isLeaf())
			return tree.keys[tree.numKeys - 1];
		else
			return max(tree.next[tree.numKeys]);
	}
}

public class Test234Tree {
	public static void main(String[] args) throws FileNotFoundException {
		TwoFourTree tree = new TwoFourTree();
		char command;
		int num;
		Scanner in = new Scanner(System.in);

		System.out.println("Enter i(nsert), s(earch), c(ount), h(eight), m(ax)," + "p(rint), or q(uit)");
		System.out.print("> ");
		command = in.next().charAt(0);
		while (command != 'q') {
			if (command == 'i') {
				num = in.nextInt();
				tree.insert(num);
			} else if (command == 's') {
				num = in.nextInt();
				if (tree.search(num))
					System.out.println(num + " found.");
				else
					System.out.println(num + " not found.");
			} else if (command == 'p')
				tree.print();
			else if (command == 'c') {
				tree.clearCount();
				tree.countNodes();
				System.out.println("2 nodes: " + TwoFourTree.num2Nodes);
				System.out.println("3 nodes: " + TwoFourTree.num3Nodes);
				System.out.println("4 nodes: " + TwoFourTree.num4Nodes);
				System.out.println("leaf nodes: " + TwoFourTree.numLeafNodes);
			} else if (command == 'h')
				System.out.println("height: " + tree.height());
			else if (command == 'm')
				System.out.println("max: " + tree.max());

			System.out.print("> ");
			command = in.next().charAt(0);
		}
		in.close();
	}
}
