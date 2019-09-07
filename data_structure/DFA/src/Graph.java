import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph<V extends Comparable<V>, E extends Comparable<E>> {
	private LinkedList<V> vertex = new LinkedList<V>(); // ��������
	private Edge<E>[][] matrix = null;
	private int size; // �׷����� �ִ� ������ ����
	private int maxSize;
	private V start;	//���� ����
	private LinkedList<V> end;	//���� ����

	@SuppressWarnings("unchecked")
	public Graph() {
		size = 0;
		maxSize = 50;
		matrix = new Edge[maxSize][maxSize];
		end = new LinkedList<V>();
	}

	//inFile�� �־��� ������ ����Ͽ� ������� ����
	@SuppressWarnings("unchecked")
	public void createGraph(Scanner inFile) { 
		V fromVert, toVert;
		String value;

		start = (V) inFile.nextLine(); // ������ġ

		StringTokenizer st = new StringTokenizer(inFile.nextLine());
		while (st.hasMoreTokens())
			end.add((V) st.nextToken()); // ���� ������ �߰�

		while (inFile.hasNextLine()) {
			st = new StringTokenizer(inFile.nextLine());
			while (st.hasMoreTokens()) {
				if (size > maxSize) {
					System.out.println("size is full");
					return;
				}
				fromVert = (V) st.nextToken(); // ���� ����
				insertVertex(fromVert);
				toVert = (V) st.nextToken(); // ���� ����
				insertVertex(toVert);

				//"����
				value = st.nextToken().replace("\"", "");
				if (value.equals(""))
					st.nextToken();
				
				//�� �������� �ε����� �����Ͽ� ������Ŀ� ����ġ �߰�.
				if (matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)] == null)
					matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)] = new Edge<E>();
				
				insertEdge(matrix[vertex.indexOf(fromVert)][vertex.indexOf(toVert)],(E)value);
			}
		}
	}

	private void insertVertex(V item) { // item�� ���� ������ ����� ���� ����Ʈ�� ����
		if (!vertex.contains(item)) {
			vertex.add(item);
			size++;
		}
	}

	// ���� fromVert���� ������ ���� toVert�� ���� ���� item�� edge�� ����
	public void insertEdge(Edge<E> edge, E item) {
		if (edge.edgeItem == null)
			edge.edgeItem = item;

		else {
			Edge<E> newEdge = new Edge<E>(item);
			newEdge.next = edge;
			edge = newEdge;
		}
	}

	//DFA ����
	public boolean DFA(String str) {
		Queue<String> value = new LinkedList<String>();
		for (int i = 0; i < str.length(); i++)
			value.add(str.charAt(i) + "");

		return DFA(value);
	}

	@SuppressWarnings("unchecked")
	public boolean DFA(Queue<String> queue) {
		V curt = start;	//���� ��ġ

		//ť�� �������� �ݺ�
		while (!queue.isEmpty()) {
			E value = (E) queue.poll();

			label: for (int i = 0; i < size; i++) {
				Edge<E> edge = matrix[vertex.indexOf(curt)][i];
				while (edge != null) {
					if (edge.edgeItem.compareTo(value) == 0) {
						curt = vertex.get(i);
						break label;	//���� ���¸� ã�� ��� �ݺ��� Ż��
					}
					edge = edge.next;
				}
				if (i == size - 1)	//���� ���¸� ã�� ���� ��� false��ȯ
					return false;
			}
			
			//ť�� ���, ���� ��������� ��� true ��ȯ
			if (end.contains(curt) && queue.isEmpty())
				return true;
		}
		return false;
	}
}
