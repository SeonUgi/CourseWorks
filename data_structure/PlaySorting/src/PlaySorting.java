
public class PlaySorting {
	private int[] arr;
	
	public PlaySorting(int[] arr){
		this.arr = arr;
	}
	
	private void swap(int prev,int next){
		int temp = arr[prev];
		arr[prev] = arr[next];
		arr[next] = temp;
	}
	
	public void bubble_Sort(){	//버블정렬
		System.out.println("\nAfter bubble sorting");
		
		for(int i=arr.length-1,count = 1;i>0;i--,count++){
			for(int j=arr.length-1;j>0;j--){
				if(arr[j-1]>arr[j])
					swap(j-1,j);
			}
			
			System.out.print("pass "+count+" : ");
			for(int j=0;j<arr.length;j++)
				System.out.print(arr[j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void selection_Sort(){	//선택정렬
		System.out.println("\nAfter selection sorting");
		int minIndex = 0;
		
		for(int i=0 ; i<arr.length-1 ;i++){
			minIndex = i;
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<arr[minIndex])
					minIndex = j;
			}
			if(minIndex != i)
				swap(i,minIndex);
			
			System.out.print("pass "+(i+1)+" : ");
			for(int j=0;j<arr.length;j++)
				System.out.print(arr[j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void insertion_Sort(){	//삽입정렬
		System.out.println("\nAfter insertion sorting");
		int temp;
		int i,j;
		for(i=1 ; i<arr.length;i++){
			temp = arr[i];
			for(j=i;j>0 && temp<arr[j-1];j--)
				arr[j] = arr[j-1];
			arr[j] = temp;
			
			System.out.print("pass "+i+" : ");
			for(j=0;j<arr.length;j++)
				System.out.print(arr[j]+" ");
			System.out.println();
		}
		System.out.println();
	}
}
