import java.util.Scanner;
public class Simulations {
	/**
	 * Over-under ���� ���� ���� (simulations)
	 * 
	 * Over-under ����------------------------------------
	 * �� ���� �ֻ����� ���� ������ ���� ��� ���� ���⸦ �Ǵ�.
	 * 
	 * (1) 7���� ũ�� (over),
	 * (2) 7���� �۴� (under),
	 * (3) 7�� ���� (exactly 7)
	 * �� ���⸦ �Ŵ� ����.
	 * 
	 * Over�� under�� �ɾ� �̱�� �� ����ŭ �޴´�.
	 * (10�� �� ��� 10�� ���� �ް� 10���� �߰��� �޴´�.)
	 * Exactly 7�� �ɾ� �̱�� �� ���� �� �踦 �޴´�.
	 * (10�� �� ��� 10�� ���� �ް� 40���� �߰��� �޴´�.)
	 * ��� ���� ���� �� ���� ���δ�.
	 * --------------------------------------------------
	 * 
	 *  �� ������ ���ǽ����� �����Ѵ�.
	 *  10,000���� ������ �����Ѵ�.
	 *  ������ �� ������ �ǵ��� 1���� �Ǵ�.
	 *  
	 * @author �̿���
	 */
	public static void main(String[] args) 
	{   Scanner input = new Scanner(System.in);
	System.out.println("Over-Under ������ 10,000�� ���ǽ����մϴ�.");
	System.out.println();



	boolean done = false;   
	while(!done)
	{   printMenu();
	String num = input.next();
	switch(num)
	{   
	int number=input.nextInt();
	int money=input.nextInt();

	case "o" : if(rollDice(number)>7)
		betOver(money); 
	break;

	case "u" : if(rollDice(number)<7)
		betUnder(money);
	break;

	case "7" : if(rollDice(number)==7)
		betExactly7(money);
	break;

	case "q" : done = true;
	break;

	default   : System.out.print("�� ���� �Է��Դϴ�.");


	}
	System.out.println();

	}
	}

	/**
	 * ���� ������ ����� �޼ҵ�.
	 */
	public static void printMenu()
	{
		System.out.println("��� �Žðڽ��ϱ�?");
		System.out.println("Over     o");
		System.out.println("Under    u");
		System.out.println("Exactly7 7");
		System.out.println("����:     q");
		System.out.print("�Է��ϼ���: ");



	}

	/**
	 * over�� �Ŵ� ���
	 * @return 10,000�� �����ؼ� �� �� (������ �� �ִ�.)
	 */

	public static int betOver(int money)
	{   
		System.out.print("�� ��: "+money*2);
		return money;
	}

	/**
	 * under�� �Ŵ� ���
	 * @return 10,000�� �����ؼ� �� �� (������ �� �ִ�.)
	 */

	public static int betUnder(int money)
	{
		System.out.print("�� ��: "+money*2);
		return money;

	}

	/**
	 * exactly7�� �Ŵ� ���
	 * @return 10,000�� �����ؼ� �� �� (������ �� �ִ�.)
	 */

	public static int betExactly7(int money)
	{
		System.out.print("�� ��: "+money*4);
		return money;
	}

	/**
	 * �� ���� �ֻ����� ���� ���� ���� ���� ��ȯ�Ѵ�.
	 * @return �� ���� �ֻ����� ��.
	 */
	public static int rollDice(int sum)
	{   int die1,die2;

	die1 = (int)(6*Math.random()+1);
	die2 = (int)(6*Math.random()+1);
	sum = die1 + die2;

	return sum;
	}

}
