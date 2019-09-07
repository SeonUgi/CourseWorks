
public class Edge<E> {
	public E edgeItem;	//����ġ
	public Edge<E> next;	//���� ����ġ
	
	public Edge() {
		edgeItem = null;
		next = null;
	}
	
	public Edge(E edgeItem) {
		this.edgeItem = edgeItem;
		next = null;
	}
	public String toString(){
		return String.valueOf(edgeItem);
	}
}
