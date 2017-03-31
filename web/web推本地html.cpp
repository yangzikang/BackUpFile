#include <iostream>
#include <math.h>
#include <winsock2.h>
#include <string>
#include<direct.h>
#pragma comment(lib,"Ws2_32.lib")
#define SERVER "Server: csr_http1.1\r\n"
using namespace std;

char getback[2];

DWORD WINAPI SimpleHTTPServer(LPVOID lparam);
int file_not_found(SOCKET sAccept);
int file_ok(SOCKET sAccept, long flen);
int send_not_found(SOCKET sAccept);
int send_file(SOCKET sAccept, FILE *resource);
int send_theAnswer(SOCKET sAccept);


int main(){
	SOCKET sListen, sAccept;
	WSADATA wsaData;
	struct sockaddr_in server, client;   //服务器地址，客户端地址
	int duankouhao = 80;//端口号

	cout << "服务器等待链接" << endl;

	//第一步：加载协议栈
	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
	{
		cout<<"Failed to load Winsock." << endl;
		return -1;
	}

	//第二步：创建监听套接字，用于监听客户请求
	sListen = socket(AF_INET, SOCK_STREAM, 0);//ipv4，面向链接的
	if (sListen == INVALID_SOCKET)//INVALID_SOCKET是 socket函数失败的返回值
	{
		cout<<"socket() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}
	//创建服务器地址：IP+端口号
	server.sin_family = AF_INET;
	server.sin_port = htons(duankouhao);               //服务器端口号由home主机to net形式short
	server.sin_addr.s_addr = htonl(INADDR_ANY);   //服务器IP地址，默认使用本机IP

	//第三步：绑定监听套接字和服务器地址
	if (bind(sListen, (LPSOCKADDR)&server, sizeof(server)) == SOCKET_ERROR)
	{
		cout<<"bind() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}

	// 第四步：通过监听套接字进行监听
	if (listen(sListen, 15) == SOCKET_ERROR)
	{
		cout<<"listen() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}

	while (1)  //循环等待客户的请求
	{
		//第五步：接受客户端的连接请求，返回与该客户建立的连接套接字
		int longer = sizeof(client);
		sAccept = accept(sListen, (struct sockaddr*)&client, &longer);
		if (sAccept == INVALID_SOCKET)
		{
			cout<<"accept() Failed:"<<WSAGetLastError()<<endl;
			break;
		}
		//第七步，创建线程接受浏览器请求
		DWORD ThreadID;
		CreateThread(NULL, 0, SimpleHTTPServer, (LPVOID)sAccept, 0, &ThreadID);
	
	}
}
DWORD WINAPI SimpleHTTPServer(LPVOID lparam)
{
	SOCKET sAccept = (SOCKET)(LPVOID)lparam;
	char recv_buf[1024];
	char method[128];//方法
	char url[128];
	char path[_MAX_PATH];//路径
	
	

	// 缓存清0，每次操作前都要记得清缓存，养成习惯；
	// 不清空可能出现的现象：输出乱码、换台机器乱码还各不相同
	// 原因：不清空会输出遇到 '\0'字符为止，所以前面的不是'\0' 也会一起输出
	memset(recv_buf, 0, sizeof(recv_buf));
	if (recv(sAccept, recv_buf, sizeof(recv_buf), 0) == SOCKET_ERROR)   //接收错误
	{
		printf("recv() Failed:%d\n", WSAGetLastError());
		return -1;
	}
	else
		printf("recv data from client:%s\n", recv_buf); //接收成功，打印请求报文

	cout<<endl << "报文打印完毕"<<endl;

	int i=0;
	int j=0;
//获取 请求报文的 方法like get
	while (!(' ' == recv_buf[j]) && (i < sizeof(method)-1)){
		method[i] = recv_buf[j];
		i++; j++;
	}
	method[i] = '\0';
	printf("method: %s\n", method);

	if (stricmp(method, "GET")&&stricmp(method,"HEAD"))
	{
		closesocket(sAccept); //释放连接套接字，结束与该客户的通信
		printf("not get or head method.\nclose ok.\n");
		printf("***********************\n\n\n\n");
		return -1;
	}

	else if (stricmp(method, "POST")==0){
		cout << "这个不支持" << endl;

	}
	
	i = 0;
	while ((' ' == recv_buf[j]) && (j < sizeof(recv_buf)))
		j++;
	while (!(' ' == recv_buf[j]) && (i < sizeof(recv_buf)-1) && (j < sizeof(recv_buf)))
	{
		if (recv_buf[j] == '/')
			url[i] = '\\';
		else if (recv_buf[j] == ' ')
			break;
		
			
		else if (recv_buf[j] == '+'){

			j++;
			int x = (int)recv_buf[j];

			x = x + 1;

			getback[0] = (char)x;
			getback[1] = '\0';
			send_theAnswer(sAccept);
			closesocket(sAccept);
			
		}
		else
			url[i] = recv_buf[j];
		i++; j++;
	}
	
		url[i] = '\0';
		printf("url: %s\n", url);

		
			// 将请求的url路径转换为本地路径
			_getcwd(path, _MAX_PATH);
			strcat(path, url);//字符串连接
			printf("path: %s\n", path);
			// 打开本地路径下的文件，网络传输中用r文本方式打开会出错
			FILE *resource = fopen(path, "rb");

			// 没有该文件则发送一个简单的404-file not found的html页面，并断开本次连接
			if (resource == NULL)
			{
				file_not_found(sAccept);//响应报头

				if (stricmp(method, "GET") == 0)//响应正文

					send_not_found(sAccept);



				closesocket(sAccept); //释放连接套接字，结束与该客户的通信
				printf("file not found.\nclose ok.\n");
				printf("***********************\n\n\n\n");
				return -1;
			}

			// 求出文件长度，记得重置文件指针到文件头
			fseek(resource, 0, SEEK_SET);
			fseek(resource, 0, SEEK_END);
			long flen = ftell(resource); //file length
			printf("file length: %ld\n", flen);
			fseek(resource, 0, SEEK_SET);

			// 发送200 OK HEAD
			file_ok(sAccept, flen);

			// 如果是GET方法则发送请求的资源
			if (0 == stricmp(method, "GET"))
			{
				if (0 == send_file(sAccept, resource))
					printf("file send ok.\n");
				else
					printf("file send fail.\n");
			}
			fclose(resource);

			closesocket(sAccept); //释放连接套接字，结束与该客户的通信
			printf("close ok.\n");
			printf("***********************\n\n\n\n");
			return 0;
		

}

// 发送404 file_not_found报头
int file_not_found(SOCKET sAccept)
{
	char send_buf[128];
	
	sprintf(send_buf, "HTTP/1.1 404 NOT FOUND\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "Connection: keep-alive\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, SERVER);
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "Content-Type: text/html\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	return 0;
}

// 发送200 ok报头
int file_ok(SOCKET sAccept, long flen)
{
	char send_buf[128];
	
	sprintf(send_buf, "HTTP/1.1 200 OK\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "Connection: keep-alive\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, SERVER);
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "Content-Length: %ld\r\n", flen);
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "Content-Type: text/html\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	return 0;
}

// 发送自定义的file_not_found页面
int send_not_found(SOCKET sAccept)
{
	char send_buf[128];
	sprintf(send_buf, "<HTML><TITLE>Not goooood</TITLE>\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "<BODY><h1 align='center'>404</h1><br/><h1 align='center'>file is break.</h1>\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "</BODY></HTML>\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	return 0;
}

// 发送请求的资源
int send_file(SOCKET sAccept, FILE *resource)
{
	char send_buf[1024];
	while (1)
	{
		memset(send_buf, 0, sizeof(send_buf));       //缓存清0
		
		fgets(send_buf, sizeof(send_buf), resource);
		printf("send_buf: %s", send_buf);
		if (SOCKET_ERROR == send(sAccept, send_buf, strlen(send_buf), 0))
		{
	 		printf("send() Failed:%d\n", WSAGetLastError());
			return -1;
		}

		if (feof(resource))
			break;
			
	}
	
	
	return 0;
}

int send_theAnswer(SOCKET sAccept){
	char send_buf[128];
	sprintf(send_buf, "<HTML><TITLE>Not goooood</TITLE>\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	sprintf(send_buf, "<BODY><h1 align='center'>");
	send(sAccept, send_buf, strlen(send_buf), 0);
	send(sAccept, getback, strlen(getback), 0);
	sprintf(send_buf, "</h1></BODY></HTML>\r\n");
	send(sAccept, send_buf, strlen(send_buf), 0);
	return 0;
}