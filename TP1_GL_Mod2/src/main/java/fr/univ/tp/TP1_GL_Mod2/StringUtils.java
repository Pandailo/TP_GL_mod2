package fr.univ.tp.TP1_GL_Mod2;

public class StringUtils {
	public static String reverseString(String s)
	{
		String res="";
		for(int i=s.length()-1;i>=0;i++)
		{
			res+=s.charAt(i);
		}
		return res;
	}
}
