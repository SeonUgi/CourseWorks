class Date implements Cloneable{
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// 목적 달의 입력을 받기 위한 생성자
	public Date(int year, int month){
		this.year = year;
		this.month = month;
		this.day = 1;
	}
	
	public Date() {
		this(0, 0, 0);
	}
	
	public String toString() {
		return year + "/" + month + "/" + day + " ";
	}
	
	// 주어진 날짜와 비교할 날짜 newDate를 비교하여 주어진 날짜가 앞서면 -1, 
	// 뒤서면 1, 같으면 0를 반환한다.
	public int compareTo(Date newDate){
		if(year > newDate.year){
			return -1;
		}else if(year < newDate.year){
			return 1;
		}else if(month > newDate.month){
			return -1;
		}else if(month < newDate.month){
			return 1;
		}else if(day > newDate.day){
			return -1;
		}else if(day < newDate.day){
			return 1;
		}else {
			return 0;
		}
	}
	
	public static int getDifferDays(Date backDate, Date foreDate){
		int differDays =0;
		Date back = backDate;
		Date fore = foreDate;
		try {
			
			if(back.compareTo(fore) == -1){
				Date temp = (Date)fore.clone();
				fore = back;
				back = temp;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		while(back.compareTo(fore)!=0){
			back.advance();
			differDays++;
		}
		return differDays;
	}

	// 주어진 날짜를 하루 증가시킨다.
	public void advance(){
		day ++;						// 객제의 날짜를 하루 증가시킨다.
		
		switch(month){				// 주어진 달에 따라 한달의 일수를 달리해 인자로 전달하며 handling 메소드 호출한다
			case 2:					// 전해진 달이 2월일 때에는 윤년 계산을 통해 한 달의 일수를 달리한다.
				int daysOfFeb = calGregroic();
				handling(daysOfFeb);
				break;
			
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				handling(31);
				break;
				
			case 4:
			case 6:
			case 9:
			case 11:
				handling(30);
				break;
		}
	}
	
	void handling(int numOfDay){		// 달과 년도가 넘어갈 때마다 달 수, 년 수를 증가 시켜주는 메소드
		if(day > numOfDay){
			month++;
			day = 1;
		}
		
		if(month > 12){
			year ++;
			month = 1;
		}
	}
	
	int calGregroic(){			// 윤년 계산 메소드
		if((year%4==0)&&(year%100!=0)||(year%400==0))
			return 29;
		else
			return 28;
	}
	
	int weekToInt(String str){
		if(str.equals("Sunday")){
			return 0;
		}else if(str.equals("Monday")){
			return 1;
		}else if(str.equals("Tuesday")){
			return 2;
		}else if(str.equals("Wednesday")){
			return 3;
		}else if(str.equals("ThursDay")){
			return 4;
		}else if(str.equals("Friday")){
			return 5;
		}else {
			return 6;
		}
	}
	
	public static int getNewDayOfWeek(Date basicDate,Date otherDate,int basicDayOfWeek,int differDays){
		int newDayOfWeek;
		
		if(basicDate.compareTo(otherDate) == 1){
			newDayOfWeek = (basicDayOfWeek + differDays%7) % 7; 
		}else {
			newDayOfWeek = (basicDayOfWeek - differDays%7) %7 ;
		}
		
		if(newDayOfWeek < 0)
			newDayOfWeek = newDayOfWeek + 7;
		
		return newDayOfWeek;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}