import java.util.Scanner;


public class MainActivity {
	private static void josephusProblem(int number, int k) {	//�似Ǫ�� ����
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		
		for(int i=1;i<=number;i++)	//�Է� ���� ���ڸ�ŭ ����Ʈ�� �߰�
			list.insert(i);
		CircularIterator<Integer> it = list.iterator();	//���ͷ�����
		System.out.println(list);
		
		for(int i=1;list.size()!=1;i++){	//������ 1�� ���� ������
			int tmp=it.next();	//���� ��带 ����Ŵ
			if(i%k==0)	//k��° ����� ��
				list.remove(tmp);
		}
		
		System.out.println(list + "is alive");
	}
	
	//�ߺ��� ��� ����
	private static CircularLinkedList<Integer> removeDup(CircularLinkedList<Integer> list) {
		CircularIterator<Integer> it = list.iterator();
		int scan=0;
		int count = 0;
		
		do{
			CircularIterator<Integer> it2 =list.iterator();
			scan=it.next();	//�ߺ��� ��Ҹ� �˻��ϱ� ���� ����
			do{
				int save=it2.next();	//�˻��� ���
				if(scan==save)
					count++;	//�ߺ��� ��Ұ� � �ִ��� ī��Ʈ
			}while(!it2.hasNext());	//������ ������ Ž�� �� Ż��
			
			for(int j=0;j<count-1;j++)	//�ߺ��� ����� ����-1��ŭ ����
				list.remove(scan);
			count=0;
		}while(!it.hasNext());
		
		return list;
	}
	
	public static void main(String[] args) {
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		Scanner input = new Scanner(System.in);
		String command;
		int item;
		
		System.out.println("Enter a command: i(insert),r(emove),s(earch),size, "
				+ "d(remove duplicates), j(Josephus problem), p(rint), or q(uit)");
		System.out.print("> ");
		command = input.next();

		while (!command.equals("q")) {
			if (command.equals("i")) {
				item = input.nextInt();
				list.insert(item);
			}
			else if (command.equals("r")) {
				item = input.nextInt();
				if (list.remove(item))
					System.out.println(item + " removed.");
				else
					System.out.println("No such " + item + "!");
			}			
			else if (command.equals("s")) {
				item = input.nextInt();
				if (list.search(item))
					System.out.println(item + " is in the list.");
				else
					System.out.println("No such " + item + "!");
			}
			else if (command.equals("size")) 
				System.out.println("size: " + list.size());
			else if (command.equals("p"))
				System.out.println(list);
			else if (command.equals("d")) { // �ߺ��� ���� �ѹ��� ���
				list = removeDup(list);
				System.out.println(list);
			}
			else if (command.equals("j")) {
				System.out.print("Enter number of people and kth number to be out : ");
				int number = input.nextInt();
				int k = input.nextInt();
				josephusProblem(number, k);				
			}
			System.out.print("> ");
			command = input.next();
		}
		System.out.println("Commands terminated.");
		input.close();
	}
}
