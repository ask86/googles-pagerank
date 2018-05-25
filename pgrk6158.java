
/*KELKAR AKSHAY CS610 6158 prp*/


import static java.lang.Math.abs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author akshay_kelkar
 * <code>pgrk6158</code> class to
 * implement Google's page rank Algorithm
 */

public class pgrk6158 {
		
	//Initialize all variables
		static int iters;
		static int iVals;
		static String inputFile;
		static int num_ver;      // number of vertices in the graph
		static int num_edge;      // number of edges in the graph
		static int[][] adj_mat;  // adjacency matrix 
		static final double d = 0.85;
		static final double error_rate = 0.00001;
		static double[] src;
		static int[] numArr; // for outgoing links

	public static void main(String[] args)
	{
	
		if(args.length!=0)
		{
			iters =(int) Integer.parseInt(args[0]);
			iVals = (int )Integer.parseInt(args[1]);
			
			if( (iVals<=1 && iVals>=-2) != true)
			{
				System.out.println("Enter Valid Inputs");
				return;
			}
			
			inputFile = args[2];
		
			//call algo method
			new pgrk6158().pagerkalgo();
			
		}
		else
		{
			System.out.println("Enter Valid Inputs");
			System.exit(0);
		}
		
	} // main
	
	/**
	 * Method to implement page rank algo
	 */
	
	public void pagerkalgo()
	{

		
///////////////////////////////////////////////		
		int a = 0;
		int b = 0;
		
		try {
			
			Scanner sc= new Scanner(new File(inputFile));
			num_ver = sc.nextInt();
			num_edge= sc.nextInt();
			
	/////////// adjacency matrix initialize
		
			adj_mat = new int[num_ver][num_ver];
			for(int i = 0; i < num_ver; i++)
				for(int j = 0; j < num_ver; j++)
					adj_mat[i][j] = 0;
			   
			while(sc.hasNextInt())
			 {
					a = sc.nextInt();
					b = sc.nextInt();
					adj_mat[a][b] = 1;
			}   
			
			numArr = new int [num_ver];
			for(int i =0 ; i<num_ver;i++)
			{	
				numArr[i]=0;
				for(int j =0 ; j<num_ver;j++)
				{
					numArr[i]+= adj_mat[i][j];
				}
			}
			
			src= new double [num_ver];
			
			if(iVals == 0)
			{
				for(int i=0; i<num_ver;i++)
				{src[i]=0;}
			
			}
			else if(iVals == 1)
			{
				for(int i=0; i<num_ver;i++)
				{src[i]=1;}
			 
			}
			else if(iVals == -1)
			{
				for(int i=0; i<num_ver;i++)
				{src[i]=1.0/num_ver;}
				
			}
			else if(iVals == -2)
			{
				for(int i=0; i<num_ver;i++)
				src[i]=1.0/Math.sqrt(num_ver);
				
			}
		
			} 
		catch (FileNotFoundException e) {
			
			System.out.println("Exception:"+ e.getMessage());

		}

/////////////////////////////////////////////////////////
				
		double [] D = new double[num_ver];
		boolean flag = true;
		if (num_ver> 10)
		{
			iters=0;
			for (int i=0; i <num_ver;i++)
			{
				src[i]=1.0/num_ver;
			}
			
			int i = 0;
			do {
				if(flag == true)
				{
					flag = false;
				}
				else
				{
					for(int x = 0; x < num_ver; x++) {
						src[x] = D[x];
					}
				}
				for(int x = 0; x < num_ver; x++) {
					D[x] = 0.0;
				}

				for(int x = 0; x < num_ver; x++) {
					for(int y = 0; y < num_ver; y++)
					{
						if(adj_mat[y][x] == 1) {
							D[x] += src[y]/numArr[y];
						}
					}
				}	
			
				//Compute and print page rank of all pages
				for(int x = 0; x < num_ver; x++) {
					D[x] = d*D[x] + (1-d)/num_ver;
				}
				i++;
			} while (Converged(src, D) != true);
///////////////////////////////////////////////////////////////////////////////////////////////////			
			// print page ranks at the stopping iteration 
						System.out.println("Iter: " + i);
						for(int l = 0 ; l < num_ver; l++) {
							System.out.printf("P[" + l + "] = %.6f\n",Math.round(D[l]*1000000.0)/1000000.0);
						}
						return;
					}
		System.out.print("Base    : 0");
		for(int j = 0; j < num_ver; j++) {
			System.out.printf(" :P[" + j + "]=%.6f",Math.round(src[j]*1000000.0)/1000000.0);
		}
//////////////////////////////////////////////////////////////
		
		
		if (iters != 0) {
					

			for(int i = 0; i < iters; i++)
			{
				for(int j = 0; j < num_ver; j++) {
					D[j] = 0.0;
				}

				for(int x = 0; x < num_ver; x++) {
					for(int y = 0; y < num_ver; y++)
					{
						if(adj_mat[y][x] == 1) {
							D[x] += src[y]/numArr[y];
						} 
					}
				}

				//Compute and print pagerank of all pages
				System.out.println();
				System.out.print("Iter    : " + (i+1));
				for(int x = 0; x < num_ver; x++) {
					D[x] = d*D[x] + (1-d)/num_ver;
					System.out.printf(" :P[" + x + "]=%.6f",Math.round(D[x]*1000000.0)/1000000.0);
				}

				for(int x = 0; x < num_ver; x++) {
					src[x] = D[x]; 
				} 
			}
			System.out.println();
		}
		else 
		{                         // try convergence
			int i = 0;
			do {
				if(flag == true)
				{
					flag = false;
				}
				else
				{
					for(int x = 0; x < num_ver; x++) {
						src[x] = D[x];
					}
				}
				for(int x = 0; x < num_ver; x++) {
					D[x] = 0.0;
				}

				for(int x = 0; x < num_ver; x++) {
					for(int y = 0; y < num_ver; y++)
					{
						if(adj_mat[y][x] == 1) {
							D[x] += src[y]/numArr[y];
						}
					}
				}
				          //pagerank of all pages
				System.out.println(); 
				System.out.print("Iter    : " + (i+1));
				for(int x = 0; x < num_ver; x++) {
					D[x] = d*D[x] + (1-d)/num_ver;
					System.out.printf(" :P[" + x + "]=%.6f",Math.round(D[x]*1000000.0)/1000000.0);
				}
				i++;  
			} while (Converged(src, D) != true);  
			System.out.println();
		}
////////////////////////////////////////////////////////
			
	}//pagerkalgo
	
	
	public static boolean Converged(double[] src, double[] target)
	{
		for(int i = 0; i < num_ver; i++)
		{
			if ( abs(src[i] - target[i]) > error_rate )
				return false;
		}
		return true;
	}//converged	

	
}//class
