
public interface Iteraor<T> {
	// 구성 요소의 끝에 도달하지 않는 한 true 반환
	public boolean hasNext();
	// 현재 커서의 위치에 있는 요소를 반환하고 커서를
	// 다음 구성 요소 위치로 이동 시킴
	public T next();
	// next() 메소드에 의해서 반환된 요소를 삭제, optional
	// method 이므로 구현하지 않아도 됨
	public void remove(); // optional method
}
