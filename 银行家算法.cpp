#include <string.h>
#include <iostream.h>
#define M 5
#define N 3
#define FALSE 0
#define TRUE 1

int MAX[M][N]={{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
int AVAILABLE[N]={3,3,2};
int ALLOCATION[M][N]={{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
int NEED[M][N]={{7,4,3},{1,2,2},{6,0,0},{0,1,1},{4,3,1}};

int Request[N]={0,0,0};

void showdata();
void changdata(int);
void rstordata(int);
int chkerr(int);

void main(){
	int i = 0;
	int j = 0;
	char flag='Y';
	showdata();
	while(flag=='Y'||flag=='y'){
		i=-1;
		while(i<0||i>=M){
			cout<<"��������������Դ�Ľ��̺ţ���0��"<<M-1<<"�����������룡����";
			cin>>i;
			if(i<0||i>=M)cout<<"����Ľ��̺Ų����ڣ��������룡"<<endl;
		}
		cout<<"���������"<<i<<"�������Դ��"<<endl;
		for(j=0;j<N;j++){
			cout<<"��Դ"<<j<<":";
			cin>>Request[j];
			if(Request[j]>NEED[i][j]){
				cout<<"����"<<i<<"�������Դ�����ڽ���"<<i<<"����Ҫ"<<j<<"����Դ����Դ��";
				cout<<"���벻��������������ѡ��"<<endl<<endl;
				flag='N';
				break;
			}
			else{
				if(Request[j]>AVAILABLE[j]){
					cout<<"����"<<i<<"�������Դ������ϵͳ����"<<j<<"����Դ����Դ����";
					cout<<"���벻��������������ѡ��"<<endl<<endl;
					flag='N';
					break;
				}
			}
		}

		if(flag == 'Y'||flag =='y'){
			changdata(i);
			if(chkerr(0)){
				rstordata(i);
				showdata();
			}
			else{
				showdata();
			}
		}
		else{
			showdata();
		}

		cout<<endl;
		cout<< " �Ƿ�������м��㷨��ʾ����'Y'��������'N'�˳�:";
		cin>>flag;
	}
} 

void showdata(){
	int i,j;
	cout<<  "  ϵͳ���õ���Դ��Ϊ��"<<endl<<endl;
	cout<<"       ";
	for(j=0;j<N;j++){
		cout<<"��Դ"<<j<<":"<<AVAILABLE[j];
	}
	cout<<endl;
	cout<<endl;
	cout<< "��������Դ�����������"<<endl<<endl;

	for(i=0;i<M;i++){
		cout<<"����"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"��Դ"<<j<<":"<<MAX[i][j];
		}
		cout<<endl;
	}
	cout<<endl;

	cout<<"  �����̻���Ҫ����Դ��:"<<endl<<endl;
	for(i=0;i<M;i++){
		cout<<"����"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"��Դ"<<j<<":"<<NEED[i][j];
		}
		cout<<endl;
	}
	cout<<endl;

	cout<<" �������Ѿ��õ�����Դ����"<<endl<<endl;
	for(i=0;i<M;i++){
		cout<<"����"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"��Դ"<<j<<":"<<ALLOCATION[i][j];
		}
		cout<<endl;
	}
	cout<<endl;
}


void changdata(int k){
	int j;
	for(j=0;j<N;j++){
		AVAILABLE[j]=AVAILABLE[j]-Request[j];
		ALLOCATION[k][j]=ALLOCATION[k][j]+Request[j];
		NEED[k][j]=NEED[k][j]-Request[j];
	}
}

void rstordata(int k){
	int j;
	for(j=0;j<N;j++){
		AVAILABLE[j]=AVAILABLE[j]+Request[j];
		ALLOCATION[k][j]=ALLOCATION[k][j]-Request[j];
		NEED[k][j]=NEED[k][j]+Request[j];
	}
}

int chkerr(int s)
{
	int WORK[N],FINISH[M],temp[M];
	int i,j,m,k=0;
	for(i=0;i<M;i++)FINISH[i]=FALSE;
	for(j=0;j<N;j++)
	WORK[j]=AVAILABLE[j];
	i=s;
	while(i<M)
	{ 
	  	if(FINISH[i]==FALSE && NEED[i][0]<=WORK[0] && NEED[i][1]<=WORK[1] && NEED[i][2]<=WORK[2])
	  	{
	  		for(m=0;m<3;m++)
	        	WORK[m]=WORK[m]+ALLOCATION[i][m];
		   	FINISH[i]=TRUE;
		   	temp[k]=i;
		   	k++;
		   	i=0;
	  	}
	  	else
	  	{i++;}
	}
	
	for(i=0;i<M;i++)
	{  
		if(FINISH[i]==FALSE)
	  	{
		   cout<<endl;
		   cout<<"  ϵͳ����ȫ!!! ������Դ���벻�ɹ�!!!"<<endl;
		   cout<<endl;
		   return 1;
	  	}
	}
	
	cout<<endl;
	cout<<"  ����ȫ�Լ�飬ϵͳ��ȫ�����η���ɹ���"<<endl;
	cout<<endl;
	cout<<"  ���ΰ�ȫ���У�";
	for(i=0;i<M;i++)cout<<"����"<<temp[i]<<"->";
	cout<<endl<<endl;;
	return 0;
}