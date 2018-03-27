package finalProject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CTAStation {
	private String name;
	private double latitude;
	private double longitude;
	private boolean[] onLines;
	private int[] positionOnLines;
	String[] lines={"red","blue","green","brown","purple","yellow","pink","orange"};
	protected static ArrayList<CTAStation> stations=new ArrayList<CTAStation>();

	public CTAStation(){
		name=null;
		latitude=0;
		longitude=0;
		onLines=new boolean[8];
		positionOnLines=new int[8];
	}
	
	public CTAStation(String na,double lat,double lon,boolean[] ol,int[] pol){
		this.name=na;
		this.latitude=lat;
		this.longitude=lon;
		this.onLines=ol;
		this.positionOnLines=pol;
	}

	public void setName(String nam){
		this.name=nam;
	}
	
	public void setLatitude(double lat){
		this.latitude=lat;
	}
	
	public void setLongitude(double lon){
		this.longitude=lon;
	}
	
	public void setOl(boolean[] ol){
		this.onLines=ol;
	}
	
	public void setPol(int[] pol){
		this.positionOnLines=pol;
	}
	
	public String getName(){
		return this.name;
	}
	
	public double getLatitude(){
		return this.latitude;
	}
	
	public double getLongitude(){
		return this.longitude;
	}
	
	public boolean[] getOl(){
		return this.onLines;
	}
	
	public int[] getPol(){
		return this.positionOnLines;
	}
	
	public void addStation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the station's name:");
        String name=sc.nextLine();
        if(searchStation(name)!=-1){
        	System.out.println("the station already existed! please try again!");
        	addStation();
        }
        System.out.println("please input the station's latitude:");
        double lat=getDouble();
        System.out.println("please input the station's longitude:");
        double lon=getDouble();
        boolean[] oli=new boolean[8];
        int[] pol=new int[8];
        String[] lines={"red","blue","green","brown","purple","yellow","pink","orange"};
        for(int i=0;i<7;i++){
        System.out.println("please input whether the station on "+lines[i]+"line(true or false):");
        oli[i]=getBoolean();
        if(oli[i]){
        	System.out.println("please input the station's position on "+lines[i]+"line:");
        	pol[i]=getInteger();
        	}else{
        		pol[i]=0;
        	}
        }
        CTAStation newStation=new CTAStation(name,lat,lon,oli,pol);
        stations.add(newStation);
        System.out.println("Add Station "+name+" successfully!");
	}
	
	public void delStation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the station's name:");
        String name=sc.nextLine();
        if(searchStation(name)==-1){
        	System.out.println("the station does not existed! please try again!");
        	delStation();
        }
        stations.remove(searchStation(name));
        System.out.println("the station "+name+" has been removed!");
	}
	
	public void modStation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("please input the station's name:");
        String name=sc.nextLine();
        if(searchStation(name)==-1){
        	System.out.println("the station does not existed! please try again!");
        	modStation();
        }
        System.out.println("please input the station's latitude:");
        double lat=getDouble();
        System.out.println("please input the station's longitude:");
        double lon=getDouble();
        boolean[] oli=new boolean[8];
        int[] pol=new int[8];
        for(int i=0;i<7;i++){
        System.out.println("please input whether the station on "+lines[i]+"line(true or false):");
        oli[i]=getBoolean();
        if(oli[i]){
        	System.out.println("please input the station's position on "+lines[i]+"line:");
        	pol[i]=getInteger();
        	}else{
        		pol[i]=0;
        	}
        }
        CTAStation newStation=new CTAStation(name,lat,lon,oli,pol);
        stations.set(searchStation(name),newStation);
        System.out.println("Modify Station "+name+" successfully!");
	}
	
	public int searchStation(String name){
		for(int i=0;i<stations.size();i++){
			if(stations.get(i).getName().equals(name)){
				return i;
			}
		}
		return -1;
	}
	
	public CTAStation findStation(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the station's name you want to find:");
		String name=sc.nextLine();
		for(int i=0;i<stations.size();i++){
			if(stations.get(i).getName().equals(name)){
				return stations.get(i);
			}
		}
		System.out.println("the station you found does not exist!");
		return null;
	}
	
	public CTAStation searchStation(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the station's name:");
		name=sc.nextLine();
		for(int i=0;i<stations.size();i++){
			if(stations.get(i).getName().equals(name)){
				return stations.get(i);
			}
		}
		System.out.println("the station you found does not exist! try again!");
		return searchStation();
	}
	
	public CTAStation nearestStation(){
		System.out.println("Please input your latitude");
		double lat=getDouble();
		System.out.println("Please input your longitude");
		double lon=getDouble();
		double distance;
		double min=0;
		int mark=0;
		int posi = 0;
		for(int s=0;s<stations.size();s++){
			double x = 69.1 * (lat - stations.get(s).latitude);		
			double y = 69.1 * (lon - stations.get(s).longitude) * Math.cos(stations.get(s).latitude/57.3) ;
			distance = Math.sqrt(x * x + y * y);
			if(mark==0){
				min=distance;
				posi=s;
				mark=1;
			}
			else if(distance<=min){
				min=distance;
				posi=s;
			}
		}
		return stations.get(posi);
	}
	
	public String toString(){
		return this.name;
	}
	
	public void readFile(){
		stations=new ArrayList<CTAStation>();
		 try {
	         Scanner in = new Scanner
	         (new FileReader("stations.txt"));
	         int i=0;
	         //int count=0;
	         String eachStop;
	         while (in.hasNextLine()) {
	        	 eachStop=in.nextLine();
	        	 String[] eSI;//stand for eachStopInfo
	        	 eSI=eachStop.split("\t");
	        	 String name=eSI[0];
	        	 int existed=searchStation(name);
	        	 if(existed==-1){
	        	 int[] positionInfo=new int[8];
	        	 for(int j=1;j<9;j++){
	        		 if(!eSI[j].equals("")){
	        			 positionInfo[j-1]=Integer.parseInt(eSI[j]);
	        		 }else{
	        			 positionInfo[j-1]=0;
	        		 }
	        	 }
	        	 boolean[] onlineInfo=new boolean[8];
	        	 for(int j=9;j<17;j++){
	        	 onlineInfo[j-9]=Boolean.parseBoolean(eSI[j].trim());
	        	 }
	        	 String loc=eSI[17].substring(1, eSI[17].length()-1);
	        	 String[] locInfo=loc.split(",");
	        	 double lat=Double.parseDouble(locInfo[0]);
	        	 double lon=Double.parseDouble(locInfo[1]);
	        	 CTAStation station=new CTAStation(name,lat,lon,onlineInfo,positionInfo);
	        	 i=i+1;
	        	 stations.add(station);
	        	 }else{
		        	 int[] positionInfo=new int[8];
		        	 for(int j=1;j<9;j++){
		        		 if(!eSI[j].equals("")){
		        			 positionInfo[j-1]=Integer.parseInt(eSI[j]);
		        		 }else{
		        			 positionInfo[j-1]=0;
		        		 }
		        		 if(stations.get(existed).getPol()[j-1]!=0){
		        			 positionInfo[j-1]=stations.get(existed).getPol()[j-1];
		        		 }
		        	 }
		        	 boolean[] onlineInfo=new boolean[8];
		        	 for(int j=9;j<17;j++){
		        		 onlineInfo[j-9]=Boolean.parseBoolean(eSI[j].trim());
		        		 if(stations.get(existed).getOl()[j-9]){
		        			 onlineInfo[j-9]=stations.get(existed).getOl()[j-9];
		        		 }
		        	 }
		        	 String loc=eSI[17].substring(1, eSI[17].length()-1);
		        	 String[] locInfo=loc.split(",");
		        	 double lat=Double.parseDouble(locInfo[0]);
		        	 double lon=Double.parseDouble(locInfo[1]);
		        	 CTAStation station=new CTAStation(name,lat,lon,onlineInfo,positionInfo);
		        	 stations.set(existed, station);
	        	 }
	        	 //count+=1;//count the number of data
	         }
	         //System.out.println(count);//count the number of data
	         //System.out.println(stations.size());//count the number of data read
	      } catch (IOException e) {
	      }		
	}
	
	public void outputFile(){
		try{
	        File file = new File("myStations.txt");
	        file.delete();
	        file.createNewFile();
	        PrintWriter out = new PrintWriter(
	                new FileWriter(file, true));
	        for(CTAStation s:stations){
	        out.println(s.name+"\t"+s.getPol()[0]+"\t"+s.getPol()[1]+"\t"+s.getPol()[2]+"\t"+s.getPol()[3]+"\t"
	        		+s.getPol()[4]+"\t"+s.getPol()[5]+"\t"+s.getPol()[6]+"\t"+s.getPol()[7]+"\t"
	        		+s.getOl()[0]+"\t"+s.getOl()[1]+"\t"+s.getOl()[2]+"\t"+s.getOl()[3]+"\t"
	        		+s.getOl()[4]+"\t"+s.getOl()[5]+"\t"+s.getOl()[6]+"\t"+s.getOl()[7]+"\t"
	        		+"("+s.getLatitude()+","+s.getLongitude()+")");
	        }
	        out.close();
		} catch (IOException e) {
	      }	
	}
	
    public double getDouble() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            scan.next();
            System.out.println("Input MUST be a double format");
        }

        return scan.nextDouble();
    }
    
    public boolean getBoolean() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextBoolean()) {
            scan.next();
            System.out.println("Input MUST be an boolean");
        }

        return scan.nextBoolean();
    }
    
    public int getInteger() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Input MUST be an integer");
        }

        return scan.nextInt();
    }
    
}
