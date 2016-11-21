package test6;

import static org.junit.Assert.*;

import org.junit.Test;

public class result_str {


	@Test
	public void testSol_ins2_part() {
		String result=test6.sol_ins2_part("x*2*y*z*3*x","x=0 y=2");
		assertEquals("0",result);
	}


	}

}
