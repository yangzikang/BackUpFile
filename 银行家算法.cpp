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
			cout<<"请输入需申请资源的进程号（从0到"<<M-1<<"，否则重输入！）：";
			cin>>i;
			if(i<0||i>=M)cout<<"输入的进程号不存在，重新输入！"<<endl;
		}
		cout<<"请输入进程"<<i<<"申请的资源数"<<endl;
		for(j=0;j<N;j++){
			cout<<"资源"<<j<<":";
			cin>>Request[j];
			if(Request[j]>NEED[i][j]){
				cout<<"进程"<<i<<"申请的资源数大于进程"<<i<<"还需要"<<j<<"类资源的资源量";
				cout<<"申请不合理，出错！请重新选择！"<<endl<<endl;
				flag='N';
				break;
			}
			else{
				if(Request[j]>AVAILABLE[j]){
					cout<<"进程"<<i<<"申请的资源数大于系统可用"<<j<<"类资源的资源量！";
					cout<<"申请不合理，出错！请重新选择！"<<endl<<endl;
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
		cout<< " 是否继续银行家算法演示，按'Y'继续，按'N'退出:";
		cin>>flag;
	}
} 

void showdata(){
	int i,j;
	cout<<  "  系统可用的资源数为："<<endl<<endl;
	cout<<"       ";
	for(j=0;j<N;j++){
		cout<<"资源"<<j<<":"<<AVAILABLE[j];
	}
	cout<<endl;
	cout<<endl;
	cout<< "各进程资源最大需求量："<<endl<<endl;

	for(i=0;i<M;i++){
		cout<<"进程"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"资源"<<j<<":"<<MAX[i][j];
		}
		cout<<endl;
	}
	cout<<endl;

	cout<<"  各进程还需要的资源量:"<<endl<<endl;
	for(i=0;i<M;i++){
		cout<<"进程"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"资源"<<j<<":"<<NEED[i][j];
		}
		cout<<endl;
	}
	cout<<endl;

	cout<<" 各进程已经得到的资源量："<<endl<<endl;
	for(i=0;i<M;i++){
		cout<<"进程"<<i<<":";
		for(j=0;j<N;j++){
			cout<<"资源"<<j<<":"<<ALLOCATION[i][j];
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
		   cout<<"  系统不安全!!! 本次资源申请不成功!!!"<<endl;
		   cout<<endl;
		   return 1;
	  	}
	}
	
	cout<<endl;
	cout<<"  经安全性检查，系统安全，本次分配成功。"<<endl;
	cout<<endl;
	cout<<"  本次安全序列：";
	for(i=0;i<M;i++)cout<<"进程"<<temp[i]<<"->";
	cout<<endl<<endl;;
	return 0;
}