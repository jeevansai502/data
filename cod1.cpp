#include<bits/stdc++.h>
using namespace std;
int trie[1000][30];
int main()
{
	int keys;
	printf("Enter number of keywords\n");
	scanf("%d",&keys);
	printf("Enter keywords:\n");
	int siz=1;
	for(int i=0;i<1000;i++)
	{
		for(int j=0;j<26;j++)
		{
			trie[i][j]=-1;
		}
	}
	for(int i=0;i<keys;i++)
	{
		char ch;
		int ptr=0;
		char str[20];
		scanf("%s",str);
		int n=strlen(str);
		for(int j=0;j<n;j++)
		{
			ch=str[j];
			if(trie[ptr][ch-'a']==-1)
			{
				trie[ptr][ch-'a']=siz;
				ptr=siz;
				siz++;	
				if(j==n-1)
				{
					trie[ptr][27]=1;
				}			
			}
			else
			{
				ptr=trie[ptr][ch-'a'];
			}
		}		
	}
	printf("\n");
	while(1)
	{
		printf("Enter a String\n");
		char ch,str[20];
		int ptr=0;
		bool flag=true;
		scanf("%s",str);
		int n=strlen(str);
		for(int i=0;i<n;i++)
		{
			ch=str[i];
			if(trie[ptr][ch-'a']!=-1)
			{
				ptr=trie[ptr][ch-'a'];
			}
			else
			{
				printf("Not a Keyword\n");
				flag=false;
				break;
			}
		}
		if(trie[ptr][27]!=1)
		{
			printf("Not a Keyword\n");			
			flag=false;
		}
		if(flag)
		{
			printf("It is a Keyword\n");
		}
		printf("\n");
	}

	return 0;
}
