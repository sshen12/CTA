package finalProject;

//ShuxianShen_FinalProject_Menu

import java.util.Scanner;

public class Menu {
    private String[] menu;

    public Menu(String[] menu) {
        this.menu = menu;
    }

	public void displayMenu(){
		for(int i=0;i<menu.length;i++){
			System.out.println(menu[i]);
			}
	}

    public int getChoice(String prompt) {
        System.out.println(prompt);
        int choice = getInteger();
        while (choice < 1 || choice > menu.length) {
            System.out.println("Choice must be 1 - " + menu.length);
            choice = getInteger();
        }
        return choice;
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
