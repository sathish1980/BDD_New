package Javaprogramstest;

import java.util.Arrays;

public class javcalass {

	public void reverseastring(String s)
	{
		int len=s.length();
		String[] sp=s.split("\\s");
		int splen=sp.length;
		char reverse;
		for(int j=0;j<=splen-1;j++)
		{
			String stcom=sp[j];
			if(sp[j].equalsIgnoreCase("kumar"))
			{
				String sss= sp[j];
				int slen=sss.length();
				for(int i=slen-1;i>=0;i--)
				{
					reverse=sss.charAt(i);
					System.out.print(reverse);
				}
				
			}
			else
			{
				//System.out.print(sp[j]+" is invalid");
			}
		}
		/*char reverse;
		for(int i=len-1;i>=0;i--)
		{
			reverse=s.charAt(i);
			System.out.print(reverse);
		}*/
	}
	
	public void smallestarray()
	{
		int[] A= {1,2,3};
		int[] B= {1,2,3,4,6};
		int c=0;
		int temp=1;
		
		int size=A.length;
		int wholesize=B.length;
		
		for(int i=0;i<=size-1;i++)
		{
			
				if(A[i]<A[i+1])
				{
					c=A[i];
					if(c<=temp)
					{
					temp=c;
					}
					
				}
					System.out.println(temp);
			
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javcalass jvc= new javcalass();
		jvc.reverseastring("sathish kumar R");
		jvc.smallestarray();

	}

}
