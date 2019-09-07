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
	private boolean free;	// �ڷ��� �������� �Ǻ��ϴ� �Ҹ��� ��
	private int transactionTime;	// ���� Ʈ����� �ð�
	private Customer customer;	//���� �޴� ��
	public Teller() {
		free = true;
	}
	public boolean isFree() {	// �ڷ��� �������� �˻�
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
	private int numTellers;	// �� �ڷ��� ��
	private int numCustomers; // ���� ���� �� ��
	private Teller[] tellers; // ���� ���� �ڷ��� �迭�� ��Ÿ��
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
	
	// ������ �ڷ� ��ȣ�� ��ȯ, ������ �ڷ��� ������ -1 ��ȯ
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
	// �ڷ� �迭�� ��ȸ�ϸ� �۾� ���� �ڷ��� �۾� �ð��� �����Ѵ�. 
	// �۾��� �������� ������ �� �� �����ϰ� �� �ڷ��� ���� ���·� ���� 
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
	private int maxTransactionTime; // �Է����� ���� �ִ� Ʈ����� �ð�
	private int transactionTime;	// ���� ����� Ʈ����� �ð�
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
		// ���� �������� �� ���͹��� ����
		probOfArrival = (double) 1/customerInterval; 
		
		System.out.println("\n Clock\t\tDescription");
		System.out.println(" -----\t\t-----------");
		for (number=1,clock = 1; clock <= simulationTime; ++clock) {
			
			// ���� �߻��Ͽ� ���������� ������ ������ ������ ����
			if (Math.random() < probOfArrival) { 
			       // ���� Ʈ����� �ð��� �Է����� ���� ����
			       // maxTransaction��  ���� �߻��Ͽ� 
			       // 1, 2, ..., maxTransaction�� ���� ������ ��
			       rand = new Random();
			       transactionTime = rand.nextInt(maxTransactionTime) + 1;	
			       // ���� �ð��� clock�� �� ����
			       customer = new Customer(number++,clock,transactionTime);
			       
			       //�� �����ð� ���
			       System.out.printf("[%4d ] Customer number %d arrived, "
			       		+ "transaction time: %d\n"
			    		,clock,customer.getnumber(),customer.getTransactionTime());
			       queue.add(customer);
			}
			// ������ �ڷ� ��ȣ�� ������
			int tellerNumber = bank.getFreeTellerNumber(); 
			if (tellerNumber != -1 && !queue.isEmpty()) {
				// ť�� ����ϰ� �ִ� ���� ������ ���� ����
				customer = queue.remove();
				bank.setCustomer(tellerNumber, customer);
				// ���� ��ٸ� �ð�
				waitingTime = clock - customer.getArrivalTime();
				customer.setWaitingTime(waitingTime);
				// ����ϰ� �ִ� ��� ���� ���� �ð�
				totalWait += waitingTime;	
				bank.setTellerActive(tellerNumber, transactionTime);
			}
			// ���� �۾����� �� �ڷ��� Ʈ����� �ð��� ���ҽ�Ŵ		
			bank.undateTellers(clock+1);
		}
		// ���� ���� ��ü �� ��
		numCustomers = bank.getServicedCustomers();
		// ť�� ���� �ִ� ���� ���� ���� �� ��
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
