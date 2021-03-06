#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netinet/in.h>

#define ERROR -1
#define MAX_CLIENTS 2
#define MAX_DATA 1024

int main(int argc, char *argv[]){

	struct sockaddr_in server;
	struct sockaddr_in client;

	int sock;
	int new;
	int sockaddr_len = sizeof(struct sockaddr_in);
	int data_len;
	char data[MAX_DATA+1];

	if((sock = socket(AF_INET,SOCK_STREAM,0)) == ERROR){

		perror("server socket");
		exit(-1);

	}

	server.sin_family = AF_INET;
	server.sin_port = htons(atoi(argv[2]));
	server.sin_addr.s_addr = htons(atoi(argv[1]));
	printf("%d",atoi(argv[1]));
	bzero(&server.sin_zero,0);

	if(bind(sock, (struct sockaddr *)&server,sockaddr_len) == ERROR){

		perror("bind");
		exit(-1);
	}

	if(listen(sock, MAX_CLIENTS) == ERROR){

		perror("listen");
		exit(-1);
	}

	while(1){

		if((new = accept(sock,(struct sockaddr *)&client,&sockaddr_len)) == ERROR){ 

			perror("accept");
			exit(-1);
		}

		data_len = 1;

		while(data_len){

			data_len = recv(new,data,MAX_DATA,0);

			if(data_len){
				data[data_len+1] = '\0';
				printf("Sent message: %s", data);
			}	

			if(fgets(data , MAX_DATA,stdin) != NULL){
				send(new, data, strlen(data),0);
			}

		}

		printf("Client disconnected\n");
		close(new);

	}
}
