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
	
	
	public HashTable(){	// ������
		table = new Bucket[INITIAL_TABLE_SIZE];	//Bucket�迭 ����
		tableSize = INITIAL_TABLE_SIZE;	//���̺� �⺻ ũ��
		for(int i=0;i<tableSize;i++)
			table[i] = new Bucket<K,V>();	//�迭���� ��ü ����
		size = 0;
	}
	public int hash(K key){	//������ �� key mod tablesize
		return key.hashCode()%tableSize;
	}
	public int probe(int tableIndex){ // �������� �����ּ� ����
		return 1;
	}
	public V search(K key){	// Ž��
		int home = hash(key);	//���̺��ּ� �޾ƿ�
		int tableIndex = home;	//���̺� �ּ� ����
		int increment = probe(tableIndex);	//�������縦 ����
		do{
			//���̺��� ������̰� key���� ���̺��� key���� ���ٸ�
			if(table[tableIndex].status == Status.USING 
					&& key.equals(table[tableIndex].key))
				return table[tableIndex].value;	//�� ����
			else
				tableIndex = (tableIndex+increment)%tableSize;//���� Ž��
		}while(tableIndex != home);//ó������ �ǵ��� �ö�����
		return null;//��ã���� null��ȯ
	}
	
	public void insert(K key, V value){	// ����
		if((double)size/tableSize>=LOAD_THRESHOLD)	//����е��� 70%�̻��Ͻ� ���ؽ�
			rehash();
		int home = hash(key);	//key�� �޾ƿ�
		int tableIndex = home;	//���̺� �ε��� ����
		int increment = probe(tableIndex);	//����Ž���� ���� h(k)+1
		while(true){
			if(table[tableIndex].status != Status.USING){	//������� �ƴ϶��
				table[tableIndex].value = value;	//�� ����
				table[tableIndex].status= Status.USING;	//���� ���̺� ��������� ��ȯ
				size++;	//������ ����
				break;	
			}
			else	//������� ��
				//���̺� �ε��� �ٽ����� h(k)
				tableIndex = (tableIndex+increment)%tableSize; 	
		}
	}
	public boolean remove(K key){
		int home = hash(key);	//ó�� ���̺� �ּ�
		int tableIndex = home;	//���̺� �ּҸ� �ε�����
		int increment = probe(tableIndex);	//����Ž���� ����
		do{
			//���̺��� ���� ������̰� key���� ���̺��� key���� ���ٸ�
			if(table[tableIndex].status == Status.USING && 
					key.equals(table[tableIndex].key)){
				//���� ���̺� ���� ���·� �ٲ�
				table[tableIndex].status=Status.DELETED;
				size--;	//������ ����
				return true;	//���� ����
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;	//���� Ž��
		}while(tableIndex!=home);	//ó������ �ǵ��� �ö�����
		
		return false;  //���� ����
	}
	
	public void rehash(){		// ���ؽ�
		Bucket<K, V>[] oldTable  = table;
		tableSize = 2*oldTable .length + 1;	//���̺��� ũ��� 2^n�� ���Ѵ�
		table = new Bucket[tableSize];	//���ο� ���̺� ����
		for (int i=0;i<tableSize;i++)
			table[i] = new Bucket<K,V>();
		size =0;
		for (int i=0;i<oldTable .length;i++){
			if (oldTable [i].status == Status.USING){	//������� ���� ���̺� ���ο� ���̺� ����
				insert(oldTable [i].key, oldTable [i].value);
			}
		}
	}
	
	public void printHashTable(){ 	// ���̺� ���
		for(int i=0;i<tableSize;i++){   
			if(table[i].status == Status.USING)	// ������� ������ �迭�� ������ ���
				System.out.println("K:"+table[i].key+"\t"+"V:"+table[i].value);
		}
	}
}
