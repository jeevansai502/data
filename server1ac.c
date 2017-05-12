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

	int new,sock,data_len;
	int sockaddr_len = sizeof(struct sockaddr_in);
	char data[BUFFER+5];
	fd_set rfds;

	if((sock = socket(AF_INET,SOCK_STREAM,0)) == ERROR){

		perror("server socket create");
		exit(-1);
	}

	server.sin_family = AF_INET;
	server.sin_port = htons(atoi(argv[2]));
	inet_pton(AF_INET,argv[1],&server.sin_addr.s_addr);
	bzero(&server.sin_zero,0);

	if(bind(sock, (struct sockaddr *)&server,sockaddr_len) == ERROR){

		perror("bind");
		exit(-1);
	}
	if(listen(sock, SOMAXCONN) == ERROR){

		perror("listen");
		exit(-1);
	}

	while(1){

		if((new = accept(sock,(struct sockaddr *)&client,&sockaddr_len)) == ERROR){
			perror("accept");
			exit(-1);
		}
                        printf("client connected\n");

		FD_ZERO(&rfds);
		FD_SET(0,&rfds);
		FD_SET(new,&rfds);

		while(1){
printf("a");
			int retval,data_len;
			if((retval = select(new+1 , &rfds , NULL , NULL , NULL)) == ERROR){
				perror("select");
				exit(-1);
			}		

			if(retval > 0){
				if(FD_ISSET(0 , &rfds)){

					data_len = recv(0,data,BUFFER,0);	
					if(data_len){
						data[data_len] = '\0';
					}
					send(new,data,strlen(data),0);
				}



				if(FD_ISSET(new , &rfds)){
					data_len = recv(new,data,BUFFER,0);
					if(data_len){
						data[data_len] = '\0';
						printf("%s",data);
					}


				}
			}	
		}
	}
}
