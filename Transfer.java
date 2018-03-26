package finalProject;

//ShuxianShen_FinalProject_Transfer

import java.util.ArrayList;

//lines order R B G BR PP Y PK O
public class Transfer extends CTAStation{
	private static boolean[] rlt=new boolean[8];
	private static boolean[] blt=new boolean[8];
	private static boolean[] glt=new boolean[8];
	private static boolean[] brlt=new boolean[8];
	private static boolean[] pplt=new boolean[8];
	private static boolean[] ylt=new boolean[8];
	private static boolean[] pklt=new boolean[8];
	private static boolean[] olt=new boolean[8];
	private static ArrayList<boolean[]> transInfo=new ArrayList<boolean[]>();
	
	public static void setTransInfo(){
		transInfo.add(rlt);
		transInfo.add(blt);
		transInfo.add(glt);
		transInfo.add(brlt);
		transInfo.add(pplt);
		transInfo.add(ylt);
		transInfo.add(pklt);
		transInfo.add(olt);
		for(CTAStation c:stations){
			for(int j=0;j<transInfo.size();j++){
				if(c.getOl()[j]){
					for(int i=0;i<8;i++){
						if(c.getOl()[i]){
							transInfo.get(j)[i]=c.getOl()[i];
						}
					}
				}
			}
		}
	}
	

	
	public static ArrayList<boolean[]> getTransInfo(){
		return transInfo;
	}
	
	public static ArrayList<Integer> generateTransfer(CTAStation start,CTAStation destination){
		ArrayList<Integer> myTrans=new ArrayList<Integer>();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(start.getOl()[i]&&destination.getOl()[j]){
					myTrans.add(i);
					return generateTransfer(i,j,myTrans);
				}
			}
		}
		return myTrans;
	}
	public static ArrayList<Integer> generateTransfer(int i,int j,ArrayList<Integer> trans){
		ArrayList<Integer> myTrans=trans;
		int sta=i;
		int des=j;
		while(!arrive(des,trans)){
			if(transInfo.get(sta)[des]){
				myTrans.add(des);
				return myTrans;
			}else{
				for(int p=0;p<8;p++){
					int mark=0;
					for(int q=0;q<trans.size();q++){
						if(!transInfo.get(sta)[p]||p==trans.get(q)){
							mark=1;
							break;
						}
					}
					if(mark==0){
						myTrans.add(p);
						sta=p;
						des=j;
						break;
					}
				}
			}
		}
		if(arrive(des,trans)){
			return myTrans;
		}
		return myTrans;
	}
	
	public static  boolean arrive(int j,ArrayList<Integer> trans){
		for(int i=0;i<trans.size();i++){
			if(trans.get(i)==j){
				return true;
			}
		}
		return false;
	}
 
}