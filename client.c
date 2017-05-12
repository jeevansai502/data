#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<string.h>

int main(int argc,char *argv[]){

	int sockfd,new_fd,serv_size;
	struct sockaddr_in serv_addr,cli_addr;
	char buffer[256];

	sockfd = socket(AF_INET,SOCK_STREAM,0);

	if(sockfd == -1){
		perror("Error socket not created");
		exit(1);	
	}

	serv_size = sizeof(serv_addr);

	bzero((void *)&serv_addr,serv_size);

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serv_addr.sin_port = htons(5000);

	while(1){
		if(connect(sockfd,(struct sockaddr *) &serv_addr,serv_size) < 0){
			perror("connection error");
			exit(1);
		}

		char a[100];
		//		while(scanf("%s",a) != EOF){
		//			write(new_fd,a,sizeof(a));	
		//		}

		bzero(buffer,256);
		int n;
		while(n > 0){
			n = read(sockfd,buffer,255);
			printf("%s",buffer);
		}

	}
}
