//ShuxianShen_FinalProject_CTAApplication

public class CTAApplication {
	public static void main(String[] args){
		CTARoute route=new CTARoute();
		route.readFile();
		route.setLine();
		Transfer.setTransInfo();
		String[] myMenu={"1.add a station","2. search a station","3. modify a station","4. delete a station",
				"5.generate a route","6. find the nearest station","7. exit"};
		Menu menu=new Menu(myMenu);
		menu.displayMenu();
		int mark=0;
		int choice=menu.getChoice("So...what's your choice?");
		while(mark==0){
		switch(choice){
			case 1:
				route.addStation();
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 2:
				System.out.println(route.findStation());
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 3:
				route.modStation();
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 4:
				route.delStation();
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 5:
				System.out.println(route.generateRoute());
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 6:
				System.out.println(route.nearestStation());
				menu.displayMenu();
				choice=menu.getChoice("So...what's your choice?");
				break;
			case 7:
				route.outputFile();
				System.out.println("Thanks for using!");
				mark=1;
				break;
		}
		}
	}

}
