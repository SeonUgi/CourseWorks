import java.util.Scanner;
public class Simulations {
	/**
	 * Over-under 게임 모의 실험 (simulations)
	 * 
	 * Over-under 게임------------------------------------
	 * 두 개의 주사위를 던져 나오는 값이 어떻게 될지 내기를 건다.
	 * 
	 * (1) 7보다 크다 (over),
	 * (2) 7보다 작다 (under),
	 * (3) 7과 같다 (exactly 7)
	 * 에 내기를 거는 게임.
	 * 
	 * Over와 under에 걸어 이기면 건 돈만큼 받는다.
	 * (10원 건 경우 10원 돌려 받고 10원을 추가로 받는다.)
	 * Exactly 7에 걸어 이기면 건 돈의 네 배를 받는다.
	 * (10원 건 경우 10원 돌려 받고 40원을 추가로 받는다.)
	 * 어느 경우건 지면 건 돈을 떼인다.
	 * --------------------------------------------------
	 * 
	 *  이 게임의 모의실험을 시행한다.
	 *  10,000번의 게임을 시행한다.
	 *  게임을 할 때마다 판돈을 1원씩 건다.
	 *  
	 * @author 이영재
	 */
	public static void main(String[] args) 
	{   Scanner input = new Scanner(System.in);
	System.out.println("Over-Under 게임을 10,000번 모의실험합니다.");
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

	default   : System.out.print("잘 못된 입력입니다.");


	}
	System.out.println();

	}
	}

	/**
	 * 내기 선택을 물어보는 메소드.
	 */
	public static void printMenu()
	{
		System.out.println("어디에 거시겠습니까?");
		System.out.println("Over     o");
		System.out.println("Under    u");
		System.out.println("Exactly7 7");
		System.out.println("종료:     q");
		System.out.print("입력하세요: ");



	}

	/**
	 * over에 거는 경우
	 * @return 10,000번 게임해서 딴 돈 (음수일 수 있다.)
	 */

	public static int betOver(int money)
	{   
		System.out.print("딴 돈: "+money*2);
		return money;
	}

	/**
	 * under에 거는 경우
	 * @return 10,000번 게임해서 딴 돈 (음수일 수 있다.)
	 */

	public static int betUnder(int money)
	{
		System.out.print("딴 돈: "+money*2);
		return money;

	}

	/**
	 * exactly7에 거는 경우
	 * @return 10,000번 게임해서 딴 돈 (음수일 수 있다.)
	 */

	public static int betExactly7(int money)
	{
		System.out.print("딴 돈: "+money*4);
		return money;
	}

	/**
	 * 두 개의 주사위를 던져 나온 값의 합을 반환한다.
	 * @return 두 개의 주사위의 합.
	 */
	public static int rollDice(int sum)
	{   int die1,die2;

	die1 = (int)(6*Math.random()+1);
	die2 = (int)(6*Math.random()+1);
	sum = die1 + die2;

	return sum;
	}

}
