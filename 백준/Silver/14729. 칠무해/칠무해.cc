#include <bits/stdc++.h>
using namespace std;
int n;
double temp;
priority_queue<double> pq;
vector<double> v;
int main(){
    scanf("%d", &n);
    for(int i=0; i<n; i++){
        scanf("%lf", &temp);
        if(pq.size() == 7){
            pq.push(temp); pq.pop();
        } else pq.push(temp);
    }
    while(pq.size()){
        v.push_back(pq.top()); pq.pop();
    }
    reverse(v.begin(), v.end());
    for(double i : v) printf("%.3lf\n", i);
    return 0;
}