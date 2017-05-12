#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netinet/in.h>
#include <sys/select.h>

#define ERROR -1
#define BUFFER 1024

int main(int argc, char *argv[]){

	struct sockaddr_in server;
	struct sockaddr_in client;
	int sock,data_len,size,new;
	char data[BUFFER + 5];
	fd_set rfds;

	client.sin_family = AF_INET;
	inet_pton(AF_INET,argv[1],&client.sin_addr.s_addr);
	client.sin_port = htons(atoi(argv[2]));
	bzero(&client.sin_zero,0);

	size = sizeof(struct sockaddr_in);

	if((sock = socket(AF_INET,SOCK_STREAM,0)) == ERROR){
		perror("socket");
		exit(-1);
	}

	if(bind(sock, (struct sockaddr *)&client,size) == ERROR){
		perror("bind");
		exit(-1);	
	}

	server.sin_family = AF_INET;
	inet_pton(AF_INET,argv[3],&server.sin_addr.s_addr);
	server.sin_port = htons(atoi(argv[4]));
	bzero(&server.sin_zero,0);

	if(( connect(sock ,(struct sockaddr *)&server , size)) == ERROR){
		perror("connect");
		exit(-1);
	}
                printf("connection established\n");


	FD_ZERO(&rfds);
	FD_SET(0,&rfds);
	FD_SET(sock,&rfds);

	while(1){
printf("a");
		int retval;

		if((retval = select(sock+1 , &rfds , NULL ,NULL, NULL)) == ERROR){
			perror("select");
			exit(-1);
		}

		if(retval > 0){
			if(FD_ISSET(0 , &rfds)){

				data_len = recv(0,data,BUFFER,0);
				if(data_len){
					data[data_len] = '\0';
				}
				send(sock,data,strlen(data),0);
			}
			if(FD_ISSET(new , &rfds)){
				data_len = recv(sock,data,BUFFER,0);
				if(data_len){
					data[data_len] = '\0';
					printf("%s",data);
				}


			}
		}

	}

}
