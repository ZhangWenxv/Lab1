package ShadowDream.Lab4;

//import java.util.Scanner;
//import java.util.logging.*;
import java.util.*;
//this line is added the first time.
public class Lab4 { // NOPMD by zz17z on 16-10-19 上午9:42
     //判断字符串类型
	private static final int NUMBER2 = 2;
	public static int judge_char(char x) {
		if (x >= '0' && x <= '9') {
		    return 1;
		} else if (x >= 'a' && x <= 'z') {
		    return 2;
		} else if (x >= 'A' && x <= 'Z') {
		    return 2;
		} else if (x == '+' || x == '*') {
			return 3;
		}
		return 0;
	}
	
	//判断多项式是否合法
	public static boolean poly_true(String poly) { // NOPMD by zz17z on 16-10-19 上午9:49
		String reg = "[a-zA-Z0-9+*]+";//only the specail charactor
		String reg4 = ".*[a-zA-Z][0-9]+.*";//wrong pattern 1
		String reg5 = ".*[0-9]+[a-zA-Z].*";//wrong pattern 2
		String reg6 = ".*[a-zA-Z]{2,}.*";//wrong pattern 3
		if(poly.matches(reg) && ! poly.matches(reg4) && !poly.matches(reg5) && !poly.matches(reg6))
		{
			return true;
		}
		else{
			return false;
		}
	}

	//判断字符是否存在算式中
	public static boolean char_no_exist(char stringx, String poly) {
		final int num1 = poly.length();
		for (int integer2 = 0; integer2 < num1; integer2++) {
			if (poly.charAt(integer2) == stringx && judge_char(stringx) == 2) {
				return false;
			}
		}
		return true;
	}

	//判断化、简求导是否合法
	public static int ins_true(String poly, String ins, int ins1_len, int ins2_len){ // NOPMD by zz17z on 16-10-19 上午9:48
		//final	Logger log = Logger.getLogger(Lab4.class.getName());
		if (ins.length() < ins1_len) {
			return 0;
		} else if (ins.substring(0, ins1_len).equals("!d/d")) {
			if (ins.length() != (ins1_len + 1) || char_no_exist(ins.charAt(ins1_len), poly)) {
				//log.fine("error!do not have the variable.");
				System.out.println("error!do not have the variable.");
				return 0;
			} else {
				return 1;
			}
		} else if (ins.length() < ins2_len) {
			return 0;
		} else if (ins.substring(0, ins2_len).equals("!simplify ")) {
			return 2;
		} else {
			//log.fine("error!do not have this instruction.");
			System.out.println("error!do not have this instruction.");
			return 0;
		}
	}

	//只对一向求导
	public static String partsolvins1(String poly, char x) { // NOPMD by zz17z on 16-10-19 上午9:45
		
		String ss[] = poly.split("\\*");
		final int len = ss.length;
		int integer3 = 0;
		int numberx = 0;
		int lengnum = 0;
		int number = 1;
		int numberj = 0;
		int numbert = 0;
		String partans = "";
		for (integer3 = 0; integer3 < len; integer3++) {
			if (ss[integer3].charAt(0) == x) {
				numberx++;
			} else if (judge_char(ss[integer3].charAt(0)) == 2) {
				continue;
			} else {
				lengnum = ss[integer3].length();
				numbert = 0;
				for (numberj = 0; numberj < lengnum; numberj++) {
					numbert = numbert * 10 + (int)(ss[integer3].charAt(numberj) - (int)('0'));
				}
				number *= numbert;
			}
		}
		if (numberx != 0) {
			number *= numberx;
			partans = partans + number;
			for (integer3 = 0; integer3 < (numberx - 1); integer3++) {
				partans = partans + "*" + x;
			}
			for (integer3 = 0; integer3 < len; integer3++) {
				if ((judge_char(ss[integer3].charAt(0)) == 2) && (ss[integer3].charAt(0) != x)) {
					partans = partans + "*" + ss[integer3].charAt(0);
				}
			}
		} else {
			partans = "0";
		}
		return partans;
	}

	//求导
	public static String solvins1(final String poly,final String ins,final int ins1len) {
		int integer4;
		int len;
		String answ = "";
		len = poly.split("\\+").length;
		for (integer4 = 0; integer4 < len; integer4++) {
			answ = answ + "+" + partsolvins1(poly.split("\\+")[integer4], ins.charAt(ins1len));
		}
		answ = answ.substring(1);
		return answ;
	}

	//判断化简式子是否合法
	public static boolean ins2_b_true(String poly, String ins2_b) {
		//final Logger log = Logger.getLogger(Lab4.class.getName());
		final	int len2 = ins2_b.split(" ").length;
		int integer5 = 0;
		int integer6 = 0;
		int lenchar;
		for (integer5 = 0; integer5 < len2; integer5++) {
			lenchar = ins2_b.split(" ")[integer5].length();
			for (integer6 = 0; integer6 < lenchar; integer6++) {
				if (integer6 == 0 && char_no_exist(ins2_b.split(" ")[integer5].charAt(integer6), poly)) {
					//log.fine("error1!");
					System.out.println("error1!");
					return false;
				} else if (integer6 == 1 && ins2_b.split(" ")[integer5].charAt(integer6) != '='){
					//log.fine("error2!");
					System.out.println("error2!");
					return false;
				} else if (integer6> 1 && judge_char(ins2_b.split(" ")[integer5].charAt(integer6)) != 1) {
					//log.fine("error3!");
					System.out.println("error3!");
					return false;
				}
			}
		}
		return true;
	}

	//字符串转数字
	public static int str_int(String str) {
		final	int integer8 = str.length();
		int numt = 0;
		int integer7;
		for (integer7 = 0; integer7 < integer8; integer7++) {
			numt = numt *10 + (int)(str.charAt(integer7) - (int)('0'));
		}
		return numt;
	}

	//求一向一向的值
	public static String sol_ins2_p_p(String poly_part, String ins2_bi) { // NOPMD by zz17z on 16-10-19 上午9:49
		final int numb1 = poly_part.split("\\*").length;
		int integer9;
		int num;
		//final	Logger log = Logger.getLogger(Lab4.class.getName());
		String answ = "";
		String ans = "";
		final char stringx = ins2_bi.split("\\=")[0].charAt(0);
		final String str = ins2_bi.split("\\=")[1];
		num = 1; //str_int(str);
		for (integer9 = 0; integer9 < numb1; integer9++) {
			if (poly_part.split("\\*")[integer9].charAt(0) == stringx) {
				num *= str_int(str);
			} else if (judge_char(poly_part.split("\\*")[integer9].charAt(0)) == NUMBER2) {
				if ("".equals(answ)) {
		    	    answ = answ + poly_part.split("\\*")[integer9];
				} else {
					answ = answ + "*" + poly_part.split("\\*")[integer9];
				}
			} else if (judge_char(poly_part.split("\\*")[integer9].charAt(0)) == 1) {
		    	num *= str_int(poly_part.split("\\*")[integer9]);
		    } else {
		    	//log.fine("error4!");
		    	System.out.println("error4");
		    }
		}
		if ("".equals(answ)) {
			ans = num + answ;
		} else {
			ans = num + "*" + answ;
		}
		return ans;
	}

	//求一向的值
	public static String solins2part(final String polypart,final String ins2b) { // NOPMD by zz17z on 16-10-19 下午3:50
		final int len_i = ins2b.split(" ").length;
		int numberi;
		String poly_t = polypart;
		for (numberi = 0; numberi < len_i; numberi++) {
			poly_t = sol_ins2_p_p(poly_t, ins2b.split(" ")[numberi]);
		}
		return poly_t;
	}

	//求值
	public static String solins2(final String poly,final String ins2b) {
	final	int len = poly.split("\\+").length;
		int inumber;
		String ans = "";
		for (inumber = 0; inumber<len; inumber++) {
			if (inumber == 0){
				ans = ans + solins2part(poly.split("\\+")[inumber], ins2b);
			} else {
				ans = ans + "+"+solins2part(poly.split("\\+")[inumber], ins2b);
			}
		}
		return ans;
	}

	//化简_判断
	public static boolean sim_true(String ans1, String ans2) {
		final	int len1 = ans1.split("\\*").length;
	    final int len2 = ans2.split("\\*").length;
		String str1 = "";
		String	 str2 = "";
		int inumber;
		int judgnumber;
		if (len1 != len2) {
			return false;
		} else {
			for (inumber = 1; inumber < len1; inumber++) {
				str1 = str1 + ans1.split("\\*")[inumber];
				str2 = str2 + ans2.split("\\*")[inumber];
			}
			if ("".equals(str1) && "".equals(str2)) {
				return true;
			}
			do {
				judgnumber = -1;
				for (inumber = 0; inumber < str1.length(); inumber++) {
					if (str2.charAt(inumber) == str1.charAt(0)) {
						judgnumber = inumber;
					}
				}
				if (judgnumber == -1) {
					return false;
				} else {
				    str1 = str1.substring(1);
				    str2 = str2.substring(0, judgnumber) + str2.substring(judgnumber + 1);
				}
			} while (str1.length() != 0);
		}
		return true;
	}

	//化简_合并
	public static String simadd(final String ans1, final String ans2) {
		String ans = "";
		int inumber;
		ans = ans + (str_int(ans1.split("\\*")[0]) + str_int(ans2.split("\\*")[0]));
		for (inumber = 1; inumber < ans1.split("\\*").length; inumber++) {
//			log.fine("ans1="+ans);
			ans = ans + ans1.split("\\*")[inumber];
//			log.fine("ans2="+ans);
		}
		return ans;
	}

	//化简结果
	public static String simp(String ans) {
		String anst = "";
		String answ = "";
		int len = ans.split("\\+").length;
		int numberi;
		int numberj;
		String[] ansa = new String[len];
		for (numberi = 0; numberi < len; numberi++) {
			ansa[numberi] = ans.split("\\+")[numberi];
		}
		for (numberi = 0; numberi < len; numberi++) {
			anst = ansa[numberi];
			ansa[numberi] = "0";
			for (numberj = (numberi + 1); numberj < len; numberj++)	{
				if (sim_true(anst, ansa[numberj])) {
//					log.fine("aaaaaaaaaaa");
					anst = simadd(anst, ansa[numberj]);
					ansa[numberj] = "0";
				}
			}
			if (!"0".equals(anst)) {
			    answ = answ + "+" + anst;
			}
		}

		if ("".equals(answ)) {
			answ = "0";
		} else {
			answ = answ.substring(1);
		}
		return answ;
	}

	//主函数
	public static void main(String []arg){  
		//final Logger log = Logger.getLogger(Lab4.class.getName());
		final int ins1_len = 4;
		int ins2_len = 10;
		Scanner innumber = new Scanner(System.in);
		final  String poly = innumber.nextLine();  
		//log.fine(poly);
		System.out.println(poly);
		String ans;

		if (poly_true(poly)) {
			String ins = innumber.nextLine();
			int judg = ins_true(poly, ins, ins1_len, ins2_len);
			while (judg != 0) {
				if (judg == 1) {
					ans = solvins1(poly, ins, ins1_len);
					ans = simp(ans);
					//log.fine(ans);
					System.out.println(ans);
				} else if (judg == NUMBER2) {
					final	String ins2_b = ins.substring(ins2_len);
					if (ins2_b_true(poly, ins2_b)) {
						ans = solins2(poly, ins2_b);
						ans = simp(ans);
						//log.fine(ans);
						System.out.println(ans);
					}
				}
				ins = innumber.nextLine();
				judg = ins_true(poly, ins, ins1_len, ins2_len);
			}
		}
		//log.fine("end!");
		System.out.println("end!");
    }
}
