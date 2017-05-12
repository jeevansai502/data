#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netinet/in.h>

#define ERROR -1
#define BUFFER 1024

int main(int argc, char *argv[]){

	struct sockaddr_in remote_server,client;
	int sock,len;

	char input;
	char output;


	client.sin_family = AF_INET;
	client.sin_port = htons(9170);
	client.sin_addr.s_addr = INADDR_ANY;
	bzero(&client.sin_zero,0);

	if((sock = socket(AF_INET,SOCK_STREAM,0)) == ERROR){
		perror("socket");
		exit(-1);
	}

	int size = sizeof(struct sockaddr_in);

	if(bind(sock, (struct sockaddr *)&client,size) == ERROR){

		perror("bind");
		exit(-1);
	}

	remote_server.sin_family = AF_INET;
	remote_server.sin_port = htons(atoi(argv[2]));
	remote_server.sin_addr.s_addr = inet_addr(argv[1]); 
	bzero(&remote_server.sin_zero , 0);

	if((connect(sock ,(struct sockaddr *)&remote_server , sizeof(struct sockaddr_in))) == ERROR){

		perror("connect");
		exit(-1);

	}

fgets((char *)&input,2,stdin);		
send(sock, input, sizeof(input), 0);


	close(sock);

}



