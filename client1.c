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

	char input[BUFFER+1];
	char output[BUFFER+1];

	client.sin_family = AF_INET;
	client.sin_port = htons(3050);
	client.sin_addr.s_addr = htons(atoi("125.0.0.6"));
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
	remote_server.sin_addr.s_addr = htonl(atoi(argv[1])); 
	bzero(&remote_server.sin_zero , 0);

	if((connect(sock ,(struct sockaddr *)&remote_server , sizeof(struct sockaddr_in))) == ERROR){

		perror("connect");
		exit(-1);

	}

	while(1){
		fgets(input, BUFFER, stdin);
		if(send(sock, input, strlen(input) , 0) == ERROR){
			perror("send");
			exit(-1);
		}

		len = recv(sock,output,BUFFER,0);

		output[len+1] = '\0';

		printf("%s\n",output);

	}

	close(sock);

}



