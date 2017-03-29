package fr.univ.tp.TP1_GL_Mod2;

import org.junit.*;

public class StringUtilsTest {
	@Test
	public void reverseStringTest(String s)
	{
			Assert.assertEquals("RUOJNOB",StringUtils.reverseString("BONJOUR"));
		
	}

}
