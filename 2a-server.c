#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netinet/in.h>

#define ERROR -1
#define MAX_CLIENTS 10
#define MAX_DATA 3

int main(int argc, char *argv[]){

	struct sockaddr_in server;
	struct sockaddr_in client;

	int sock;
	int new1,new2;
	int sockaddr_len = sizeof(struct sockaddr_in);
	int data_len;
	char data[MAX_DATA];

	if((sock = socket(AF_INET,SOCK_STREAM,0)) == ERROR){

		perror("server socket");
		exit(-1);

	}

	server.sin_family = AF_INET;
	server.sin_port = htons(atoi(argv[2]));
	inet_aton(argv[1],&server.sin_addr.s_addr);
	bzero(&server.sin_zero,0);

	if(bind(sock, (struct sockaddr *)&server,sockaddr_len) == ERROR){

		perror("bind");
		exit(-1);
	}

	if(listen(sock, MAX_CLIENTS) == ERROR){

		perror("listen");
		exit(-1);
	}


		if((new1 = accept(sock,(struct sockaddr *)&client,&sockaddr_len)) == ERROR){ 

			perror("accept");
			exit(-1);

		}
		
	//	printf("New client connected from port no %d IP %s\n",ntohs(client.sin_port),inet_ntoa(client.sin_addr));
		

		if((new2 = accept(sock,(struct sockaddr *)&client,&sockaddr_len)) == ERROR){ 

			perror("accept");
			exit(-1);

		}
		
		//printf("New client connected from port no %d IP %s\n",ntohs(client.sin_port),inet_ntoa(client.sin_addr));
		
		data_len = 1;

			data_len = recv(new1,(void *)data,MAX_DATA,0);

			data[2] = (char)((int)data[2]-1);
			if(data_len){

				send(new2, "a", 1,0);
				printf("Sent message: %s", data);

			}

		

		//printf("Client disconnected\n");
		close(new1);
		close(new2);

	
}
