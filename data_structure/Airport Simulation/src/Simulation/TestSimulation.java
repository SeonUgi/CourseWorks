package Simulation;
// Bank Simulation

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

class Plane {
	private int number;			//����� �ѹ�
	private int arrivalTime;	//�����ð�
	private int waitingTime;	//���ð�
	private int actionTime;		//���� Ȥ�� �̷��ð�
	private boolean type;		//true ����, flase �̷�
	
	public Plane(int number,int arrivalTime,int actionTime,boolean type) {
		this.number = number;
		this.arrivalTime = arrivalTime;
		this.actionTime = actionTime; 
		this.type=type;
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
	public int getActionTime() {
		return actionTime;
	}
	public boolean getType(){
		return type;
	}

}
class Runway {
	private boolean free;	// Ȱ�ַΰ� �������� �Ǻ��ϴ� �Ҹ��� ��
	private int actionTime;	// ������� ����,Ȥ�� �̷� �ð�
	private Plane plane;	// �� ���� �ϴ� �����
	public Runway() {
		free = true;
	}
	public boolean isFree() {	// Ȱ�ַΰ� �������� �˻�
		return (free == true) ? true : false;
	}
	public void setBusy() {free = false;}
	public void setFree() {free = true;}
	public void setPlane(Plane plane){
		this.plane = plane;
	}
	
	public int getActionTime() {return actionTime;}	//�̷�,���� �ð�
	public Plane getPlane() {return plane;}
	
	public void decrementActionTime() {	//Ȱ�ַγ� �������� �Ϸ� �Ǹ� Ȱ�ַ� free
		--actionTime;
		if (actionTime == 0)
			free = true;
	}
	
	public void setActionTime(int actionTime) {
		this.actionTime = actionTime;
	}
	
}
class AirPort {
	private int numRunways;	// �� Ȱ�ַ��� ��
	private int numArrivalPlanes; // �̷� �ϴ� ������� ��
	private int numDeparturePlane;// �����ϴ� ������� ��
	private Runway[] runways; // �������� Ȱ�ַθ� �迭�� ��Ÿ��
	
	public AirPort(int numRunways) {
		this.numRunways = numRunways;
		numArrivalPlanes = 0;
		numDeparturePlane = 0;
		runways = new Runway[numRunways];
		for (int i = 0; i < numRunways; i++) {
			runways[i] = new Runway();
		}
	}
	public AirPort() {
		this(1);		
	}	
	
	public void setPlane(int runwayNumber,Plane plane){
		runways[runwayNumber].setPlane(plane);
	}
	public int getArrivalPlanes() {	//������ ������� ��
		return numArrivalPlanes;
	}
	public int getDeparturePlanes(){//�̷��� ������� ��
		return numDeparturePlane;
	}
	
	// ������ Ȱ�ַ� ��ȣ�� ��ȯ, ������ Ȱ�ַΰ� ������ -1 ��ȯ
	public int getFreeRunwayNumber() {	
		for (int i = 0; i < numRunways; i++) {
			if (runways[i].isFree()) {
				return i;
			}
		}
		return -1;
	}
	public void setRunwayActive(int runwayNumber, int actionTime) {
		runways[runwayNumber].setBusy();
		runways[runwayNumber].setActionTime(actionTime);
	}
	// Ȱ�ַ� �迭�� ��ȸ�ϸ� �۾� ���� Ȱ�ַ��� �۾� �ð��� �����Ѵ�. 
	// �۾��� �������� �� ������ ������� �� �����ϰ� �� Ȱ�ַθ� ���� ���·� ���� 
	public void undateRunways(int clock) {
		for (int i = 0; i < numRunways; i++) {
			if (!runways[i].isFree()) {
				runways[i].decrementActionTime();
				if (runways[i].getActionTime() == 0) {
					Plane plane = runways[i].getPlane();
					runways[i].setFree();
					
					if(plane.getType()){	//�����ϴ� ������� ���
						++numArrivalPlanes;
						System.out.printf("[%4d ] Arrival plane %d landed "
								+ "at runway %d\n",clock,plane.getnumber(),i+1);
						
						System.out.printf("\t(Arrival plane: %d, arrival time: %d, "
								+ "wating time: %d, landing time: %d)\n",
								plane.getnumber(),plane.getArrivalTime(),
								plane.getWaitingTime(),plane.getActionTime());
					}
					else{					//�̷��ϴ� ������� ���
						++numDeparturePlane;
						System.out.printf("[%4d ] Departure plane %d took off "
								+ "at runway %d\n",clock,plane.getnumber(),i+1);
						
						System.out.printf("\t(Departure plane: %d, arrival time: %d, "
								+ "wating time: %d, takingoff time: %d)\n",
								plane.getnumber(),plane.getArrivalTime(),
								plane.getWaitingTime(),plane.getActionTime());
					}
				}
			}
		}
	}
}

class Simulation {
	private int maxLandingTime; // �Է����� ���� �ִ� �� ���� �ð�
	private int maxTakingOffTime; // �Է����� ���� �ִ� �� ���� �ð�
	private int landingTime;	// ���� ����� �̷� �ð�
	private int takingOffTime;	// ���� ����� �̷� �ð�
	private int planeInterval;	
	private int numRunways;
	private int simulationTime;
	private int numArrivalPlane;	//������ ����� ��
	private int numDeparturePlane;	//�̷��� ����� ��
	private int numPlaneNotLanded;	//ť�� �����մ� ���� �� ����� ��
	private int numPlaneNotTookOff;	//ť�� �����մ� �̷� �� ����� ��
	private int waitLandingTime;	
	private int waitTakingOffTime;	
	private int totalLandingWait;
	private int totalTakingOffWait;
	private Queue<Plane> a_queue; //������ ����Ⱑ �� ť
	private Queue<Plane> d_queue;	//�̷��� ����Ⱑ �� ť
	
	public Simulation() {
		getParameters();
		totalLandingWait = 0;
		totalTakingOffWait = 0;
		a_queue = new LinkedList<Plane>();
		d_queue = new LinkedList<Plane>();
	}
	public void run() {
		double probOfArrival;
		int clock;
		int arrivalPlaneNum = 0;	//arrivalPlane number
		int departurePlaneNum = 0;	//departurePlane number
		Plane plane;
		Random rand;
		AirPort airport = new AirPort(numRunways);
		// ���� �������� �� ���͹��� ����
		probOfArrival = (double) 1/planeInterval; 
		
		System.out.println("\n Clock\t\tDescription");
		System.out.println(" -----\t\t-----------");
		for (clock = 1; clock <= simulationTime; clock++) {
			
			// ���� �߻��Ͽ� ���������� ������ ������ ������ ����
			if (Math.random() < probOfArrival) { 
			       // ������� ���� �ð��� �Է����� ���� ����
			       // maxLandingTime��  ���� �߻��Ͽ� 
			       // 1, 2, ..., maxLandingTime�� ���� ������ ��
			       rand = new Random();
			       landingTime = rand.nextInt(maxLandingTime) + 1;	
			       // ���� �ð��� clock�� ������ ����� ����
			       plane = new Plane(++arrivalPlaneNum,clock,landingTime,true);
			       
			       //������� �����ð� ���
			       System.out.printf("[%4d ] Arrival plane %d ready, "
			       		+ "landing time: %d\n"
			    		,clock,plane.getnumber(),plane.getActionTime());
			       
			       a_queue.add(plane);
			}
			if (Math.random() < probOfArrival) { 
			       // ������� �̷� �ð��� �Է����� ���� ����
			       // maxTakingOffTime��  ���� �߻��Ͽ� 
			       // 1, 2, ..., maxTakingOffTime�� ���� ������ ��
			       rand = new Random();
			       takingOffTime = rand.nextInt(maxTakingOffTime) + 1;	
			       // ���� �ð��� clock�� �̷��� ����� ����
			       plane = new Plane(++departurePlaneNum,clock,takingOffTime,false);
			       
			       //������� �����ð� ���
			       System.out.printf("[%4d ] Departure plane %d ready, "
			       		+ "takingoff time: %d\n"
			    		,clock,plane.getnumber(),plane.getActionTime());
			       d_queue.add(plane);
			}
			// ������ Ȱ�ַ� ��ȣ�� ������
			int runwayNumber = airport.getFreeRunwayNumber(); 
			if (runwayNumber != -1 && !a_queue.isEmpty()) {
				// ť�� ����ϰ� �ִ� ������� ������ ���� ����
				plane = a_queue.remove();
				airport.setPlane(runwayNumber, plane);
				// ����Ⱑ ��ٸ� �ð�
				waitLandingTime = clock - plane.getArrivalTime();
				plane.setWaitingTime(waitLandingTime);
				// ����ϰ� �ִ� ��� ������� ���� �ð�
				totalLandingWait += waitLandingTime;	
				airport.setRunwayActive(runwayNumber, landingTime);
			}
			
			runwayNumber = airport.getFreeRunwayNumber(); 
			if (runwayNumber != -1 && !d_queue.isEmpty()) {
				// ť�� ����ϰ� �ִ� ������� ������ �̷� ����
				plane = d_queue.remove();
				airport.setPlane(runwayNumber, plane);
				// ����Ⱑ ��ٸ� �ð�
				waitTakingOffTime = clock - plane.getArrivalTime();
				plane.setWaitingTime(waitTakingOffTime);
				// ����ϰ� �ִ� ��� ������� ���� �ð�
				totalTakingOffWait += waitTakingOffTime;	
				airport.setRunwayActive(runwayNumber, takingOffTime);
			}
			// ���� �۾����� �� Ȱ�ַ��� �۾� �ð��� ���ҽ�Ŵ		
			airport.undateRunways(clock+1);
		}
		// ������ ��ü ������� ��
		numArrivalPlane = airport.getArrivalPlanes();
		// �̷��� ��ü ������� ��
		numDeparturePlane = airport.getDeparturePlanes();
		// ť�� ���� �ִ� ���� ���� ���� �� ��
		numPlaneNotLanded = checkWaitA_Queue();	
		numPlaneNotTookOff = checkWaitD_Queue();
		printResult();
	}
	
	private int checkWaitA_Queue() {
		int numPlaneNotLanded = 0;
		while (!a_queue.isEmpty()) {
			++numPlaneNotLanded;
			a_queue.remove();
		}
		return numPlaneNotLanded;
	}
	private int checkWaitD_Queue() {
		int numPlaneNotTookOff = 0;
		while (!d_queue.isEmpty()) {
			++numPlaneNotTookOff;
			d_queue.remove();
		}
		return numPlaneNotTookOff;
	}
	
	private void getParameters() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter landing time: ");
		maxLandingTime = input.nextInt();
		System.out.print("Enter takingoff time: ");
		maxTakingOffTime = input.nextInt();
		System.out.print("Enter plane interval in time: ");
		planeInterval = input.nextInt();
		System.out.print("Enter number of runway: ");
		numRunways = input.nextInt();
		System.out.print("Enter simulation time: ");
		simulationTime = input.nextInt();
		input.close();		
	}
	
	private void printResult() {
		System.out.println("\nNumber of planes landed: " + 
					numArrivalPlane);
		System.out.println("Number of planes taken off: " + 
				numDeparturePlane);
		System.out.printf("Average wait to land %.2f\n", 
					(double)totalLandingWait/numArrivalPlane);
		System.out.printf("Average wait to take off %.2f\n", 
				(double)totalTakingOffWait/numDeparturePlane);
		System.out.println("Planes left in the arrival queue: " + 
					numPlaneNotLanded);
		System.out.println("Planes left in the departure queue: " + 
				numPlaneNotTookOff);
	}

}
public class TestSimulation {
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.run();
	}
}
