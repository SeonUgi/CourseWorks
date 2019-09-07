import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph<V extends Comparable<V>, E extends Comparable<E>> {
	private LinkedList<V> vertex = new LinkedList<V>(); // 정점집합
	private Edge<E>[][] matrix = null;
	private int size; // 그래프에 있는 정점의 개수
	private int maxSize;
	private V start;	//시작 정점
	private LinkedList<V> end;	//종료 정점

	@SuppressWarnings("unchecked")
	public Graph() {
		size = 0;
		maxSize = 50;
		matrix = new Edge[maxSize][maxSize];
		end = new LinkedList<V>();
	}

	//inFile에 주어진 간선을 사용하여 인접행렬 생성
	@SuppressWarnings("unchecked")
	public void createGraph(Scanner inFile) { 
		V fromVert, toVert;
		String value;

		start = (V) inFile.nextLine(); // 시작위치

		StringTokenizer st = new StringTokenizer(inFile.nextLine());
		while (st.hasMoreTokens())
			end.add((V) st.nextToken()); // 종료 정점들 추가

		while (inFile.hasNextLine()) {
			st = new StringTokenizer(inFile.nextLine());
			while (st.hasMoreTokens()) {
				if (size > maxSize) {
					System.out.println("size is full");
					return;
				}
				fromVert = (V) st.nextToken(); // 시작 정점
				insertVertex(fromVert);
				toVert = (V) st.nextToken(); // 도달 정점
				insertVertex(toVert);

				//"제거
				value = st.nextToken().replace("\"", "");
				if (value.equals(""))
					st.nextToken();
				
				//각 정점들의 인덱스를 참고하여 인접행렬에 가중치 추가.
				if (matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)] == null)
					matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)] = new Edge<E>();
				
				insertEdge(matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)],(E)value);
			}
		}
	}

	private void insertVertex(V item) { // item을 갖는 정점을 만들어 정점 리스트에 삽입
		if (!vertex.contains(item)) {
			vertex.add(item);
			size++;
		}
	}

	// 정점 fromVert에서 인접한 정점 toVert를 갖는 간선 item을 edge에 삽입
	public void insertEdge(Edge<E> edge, E item) {
		if (edge.edgeItem == null)
			edge.edgeItem = item;

		else {
			Edge<E> newEdge = new Edge<E>(item);
			newEdge.next = edge;
			edge = newEdge;
		}
	}

	//DFA 실행
	public boolean DFA(String str) {
		Queue<String> value = new LinkedList<String>();
		for (int i = 0; i < str.length(); i++)
			value.add(str.charAt(i) + "");

		return DFA(value);
	}

	@SuppressWarnings("unchecked")
	public boolean DFA(Queue<String> queue) {
		V curt = start;	//시작 위치

		//큐가 빌때까지 반복
		while (!queue.isEmpty()) {
			E value = (E) queue.poll();

			label: for (int i = 0; i < size; i++) {
				Edge<E> edge = matrix[vertex.indexOf(curt)][i];
				while (edge != null) {
					if (edge.edgeItem.compareTo(value) == 0) {
						curt = vertex.get(i);
						break label;	//전이 상태를 찾을 경우 반복문 탈출
					}
					edge = edge.next;
				}
				if (i == size - 1)	//전이 상태를 찾지 못할 경우 false반환
					return false;
			}
			
			//큐가 비고, 현재 종료상태일 경우 true 반환
			if (end.contains(curt) && queue.isEmpty())
				return true;
		}
		return false;
	}
}
