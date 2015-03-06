#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;

const int MAXN = 50,inf = 0x3f3f3f3f;
int dp[MAXN][MAXN];
int N,M;
string A,B;

int diff(int i,int j){
	return A[i] == B[j] ? 0:1;
}
int edit_distance(){
    A.insert(0," "),B.insert(0," ");
	N = A.size(), M = B.size();
	memset(dp,inf,sizeof(dp));
	for(int i = 0;i < N;i++){
		for(int j = 0;j < M;j++){
			if(i == 0)
				dp[i][j] = j;
			else if(j == 0)
				dp[i][j] = i;
			else
				dp[i][j] = min(dp[i-1][j-1]+diff(i,j), min(dp[i-1][j]+1,dp[i][j-1]+1) );
		}
	}
	return dp[N-1][M-1];
}

int main(){
    //freopen("input.txt","r",stdin);
	cin >> A >> B;
	printf("%d\n",edit_distance());
	return 0;
}
