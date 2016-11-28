package Lab1;

public class control {
	

	public static int judge_char(char x)
	{
		if(x>='0' && x<='9')
		{
		    return 1;
		}
		else if(x>='a' && x<='z')
		{
		    return 2;
		}
		else if(x>='A' && x<='Z')
		{
		    return 2;
		}
		else if(x=='+' || x=='*')
		{
			return 3;
		}
		return 0;
	}
	
	public static boolean poly_true(String poly)
	{
		int i=0;
		char x, y;
		
		int len = poly.length();
		for(i = 0; i < (len-1); i++)
		{
			x = poly.charAt(i);
			y = poly.charAt(i+1);
			if(judge_char(x)==0 || judge_char(y)==0)
			{
				System.out.println("error!");
				return false;
			}
			else if(judge_char(poly.charAt(len-1))==3)
			{
				System.out.println("error!");
				return false;
			}
			else if(i==0 && judge_char(x)==3)
			{
				System.out.println("error!");
				return false;
			}
			else
			{
				
				if(judge_char(x)==3 && judge_char(y)==3)
				{
					System.out.println("error!");
					return false;
				}
				else if(judge_char(x)==1 && judge_char(y)==2)
				{
					System.out.println("error!");
					return false;
				}
				else if(judge_char(x)==2 && judge_char(y)==2)
				{
					System.out.println("error!");
					return false;
				}
				else if(judge_char(x)==2 && judge_char(y)==1)
				{
					System.out.println("error!");
					return false;
				}
			}
		}
		return true;		
		
	}
	
	
	public static boolean char_no_exist(char x, String poly)
	{
		int n = poly.length();
		int i;
		for(i=0; i<n; i++)
		{
			if(poly.charAt(i)==x && judge_char(x)==2)
			{
				return false;
			}
		}
		return true;
	}
	

	public static int ins_true(String poly, String ins, int ins1_len, int ins2_len)
	{
		if(ins.length()<ins1_len)
		{
			return 0;
		}
	    else if(ins.substring(0, ins1_len).equals("!d/d "))
		{
			if(ins.length()!=(ins1_len+1) || char_no_exist(ins.charAt(ins1_len),poly))
			{
				System.out.println("error!do not have the variable.");
				return 0;
			}
			else
			{
				return 1;
			}
		}
	    else if(ins.length()<ins2_len)
		{
			return 0;
		}
		else if(ins.substring(0, ins2_len).equals("!simplify "))
		{
			return 2;
		}
		
		else
		{
			System.out.println("error!do not have this instruction.");
			return 0;
		}
	}
    

	public static String part_solv_ins1(String poly, char x)
	{
		int len=poly.split("\\*").length;
		int i=0, numx=0, leng=0, num=1, j=0, numt=0;
		String part_ans="";
		for(i=0;i<len;i++)
		{
			if(poly.split("\\*")[i].charAt(0)==x)
			{
				numx++;
			}
			else if(judge_char(poly.split("\\*")[i].charAt(0))==2)
			{
				continue;
			}
			else
			{
				leng=poly.split("\\*")[i].length();
				numt=0;
				for(j=0;j<leng;j++)
				{
					numt=numt*10 + (int)(poly.split("\\*")[i].charAt(j)-(int)('0'));
				}
				num*=numt;
			}
		}
		if(numx!=0)
		{
			num*=numx;
			part_ans=part_ans+num;
			for(i=0;i<(numx-1);i++)
			{
				part_ans=part_ans+"*"+x;
			}
			for(i=0;i<len;i++)
			{
				if((judge_char(poly.split("\\*")[i].charAt(0))==2) && (poly.split("\\*")[i].charAt(0)!=x))
				{
					part_ans=part_ans+"*"+poly.split("\\*")[i].charAt(0);
				}
			}
		}
		else
		{
			part_ans="0";
		}
//			    System.out.println(part_ans);
		return part_ans;
	}
	

	public static String solv_ins1(String poly, String ins, int ins1_len)
	{
		int i, len;
		String answ="";
		len=poly.split("\\+").length;
		for(i=0;i<len;i++)
		{
			answ=answ+"+"+part_solv_ins1(poly.split("\\+")[i], ins.charAt(ins1_len));
		}
		answ=answ.substring(1);
		return answ;
	}
	

	public static boolean ins2_b_true(String poly, String ins2_b)
	{
		int len2 = ins2_b.split(" ").length;
		int i=0, j=0, lenchar;
		for(i=0;i<len2;i++)
		{
			lenchar=ins2_b.split(" ")[i].length();
			for(j=0;j<lenchar;j++)
			{
				if(j==0 && char_no_exist(ins2_b.split(" ")[i].charAt(j), poly))
				{
					System.out.println("error1!");
					return false;
				}
				else if(j==1 && ins2_b.split(" ")[i].charAt(j)!='=')
				{
					System.out.println("error2!");
					return false;
				}
				else if(j>1 && judge_char(ins2_b.split(" ")[i].charAt(j))!=1)
				{
					System.out.println("error3!");
					return false;
				}
			}
		}
		return true;
	}
	

	public static int str_int(String str)
	{
		int len=str.length();
		int numt=0, j;
		for(j=0;j<len;j++)
		{
			numt=numt*10 + (int)(str.charAt(j)-(int)('0'));
		}
		return numt;
	}
	

	public static String sol_ins2_p_p(String poly_part, String ins2_bi)
	{
		int len=poly_part.split("\\*").length;
		int i, num;
		String answ="", ans="";
		char x = ins2_bi.split("\\=")[0].charAt(0);
		String str = ins2_bi.split("\\=")[1];
		num = 1;//str_int(str);
		for(i=0;i<len;i++)
		{
			if(poly_part.split("\\*")[i].charAt(0)==x)
			{
				num*=str_int(str);
			}
		    else if(judge_char(poly_part.split("\\*")[i].charAt(0))==2)
			{
				if(answ=="")
				{
		    	    answ=answ+poly_part.split("\\*")[i];
				}
				else
				{
					answ=answ+"*"+poly_part.split("\\*")[i];
				}
			}
		    else if(judge_char(poly_part.split("\\*")[i].charAt(0))==1)
		    {
		    	num*=str_int(poly_part.split("\\*")[i]);
		    }
		    else
		    {
		    	System.out.println("error4!");
		    }
		}
		if(answ=="")
		{
			ans=num+answ;
		}
		else
		{
			ans=num+"*"+answ;
		}
//			System.out.println(ans);
		return ans;
	}
	

	public static String sol_ins2_part(String poly_part, String ins2_b)
	{
		int len_i=ins2_b.split(" ").length;
		int i;
		String poly_t=poly_part;
		for(i=0;i<len_i;i++)
		{
			poly_t=sol_ins2_p_p(poly_t, ins2_b.split(" ")[i]);
		}
//			System.out.println(poly_t);
		if(poly_t.charAt(0)=='0')
		{
			return "0";
		}
		return poly_t;
	}
	

	public static String sol_ins2(String poly, String ins2_b)
	{
		int len=poly.split("\\+").length;
		int i;
		String ans="";
		for(i=0;i<len;i++)
		{
			if(i==0)
			{
				ans=ans+sol_ins2_part(poly.split("\\+")[i], ins2_b);
			}
			else
			{
				ans=ans+"+"+sol_ins2_part(poly.split("\\+")[i], ins2_b);
			}
		}
		return ans;
	}
	

	public static boolean sim_true(String ans1, String ans2)
	{
		int len1=ans1.split("\\*").length;
		int len2=ans2.split("\\*").length;
		String str1="", str2="";
		int i, judg;
		if(len1!=len2)
		{
			return false;
		}
		else
		{
			for(i=1;i<len1;i++)
			{
				str1=str1+ans1.split("\\*")[i];
				str2=str2+ans2.split("\\*")[i];
			}
			if(str1=="" && str2=="")
			{
				return true;
			}
			do{
				judg=-1;
				for(i=0;i<str1.length();i++)
				{
					if(str2.charAt(i)==str1.charAt(0))
					{
						judg=i;
					}
				}
				if(judg==-1)
				{
					return false;
				}
				else
				{
				    str1=str1.substring(1);
				    str2=str2.substring(0, judg)+str2.substring(judg+1);
				}
			}while(str1.length()!=0);
		}
		return true;
	}
	

	public static String sim_add(String ans1, String ans2)
	{
		String ans="";
		int i,j;
		
	    j=(str_int(ans1.split("\\*")[0])+str_int(ans2.split("\\*")[0]));
	    if(j!=0)
	    {
		    ans=ans+j;
			for(i=1;i<ans1.split("\\*").length;i++)
			{
	//			System.out.println("ans1="+ans);
				ans=ans+"*"+ans1.split("\\*")[i];
	//			System.out.println("ans2="+ans);
			}
	    }
		else
		{
			ans="0";
		}
//			System.out.println(ans);
		return ans;
	}
	

	public static String simp(String ans)
	{
		String anst="";
		String answ="";
		int len=ans.split("\\+").length;
		int i, j;
		String[] ansa=new String[len];
		for(i=0;i<len;i++)
		{
			ansa[i]=ans.split("\\+")[i];
		}
		for(i=0;i<len;i++)
		{
			anst=ansa[i];
			ansa[i]="0";
			for(j=(i+1);j<len;j++)
			{
				if(sim_true(anst, ansa[j]))
				{
//						System.out.println("aaaaaaaaaaa");
					anst=sim_add(anst, ansa[j]);
					ansa[j]="0";
				}
			}
			if(anst!="0")
			{
			    answ=answ+"+"+anst;
			}
		}
		
		if(answ=="")
		{
			answ="0";
		}
		else
		{
			answ=answ.substring(1);
		}
		return answ;
	}
	
	
	public static String simp_s(String ans)
	{
		String answ=ans;
//		String answ="1*x+0+0+0+0";
		int i=0;
		while(i<=(answ.length()-1))
		{
			if(i==0 && answ.charAt(0)=='0' && answ.charAt(1)=='+')
			{
				answ=answ.substring(2);
			}
			else if(i==0 && answ.charAt(0)=='1' && answ.charAt(1)=='*')
			{
				answ=answ.substring(2);
			}
			else if(i==(answ.length()-1) && answ.charAt(i)=='0' && answ.charAt((i-1))=='+')
			{
				answ=answ.substring(0, (i-1));
			}
			else if(i==(answ.length()-1) && answ.charAt(i)=='1' && answ.charAt((i-1))=='*')
			{
				answ=answ.substring(0, (i-1));
			}
			else if(i>0 && answ.charAt(i)=='0' && answ.charAt((i-1))=='+')
			{
				answ=answ.substring(0, i)+answ.substring((i+2), answ.length());				
			}
			else if(i>0 && answ.charAt(i)=='1' && answ.charAt((i-1))=='*')
			{
				answ=answ.substring(0, i)+answ.substring((i+2), answ.length());				
			}
			else
			{
				i++;
			}
		};
		return answ;
	}

}
