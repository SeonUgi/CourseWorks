package JosephusSolver;

import java.util.Scanner;
import java.util.Iterator;

public class Josephus_Solve {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		int pnum;	//사람 수
		int period;	//몇 번째 사람을 고를 지에 대한 주기 
		
		System.out.println("요세푸스 문제");
		System.out.print("몇 명의 사람이 있는지? : ");
		pnum=input.nextInt();
		System.out.print("주기를 입력 : ");
		period=input.nextInt();
		input.close();
		
		for(int i=1;i<=pnum;i++)	//n명의 사람 순서대로 입력
			list.insert(i);
		
		for(int i:list)
			System.out.print(i+" ");
		System.out.println();
		
		Iterator<Integer> it = list.iterator();
		for(int i=1;list.size()>1;i++){
			if(i%period==0)
				it.remove();
			it.next();
		}
		
		System.out.print("마지막으로 남은 사람 : "+it.next());
	}
}
