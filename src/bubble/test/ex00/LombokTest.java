package bubble.test.ex00; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Dog {
	private String name;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	} 
}

public class LombokTest {

	public static void main(String[] args) {

			Dog d = new Dog();
			d.setName("홍길동");
			//d.name = "홍길동";
			System.out.println(d.getName());
	}
}
