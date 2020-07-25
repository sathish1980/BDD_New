package Javaprogramstest;

import secondpackage.samplecheck;

class tester
{
	
}

public class corejave_besant implements samplecheck{
	
	private int s=1;
	private int l=2;
	private int m;

	 public void setEmpAge(int newValue){
	        s = newValue;
	    }
	public int test() {
		int a=1;
		int b=2;
		int c=0;
		System.out.println(c=a+b);
		System.out.println(m=s+l);
		return c;
	}
	public void team()
	{
int d=test();
		System.out.println("this is team");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//samplecheck c= new corejave_besant();
		corejave_besant c= new corejave_besant();
		c.setEmpAge(20);
		c.test();
		c.team();
	}

	

}
