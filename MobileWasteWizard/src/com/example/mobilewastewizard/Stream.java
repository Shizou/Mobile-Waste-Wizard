package com.example.mobilewastewizard;

public class Stream {

	public Stream() {
		
	}
	public int diff(String A, String B,int i,int j){
		return A.charAt(i) == B.charAt(j) ? 0:1;
	}
	public int edit_distance(String A,String B){
	    A = " " + A;
	    B = " " + B;
		int N = A.length(), M = B.length(),inf = 1000000;
		int [][]dp = new int[N][M];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < M;j++)
				dp[i][j] = inf;
		for(int i = 0;i < N;i++){
			for(int j = 0;j < M;j++){
				if(i == 0)
					dp[i][j] = j;
				else if(j == 0)
					dp[i][j] = i;
				else
					dp[i][j] = Math.min(dp[i-1][j-1]+diff(A,B,i,j), Math.min(dp[i-1][j]+1,dp[i][j-1]+1) );
			}
		}
		return dp[N-1][M-1];
	}
	
}
