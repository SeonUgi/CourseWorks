
public class Vertex<T> {
	public T vertItem;
	public Vertex<T> nextVert;	//���� ���
	public Edge<T> nextEdge;	//����� ����
	public boolean isVisit;	//�湮 ����
	
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
