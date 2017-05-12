#include<bits/stdc++.h>
using namespace std;

double sup;
vector<set<int> > l;
set<set<int>	> indepen;
ifstream infile;
string str="";
map<set<int> ,int> m; 
int found = 0;

int findSupport(set<int> s){

	int count = 0;
	for(set<int> s1 : l){

		vector<int> diff;
		set_difference(s.begin(), s.end(), s1.begin(), s1.end(), 
				inserter(diff, diff.begin()));

		if(diff.size() == 0){
			count++;
		}

	}
	return count;


}

void fun(set<set<int> > se,int size){



	if(se.size() == 1){
		found = 1;
		for ( auto it = se.begin(); it != se.end(); it++ ){
			set<int> se2 = *it;
			for ( auto it1 = se2.begin(); it1 != se2.end(); it1++ ){
				cout << *it1 << " ";
			}				
		}
	}

	set<set<int>> se1;
	for ( auto it = se.begin(); it != se.end(); it++ ){
		if(m[*it] < sup){
			se.erase(*it);
		}
	}
	for ( auto it1 = se.begin(); it1 != se.end(); it1++ ){
		for ( auto it2 = se.begin(); it2 != se.end(); it2++ ){

			set<int> s1 = *it1;
			set<int> s2 = *it2;

			s1.insert(s2.cbegin(), s2.cend());			
			int c = findSupport(s1);
			if(c > sup && s1.size() == size+1){
				m.emplace(s1,c);
				se1.insert(s1);
			}	

		}
	}

	if(se1.size() > 0)
		fun(se1,size+1);

}

int main(){

	string str="";
	cin >> sup;

	infile.open("chess.dat");
int count=0; 
	while(!cin.eof()){
		count++;
		set<int> s;
		getline(cin,str);
		stringstream stream(str);
		while(1) {
			int n;
			stream >> n;
			if(!stream)
				break;

			s.insert(n);

			set<int> si;
			si.insert(n);
			if(m.find(si) == m.end()){
				indepen.insert(si);
				m.emplace(si,1);
			}else{
			int x = m[si];
				x += 1;
				m[si] = x; 
			}

		}
		l.push_back(s);
	}
	sup = (sup*count)/100;
	fun(indepen,1);

	infile.close();

	return 0;
}
