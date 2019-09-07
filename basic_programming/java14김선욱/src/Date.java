class Date implements Cloneable{
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// ���� ���� �Է��� �ޱ� ���� ������
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
	
	// �־��� ��¥�� ���� ��¥ newDate�� ���Ͽ� �־��� ��¥�� �ռ��� -1, 
	// �ڼ��� 1, ������ 0�� ��ȯ�Ѵ�.
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

	// �־��� ��¥�� �Ϸ� ������Ų��.
	public void advance(){
		day ++;						// ������ ��¥�� �Ϸ� ������Ų��.
		
		switch(month){				// �־��� �޿� ���� �Ѵ��� �ϼ��� �޸��� ���ڷ� �����ϸ� handling �޼ҵ� ȣ���Ѵ�
			case 2:					// ������ ���� 2���� ������ ���� ����� ���� �� ���� �ϼ��� �޸��Ѵ�.
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
	
	void handling(int numOfDay){		// �ް� �⵵�� �Ѿ ������ �� ��, �� ���� ���� �����ִ� �޼ҵ�
		if(day > numOfDay){
			month++;
			day = 1;
		}
		
		if(month > 12){
			year ++;
			month = 1;
		}
	}
	
	int calGregroic(){			// ���� ��� �޼ҵ�
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