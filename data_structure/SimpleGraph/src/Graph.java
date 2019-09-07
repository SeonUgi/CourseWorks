import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph<T extends Comparable <T>> {
	private LinkedList<Vertex<T>> table = new LinkedList<Vertex<T>>();
	final int INFINITY = Integer.MAX_VALUE;
	private Vertex<T> head;	
    private int size; 	// 그래프에 있는 정점의 개수
    private boolean isDirected;  	// 방향/무방향을 나타내는 불리언 값
    private boolean hasEdgeValue;  // 간선에 가중치가 있는지 나타내는 불리언 값
    
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
	public void createGraph(Scanner inFile){ // inFile에 주어진 간선을 사용하여 인접리스트 생성
    	T fromVert, toVert;
        int value;
       
		while (inFile.hasNext()) {
			fromVert = (T) inFile.next();	//시작 정점
			toVert = (T) inFile.next();		//도달 정점

			if (hasEdgeValue) {		//가중치가 있을 때
				value = inFile.nextInt();
				insertVertex(fromVert);	
				insertVertex(toVert);
				insertEdge(fromVert, toVert, value);	//간선으로 연결
				
				if (!isDirected)	//무방향 그래프일 경우
					insertEdge(toVert, fromVert, value);
			}
           
			else {		//가중치가 없을 때
				insertVertex(fromVert);
				insertVertex(toVert);
				insertEdge(fromVert, toVert, 0);	//가중치 0
				
				if (!isDirected)
					insertEdge(toVert, fromVert, 0);
			}
        }
    }
    
	public void insertVertex(T item) { // item을 갖는 정점을 만들어 정점 리스트에 삽입
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
			
			if (curr != null && item.equals(curr.vertItem))	//이미 존재 할 시 리턴
				return;
			
			if (prev == null)	//맨 앞이면 맨 앞에 삽입
				head = vertex;
			
			else	//맨 앞이 아니면 prev 뒤에 
				prev.nextVert = vertex;
			
			vertex.nextVert = curr;
		}
		table.add(vertex);
		size++;
	}
	
	

	// 정점 fromVert에서 인접한 정점 toVert를 갖는 간선 item을 인접 리스트에 삽입 
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
    
   // 정점 노드 중에서 item을갖는 노드를  찾아 그 정점을 반환
    public Vertex<T> findVertex(T item){
    	Vertex<T> vertex = head;
    	
    	while(vertex != null){
    		if(item.equals(vertex.vertItem))
    			return vertex;
    		vertex = vertex.nextVert;
    	}
    	
		return null;
    }
    
    public void print(){	// 그래프 출력
    	Vertex<T> scan = head;
    	int count = 0;
    	
    	while(scan != null){
    		System.out.print(scan.vertItem+" ->");	//방문 정점 출력
    		Edge<T> edge = scan.nextEdge;	//정점과 연결된 간선
    		
    		while(edge != null){
    			System.out.print(" ("+edge.vertItem+", "+edge.edgeItem+")");	//간선에 있는 정점 출력
    			edge = edge.next;
    		}
    		
    		scan = scan.nextVert;
    		count++;
    		System.out.println();
    	}
    	System.out.println("Number of vertices: "+count);
    	System.out.println();
    }
    
    public void DFS(){	//깊이 우선 탐색
    	Vertex<T> scan = head;
    	System.out.print("Depth First Search: "+scan.vertItem);
    	DFS(scan);	//재귀로 깊이 우선탐색
    	initialize();	//방문기록 초기화
    	System.out.println();
    }
    
    private void DFS(Vertex<T> curt) {
		curt.isVisit = true;	//현재 정점 방문체크
		Edge<T> edge = curt.nextEdge;	//현제 정점과 연결된 간선
		while(edge != null){	//간선들에 대해 탐색
			Vertex<T> temp = findVertex(edge.vertItem);	//간선에 연결된 정점
			if(!temp.isVisit){	//방문하지 않은 정점일 경우
				System.out.print(" "+temp.vertItem);	//출력
				DFS(temp);	//간선에 연결된 
			}
			edge = edge.next;
		}
	}
    
    //깊이 우선 탐색시 방문시 true로 했으나 
    //넓이 우선 탐색시 true바뀌어 방문시 false로 함
    public void BFS(){	//넓이 우선 탐색
    	Queue<T> queue = new LinkedList<T>();
    	Vertex<T> scan = head;	//맨 처음 시작 위치
    	scan.isVisit = true;	//시작위치 방문 체크
    	queue.add(scan.vertItem);	//처음 정점 큐에 삽입
    	System.out.print("Breadth First Search: "+scan.vertItem);
    	
    	while(!queue.isEmpty()){
    		T data = queue.poll();
    		Edge<T> tmpEdge = findVertex(data).nextEdge;	//정점과 연결된 간선
    		while(tmpEdge != null){
    			Vertex<T> tmpVert = findVertex(tmpEdge.vertItem);	//인접 정점
    			if(!tmpVert.isVisit){	//들리지 않앗으면
    				System.out.print(" "+tmpVert.vertItem);
    				findVertex(tmpEdge.vertItem).isVisit = true;	//방문함으로 체크
    				queue.add(tmpEdge.vertItem);
    			}
    			tmpEdge = tmpEdge.next;
    		}
    	}
    	initialize();	//방문기록 초기화
    	System.out.println("\n");
    }
    
    private void initialize(){	//방문 기록을 초기화
    	Vertex<T> scan = head;
    	for(int i=0;i<size;i++){
    		scan.isVisit = false;
    		scan = scan.nextVert;
    	}
    }
    
    private void intialDistance(int[] distance, int index){
    	Vertex<T> scan = table.get(index);	//시작 정점
    	Edge<T> edge = scan.nextEdge;	//정점의 간선
    	
    	for(int i=0;i<distance.length;i++){
    		if(i == index)
    			distance[i] = 0;
    		else
    			distance[i] = INFINITY;
    	}

		while (edge != null) { // 최단 거리로 초기화
			distance[table.indexOf(findVertex(edge.vertItem))] = edge.edgeItem;
			edge = edge.next;
		}
    }
    
    private void dijkstra(int[] distance,int index){
    	//최단 정점 집합
    	HashMap<Integer, Vertex<T>> VertexSet = new HashMap<Integer, Vertex<T>>();	
    	Vertex<T> scan = table.get(index);	//시작 정점
    	Edge<T> edge = null;
    	VertexSet.put(index, scan);		//시작 정점 집합에 포함
    	
		while(VertexSet.size() != size){	// 집합이 꽉 찰 때까지
			int minIndex = 0;
			for (int i = 0, min = INFINITY; i < distance.length; i++) {
				if (!VertexSet.containsValue(table.get(i))) {
					if (min > distance[i]) {
						min = distance[i];
						minIndex = i; // 최소값의 인덱스
					}
				}
			}
			
			scan = table.get(minIndex);	//최소 가중치를 가지는 정점
			edge = scan.nextEdge;
			VertexSet.put(minIndex, scan);	//집합에 정점 삽입
			
			while (edge != null) { // 최단 거리로 초기화
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
    		System.out.println("시작 정점:\t"+scan.vertItem);
    		
    		intialDistance(distance,i);
    		dijkstra(distance,i);
    		
    		System.out.print(" 정점\t: ");
    		for(int j=0;j<size;j++)
    			System.out.print(table.get(j)+"\t");
    		System.out.println();
    		
    		System.out.print("최단거리\t: ");
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
