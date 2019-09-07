enum Status {EMPTY, USING, DELETED}

class Bucket<K, V> {
	public K key;
	public V value;
	public Status status; 
	public Bucket(K key, V value) {
		this.key = key;
		this.value = value;
		status = Status.USING;
	}
	public Bucket() {
		status = Status.EMPTY;
	}
}

@SuppressWarnings("unchecked")
public class HashTable<K,V> {
	private Bucket<K, V>[] table;
	private int size;
	private int tableSize;
	private double LOAD_THRESHOLD = 0.7;
	private static int INITIAL_TABLE_SIZE = 5;
	
	
	public HashTable(){	// 생성자
		table = new Bucket[INITIAL_TABLE_SIZE];	//Bucket배열 생성
		tableSize = INITIAL_TABLE_SIZE;	//테이블 기본 크기
		for(int i=0;i<tableSize;i++)
			table[i] = new Bucket<K,V>();	//배열마다 객체 생성
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
			//테이블이 사용중이고 key값이 테이블의 key값과 같다면
			if(table[tableIndex].status == Status.USING 
					&& key.equals(table[tableIndex].key))
				return table[tableIndex].value;	//값 리턴
			else
				tableIndex = (tableIndex+increment)%tableSize;//선형 탐색
		}while(tableIndex != home);//처음으로 되돌아 올때까지
		return null;//못찾으면 null반환
	}
	
	public void insert(K key, V value){	// 삽입
		if((double)size/tableSize>=LOAD_THRESHOLD)	//적재밀도가 70%이상일시 리해싱
			rehash();
		int home = hash(key);	//key값 받아옴
		int tableIndex = home;	//테이블 인덱스 저장
		int increment = probe(tableIndex);	//선형탐색을 위함 h(k)+1
		while(true){
			if(table[tableIndex].status != Status.USING){	//사용중이 아니라면
				table[tableIndex].value = value;	//값 저장
				table[tableIndex].status= Status.USING;	//현제 테이블 사용중으로 변환
				size++;	//사이즈 증가
				break;	
			}
			else	//사용중일 시
				//테이블 인덱스 다시지정 h(k)
				tableIndex = (tableIndex+increment)%tableSize; 	
		}
	}
	public boolean remove(K key){
		int home = hash(key);	//처음 테이블 주소
		int tableIndex = home;	//테이블 주소를 인덱스로
		int increment = probe(tableIndex);	//선형탐색을 위해
		do{
			//테이블이 현제 사용중이고 key값이 테이블의 key값과 같다면
			if(table[tableIndex].status == Status.USING && 
					key.equals(table[tableIndex].key)){
				//현제 테이블 삭제 상태로 바꿈
				table[tableIndex].status=Status.DELETED;
				size--;	//사이즈 줄임
				return true;	//삭제 성공
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;	//선형 탐색
		}while(tableIndex!=home);	//처음으로 되돌아 올때까지
		
		return false;  //삭제 실패
	}
	
	public void rehash(){		// 재해싱
		Bucket<K, V>[] oldTable  = table;
		tableSize = 2*oldTable .length + 1;	//테이블의 크기는 2^n을 피한다
		table = new Bucket[tableSize];	//새로운 테이블 생성
		for (int i=0;i<tableSize;i++)
			table[i] = new Bucket<K,V>();
		size =0;
		for (int i=0;i<oldTable .length;i++){
			if (oldTable [i].status == Status.USING){	//사용중인 이전 테이블 새로운 테이블에 삽입
				insert(oldTable [i].key, oldTable [i].value);
			}
		}
	}
	
	public void printHashTable(){ 	// 테이블 출력
		for(int i=0;i<tableSize;i++){   
			if(table[i].status == Status.USING)	// 사용중인 상태인 배열만 가져와 출력
				System.out.println("K:"+table[i].key+"\t"+"V:"+table[i].value);
		}
	}
}
