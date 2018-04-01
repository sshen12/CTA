package cta;

import java.util.ArrayList;

public class CTARoute extends CTAStation{
	private ArrayList<CTAStation> rLine;
	private ArrayList<CTAStation> bLine;
	private ArrayList<CTAStation> gLine;
	private ArrayList<CTAStation> brLine;
	private ArrayList<CTAStation> ppLine;
	private ArrayList<CTAStation> yLine;
	private ArrayList<CTAStation> pkLine;
	private ArrayList<CTAStation> oLine;
	private ArrayList<ArrayList<CTAStation>> routeInfo;
	
	public CTARoute(){
		rLine=new ArrayList<CTAStation>();
		bLine=new ArrayList<CTAStation>();
		gLine=new ArrayList<CTAStation>();
		brLine=new ArrayList<CTAStation>();
		ppLine=new ArrayList<CTAStation>();
		yLine=new ArrayList<CTAStation>();
		pkLine=new ArrayList<CTAStation>();
		oLine=new ArrayList<CTAStation>();
		routeInfo=new ArrayList<ArrayList<CTAStation>>();
	}
	
	public void setLine(){
		routeInfo.add(rLine);
		routeInfo.add(bLine);
		routeInfo.add(gLine);
		routeInfo.add(brLine);
		routeInfo.add(ppLine);
		routeInfo.add(yLine);
		routeInfo.add(pkLine);
		routeInfo.add(oLine);
		for(CTAStation c:stations){
			for(int i=0;i<routeInfo.size();i++){
				if(c.getOl()[i]&&c.getPol()[i]!=0){
					routeInfo.get(i).add(c);
				}
			}
		}
		for(int i=0;i<routeInfo.size();i++){
			sortLines(routeInfo.get(i),i);
		}
	}
	
	private void sortLines(ArrayList<CTAStation> line,int lineID){
		for(int i=0;i<line.size();i++){
			int min=line.get(i).getPol()[lineID];
			int ind=i;
			for(int j=i;j<line.size();j++){
				if(line.get(j).getPol()[lineID]<min){
					min=line.get(j).getPol()[lineID];
					ind=j;
				}
			}
			CTAStation ss=line.get(i);
			line.set(i, line.get(ind));
			line.set(ind, ss);
		}
	}
	
	public ArrayList<ArrayList<CTAStation>> getRoute(){
		return this.routeInfo;
	}
	
	public void addStation(){
		super.addStation();
		Transfer.setTransInfo();
		setLine();
	}
	
	public void delStation(){
		super.delStation();
		Transfer.setTransInfo();
		setLine();
	}
	
	public void modStation(){
		super.modStation();
		Transfer.setTransInfo();
		setLine();
	}
	
	public ArrayList<CTAStation> generateRoute(){
		System.out.println("origin Station:");
		CTAStation start=searchStation();
		System.out.println("destination:");
		CTAStation destination=searchStation();
		ArrayList<CTAStation> myRoute=new ArrayList<CTAStation>();
		ArrayList<Integer> trans=new ArrayList<Integer>();
		boolean[] sol=start.getOl();
		boolean[] dol=destination.getOl();
		for(int i=0;i<8;i++){
			if(sol[i]&&dol[i]){
				trans.add(i);
				return generateRoute(start,destination,trans,myRoute);
			}
		}
		trans=Transfer.generateTransfer(start, destination);
		return generateRoute(start,destination,trans,myRoute);
	}
	
	public ArrayList<CTAStation> generateRoute(CTAStation start,CTAStation destination,ArrayList<Integer> transInfo,ArrayList<CTAStation> myRoute){
		ArrayList<CTAStation> route=myRoute;
		CTAStation sta=start;
		CTAStation des=destination;
		int init;
		int fina;
		while(transInfo.size()>1){
				init=searchStation(sta,transInfo.get(0));
				fina=searchStation(transInfo.get(0),transInfo.get(1));
				if(init<=fina){
					for(int i=init;i<fina;i++){
						route.add(routeInfo.get(transInfo.get(0)).get(i));
					}
				}else{
					for(int i=init;i>fina;i--){
						route.add(routeInfo.get(transInfo.get(0)).get(i));
					}
				}
				sta=routeInfo.get(transInfo.get(0)).get(fina);
				transInfo.remove(0);
		}
		if(transInfo.size()==1){
			init=searchStation(sta,transInfo.get(0));
			fina=searchStation(des,transInfo.get(0));
			if(init<=fina){
				for(int i=init;i<fina+1;i++){
					route.add(routeInfo.get(transInfo.get(0)).get(i));
				}
			}else{
				for(int i=init;i>fina-1;i--){
					route.add(routeInfo.get(transInfo.get(0)).get(i));
				}
			}
		}
		return route;
	}
	
	
	public int searchStation(CTAStation c,int line){
		for(int i=0;i<routeInfo.get(line).size();i++){
			if(c.equals(routeInfo.get(line).get(i))){
				return i;
			}
		}
		return -1;
	}
	
	public int searchStation(int line1,int line2){
		for(int i=0;i<routeInfo.get(line1).size();i++){
			if(routeInfo.get(line1).get(i).getOl()[line2]){
				return i;
			}
		}
		return -1;
	}
	
	public String toString(ArrayList<String> myRoute){
		return myRoute.toString();
	}

}
