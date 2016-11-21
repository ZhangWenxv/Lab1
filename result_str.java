package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class result_str {

	@Test
	public void testSol_ins2() {
		String result=test6.sol_ins2("x*2*y*z*3*x+x*2","x=0 y=2");
		assertEquals("0+0",result);
	}

	@Test
	public void testSim_add() {
		String result=test6.sim_add("22*x*z*y","2*x*y*z");
		assertEquals("24*x*z*y",result);
	}

	@Test
	public void testSimp() {
		String result=test6.simp("2*x*y+2*y*x+0+0");
		assertEquals("4*x*y",result);
	}

}
