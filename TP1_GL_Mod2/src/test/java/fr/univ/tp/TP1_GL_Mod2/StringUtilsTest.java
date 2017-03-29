package fr.univ.tp.TP1_GL_Mod2;

import org.junit.*;

public class StringUtilsTest {
	@Test
	public void reverseStringTest() throws Exception
	{
		
		Assert.assertEquals("RUOJNOB",StringUtils.reverseString("BONJOUR"));		
	}
	@Test(expected=Exception.class)
	public void reverseStringTestVide() throws Exception
	{
		Assert.assertEquals("",StringUtils.reverseString(""));		
	}

}
