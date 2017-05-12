#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <signal.h>
#include <pthread.h>
#include <string>
#include <iostream>
using namespace std;

void error(const char *msg) 
{
    perror(msg);
    exit(EXIT_FAILURE);
}


string recv_message(int sockfd)
{
    char buffer[256];
    bzero(buffer, 256);
    ssize_t n = recv(sockfd, buffer, sizeof(buffer), 0);
    if (n <= 0) {
        error("ERROR receiving from socket");
    }
    return string(buffer);
}

void send_message(int sockfd, string msg)
{
    ssize_t n = send(sockfd, msg.c_str(), msg.length() + 1, 0);
    if (n < 0) {
        error("ERROR sending to socket");
    }
}



void *recv_message_from_server(void *sfd)
{
int filesize,temp,r=0;
FILE *fp;
	int sockfd = *(int *)sfd;
int numbytes;
char buf[1024];

	fp=fopen("test1","w");
	if ((numbytes=recv(sockfd,&filesize,sizeof(filesize),0)) == -1) 
	{
		perror("recv");
		printf("\n error  message: not recieved");
	}
	//printf("\n%d\n",filesize); 
	if ((temp=recv(sockfd,buf,1023, 0)) == -1) 
	{
		perror("recv");
		printf("\n error  message: not recieved");
	}
	//printf("\ntempout: %d\n",temp);
	//buf[temp]='\0';
	fseek(fp,0,SEEK_END);
	fputs(buf,fp);
	if(temp==filesize)
	{
		printf("completed transfer\n");
		printf("\ntempout: %d\n",temp);
	}
	else
	{
		r=r+temp;
		//printf("%d\n",r);
		while(temp)
		{
			if ((temp=recv(sockfd,buf,1023, 0)) == -1) 
			{
				perror("recv");
				printf("/n error  message: not recieved");
			}
			if(temp!=-1)
				r=r+temp;
			buf[temp]='\0';
			fseek(fp,0,SEEK_END);
			fputs(buf,fp);
			bzero(buf,1024);
			printf("%d\n",r);
			if(r==filesize)
			{
				printf("completed transfer");
				break;
			}
		}
	}
	bzero(buf,1024);
	fclose(fp);
}

void *send_message_to_server(void *sfd)
{
	int sockfd = *(int *)sfd;
	while (1) {
		string buffer;
		getline(cin, buffer);
		ssize_t n = send(sockfd, buffer.c_str(), buffer.length(), 0);
		if (n < 0) {
			error("ERROR sending to socket");
		}
	}
}


int setup(int argc, char *argv[])
{
	int sockfd, portno;
	struct sockaddr_in serv_addr;
	struct hostent *server;
	if (argc < 3) {
		error("ERROR no hostname or port provided");
	}
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) {
		error("ERROR opening socket");
	}
	portno = atoi(argv[2]);
	bzero((char *) &serv_addr, sizeof(serv_addr));
	server = gethostbyname(argv[1]);
	if (server == NULL) {
		error("ERROR no such host");
	}
	serv_addr.sin_family = AF_INET;
	bcopy((char *) server -> h_addr, (char *) &serv_addr.sin_addr.s_addr, 
			server -> h_length);

	serv_addr.sin_port = htons(portno);
	if (connect(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
		error("ERROR on connecting");
	}
	return sockfd;
}

int main(int argc, char *argv[])
{
	int sockfd = setup(argc, argv);
	string greetmsg = recv_message(sockfd);
	cout << greetmsg << endl;
	string name;
	cin >> name;
	send_message(sockfd, name);

	pthread_t rid, sid;
	pthread_create(&rid, NULL, recv_message_from_server, (void *)&sockfd);
	pthread_create(&sid, NULL, send_message_to_server, (void *)&sockfd);

	pthread_join(rid, NULL);
	pthread_join(sid, NULL);

	close(sockfd);
	return 0;
}



