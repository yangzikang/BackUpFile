package imServer;

import java.io.*;
import java.util.*;

public class yonghu {
	private String xingming;
	private String zhanghao;
	private String mima;
	public List<String>haoyou = new ArrayList<String>();
	
	public yonghu(){
		xingming=null;
		zhanghao=null;
		mima=null;
	}
	
	public void setXingming(String xingming){
		this.xingming=xingming;
	}
	public void setZhanghao(String zhanghao){
		this.zhanghao=zhanghao;
	}
	public void setMima(String mima){
		this.mima=mima;
	}
	public String getXingming(){
		return xingming;
	}
	public String getZhanghao(){
		return zhanghao;
	}
	public String getMima(){
		return mima;
	}

}
