
public class Student {
	private int number;
	private String name;
	private double score;
	
	public Student(int number, String name, double score){
		this.number =  number;
		this.name = name;
		this.score = score;
	}
	
	public String toString(){
		return "["+number+", "+name+", "+score+"]";
	}
}
