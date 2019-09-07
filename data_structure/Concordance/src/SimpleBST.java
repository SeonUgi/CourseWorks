
class BSTNode {
	public String word;
	public SimpleLinkedList list;
	public BSTNode left;
	public BSTNode right;
	
	public BSTNode(String word) {  // 생성자
		this.word = word;
		list = new SimpleLinkedList();
		left = null;
		right = null;
	}
}

public class SimpleBST {
	private BSTNode root;
	public SimpleBST() {	// 생성자
		root = null;
	}
// 단어 word를 라인 번호 lineNum와 함께 BST에 삽입
	public void insert(String word, int lineNum){
		if(String.valueOf(word.charAt(0)).matches("[0-9]")){
			return;
		}
		BSTNode newNode = new BSTNode(word);
		newNode.list.insert(lineNum);
		if(root == null){
			root =newNode;
		}
		else{
			BSTNode parent = null;
			BSTNode curr = root;
			while(curr != null){
				parent = curr;
				if(word.compareTo(curr.word)<0)
					curr = curr.left;
				
				else if(word.compareTo(curr.word)>0)
					curr = curr.right;
				
				else{
					curr.list.insert(lineNum);
					return;
				}
			}
			if(parent.word.compareTo(word)>0)
				parent.left=newNode;
			else
				parent.right=newNode;
		}
	}
	
	public void print(){
		print(root);
	}
	public void print(BSTNode tree){
		if(tree.left != null)
			print(tree.left);
		System.out.printf("%-8s\t\t%s\n",tree.word,tree.list);
		if(tree.right != null)
			print(tree.right);
	}
}

