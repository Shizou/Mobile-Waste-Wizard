#include<iostream>
#include<cstdio>
#include<vector>
#include<cctype>
#include<set>
#include<algorithm>
using namespace std;

string s,file = "YW.txt";;
set<string>in;

int main(){
    freopen(file.c_str(),"r",stdin);
    while(getline(cin,s)){
        if(s == "")continue;
        for(int i = 0;i < s.size();i++)
            s[i] = tolower(s[i]);
        in.insert(s);
    }
    freopen(file.c_str(),"w",stdout);
    for(set<string>::iterator it = in.begin();it != in.end();++it)
        cout << *it << endl;
    return 0;
}
