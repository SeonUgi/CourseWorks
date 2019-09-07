package polynomial_arithmetic;

public class Element{	//지수와 차수를 저장할 클래스
	private int coefficient;
	private int power;
	
	Element(){
		coefficient=1;
		power=1;
	}
	
	Element(int coefficient){
		this.coefficient=coefficient;
		power=0;
	}
	
	Element(int coefficient,int power){
		this.coefficient=coefficient;
		this.power=power;
	}
	
	public int getCoef(){ return coefficient; }
	public int getPower(){ return power; }
	
	public void setCoef(int num){ coefficient = num; }
	public void setPower(int num){ power = num; }
	
	public String toString(){	//항을  string으로 변환
		String str = null;
		if(power == 0)
			str = String.valueOf(coefficient);
		
		else if(power == 1){
			if(coefficient == 1)
				str = "x";
			else if(coefficient == -1)
				str = "-x";
			else
				str = String.valueOf(coefficient)+"x";
		}
		
		else{
			if(coefficient == 1)
				str = "x^"+power;
			else if(coefficient == -1)
				str = "-x^"+power;
			else
				str = String.valueOf(coefficient)+"x^"+power;
		}
			
		return str; 
	}
}
