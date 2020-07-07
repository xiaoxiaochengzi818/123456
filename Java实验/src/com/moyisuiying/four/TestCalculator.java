package com.moyisuiying.four;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author 陌意随影
 TODO :
 *2019年12月17日  下午5:47:02
 */
public class TestCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		test();
   new Calculator();
	}

	private static void test() {
		while(true){
			System.out.println("请输入表达式：");
			String s = new Scanner(System.in).nextLine();
			System.out.println(getExpResult(s));
		}
	}
/**
		   * 获取表达式的值
		   * @param str
		   * @return 返回一个表达式的值字符串
		   */
		  private static String getExpResult(String str){
			  if(str == null || str.length() == 0){
				  return "";
			  }
			  
			  str = str+"#";
			  Stack<Object>  stack =  getOperation(str);
			  Stack<Double> OPND = new Stack<>();
			  Stack<Character> OPTR = new Stack<>();
			  OPTR.add('#');
//			 for(Object o :stack){
//				 System.out.println(o);
//			 }
			  Object obj = stack.pop();
			  if(obj instanceof Double && stack.size() == 0){
				  return  obj.toString();
			  }
			  if(obj instanceof Character && stack.size() == 0){
				  return  obj.toString();
			  }
			  while(true){
				  if(obj instanceof Double){
					 Double value =  (Double) obj;
					 OPND.push(value);
					 obj = stack.pop();
				  }else{
					  Character value =  (Character) obj;
//					  System.out.println(value);
					  if(value == '#'&&OPTR.peek()=='#'){
						  break;
					  }
//					  System.out.println(11);
					  char temp = Precede( OPTR.peek(), value);
//					  System.out.println(temp+"  1111");
					  switch (temp) {
					case '<':
							OPTR.push(value);
							obj = stack.pop();
						break;
					case '=':
				    OPTR.pop();
				    obj = stack.pop();
						break;
		                case '>':
		                	char  theta = OPTR.pop();
		                	 Double b = OPND.pop();
		                	 Double a = OPND.pop();
		                	 Double sum = result(a, theta, b);
		                	 System.out.println(sum);
		    				OPND.push(sum);
						break;
					default:
						break;
					}
				  }
				  
				  
				  
			  }
			 if(OPND.size() != 1){
				 return "计算错误";
			 }
				
				
				return OPND.peek().toString();
				
			}
		  private static  Stack<Object>  getOperation(String str){
			  Stack<Object> stack = new Stack<>();
			  if(str == null || str.length() == 0){
				  return stack;
			  }
			  ArrayList<Double> OPND = new ArrayList<>();
			  ArrayList<Character> OPTR = new ArrayList<>();
			  for(int i = 0;i < str.length();i++){
				  char c  = str.charAt(i);
				  if(isSymbol(c)){
					  OPTR.add(c);
				  }
				  }
			  for(int i = 0;i < OPTR.size();i++){
				int index = str.indexOf(OPTR.get(i));
				String value = str.substring(0,index);
				if(value.trim().length() != 0){
					OPND.add(Double.valueOf(value));
					stack.push(Double.valueOf(value));
				}
				stack.push(OPTR.get(i));
				str = str.substring(index+1);
			  }
			  Collections.reverse(stack);
//			  while(stack.size()!= 0){
//				  Object o = stack.pop();
//				  if( o instanceof Character){
//					  System.out.println("Character");
//				  }else if( o instanceof Double){
//					  System.out.println("D");
//				  }else{
//					  System.out.println("未知");
//				  }
//			  }
			  return stack;
			  }
		  
		  /**
		   * 获取该两个数的运算结果
		   * @param num1
		   * @param c
		   * @param num2
		   * @return
		   */
		  private static double result(double num1, char c,double num2)
		  {
			  double sum = 0;
		  	switch (c)
		  	{
		  	case '+':
		  		sum = num1 + num2;
		  			break;
		  	case '-':
		  		sum = num1 - num2;
		  		break;
		  	case '*':
		  		sum = num1 * num2;
		  		break;
		  	case '/':
		  		sum = num1 / num2;
		  		break;
		  	case '%':
		  		sum = num1 % num2;
		  		break;
		  	}
		  	return sum;
		  }

		  /**
		 * @param c
		 * @return  返回该字符是否是运算符
		 */
		  private static boolean isSymbol(char c)
		  {
				if (c == '+'||c == '-'||c=='*'|| c == '/' ||c== '%' || c =='('||c =='#'||c==')')
				{
					return true;

				}

				return false;
			}
		  /**
		   * 返回这两个运算符的优先级
		   * @param a
		   * @param ch
		   * @return 
		   */
		  private static  char Precede(char a, char ch) {
			  	switch (a)
			  	{
			  	case '+':
			  		switch (ch)
			  		{
			  		case '+':
			  			return '>';
			  		case '-':
			  			return '>';
			  		case '*':
			  			return '<';
			  		case '/':
			  			return '<';
			  		case '%':
			  			return '<';
			  		case '(':
			  			return '<';
			  		case ')':
			  			return '>';
			  		case '#':
			  			return '>';
			  		}
			  	
			  	case '-':
			  		switch (ch)
			  		{
			  		case '+':
			  			return '>';
			  		case '-':
			  			return '>';
			  		case '*':
			  			return '<';
			  		case '%':
			  			return '<';
			  		case '/':
			  			return '<';
			  		case '(':
			  			return '<';
			  		case ')':
			  			return '>';
			  		case '#':
			  			return '>';
			  		}
			  	case '*':
			  		switch (ch)
			  		{
			  		case '+':
			  			return '>';
			  		case '-':
			  			return '>';
			  		case '*':
			  			return '>';
			  		case '%':
			  			return '>';
			  		case '/':
			  			return '>';
			  		case '(':
			  			return '<';
			  		case ')':
			  			return '>';
			  		case '#':
			  			return '>';
			  		}
			  	case '%':
			  		switch (ch)
			  		{
			  		case '+':
			  			return '>';
			  		case '-':
			  			return '>';
			  		case '*':
			  			return '>';
			  		case '/':
			  			return '>';
			  		case '%':
			  			return '>';
			  		case '(':
			  			return '<';
			  		case ')':
			  			return '>';
			  		case '#':
			  			return '>';
			  		}
			  	case '/':
			  		switch (ch)
			  		{
			  		case '+':
			  			return '>';
			  		case '-':
			  			return '>';
			  		case '*':
			  			return '>';
			  		case '/':
			  			return '>';
			  		case '%':
			  			return '>';
			  		case '(':
			  			return '<';
			  		case ')':
			  			return '>';
			  		case '#':
			  			return '>';
			  		}
			  	case '(':
			  		if (ch == ')')
			  		{
			  			return '=';
			  		}
			  		else {
			  			return '<';
			  		}
			  	case ')':
			  			return '>';
			  	case '#':
			  		if (ch =='#')
			  		{
			  			return '=';
			  		}
			  		else {
			  			return '<';
			  		}
			  	}
			  	return ' ';
		  }
}
