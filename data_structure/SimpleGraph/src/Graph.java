import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph<T extends Comparable <T>> {
	private LinkedList<Vertex<T>> table = new LinkedList<Vertex<T>>();
	final int INFINITY = Integer.MAX_VALUE;
	private Vertex<T> head;	
    private int size; 	// �׷����� �ִ� ������ ����
    private boolean isDirected;  	// ����/�������� ��Ÿ���� �Ҹ��� ��
    private boolean hasEdgeValue;  // ������ ����ġ�� �ִ��� ��Ÿ���� �Ҹ��� ��
    
    public Graph(){
    	this(false, false);	
    }
    
    public Graph(boolean isDirected, boolean hasEdgeValue) {
		head = null;
		size = 0;
		this.isDirected = isDirected;
		this.hasEdgeValue = hasEdgeValue;
    }
    
    @SuppressWarnings("unchecked")
	public void createGraph(Scanner inFile){ // inFile�� �־��� ������ ����Ͽ� ��������Ʈ ����
    	T fromVert, toVert;
        int value;
       
		while (inFile.hasNext()) {
			fromVert = (T) inFile.next();	//���� ����
			toVert = (T) inFile.next();		//���� ����

			if (hasEdgeValue) {		//����ġ�� ���� ��
				value = inFile.nextInt();
				insertVertex(fromVert);	
				insertVertex(toVert);
				insertEdge(fromVert, toVert, value);	//�������� ����
				
				if (!isDirected)	//������ �׷����� ���
					insertEdge(toVert, fromVert, value);
			}
           
			else {		//����ġ�� ���� ��
				insertVertex(fromVert);
				insertVertex(toVert);
				insertEdge(fromVert, toVert, 0);	//����ġ 0
				
				if (!isDirected)
					insertEdge(toVert, fromVert, 0);
			}
        }
    }
    
	public void insertVertex(T item) { // item�� ���� ������ ����� ���� ����Ʈ�� ����
		Vertex<T> vertex = new Vertex<T>(item);
		Vertex<T> prev, curr;
		if (head == null)
			head = vertex;
		
		else {
			prev = null;
			curr = head;
			while (curr != null && item.compareTo(curr.vertItem) > 0) {
				prev = curr;
				curr = curr.nextVert;
			}
			
			if (curr != null && item.equals(curr.vertItem))	//�̹� ���� �� �� ����
				return;
			
			if (prev == null)	//�� ���̸� �� �տ� ����
				head = vertex;
			
			else	//�� ���� �ƴϸ� prev �ڿ� 
				prev.nextVert = vertex;
			
			vertex.nextVert = curr;
		}
		table.add(vertex);
		size++;
	}
	
	

	// ���� fromVert���� ������ ���� toVert�� ���� ���� item�� ���� ����Ʈ�� ���� 
    public void insertEdge(T fromVert, T toVert, int item){   
       Vertex<T> tmp = findVertex(fromVert);
       Edge<T> edge = tmp.nextEdge;
       if(edge == null)
          tmp.nextEdge = new Edge<T>(toVert, item);
       else{
          while(edge.next != null)
             edge = edge.next;
          edge.next = new Edge<T>(toVert, item);
       }
    }
    
   // ���� ��� �߿��� item������ ��带  ã�� �� ������ ��ȯ
    public Vertex<T> findVertex(T item){
    	Vertex<T> vertex = head;
    	
    	while(vertex != null){
    		if(item.equals(vertex.vertItem))
    			return vertex;
    		vertex = vertex.nextVert;
    	}
    	
		return null;
    }
    
    public void print(){	// �׷��� ���
    	Vertex<T> scan = head;
    	int count = 0;
    	
    	while(scan != null){
    		System.out.print(scan.vertItem+" ->");	//�湮 ���� ���
    		Edge<T> edge = scan.nextEdge;	//������ ����� ����
    		
    		while(edge != null){
    			System.out.print(" ("+edge.vertItem+", "+edge.edgeItem+")");	//������ �ִ� ���� ���
    			edge = edge.next;
    		}
    		
    		scan = scan.nextVert;
    		count++;
    		System.out.println();
    	}
    	System.out.println("Number of vertices: "+count);
    	System.out.println();
    }
    
    public void DFS(){	//���� �켱 Ž��
    	Vertex<T> scan = head;
    	System.out.print("Depth First Search: "+scan.vertItem);
    	DFS(scan);	//��ͷ� ���� �켱Ž��
    	initialize();	//�湮��� �ʱ�ȭ
    	System.out.println();
    }
    
    private void DFS(Vertex<T> curt) {
		curt.isVisit = true;	//���� ���� �湮üũ
		Edge<T> edge = curt.nextEdge;	//���� ������ ����� ����
		while(edge != null){	//�����鿡 ���� Ž��
			Vertex<T> temp = findVertex(edge.vertItem);	//������ ����� ����
			if(!temp.isVisit){	//�湮���� ���� ������ ���
				System.out.print(" "+temp.vertItem);	//���
				DFS(temp);	//������ ����� 
			}
			edge = edge.next;
		}
	}
    
    //���� �켱 Ž���� �湮�� true�� ������ 
    //���� �켱 Ž���� true�ٲ�� �湮�� false�� ��
    public void BFS(){	//���� �켱 Ž��
    	Queue<T> queue = new LinkedList<T>();
    	Vertex<T> scan = head;	//�� ó�� ���� ��ġ
    	scan.isVisit = true;	//������ġ �湮 üũ
    	queue.add(scan.vertItem);	//ó�� ���� ť�� ����
    	System.out.print("Breadth First Search: "+scan.vertItem);
    	
    	while(!queue.isEmpty()){
    		T data = queue.poll();
    		Edge<T> tmpEdge = findVertex(data).nextEdge;	//������ ����� ����
    		while(tmpEdge != null){
    			Vertex<T> tmpVert = findVertex(tmpEdge.vertItem);	//���� ����
    			if(!tmpVert.isVisit){	//�鸮�� �ʾ�����
    				System.out.print(" "+tmpVert.vertItem);
    				findVertex(tmpEdge.vertItem).isVisit = true;	//�湮������ üũ
    				queue.add(tmpEdge.vertItem);
    			}
    			tmpEdge = tmpEdge.next;
    		}
    	}
    	initialize();	//�湮��� �ʱ�ȭ
    	System.out.println("\n");
    }
    
    private void initialize(){	//�湮 ����� �ʱ�ȭ
    	Vertex<T> scan = head;
    	for(int i=0;i<size;i++){
    		scan.isVisit = false;
    		scan = scan.nextVert;
    	}
    }
    
    private void intialDistance(int[] distance, int index){
    	Vertex<T> scan = table.get(index);	//���� ����
    	Edge<T> edge = scan.nextEdge;	//������ ����
    	
    	for(int i=0;i<distance.length;i++){
    		if(i == index)
    			distance[i] = 0;
    		else
    			distance[i] = INFINITY;
    	}

		while (edge != null) { // �ִ� �Ÿ��� �ʱ�ȭ
			distance[table.indexOf(findVertex(edge.vertItem))] = edge.edgeItem;
			edge = edge.next;
		}
    }
    
    private void dijkstra(int[] distance,int index){
    	//�ִ� ���� ����
    	HashMap<Integer, Vertex<T>> VertexSet = new HashMap<Integer, Vertex<T>>();	
    	Vertex<T> scan = table.get(index);	//���� ����
    	Edge<T> edge = null;
    	VertexSet.put(index, scan);		//���� ���� ���տ� ����
    	
		while(VertexSet.size() != size){	// ������ �� �� ������
			int minIndex = 0;
			for (int i = 0, min = INFINITY; i < distance.length; i++) {
				if (!VertexSet.containsValue(table.get(i))) {
					if (min > distance[i]) {
						min = distance[i];
						minIndex = i; // �ּҰ��� �ε���
					}
				}
			}
			
			scan = table.get(minIndex);	//�ּ� ����ġ�� ������ ����
			edge = scan.nextEdge;
			VertexSet.put(minIndex, scan);	//���տ� ���� ����
			
			while (edge != null) { // �ִ� �Ÿ��� �ʱ�ȭ
				if(distance[minIndex] + edge.edgeItem >= 0){
					if(distance[table.indexOf(findVertex(edge.vertItem))] > distance[minIndex] + edge.edgeItem)
						distance[table.indexOf(findVertex(edge.vertItem))] = distance[minIndex] + edge.edgeItem;
				}
				edge = edge.next;
			}
		}
    }
    
    

	public void dijkstra() {
		int[] distance = new int[size];
    	Vertex<T> scan = head;
    	for(int i=0;i<size;i++){
    		System.out.println("���� ����:\t"+scan.vertItem);
    		
    		intialDistance(distance,i);
    		dijkstra(distance,i);
    		
    		System.out.print(" ����\t: ");
    		for(int j=0;j<size;j++)
    			System.out.print(table.get(j)+"\t");
    		System.out.println();
    		
    		System.out.print("�ִܰŸ�\t: ");
    		for(int j=0;j<size;j++){
    			if(distance[j] == INFINITY)
    				System.out.print('*'+"\t");
    			else
    				System.out.print(distance[j]+"\t");
    		}
    		System.out.println("\n");
    		scan = scan.nextVert;
    	}
	}
}
