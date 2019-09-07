
public class Edge<T> {
	public T vertItem;
	public int edgeItem;
	public Edge<T> next;
	public Edge(T vertItem, int edgeItem) {
		this.vertItem = vertItem;
		this.edgeItem = edgeItem;
		next = null;
	}
	public String toString(){
		return String.valueOf(edgeItem);
	}
}
