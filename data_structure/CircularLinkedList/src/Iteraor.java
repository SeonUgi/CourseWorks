
public interface Iteraor<T> {
	// ���� ����� ���� �������� �ʴ� �� true ��ȯ
	public boolean hasNext();
	// ���� Ŀ���� ��ġ�� �ִ� ��Ҹ� ��ȯ�ϰ� Ŀ����
	// ���� ���� ��� ��ġ�� �̵� ��Ŵ
	public T next();
	// next() �޼ҵ忡 ���ؼ� ��ȯ�� ��Ҹ� ����, optional
	// method �̹Ƿ� �������� �ʾƵ� ��
	public void remove(); // optional method
}
