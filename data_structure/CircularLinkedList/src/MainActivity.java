import java.util.Scanner;


public class MainActivity {
	private static void josephusProblem(int number, int k) {	//요세푸스 문제
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		
		for(int i=1;i<=number;i++)	//입력 받은 숫자만큼 리스트에 추가
			list.insert(i);
		CircularIterator<Integer> it = list.iterator();	//인터레이터
		System.out.println(list);
		
		for(int i=1;list.size()!=1;i++){	//마지막 1명만 남을 때까지
			int tmp=it.next();	//다음 노드를 가리킴
			if(i%k==0)	//k번째 사람일 시
				list.remove(tmp);
		}
		
		System.out.println(list + "is alive");
	}
	
	//중복된 요소 제거
	private static CircularLinkedList<Integer> removeDup(CircularLinkedList<Integer> list) {
		CircularIterator<Integer> it = list.iterator();
		int scan=0;
		int count = 0;
		
		do{
			CircularIterator<Integer> it2 =list.iterator();
			scan=it.next();	//중복된 요소를 검사하기 위한 기준
			do{
				int save=it2.next();	//검사할 요소
				if(scan==save)
					count++;	//중복된 요소가 몇개 있는지 카운트
			}while(!it2.hasNext());	//마지막 노드까지 탐색 후 탈출
			
			for(int j=0;j<count-1;j++)	//중복된 요소의 개수-1만큼 제거
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
			else if (command.equals("d")) { // 중복된 것은 한번만 출력
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
