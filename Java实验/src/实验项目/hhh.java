package 实验项目;

import java.util.Random;

public class hhh {
	public static void main(String[] args)
	{
		Random random = new Random();
		
		System.out.println(random.nextInt(10));//产生一个1~9的随机数
		
			
		System.out.println("------------------------------------");
		
		
		Random random1 = new Random();
		
		for(int i = 0; i < 50; i++ )
		{
			System.out.println(random1.nextInt(41) + 10);//产生一个10~50的随机数（方法1）
		}
		
		System.out.println("------------------------------------");
			
		for(int j = 0; j < 50; j++)
		{
			
			double result = Math.random();
			result *= 41;
			int result1 = (int)result;
			result1 += 10;
			
			System.out.println(result1);
		}
	}
}
