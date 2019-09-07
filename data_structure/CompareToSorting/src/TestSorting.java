import java.util.Random;

public class TestSorting {
	private static void makeArray(Integer[][] arr){
		Random random = new Random();
		arr[0] = new Integer[10000];
		arr[1] = new Integer[50000];
		arr[2] = new Integer[100000];
		arr[3] = new Integer[500000];
		arr[4] = new Integer[1000000];
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++)
				arr[i][j] = random.nextInt(arr[i].length);
		}
	}
	public static void main(String[] args) {
		Integer[][] arr = new Integer[5][];
		makeArray(arr);
		
		//버블 정렬 시
		System.out.println("Bubble Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.bubble_Sort();	//버블정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
		//선택 정렬 시
		System.out.println("\nSelection Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.selection_Sort();	//선택정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
		//삽입 정렬 시
		System.out.println("\nInsertion Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.insertion_Sort();	//삽입 정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
		//힙 정렬 시
		System.out.println("\nHeep Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.heep_Sort();	//힙 정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
		//합병 정렬 시
		System.out.println("\nMerge Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.merge_Sort();	//합병 정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
		//퀵 정렬 시
		System.out.println("\nQuick Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//시작시간
			sort.quick_Sort();	//퀵 정렬
			endTime = System.currentTimeMillis();	//종료시간
			elapsed = endTime - startTime;
			System.out.print("데이터 수 : "+arr[i].length+"  시간: "+ elapsed+"   ");
			sort.inspection();
		}
	}
}
