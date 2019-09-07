package JosephusSolver;

import java.util.Scanner;
import java.util.Iterator;

public class Josephus_Solve {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
		int pnum;	//��� ��
		int period;	//�� ��° ����� �� ���� ���� �ֱ� 
		
		System.out.println("�似Ǫ�� ����");
		System.out.print("�� ���� ����� �ִ���? : ");
		pnum=input.nextInt();
		System.out.print("�ֱ⸦ �Է� : ");
		period=input.nextInt();
		input.close();
		
		for(int i=1;i<=pnum;i++)	//n���� ��� ������� �Է�
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
		
		System.out.print("���������� ���� ��� : "+it.next());
	}
}
