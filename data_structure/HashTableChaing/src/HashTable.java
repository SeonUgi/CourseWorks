import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class HashTable<K,V> {
	private LinkedList<Bucket<K, V>>[] table;
	private int size;
	private int tableSize;
	private double LOAD_THRESHOLD = 2;
	private static int INITIAL_TABLE_SIZE = 5;
	
	public HashTable(){	// ������
		tableSize = INITIAL_TABLE_SIZE;	//���̺� �⺻ ũ��
		table = new LinkedList[tableSize];
		
		for(int i=0;i<tableSize;i++)
			table[i] = new LinkedList<Bucket<K,V>>();
		
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
			if(table[tableIndex] != null){	//���̺��� ������϶�
				for(Bucket<K, V> item : table[tableIndex]){	//for loop ���
					if(key.equals(item.key))	//key ã��
						return item.value;	//value ��ȯ
				}
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;//���� Ž��
		}while(tableIndex != home);
		
		return null;//��ã���� null��ȯ
	}
	
	public void insert(K key, V value){	// ����
		if((double)size/tableSize >= LOAD_THRESHOLD)	//���̺� ������� 2�� ���� ���� ������
			rehash();
		
		int home = hash(key);	//key�� �޾ƿ�
		int tableIndex = home;	//���̺� �ε��� ����
		
		Bucket<K, V> item = new Bucket<K, V>(key, value);	//��Ŷ ����
		table[tableIndex].addFirst(item);
		size++;
	}
	
	public boolean remove(K key){
		int home = hash(key);	//���̺��ּ� �޾ƿ�
		int tableIndex = home;	//���̺� �ּ� ����
		int increment = probe(tableIndex);	//�������縦 ����
		
		do{
			if(table[tableIndex] != null){	//���̺��� ������϶�
				for(Bucket<K, V> item : table[tableIndex]){	//for loop ���
					if(key.equals(item.key)){	//key ã��
						table[tableIndex].remove(item);
						size--;
						return true;	//value ��ȯ
					}
				}
			}
			else
				tableIndex = (tableIndex+increment)%tableSize;//���� Ž��
		}while(tableIndex != home);
		
		return false;//��ã���� false��ȯ
	}
	
	public void rehash(){		// ���ؽ�
		LinkedList<Bucket<K, V>>[] oldTable = table;
		tableSize = 2*oldTable .length + 1;	//���̺��� ũ��� 2^n�� ���Ѵ�
		table = new LinkedList[tableSize];	//�� ū ���̺� ����
		for(int i=0;i<tableSize;i++)
			table[i] = new LinkedList<Bucket<K,V>>(); 
		size = 0;
		for(int i=0;i<oldTable.length;i++){	//���� ���̺��� ���ο� ���̺�� ����
			if(oldTable[i] != null){
				for(Bucket<K, V> item : oldTable[i])	//for loop ���
					insert(item.key, item.value);
			}
		}
	}
	
	public void printHashTable(){ 	// ���̺� ���
		for(int i=0;i<tableSize;i++){   
			System.out.print(i);
			if(table[i].size() == 0)
				System.out.println();
			
			else{
				for(Bucket<K, V> item : table[i]){	//for loop ���
					System.out.println("\t["+item.key+", "+item.value+"]");
				}
			}
		}
	}
	
	public void dijstra(){
		
	}
}
