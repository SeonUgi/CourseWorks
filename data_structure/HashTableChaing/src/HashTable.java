import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class HashTable<K,V> {
	private LinkedList<Bucket<K, V>>[] table;
	private int size;
	private int tableSize;
	private double LOAD_THRESHOLD = 2;
	private static int INITIAL_TABLE_SIZE = 5;
	
	public HashTable(){	// 생성자
		tableSize = INITIAL_TABLE_SIZE;	//테이블 기본 크기
		table = new LinkedList[tableSize];
		
		for(int i=0;i<tableSize;i++)
			table[i] = new LinkedList<Bucket<K,V>>();
		
		size = 0;
	}
	
	public int hash(K key){	//나눗셈 범 key mod tablesize
		return key.hashCode()%tableSize;
	}
	
	public int probe(int tableIndex){ // 선형조사 다음주소 참조
		return 1;
	}
	
	public V search(K key){	// 탐색
		int home = hash(key);	//테이블주소 받아옴
		int tableIndex = home;	//테이블 주소 저장
		int increment = probe(tableIndex);	//선형조사를 위함
		
		do{
			if(table[tableIndex] != null){	//테이블이 사용중일때
				for(Bucket<K, V> item : table[tableIndex]){	//for loop 사용
					if(key.equals(item.key))	//key 찾음
						return item.value;	//value 반환
				}
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;//선형 탐색
		}while(tableIndex != home);
		
		return null;//못찾으면 null반환
	}
	
	public void insert(K key, V value){	// 삽입
		if((double)size/tableSize >= LOAD_THRESHOLD)	//테이블 사이즈보다 2배 많게 적재 됬을시
			rehash();
		
		int home = hash(key);	//key값 받아옴
		int tableIndex = home;	//테이블 인덱스 저장
		
		Bucket<K, V> item = new Bucket<K, V>(key, value);	//버킷 생성
		table[tableIndex].addFirst(item);
		size++;
	}
	
	public boolean remove(K key){
		int home = hash(key);	//테이블주소 받아옴
		int tableIndex = home;	//테이블 주소 저장
		int increment = probe(tableIndex);	//선형조사를 위함
		
		do{
			if(table[tableIndex] != null){	//테이블이 사용중일때
				for(Bucket<K, V> item : table[tableIndex]){	//for loop 사용
					if(key.equals(item.key)){	//key 찾음
						table[tableIndex].remove(item);
						size--;
						return true;	//value 반환
					}
				}
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;//선형 탐색
		}while(tableIndex != home);
		
		return false;//못찾으면 false반환
	}
	
	public void rehash(){		// 재해싱
		LinkedList<Bucket<K, V>>[] oldTable = table;
		tableSize = 2*oldTable .length + 1;	//테이블의 크기는 2^n을 피한다
		table = new LinkedList[tableSize];	//더 큰 테이블 생성
		for(int i=0;i<tableSize;i++)
			table[i] = new LinkedList<Bucket<K,V>>(); 
		size = 0;
		for(int i=0;i<oldTable.length;i++){	//이전 테이블에서 새로운 테이블로 삽입
			if(oldTable[i] != null){
				for(Bucket<K, V> item : oldTable[i])	//for loop 사용
					insert(item.key, item.value);
			}
		}
	}
	
	public void printHashTable(){ 	// 테이블 출력
		for(int i=0;i<tableSize;i++){   
			System.out.print(i);
			if(table[i].size() == 0)
				System.out.println();
			
			else{
				for(Bucket<K, V> item : table[i]){	//for loop 사용
					System.out.println("\t["+item.key+", "+item.value+"]");
				}
			}
		}
	}
	
	public void dijstra(){
		
	}
}
