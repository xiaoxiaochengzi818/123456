package com.moyisuiying.four;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author 陌意随影
 TODO :模拟计算器
 *2019年12月17日  下午5:39:20
 */
public class Calculator extends JFrame{

	private static final long serialVersionUID = 1L;
  private Container contentPanel = null;
  private JButton[] btns = new JButton[22];
  private JTextArea textArea  = null;
  @SuppressWarnings("javadoc")
public Calculator(){
	  this.setTitle("计算器");
	  this.setBounds(300, 100, 600, 580);
	  this.contentPanel = this.getContentPane();
	  this.textArea = new JTextArea();
	  textArea.setFont(new Font("微软雅黑",Font.BOLD,14));
	  initConponents();
	  this.setVisible(true);
	  this.setResizable(false);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
private void initConponents() {
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	this.textArea.setPreferredSize(new Dimension(580,200));
	 panel1.add(this.textArea);
	 panel.add(panel1);
	   GridLayout gridLayout = new GridLayout(6,4);
	   JPanel panel2 = new JPanel(gridLayout);
	   panel2.setPreferredSize(new Dimension(600,320));
	   this.btns[0] = new JButton("C");
	   //给清空输入的按钮添加事件
	   ClearBtnActionEvent();
	   this.btns[1] = new JButton("删除");
	   //给删除的按钮添加事件
	   delBtnActionEvent();
	   this.btns[2] = new JButton("%");
	   this.btns[3] = new JButton("/");
	   this.btns[4] = new JButton("7");
	   this.btns[5] = new JButton("8");
	   this.btns[6] = new JButton("9");
	   this.btns[7] = new JButton("*");
	   
	   this.btns[8] = new JButton("4");
	   this.btns[9] = new JButton("5");
	   this.btns[10] = new JButton("6");
	   this.btns[11] = new JButton("-");
	   
	   this.btns[12] = new JButton("1");
	   this.btns[13] = new JButton("2");
	   this.btns[14] = new JButton("3");
	   this.btns[15] = new JButton("+");
	   
	   this.btns[16] = new JButton("  ");
	   this.btns[17] = new JButton("0");
	   this.btns[18] = new JButton(".");
	   this.btns[19] = new JButton("=");
	   this.btns[20] = new JButton("(");
	   this.btns[21] = new JButton(")");
	   for(int i = 2; i < this.btns.length;i++){
		   if(i == 19){
			   //给等号 =按钮添加事件
			   ResultActionEvent(this.btns[i]);
		   }else{
			   //给数字按钮添加事件
			   digBtnEvent(this.btns[i]);
		   }
	   }
	  
	   Font font = new Font("微软雅黑",Font.BOLD,16);
	   for(int i = 0;i  < this.btns.length;i++){
		   this.btns[i].setFont(font);
		   panel2.add(this.btns[i]);
	   }
	   panel.add(panel2);
	   this.contentPanel.add(panel);
}
/***
 * 等号结果的事件
 * @param btn
 */
private void ResultActionEvent(JButton btn) {
	 btn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String exp = textArea.getText();
			boolean fla = isCanCalculate(exp);
			if(!fla){
				JOptionPane.showConfirmDialog(Calculator.this, "输入格式错误，无法计算！");
				return ;
			}
			String reultValue =  exp+"="+getExpResult(exp.trim());
			if(reultValue != null &&reultValue.trim().length()!= 0 ){
						textArea.setText(reultValue);
			}else{
				JOptionPane.showConfirmDialog(Calculator.this, "输入有误！");
			}
		}
	});
}
/**
 * 返回该表达式是否格式正确能进行计算
 * @param str
 * @return 正确返回true，否则返回false
 */
private boolean isCanCalculate(String str){
	if(str == null||str.trim().length() == 0){
		return false;
	}
	
	if(str.startsWith("*")||str.startsWith("/")||str.startsWith("%")|| str.contains("=")){
		return false;
	}
	if(!isSureCode(str)){
		return false;
	}
	return true ;
	
}
/**
 * @return 返回判断表达式的正误
 */
public boolean isSureCode(String code) {
	
	if(code.isEmpty() || code.length() == 0) {
		return false;
	}
	 Map<Integer,Character> map1 = new HashMap<Integer,Character>();
	 Map<Integer,Character> map2 =new HashMap<Integer,Character>();
	 Stack<Character> stack = new Stack<>();
	 map1.put(0,'{');
	 map1.put(1,'[');
	 map1.put(2,'(');
	 map2.put(0,'}');
	 map2.put(1,']');
	 map2.put(2,')');
	char temp = '\0';
	for(int i = 0;i < code.length();i++) {
		temp = code.charAt(i);
		if(map1.containsValue(temp)) {
		 stack.push(temp);
		}else if(map2.containsValue(temp)) {
			char pop = '\0';
			try {
				 pop = stack.pop();
			} catch (Exception e) {
				return false;
			}
			for(int j = 0;j < map2.size();j++) {
				if(temp ==map2.get(j)) {
					if(pop != map1.get(j)) {
						return false;
					}
				}
			}
			
		}else{
			
		}
	}
	 if(stack.size() != 0){
		 return false;
	 }
		return true;
	 
 }


/**
 * 删除按钮的事件
 */
private void delBtnActionEvent() {
this.btns[1].addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		 String text = textArea.getText().trim();
		 if(text.length() == 0){
			 return;
		 }
		 text = text.substring(0, text.length()-1) ;
		 textArea.setText(text);
		}
	});;
}
/**
 * 情况按钮的监听事件
 */
private void ClearBtnActionEvent() {
	this.btns[0].addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
		}
	});;
}
/**
 * 所有的数字按钮和操作符按钮的事件监听
 * @param btn
 */
  private void   digBtnEvent(final JButton btn){
	  btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 String text = textArea.getText().trim();
			 text = text+ btn.getText() ;
			 textArea.setText(text);
		}
	});
	  
  }
  /**
   * 获取表达式的值
   * @param str
   * @return 返回一个表达式的值字符串
   */
  private String getExpResult(String str){
	  if(str == null || str.length() == 0){
		  return "";
	  }
	  //在表达式尾部增加一个结束标识
	  str = str+"#";
	  //获取表达式每个元素的对象栈
	  Stack<Object>  stack =  getOperation(str);
	  //操作数栈
	  Stack<Double> OPND = new Stack<>();
	  //符号栈
	  Stack<Character> OPTR = new Stack<>();
	  //给符号栈添加一个结束的标识
	  OPTR.add('#');
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
			  //用于判定跳出循环
			  if(value == '#'&& OPTR.peek()=='#'){
				  break;
			  }
			  //获取运算符优先级
			  char temp = this.Precede( OPTR.peek(), value);
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
                	 //获取运算结果
                	 Double sum = result(a, theta, b);
                	 //结果入栈
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
 /***
  * 将表达式的字符串切分为操作数和运算符,然后依次存放到对象栈里
  * 比如：(8-4)*4+6#  分解为 栈{(,8,-,4,),*,4,+,6,#}
  * @param str
  * @return  返回一个对象栈
  */
  private  Stack<Object>  getOperation(String str){
	  Stack<Object> stack = new Stack<>();
	  if(str == null || str.length() == 0){
		  return stack;
	  }
	  //符号集合
	  ArrayList<Character> OPTR = new ArrayList<>();
	  //获取所有的符号，包括+ - * / % ( )
	  for(int i = 0;i < str.length();i++){
		  char c  = str.charAt(i);
		  //判定是否是符号
		  if(isSymbol(c)){
			  //将符号添加到符号集合中
			  OPTR.add(c);
		  }
		  }
	  //利用符号为分界线将操作数和符号逐一分割出来然后添加到对象栈中
	  for(int i = 0;i < OPTR.size();i++){
		  
		int index = str.indexOf(OPTR.get(i));
		String value = str.substring(0,index);
		if(value.trim().length() != 0){
			stack.push(Double.valueOf(value));
		}
		stack.push(OPTR.get(i));
		str = str.substring(index+1);
	  }
	  //将栈的内容倒序
	  Collections.reverse(stack);
	  //如果表达式以+或-开始，即栈顶元素为+或-，那么就要在-或+号前增加一个double 0.00
	  Object o = stack.peek();
	  if(o instanceof Character){
		  Character c = (Character) o;
		  if(c == '-'||c == '+'){
			  stack.push(0.0);
		  }
	  }
	  
	  return stack;
	  }
  
  /**
   * 获取该两个数的运算结果
   * @param num1
   * @param c
   * @param num2
   * @return
   */
  private double result(double num1, char c,double num2)
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
  private boolean isSymbol(char c)
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
  private  char Precede(char a, char ch) {
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
