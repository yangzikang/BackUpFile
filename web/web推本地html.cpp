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
	struct sockaddr_in server, client;   //��������ַ���ͻ��˵�ַ
	int duankouhao = 80;//�˿ں�

	cout << "�������ȴ�����" << endl;

	//��һ��������Э��ջ
	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
	{
		cout<<"Failed to load Winsock." << endl;
		return -1;
	}

	//�ڶ��������������׽��֣����ڼ����ͻ�����
	sListen = socket(AF_INET, SOCK_STREAM, 0);//ipv4���������ӵ�
	if (sListen == INVALID_SOCKET)//INVALID_SOCKET�� socket����ʧ�ܵķ���ֵ
	{
		cout<<"socket() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}
	//������������ַ��IP+�˿ں�
	server.sin_family = AF_INET;
	server.sin_port = htons(duankouhao);               //�������˿ں���home����to net��ʽshort
	server.sin_addr.s_addr = htonl(INADDR_ANY);   //������IP��ַ��Ĭ��ʹ�ñ���IP

	//���������󶨼����׽��ֺͷ�������ַ
	if (bind(sListen, (LPSOCKADDR)&server, sizeof(server)) == SOCKET_ERROR)
	{
		cout<<"bind() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}

	// ���Ĳ���ͨ�������׽��ֽ��м���
	if (listen(sListen, 15) == SOCKET_ERROR)
	{
		cout<<"listen() Failed:"<< WSAGetLastError()<<endl;
		return -1;
	}

	while (1)  //ѭ���ȴ��ͻ�������
	{
		//���岽�����ܿͻ��˵��������󣬷�����ÿͻ������������׽���
		int longer = sizeof(client);
		sAccept = accept(sListen, (struct sockaddr*)&client, &longer);
		if (sAccept == INVALID_SOCKET)
		{
			cout<<"accept() Failed:"<<WSAGetLastError()<<endl;
			break;
		}
		//���߲��������߳̽������������
		DWORD ThreadID;
		CreateThread(NULL, 0, SimpleHTTPServer, (LPVOID)sAccept, 0, &ThreadID);
	
	}
}
DWORD WINAPI SimpleHTTPServer(LPVOID lparam)
{
	SOCKET sAccept = (SOCKET)(LPVOID)lparam;
	char recv_buf[1024];
	char method[128];//����
	char url[128];
	char path[_MAX_PATH];//·��
	
	

	// ������0��ÿ�β���ǰ��Ҫ�ǵ��建�棬����ϰ�ߣ�
	// ����տ��ܳ��ֵ�����������롢��̨�������뻹������ͬ
	// ԭ�򣺲���ջ�������� '\0'�ַ�Ϊֹ������ǰ��Ĳ���'\0' Ҳ��һ�����
	memset(recv_buf, 0, sizeof(recv_buf));
	if (recv(sAccept, recv_buf, sizeof(recv_buf), 0) == SOCKET_ERROR)   //���մ���
	{
		printf("recv() Failed:%d\n", WSAGetLastError());
		return -1;
	}
	else
		printf("recv data from client:%s\n", recv_buf); //���ճɹ�����ӡ������

	cout<<endl << "���Ĵ�ӡ���"<<endl;

	int i=0;
	int j=0;
//��ȡ �����ĵ� ����like get
	while (!(' ' == recv_buf[j]) && (i < sizeof(method)-1)){
		method[i] = recv_buf[j];
		i++; j++;
	}
	method[i] = '\0';
	printf("method: %s\n", method);

	if (stricmp(method, "GET")&&stricmp(method,"HEAD"))
	{
		closesocket(sAccept); //�ͷ������׽��֣�������ÿͻ���ͨ��
		printf("not get or head method.\nclose ok.\n");
		printf("***********************\n\n\n\n");
		return -1;
	}

	else if (stricmp(method, "POST")==0){
		cout << "�����֧��" << endl;

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

		
			// �������url·��ת��Ϊ����·��
			_getcwd(path, _MAX_PATH);
			strcat(path, url);//�ַ�������
			printf("path: %s\n", path);
			// �򿪱���·���µ��ļ������紫������r�ı���ʽ�򿪻����
			FILE *resource = fopen(path, "rb");

			// û�и��ļ�����һ���򵥵�404-file not found��htmlҳ�棬���Ͽ���������
			if (resource == NULL)
			{
				file_not_found(sAccept);//��Ӧ��ͷ

				if (stricmp(method, "GET") == 0)//��Ӧ����

					send_not_found(sAccept);



				closesocket(sAccept); //�ͷ������׽��֣�������ÿͻ���ͨ��
				printf("file not found.\nclose ok.\n");
				printf("***********************\n\n\n\n");
				return -1;
			}

			// ����ļ����ȣ��ǵ������ļ�ָ�뵽�ļ�ͷ
			fseek(resource, 0, SEEK_SET);
			fseek(resource, 0, SEEK_END);
			long flen = ftell(resource); //file length
			printf("file length: %ld\n", flen);
			fseek(resource, 0, SEEK_SET);

			// ����200 OK HEAD
			file_ok(sAccept, flen);

			// �����GET���������������Դ
			if (0 == stricmp(method, "GET"))
			{
				if (0 == send_file(sAccept, resource))
					printf("file send ok.\n");
				else
					printf("file send fail.\n");
			}
			fclose(resource);

			closesocket(sAccept); //�ͷ������׽��֣�������ÿͻ���ͨ��
			printf("close ok.\n");
			printf("***********************\n\n\n\n");
			return 0;
		

}

// ����404 file_not_found��ͷ
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

// ����200 ok��ͷ
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

// �����Զ����file_not_foundҳ��
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

// �����������Դ
int send_file(SOCKET sAccept, FILE *resource)
{
	char send_buf[1024];
	while (1)
	{
		memset(send_buf, 0, sizeof(send_buf));       //������0
		
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