
public class Vertex<T> {
	public T vertItem;
	public Vertex<T> nextVert;	//다음 노드
	public Edge<T> nextEdge;	//연결된 간선
	public boolean isVisit;	//방문 여부
	
	public Vertex(T vertItem) {
		this.vertItem = vertItem;
		nextVert = null;
		nextEdge = null;
		isVisit = false;
	}
	
	public String toString(){
		return vertItem.toString();
	}
}
