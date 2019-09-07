public class Item  implements Comparable<Item>{
	private int item;
	private int row;
	private int col;
	
	public Item(int item, int row, int col){
		this.item = item;
		this.row = row;
		this.col =col;
	}
	
	public int getItem(){return item;}
	public int getRow(){return row;}
	public int getCol(){return col;}

	@Override
	public int compareTo(Item o) {
		return this.item - o.getItem();
	}
	
	public String toString() {
		return String.valueOf(getItem());
	}
}
