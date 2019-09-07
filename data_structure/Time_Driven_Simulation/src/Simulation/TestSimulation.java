package Simulation;
// Bank Simulation

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

class Customer {
	private int number;
	private int arrivalTime;
	private int waitingTime;
	private int transactionTime;
	
	public Customer(int number,int arrivalTime,int transactionTime) {
		this.number = number;
		this.arrivalTime = arrivalTime;
		this.transactionTime = transactionTime; 
	} 
	
	public void setWaitingTime(int time){
		waitingTime=time;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getnumber() {
		return number;
	}
	public int getWaitingTime() {
		return waitingTime;
	}
	public int getTransactionTime() {
		return transactionTime;
	}

}
class Teller {
	private boolean free;	// 텔러가 프린인지 판별하는 불리언 값
	private int transactionTime;	// 고객의 트랜잭션 시간
	private Customer customer;	//서비스 받는 고객
	public Teller() {
		free = true;
	}
	public boolean isFree() {	// 텔러가 프린인지 검사
		return (free == true) ? true : false;
	}
	public void setBusy() {free = false;}
	public void setFree() {free = true;}
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public int getTransacionTime() {return transactionTime;}
	public Customer getCustomer() {return customer;}
	
	public void decrementTransactionTime() {
		--transactionTime;
		if (transactionTime == 0)
			free = true;
	}
	
	public void setTransactionTime(int transactionTime) {
		this.transactionTime = transactionTime;
	}
	
}
class Bank {
	private int numTellers;	// 총 텔러의 수
	private int numCustomers; // 서비스 받은 고객 수
	private Teller[] tellers; // 여러 명의 텔러를 배열로 나타냄
	public Bank(int numTellers) {
		this.numTellers = numTellers;
		numCustomers = 0;
		tellers = new Teller[numTellers];
		for (int i = 0; i < numTellers; i++) {
			tellers[i] = new Teller();
		}
	}
	public Bank() {
		this(1);		
	}	
	
	public void setCustomer(int tellerNumber,Customer customer){
		tellers[tellerNumber].setCustomer(customer);
	}
	public int getServicedCustomers() {
		return numCustomers;
	}
	
	// 프리인 텔러 번호를 반환, 프린인 텔러가 없으면 -1 반환
	public int getFreeTellerNumber() {	
		for (int i = 0; i < numTellers; i++) {
			if (tellers[i].isFree()) {
				return i;
			}
		}
		return -1;
	}
	public void setTellerActive(int tellerNumber, int transactionTime) {
		tellers[tellerNumber].setBusy();
		tellers[tellerNumber].setTransactionTime(transactionTime);
	}
	// 텔러 배열을 순회하며 작업 중인 텔러의 작업 시간을 감소한다. 
	// 작업을 마쳤으면 서비스한 고객 수 증가하고 그 텔러를 프리 상태로 설정 
	public void undateTellers(int clock) {
		for (int i = 0; i < numTellers; i++) {
			if (!tellers[i].isFree()) {
				tellers[i].decrementTransactionTime();
				if (tellers[i].getTransacionTime() == 0) {
					Customer customer = tellers[i].getCustomer();
					numCustomers++;
					tellers[i].setFree();
					System.out.printf("[%4d ] Teller number %d finished "
							+ "serving Customer number %d\n",
							clock,customer.getnumber(),i+1);
					
					System.out.printf("\t(Customer number %d, arrival time: %d, "
							+ "wating time: %d, transcation time: %d)\n",
							customer.getnumber(),customer.getArrivalTime(),
							customer.getWaitingTime(),customer.getTransactionTime());
				}
			}
		}
	}
}

class Simulation {
	private int maxTransactionTime; // 입력으로 받은 최대 트랙잭션 시간
	private int transactionTime;	// 실제 사용할 트랙잭션 시간
	private int customerInterval;
	private int numTellers;
	private int simulationTime;
	private int numCustomers;
	private int numCustNotServiced;
	private int waitingTime;
	private int totalWait;
	private Queue<Customer> queue; 
	
	public Simulation() {
		getParameters();
		totalWait = 0;
		queue = new LinkedList<Customer>();
	}
	public void run() {
		double probOfArrival;
		int clock;
		int number;	//customer number
		Customer customer;
		Random rand;
		Bank bank = new Bank(numTellers);
		// 고객의 도착율은 고객 인터벌의 역수
		probOfArrival = (double) 1/customerInterval; 
		
		System.out.println("\n Clock\t\tDescription");
		System.out.println(" -----\t\t-----------");
		for (number=1,clock = 1; clock <= simulationTime; ++clock) {
			
			// 난수 발생하여 도착율보다 작으면 도학한 것으로 간주
			if (Math.random() < probOfArrival) { 
			       // 고객의 트랜잭션 시간은 입력으로 부터 받은
			       // maxTransaction을  난수 발생하여 
			       // 1, 2, ..., maxTransaction의 값을 갖도록 함
			       rand = new Random();
			       transactionTime = rand.nextInt(maxTransactionTime) + 1;	
			       // 도착 시간이 clock인 고객 생성
			       customer = new Customer(number++,clock,transactionTime);
			       
			       //고객 도착시간 출력
			       System.out.printf("[%4d ] Customer number %d arrived, "
			       		+ "transaction time: %d\n"
			    		,clock,customer.getnumber(),customer.getTransactionTime());
			       queue.add(customer);
			}
			// 프리인 텔러 번호를 가져옴
			int tellerNumber = bank.getFreeTellerNumber(); 
			if (tellerNumber != -1 && !queue.isEmpty()) {
				// 큐에 대기하고 있는 고객을 가져와 서비스 시작
				customer = queue.remove();
				bank.setCustomer(tellerNumber, customer);
				// 고객이 기다린 시간
				waitingTime = clock - customer.getArrivalTime();
				customer.setWaitingTime(waitingTime);
				// 대기하고 있는 모든 고객의 누적 시간
				totalWait += waitingTime;	
				bank.setTellerActive(tellerNumber, transactionTime);
			}
			// 현재 작업중인 각 텔러의 트랜잭션 시간을 감소시킴		
			bank.undateTellers(clock+1);
		}
		// 서비스 받은 전체 고객 수
		numCustomers = bank.getServicedCustomers();
		// 큐에 남아 있는 서비스 받지 못한 고객 수
		numCustNotServiced = checkWaitQueue();	
		printResult();
	}
	private void getParameters() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter transcatioin time: ");
		maxTransactionTime = input.nextInt();
		System.out.print("Enter customer interval in time: ");
		customerInterval = input.nextInt();
		System.out.print("Enter number of teller: ");
		numTellers = input.nextInt();
		System.out.print("Enter simulation time: ");
		simulationTime = input.nextInt();
		input.close();		
	}
	private int checkWaitQueue() {
		int numCustNotServiced = 0;
		while (!queue.isEmpty()) {
			++numCustNotServiced;
			queue.remove();
		}
		return numCustNotServiced;
	}
	private void printResult() {
		System.out.println("\nNumber of serviced customers: " + 
					numCustomers);
		System.out.printf("Average wait in queue %.2f\n", 
					(double)totalWait/numCustomers);
		System.out.println("Customers left in queue: " + 
					numCustNotServiced);
	}

}
public class TestSimulation {
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.run();
	}
}
