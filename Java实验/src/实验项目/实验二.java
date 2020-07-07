package 实验项目;

public class 实验二 {
	public static void main(String[] args){
		int[][]a=new int[10][10];
		int[]b=new int [10];
		for(int i=0;i<10;i++)
		{
			int k=0;
			for(int j=0;j<10;j++)
			{
				b[k]=a[i][0];
				int x=a[i][j];
				if(x>b[k]);
				b[k]=x;
			}
			
			k++;
		}
		
	}

}
