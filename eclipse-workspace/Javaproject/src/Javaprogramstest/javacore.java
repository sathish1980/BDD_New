package Javaprogramstest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class javacore {
	
	private static final Boolean True = null;
	public void array()
	{
		
		for(int i=1;i<=5;i++)
		{
			
			for(int j=1;j<=5;j++)
			{
				System.out.print(j);
			if(i==j)
			{
				break;
				
			}
			
			}
			System.out.println();
		}
		
	}
	
	public void largest()
	{

		 int[] arr={28,3,15,9,17,4,23,2};
		 
		 int val=arr[0];
		 
		 for(int j=0;j<=arr.length-1;j++)
		 {
			 if(arr[j]>val)
			 {
				 val=arr[j];
			 }
		 }
		 System.out.println(val);
		 
		 
		 
	}
	
	public void primeornot(int str)
	{
		int flag=0;
		for(int i=2;i<=100;i++) {
		if(str%i==0)
		{
			flag=1;
			break;
			
		}
		else
		{
			flag=2;
			
		}
		}
		
		if(flag==1)
		{

			System.out.print("this is prime");
			
		}
		else
		{

			System.out.print("this is not a prime");
		}
		
	}
	
	public void splitandreverse()
	{
		String s="Sathish kumar";
		String[] s1=s.split("\\s");
		int size=s1.length;
		char ca;
		
		for(int j=0;j<=size-1;j++)
		{
			String sa=s1[j];
			//if(sa.equalsIgnoreCase("Kumar"))
			//{
				int sale=sa.length();
				for(int k=sale-1;k>=0;k--) {
					ca=sa.charAt(k);
					System.out.print(ca);
					
					
				}
			
			//}
		}
		
	}
	
/*	static void permute(char[] a, int k) 
    {
        if (k == a.length) 
        {
            for (int i = 0; i < a.length; i++) 
            {
                System.out.print(a[i]);
            }
            System.out.println();
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                char temp = a[k];
                a[k] = a[i];
                a[i] = temp;
 
                permute(a, k + 1);
 
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }*/
	
	public void upercase(String s)
	{
		System.out.print(s.toUpperCase());
	}
	
	public void whitespace(String s)
	{
		System.out.print(s.trim());
		
	}
	
	public void duplicatechar()
	{
		String sat="sathish";
		int len=sat.length();
		char[] cr=sat.toCharArray();
		int count=0;
		for(int i=0 ;i<len;i++)
		{
			for(int j=i+1;j<len;j++)
			{
				//System.out.print(sat.charAt(i));
				//System.out.print(sat.charAt(j));
			if(cr[i]==cr[j]) {
				System.out.println();
				System.out.print(cr[j]);
				count++; 
				System.out.print("="+count);
			}
			}
		}
	}
	
	public void sortusingtreeset()
	{
	
		TreeSet<String> TS=new TreeSet<String>(Collections.reverseOrder());
		TS.add("sathish");
		TS.add("kumar");
		TS.add("r");
		TS.add("apple");
		
		Iterator<String> ir= TS.iterator();
		
		while(ir.hasNext())
		{
			System.out.println(ir.next());
		
		}
		
		
	}
	
	public void binary(int n)
	{
	    {
	        int count=0,temp=0,counter=0;
	        int count1 = 0, a;
	        String x = "";
	      
	        while(n > 0)
	        {
	            a = n % 2;
	            if(a == 1)
	            {
	                count++;
	                count1=0;
	               
	            }
	            if((a==0))
	            {
	            	count1++;
	            }
	           
	            
	            x = x + "" + a;
	            n = n / 2;
	            if(count1>temp)
	            {
	            temp=count1;
	            }
	           counter++;
	        }
	        if(count==1) {
	        	temp=0;
	        }
	        System.out.println("Binary number:"+x);
	      /*  String sa=x;
	        int len=sa.length();
	        String[] sat;
	        for(int i=0;i<=len;i++)
	        {
	        	for(int j=1;j<=len;j++)
	        	{
	        		if
	        	}
	        }*/
	        System.out.println("0's value:"+temp);
	    }
	}
	
	
	public void arrayshifting()
	{
		
			        int k=3;
			        int[] A= {1,2,3,4,5};
			        int[] s;
			        int timeshifted=0;
			        for(int z=0;z<k;z++) {
			        	
			        	int size=A.length;
			        while(timeshifted<size-1)
			        {
			        int temp = A[0];
			        
			      for(int i=0;i<size-1;i++)
			      {
			    	
			    	 A[i]=A[i+1];
			    	
			      }
			      A[A.length-1]=temp;
			      timeshifted++;
			        }
			        timeshifted=0;
			        }
			        System.out.println(Arrays.toString(A));
	}	
	public void unparried() {
		int [] sa= {1,5,3,1,5,7};
		int size=sa.length;
		int[] su;
		int[] sas;
		int temp=0;
		Boolean sathish;
		
				for(int i=0;i<=size-1;i++)
				{
					
					for(int j=i+1;j<=size-1;j++)
					{
						if((sa[i]==sa[j])&&(i<j))
						//	if((sa[i]==sa[j]))
						{
						//	System.out.println(sa[j]);
							//sa[i]=0;
							//sa[j]=0;
							
							temp=0;
							break;
							
							
							}
						else
						{
							temp=sa[i];
							
						}
							
						}
					if(temp==0)
					{
						
					}
					else
					{
						System.out.print(temp);
					}
				
				
						
				}
	}
	
	public void frogjump()
	{
		int x=10;
		int y=180;
		int d=30;
		
		
		int progress = x;
        int count = 0;
        while (progress < y) {
            progress = progress + d;
            count++;
        }
        System.out.println("the count is "+count);
		
	}
	
	public void missingelementinaray()
	{
		int[] A= {1,2,3,5};
		int size=A.length;
		int unmatch=0;
		TreeMap<String,Object> hmp=new TreeMap<String,Object>();
		TreeMap<String,Object> hmp1=new TreeMap<String,Object>();
		
		for(int i=0;i<=size-1;i++)
		{
			hmp.put("map"+i, A[i]);
			
		}
		System.out.println(hmp);
		for(int j=0;j<=1;j++)
		{
			Object jp=hmp.get("map"+j);
			String sa=jp.toString();
			int jk=Integer.parseInt(sa);
			for(int K=jk;K<jk+hmp.size()-1;K++)
			{
			hmp1.put("Hmap"+K, K);
			}
		}
		System.out.println(hmp1);
		
		int hmpsize=hmp.size();
		int hmp1size=hmp1.size();
		for(int l=0;l<hmp.size();l++)
		
		{
		int j=l+1;	
			//System.out.println(hmp.get("map"+l));
			//System.out.println(hmp1.firstKey());
			//System.out.println(hmp1.get("Hmap"+j));
			
			Object jp=(hmp.get("map"+l));
			String sa=jp.toString();
			int jk=Integer.parseInt(sa);
			
			Object jp1=hmp1.get("Hmap"+j);
			String sa1=jp1.toString();
			int jk1=Integer.parseInt(sa1);
			
			
			if(jk==jk1)
			{
				
			}
			else
			{
				
				Object jp2=hmp1.get("Hmap"+j);
				String unmatchs=jp2.toString();
				unmatch=Integer.parseInt(unmatchs);
				System.out.println("\"the unmatched sequence value is\"+"+unmatchs);
			}
		}
		
	}
	
	 public void solution() {
	        // write your code in Java SE 8
		 int[] A= {2,3,1,5};
	        int total=0;
	        int sizes=A.length+1;
	        int totalnumber=(sizes*(sizes+1)/2);
	        for(int i :A)
	        {
	            total+=i;
	        }
			System.out.println( totalnumber);
	    }
	
	 public void reverse()
	 {
		 String s ="sathish kumar r the new role";
		 String[] s1=s.split("\\s");
		 int size=s1.length;
		 
		 for(int i=size-1;i>=0;i--)
		 {
			 System.out.print(s1[i]);
		 }
				 
	 }
	 
	 public void secondlargest() {
		 int ar[] = {12,15,1,25,99,178};
		 int temp=0;
		 int size=ar.length;
		 for(int i=0;i<size-1;i++)
		 {
			 for(int j=i+1;j<size-1;j++)
			 {
				 if(ar[i]>ar[j])
				 {
					 temp=ar[i];
					
				 }
			 }
			 System.out.println(temp);
		 }
		 
	 }
	 public void smallest()
	 {
		 int [] arr1= {0,2,3,6,5};
		 int size=arr1.length;
		 int temp=001;
		 for(int i=0;i<=size-1;i++)
		 {
			 for(int j=i+1;j<=size-1;j++)
			 {
				
				 if(arr1[i]<arr1[j])
				 {
					 if(temp==001)
					 {
					 temp=arr1[i];
					 }
					 else
					 {
						// System.out.println(arr1[i]);
						 if(arr1[i]<=temp)
						 {
							 temp=arr1[i];
						 }
						 
					 }
					 
				 }
			 }
		 }
		 if(arr1[size-1]<=temp)
		 {
			 temp=arr1[size-1];
		 }
		 System.out.println(temp);
	
	 }
	 public void sortarray()
	 {
		 int arr1[]= {2,5,6,3};
	
	 }
	public static void main(String[]  args) {
		// TODO Auto-generated method stub
		javacore jvc= new javacore();
		/*jvc.array();
		jvc.largest();
		jvc.primeornot(8);
jvc.splitandreverse();
jvc.upercase("sathish kumar RAMAKRISHNAN");
jvc.whitespace("sathish kumar RAMAKRISHNAN");
jvc.duplicatechar();
jvc.sortusingtreeset();
jvc.binary(51712);
jvc.arrayshifting();
jvc.unparried();
jvc.frogjump();
jvc.missingelementinaray();
jvc.solution();
jvc.reverse();
jvc.secondlargest();*/
jvc.smallest();
/*System.out.println("Enter the length of character string: ");
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();

char[] sequence = new char[n];
System.out.println("Enter the original string: ");
for (int i = 0; i < n; i++)
    sequence[i] = sc.next().charAt(0);
System.out.println("The permuted strings are: ");
permute(sequence, 0);

sc.close();*/
	}

}
