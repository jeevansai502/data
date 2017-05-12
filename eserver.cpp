#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <cstring>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <pthread.h>
#include <signal.h>
#include <string>
#include <map>
#include <iostream>
#include <vector>
using namespace std;

map<string, int> nametable;
char m[] = {-1};
char buffer[256];
char *message;

void error(const char *msg) 
{
	perror(msg);
	exit(EXIT_FAILURE);
}

void send_message(int sockfd, string msg)
{
	ssize_t n = send(sockfd, msg.c_str(), msg.length() + 1, 0);
	if (n < 0) {
		perror("ERROR sending to socket");
	}
}

ssize_t n;
char* recv_message(int sockfd)
{

	bzero(buffer, 256);
	n = recv(sockfd, buffer, sizeof(buffer), 0);
	if (n <= 0) {
		cout << "ERROR receiving from socket" << endl;

		return m;
	}
	//string tempstr (buffer);
	return buffer;
}


string ask_name(int clisockfd)
{
	send_message(clisockfd, "Connected, type list for the list of clients\nEnter your name");
	char *cliname = recv_message(clisockfd);
	cout << cliname << " connected" << endl;
	return cliname;
}

int filesize;

void send_to_another_client(int clisockfd, string cliname, char *message)
{
	FILE *fp;
	int numbytes;
	char buffer[1024];
	fp=fopen((char *)message,"r");
	fseek(fp,0,SEEK_END);
	filesize=ftell(fp);
	printf("%d",filesize);     
	fseek(fp,0,SEEK_SET);
	if(send(clisockfd,&filesize,sizeof(filesize), 0)==-1)
	{
		perror("send");
		printf("\n error  message: not sent"); 
	}
	while ( fgets ( buffer, 1023,fp ) != NULL ) 
	{
		if((numbytes=send(clisockfd,buffer,strlen(buffer), 0)) == -1) 
		{
			perror("send");
			printf("\n error  message: not sent");
		}
		printf("\n%d\n",numbytes);  
		bzero(buffer,1024); 
	}
	fclose(fp);
}



void *handle_client(void *sfd)
{
	int clisockfd = *(int *)sfd;
	string cliname = ask_name(clisockfd);
	nametable[cliname] = clisockfd;
	while (1) {
		//char *message;		
		message = recv_message(clisockfd);
		cout << cliname << ": " << message << endl;
		if (message == "EOF") {
			nametable.erase(cliname);
			break;
		}else {
			FILE *fp = fopen("database.txt","r");
			char na[20];
			int count = 1;
		/*	while(fscanf(fp,"%s",na) != EOF){

				if(na == message){
					cout<<"b";	
					count = 1;
					break;
				}
			}*/
			if(count == 1){
				send_to_another_client(clisockfd, cliname, message);
				message[n]='\0';
				//printf("\n %s",filename);
				fp=fopen(message,"r");
				fseek(fp,0,SEEK_END);
				filesize=ftell(fp);
				printf("%d",filesize);     
				fseek(fp,0,SEEK_SET);

				if(send(clisockfd,&filesize,sizeof(filesize), 0)==-1)
				{
					perror("send");
					printf("\n error  message: not sent"); 
				}
*/	
		}else
				cout<<"no file";        

		}


	}



}

int setup(int argc, char *argv[])
{
	signal(SIGCHLD, SIG_IGN);
	int sockfd, portno;
	struct sockaddr_in serv_addr;
	if (argc < 2) {
		error("ERROR no port provided");
	}
	cout << "Server running on port " << argv[1] << endl;
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) {
		error("ERROR opening socket");
	}
	bzero((char *) &serv_addr, sizeof(serv_addr));
	portno = atoi(argv[1]);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);
	if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
		error("ERROR on binding");
	}
	return sockfd;
}

int main(int argc, char *argv[])
{
	int sockfd = setup(argc, argv);
	struct sockaddr_in cli_addr;
	listen(sockfd, 5);
	socklen_t clilen = sizeof(cli_addr);

	while (1) {
		int clisockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
		if (clisockfd < 0) {
			perror("ERROR on accept");
		}
		pthread_t tid;
		pthread_create(&tid, NULL, handle_client, (void *)&clisockfd);
	}

	close(sockfd);
	return 0;
}
