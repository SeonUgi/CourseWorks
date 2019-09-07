package Simulation;
// Bank Simulation

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

class Plane {
	private int number;			//비행기 넘버
	private int arrivalTime;	//도착시간
	private int waitingTime;	//대기시간
	private int actionTime;		//착륙 혹은 이륙시간
	private boolean type;		//true 착륙, flase 이륙
	
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
	private boolean free;	// 활주로가 프린인지 판별하는 불리언 값
	private int actionTime;	// 비행기의 착륙,혹은 이륙 시간
	private Plane plane;	// 이 착륙 하는 비행기
	public Runway() {
		free = true;
	}
	public boolean isFree() {	// 활주로가 프린인지 검사
		return (free == true) ? true : false;
	}
	public void setBusy() {free = false;}
	public void setFree() {free = true;}
	public void setPlane(Plane plane){
		this.plane = plane;
	}
	
	public int getActionTime() {return actionTime;}	//이륙,착륙 시간
	public Plane getPlane() {return plane;}
	
	public void decrementActionTime() {	//활주로내 이착륙이 완료 되면 활주로 free
		--actionTime;
		if (actionTime == 0)
			free = true;
	}
	
	public void setActionTime(int actionTime) {
		this.actionTime = actionTime;
	}
	
}
class AirPort {
	private int numRunways;	// 총 활주로의 수
	private int numArrivalPlanes; // 이륙 하는 비행기의 수
	private int numDeparturePlane;// 착륙하는 비행기의 수
	private Runway[] runways; // 여러개의 활주로를 배열로 나타냄
	
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
	public int getArrivalPlanes() {	//착륙한 비행기의 수
		return numArrivalPlanes;
	}
	public int getDeparturePlanes(){//이륙한 비행기의 수
		return numDeparturePlane;
	}
	
	// 프리인 활주로 번호를 반환, 프린인 활주로가 없으면 -1 반환
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
	// 활주로 배열을 순회하며 작업 중인 활주로의 작업 시간을 감소한다. 
	// 작업을 마쳤으면 이 착륙한 비행기의 수 증가하고 그 활주로를 프리 상태로 설정 
	public void undateRunways(int clock) {
		for (int i = 0; i < numRunways; i++) {
			if (!runways[i].isFree()) {
				runways[i].decrementActionTime();
				if (runways[i].getActionTime() == 0) {
					Plane plane = runways[i].getPlane();
					runways[i].setFree();
					
					if(plane.getType()){	//착륙하는 비행기의 경우
						++numArrivalPlanes;
						System.out.printf("[%4d ] Arrival plane %d landed "
								+ "at runway %d\n",clock,plane.getnumber(),i+1);
						
						System.out.printf("\t(Arrival plane: %d, arrival time: %d, "
								+ "wating time: %d, landing time: %d)\n",
								plane.getnumber(),plane.getArrivalTime(),
								plane.getWaitingTime(),plane.getActionTime());
					}
					else{					//이륙하는 비행기의 경우
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
	private int maxLandingTime; // 입력으로 받은 최대 이 착륙 시간
	private int maxTakingOffTime; // 입력으로 받은 최대 이 착륙 시간
	private int landingTime;	// 실제 사용할 이륙 시간
	private int takingOffTime;	// 실제 사용할 이륙 시간
	private int planeInterval;	
	private int numRunways;
	private int simulationTime;
	private int numArrivalPlane;	//착륙한 비행기 수
	private int numDeparturePlane;	//이륙한 비행기 수
	private int numPlaneNotLanded;	//큐에 남아잇는 착륙 할 비행기 수
	private int numPlaneNotTookOff;	//큐에 남아잇는 이륙 할 비행기 수
	private int waitLandingTime;	
	private int waitTakingOffTime;	
	private int totalLandingWait;
	private int totalTakingOffWait;
	private Queue<Plane> a_queue; //착륙할 비행기가 들어갈 큐
	private Queue<Plane> d_queue;	//이륙할 비행기가 들어갈 큐
	
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
		// 고객의 도착율은 고객 인터벌의 역수
		probOfArrival = (double) 1/planeInterval; 
		
		System.out.println("\n Clock\t\tDescription");
		System.out.println(" -----\t\t-----------");
		for (clock = 1; clock <= simulationTime; clock++) {
			
			// 난수 발생하여 도착율보다 작으면 도학한 것으로 간주
			if (Math.random() < probOfArrival) { 
			       // 비행기의 착륙 시간은 입력으로 부터 받은
			       // maxLandingTime을  난수 발생하여 
			       // 1, 2, ..., maxLandingTime의 값을 갖도록 함
			       rand = new Random();
			       landingTime = rand.nextInt(maxLandingTime) + 1;	
			       // 도착 시간이 clock인 착륙할 비행기 생성
			       plane = new Plane(++arrivalPlaneNum,clock,landingTime,true);
			       
			       //비행기의 도착시간 출력
			       System.out.printf("[%4d ] Arrival plane %d ready, "
			       		+ "landing time: %d\n"
			    		,clock,plane.getnumber(),plane.getActionTime());
			       
			       a_queue.add(plane);
			}
			if (Math.random() < probOfArrival) { 
			       // 비행기의 이륙 시간은 입력으로 부터 받은
			       // maxTakingOffTime을  난수 발생하여 
			       // 1, 2, ..., maxTakingOffTime의 값을 갖도록 함
			       rand = new Random();
			       takingOffTime = rand.nextInt(maxTakingOffTime) + 1;	
			       // 도착 시간이 clock인 이륙할 비행기 생성
			       plane = new Plane(++departurePlaneNum,clock,takingOffTime,false);
			       
			       //비행기의 도착시간 출력
			       System.out.printf("[%4d ] Departure plane %d ready, "
			       		+ "takingoff time: %d\n"
			    		,clock,plane.getnumber(),plane.getActionTime());
			       d_queue.add(plane);
			}
			// 프리인 활주로 번호를 가져옴
			int runwayNumber = airport.getFreeRunwayNumber(); 
			if (runwayNumber != -1 && !a_queue.isEmpty()) {
				// 큐에 대기하고 있는 비행기을 가져와 착륙 시작
				plane = a_queue.remove();
				airport.setPlane(runwayNumber, plane);
				// 비행기가 기다린 시간
				waitLandingTime = clock - plane.getArrivalTime();
				plane.setWaitingTime(waitLandingTime);
				// 대기하고 있는 모든 비행기의 누적 시간
				totalLandingWait += waitLandingTime;	
				airport.setRunwayActive(runwayNumber, landingTime);
			}
			
			runwayNumber = airport.getFreeRunwayNumber(); 
			if (runwayNumber != -1 && !d_queue.isEmpty()) {
				// 큐에 대기하고 있는 비행기을 가져와 이륙 시작
				plane = d_queue.remove();
				airport.setPlane(runwayNumber, plane);
				// 비행기가 기다린 시간
				waitTakingOffTime = clock - plane.getArrivalTime();
				plane.setWaitingTime(waitTakingOffTime);
				// 대기하고 있는 모든 비행기의 누적 시간
				totalTakingOffWait += waitTakingOffTime;	
				airport.setRunwayActive(runwayNumber, takingOffTime);
			}
			// 현재 작업중인 각 활주로의 작업 시간을 감소시킴		
			airport.undateRunways(clock+1);
		}
		// 착륙한 전체 비행기의 수
		numArrivalPlane = airport.getArrivalPlanes();
		// 이륙한 전체 비행기의 수
		numDeparturePlane = airport.getDeparturePlanes();
		// 큐에 남아 있는 서비스 받지 못한 고객 수
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
