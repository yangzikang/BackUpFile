package p2;

public class Student {
	private String id;
	private String name;
	private int age;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
	
	public String toString(){
		return "学生信息：\n编号："+id+",姓名："+name+",年龄：" +age;
	}
}
