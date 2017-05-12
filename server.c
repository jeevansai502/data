#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<string.h>

int main(int argc,char *argv[]){

	int sockfd,server_size,client_size,new_sockfd;
	struct sockaddr_in serv_addr,client_addr;	
	char buffer[256];

	sockfd = socket(AF_INET,SOCK_STREAM,0);
	if(sockfd == -1){
		perror("Error socket not created");
		exit(1);	
	}

	server_size = sizeof(serv_addr);

	bzero((void *)&serv_addr,server_size);

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serv_addr.sin_port = htons(5000);	

	if (bind(sockfd, (struct sockaddr *) &serv_addr,server_size )  < 0) {
		perror("socket not binded to address");
		exit(1);
	}

	listen(sockfd,10);
	client_size = sizeof(client_addr);

	while(1){
		new_sockfd = accept(sockfd,(struct sockaddr *)&client_addr,&client_size);

		printf("Client connected");
		if(new_sockfd < 0){
			perror("Error on accept");
			exit(1);
		}	

		bzero(buffer,256);
		int n;
		n = read(new_sockfd,buffer,255);

		if(n < 0){
			perror("Error reading from socket");
			exit(1);
		}
		printf("%s",buffer);

		char a[100];
		while(scanf("%s",a) != EOF){
			write(new_sockfd,a,sizeof(a));	
		}
}


}
