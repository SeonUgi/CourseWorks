
public class Edge<E> {
	public E edgeItem;	//가중치
	public Edge<E> next;	//다음 가중치
	
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
