package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean onlyAlpha(String str) {
		return str.matches("[A-Za-z]+");
	}
	public static boolean countPassword(String str)
	{
		int cntd=0,cnta=0,cntA=0,cnts=0;
		Pattern d=Pattern.compile("[0-9]");
		Pattern a=Pattern.compile("[a-z]");
		Pattern A=Pattern.compile("[A-Z]");
		Pattern s=Pattern.compile("[!@#$%^&*]");
		Matcher md=d.matcher(str);
		Matcher ma=a.matcher(str);
		Matcher mA=A.matcher(str);
		Matcher ms=s.matcher(str);
		if(md.find())
		{
			cntd++;
			System.out.println("digit count"+cntd);
		}
		if(ma.find())
		{
			cnta++;
			System.out.println("lowercase char count"+cnta);
		}
		if(mA.find())
		{
			cntA++;
			System.out.println("uppercase char count"+cntA);
		}
		if(ms.find())
		{
			cnts++;
			System.out.println("special char count"+cnts);
		}
		if((cntd+cnta+cntA+cnts)>=4 && cnta>=1 && cntd>=1 && cntA>=1 && cnts>=1 && str.length()>=8)
		{
			System.out.println("Return true from password validator");
			return true;
		}
		return false;
				
	}
	public static boolean emailValidator(String str)
	{
		if(str.endsWith("@gmail.com"))
		{
			return true;
		}
		return false;
	}
}
