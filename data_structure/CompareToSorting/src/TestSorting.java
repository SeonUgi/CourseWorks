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
		
		//���� ���� ��
		System.out.println("Bubble Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.bubble_Sort();	//��������
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
		//���� ���� ��
		System.out.println("\nSelection Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.selection_Sort();	//��������
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
		//���� ���� ��
		System.out.println("\nInsertion Sort");
		for(int i=0;i<3;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.insertion_Sort();	//���� ����
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
		//�� ���� ��
		System.out.println("\nHeep Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.heep_Sort();	//�� ����
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
		//�պ� ���� ��
		System.out.println("\nMerge Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.merge_Sort();	//�պ� ����
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
		//�� ���� ��
		System.out.println("\nQuick Sort");
		for(int i=0;i<arr.length;i++){
			PlaySorting<Integer> sort = new PlaySorting<Integer>(arr[i].clone());
			long startTime, endTime, elapsed;
			startTime = System.currentTimeMillis();	//���۽ð�
			sort.quick_Sort();	//�� ����
			endTime = System.currentTimeMillis();	//����ð�
			elapsed = endTime - startTime;
			System.out.print("������ �� : "+arr[i].length+"  �ð�: "+ elapsed+"   ");
			sort.inspection();
		}
	}
}
