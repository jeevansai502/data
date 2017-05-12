#include<stdio.h>
#include<stdlib.h>
struct node
{
	int i;
	struct node *next;
};

void main()
{

	struct node *first;
	struct node *last;

	struct node *temp;
	int ch,user,add,cnt=0,t=0;
	struct node *p;


	printf("\n\t 1.CREATION");
	printf("\n\t 2.INSERT AT STARTING");
	printf("\n\t 3.INSERT AT MIDDLE(USER'S CHOICE)");
	printf("\n\t 4.INSERT AT END");
	printf("\n\t 5.DELETE 1ST NODE");
	printf("\n\t 6.DELETE LAST NODE");
	printf("\n\t 7.DELETE MIDDLE NODE(USER'S CHOICE)");
	printf("\n\t 8.DISPLAY");
	printf("\n\t 10.EXIT");
	scanf("%d",&user);

	while(user!=10)
	{
		if(user==1)
		{
			printf("\n\t ENTER DATA  ::: ");
			first=(struct node*)malloc(sizeof(struct node));
			scanf("%d",&ch);

			first->i=ch;
			first->next=0;
			p=first;
			cnt=1;
		}
		if(user==2)
		{
			p=(struct node*)malloc(sizeof(struct node));
			printf("\n\t ENTER DATA FOR 1ST NODE");
			scanf("%d",&p->i);
			p->next=first;
			first=p;
			cnt++;
		}
		if(user==4)
		{
			p=(struct node*)malloc(sizeof(struct node));
			printf("\n\t ENTER DATA FOR LAST NODE");
			scanf("%d",&p->i);
			temp=first;
			while(temp->next!=0)
			{
				temp=temp->next;
			}
			temp->next=p;
			p->next=0;
			cnt++;
		}
		if(user==3)
		{
			printf("\n\t ENTER ANY ADDRESS BETWEEN 1 and %d",cnt);
			scanf("%d",&add);

			t=1;
			p=first;
			while(t!=add)
			{
				p=p->next;
				t++;
			}
			temp=(struct node*)malloc(sizeof(struct node));
			printf("\n\t ENTER DATA FOR NODE");
			scanf("%d",&temp->i);
			temp->next=p->next;
			p->next=temp;
			cnt++;
		}
		if(user==5)
		{
			p=first;
			first=p->next;
			free(p);
		}
		if(user==6)
		{
			p=first;
			while(p->next->next!=0)
			{
				p=p->next;
			}
			p->next=0;
			free(p->next->next);
		}
		if(user==8)
		{
			p=first;
			while(p!=0)
			{
				printf("\n\t %d",p->i);
				p=p->next;
			}
		}
		if(user==7)
		{
			printf("\n\t ENTER ANY ADDRESS BETWEEN 1 and %d",cnt);
			scanf("%d",&add);

			t=1;
			p=first;
			while(t<add-1)
			{
				p=p->next;
				t++;
			}
			temp=p->next;
			p->next=temp->next;
			free(temp);
			cnt--;

		}
		printf("\n\t 1.CREATION");
		printf("\n\t 2.INSERT AT STARTING");
		printf("\n\t 3.INSERT AT MIDDLE(USER'S CHOICE)");
		printf("\n\t 4.INSERT AT END");
		printf("\n\t 5.DELETE 1ST NODE");
		printf("\n\t 6.DELETE LAST NODE");
		printf("\n\t 7.DELETE MIDDLE NODE(USER'S CHOICE)");
		printf("\n\t 8.DISPLAY");
		printf("\n\t 10.EXIT");
		scanf("%d",&user);
	}
}
