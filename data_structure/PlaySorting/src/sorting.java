import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class sorting {
	private static void swap(int[] tiems,int prev,int next){
		int temp = tiems[prev];
		tiems[prev] = tiems[next];
		tiems[next] = temp;
	}
	
	private static int partition(int[] items,int first,int last){
		swap(items,first,(first+last)/2);
		int pivot = items[first];
		int savefirst = first;
		first++;
		
		while(first<=last){
			while(first<last && items[first]<pivot)
				first++;
			while(items[last]>pivot)
				last --;
			
			if(first<last)
				swap(items,first++,last--);
			else
				break;
		}
		swap(items,savefirst,last);
		
		return last;
	}
	
	private static int kthSamllestItem(int[] items,int num,int first,int last){
		int index = partition(items,first,last);
		if(num<index)
			return kthSamllestItem(items,num,first,index-1);
		if(num>index)
			return kthSamllestItem(items,num,index+1,last);
		return items[index];
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner inFile = new Scanner(new FileReader("input.txt"));
		Scanner input = new Scanner(System.in);
				
		int[] arr= {24,15,29,11,47,12};

		for(int i=0;i<3;i++){
			PlaySorting sort = new PlaySorting(arr.clone());
			System.out.println("Before sorting");
			for(int j=0;j<arr.length;j++)
				System.out.print(arr[j]+" ");
			System.out.println();
			
			if(i==0)
				sort.bubble_Sort();
			
			else if(i==1)
				sort.selection_Sort();
			
			else
				sort.insertion_Sort();
		}
		
		String[] tempStrArr = inFile.nextLine().split(" ");
		int[] items= new int[tempStrArr.length];
		for(int i=0;i<tempStrArr.length;i++)
			items[i] = Integer.parseInt(tempStrArr[i]);
			
		System.out.println("Input data");
		for(int i=0;i<items.length;i++)
				System.out.print(items[i]+ " ");
		System.out.println();
		
		while(true){
			int command;
			System.out.print("> Enter k : ");
			command = input.nextInt();
			if(command == 0)
				break;
			System.out.println(command+"th item: "+ kthSamllestItem(items,command-1,0,items.length-1));
		}
		
		input.close();
		inFile.close();
	}
}
