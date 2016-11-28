package Lab1;
import java.util.Scanner;

public class boundary {
	
	public static void main(String []arg)
	{  
		
//		final int ins1_len=5, ins2_len=10;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		entity.poly = in.nextLine();  
		System.out.println(entity.poly);
//		String ans;
		
		if(control.poly_true(entity.poly))
		{
			entity.ins = in.nextLine();
			int judg=control.ins_true(entity.poly, entity.ins, entity.ins1_len, entity.ins2_len);
			while(judg!=0)
			{
				if(judg==1)
				{
					entity.ans=control.solv_ins1(entity.poly, entity.ins, entity.ins1_len);
					entity.ans=control.simp(entity.ans);
					entity.ans=control.simp_s(entity.ans);
					System.out.println(entity.ans);
				}
				else if(judg==2)
				{
					String ins2_b=entity.ins.substring(entity.ins2_len);
					if(control.ins2_b_true(entity.poly, ins2_b))
					{
						entity.ans=control.sol_ins2(entity.poly, ins2_b);
						entity.ans=control.simp(entity.ans);
						entity.ans=control.simp_s(entity.ans);
						System.out.println(entity.ans);
//						return ans;
					}
				}
				entity.ins = in.nextLine();
				judg=control.ins_true(entity.poly, entity.ins, entity.ins1_len, entity.ins2_len);
			}
		}
		System.out.println("end!");
    }

}
