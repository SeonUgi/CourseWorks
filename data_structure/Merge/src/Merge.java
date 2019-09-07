public class Merge {
	Item[][] item=null; 
	private int result[]=null;
	
	public Merge(int[][] arr){
		int len = 0;
		for(int i=0;i<arr.length;i++)
			len+=arr[i].length;
		result = new int[len];
		
		item = new Item[arr.length][];
		for(int i=0;i<arr.length;i++){
			item[i] = new Item[arr[i].length];
			for(int j=0;j<arr[i].length;j++)
				item[i][j]=new Item(arr[i][j],i,j);
		}
	}
	
	public void array_merge(){
		PriorityQueue<Item> queue = new PriorityQueue<Item>();
		Item keep;
		for(int i=0;i<item.length;i++)
			queue.enqueue(item[i][0]);
		
		for(int i=0;;i++){
			if(queue.isEmpty())
				break;
			
			keep=queue.peek();
			result[i]=queue.dequeue().getItem();
			
			if(keep.getCol() != item[keep.getRow()].length-1)
				queue.enqueue(item[keep.getRow()][keep.getCol()+1]);
		}
	}

	public void print(){
		array_merge();
		System.out.print("\nMerged data in one array");
		for(int i=0;i<result.length;i++){
			if((i+1)%20==1 && i!=1)
				System.out.println();
			System.out.print(result[i]+" ");
		}
	}
}
