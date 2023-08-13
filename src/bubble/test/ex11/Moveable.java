package bubble.test.ex11;

public interface Moveable {

	public abstract void left();

	public abstract void right();

	public abstract void up();
	
	// 예전에는 adatable 추상클래스를 만들어서 선택적인 메소드만 따로 구현하고 이를 필요한 자식클래스에서 상속받아서 사용하도록 한다. 하지만 자식클래스에서 다중상속이 안되는 문제때문에 불편하다.
	//public abstract void down();   
	
	//자바 1.8? 이상 부터는 이렇게 하면 자식 클래스가 Moveable 인스턴스를 구현시 default 메서드를 반드시 구현하지 않아도 된다.
	default public void down() {};  
	
	 
}
