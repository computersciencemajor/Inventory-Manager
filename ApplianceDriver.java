/*
 * @NAME:Jason Achkar Diab 
 * @ID: 40227239
 * @NAME: William Harpin
 * @ID:40212540
 * @COMP 249
 * @Assignment: #1(#0)
 * @Due Date: 07 October 2022
 * */
import java.util.Scanner;

public class ApplianceDriver extends Appliance {

	public ApplianceDriver(String type, String brand, double price) {
		super(type, brand, price);
		
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the appliance inventory program!");
		System.out.println("-------------------------------------------");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the maximum number of appliances your store is able to contain: ");
		if(scanner.hasNextInt()) { //Checks if user entered an Integer. If not it displays a message asking the user to input an integer and terminates the program.
		maxinventory=scanner.nextInt();
		}else {
			scanner.nextLine();//Clear scanner buffer.
			System.out.println("Please enter an integer!!!");
			System.exit(0);
		}
		int availableinventory=maxinventory-findNumberOfCreatedAppliances();//Number of available space left in the inventory;
		Appliance[] Inventory=new Appliance[maxinventory];
		String password="c249";
		int choice=0;
		int tries=1;//How many times has the user tried to enter a password(and got it wrong).
		int continuoustries=1;//How many times has the user tried to enter a password continuously(and got it wrong).
		String mainmenu1="What do you want to do?\n"
				+ "\t 1. Enter new appliances (password required)\n"
				+ "\t 2. Change information of an appliance (password required)\n"
				+ "\t 3. Display all appliances by a specific brand\n"
				+ "\t 4. Display all appliances under a certain price\n"
				+ "\t 5. Quit\n"
				+ "Please enter your choice >";
		
		String mainmenu2="What information would you like to change?\n"
				+ "\t 1.Brand\n"
				+ "\t 2.Type\n"
				+ "\t 3.price\n"
				+ "\t 4.Quit\n"
				+ "Please Enter your choice >";
		boolean again=true;
		/*This while loop keeps prompting the user to see what they want to do.
		 * It never breaks unless the user explicitly states that they want to 
		   quit the program by pressing 5.
		 * */
		while(again) {
			boolean again2=true;
			System.out.print(mainmenu1);
			if(scanner.hasNextInt()) {//Checks if User has entered an integer.
			choice=scanner.nextInt();
			if(choice>5) {
				System.out.println("Invalid number! Please Try Again.");
				continue;
			}
			switch(choice) {
				case 1:
					/*This while loop prompts the user to enter their password if they have selected option 1 from the main menu.
					 * Breaks only on two conditions:
					 * 1)Password was entered successfully.
					 * 2)Password was entered wrong 3 times in a row.*/
					while(again2) { 
						tries+=1;
						continuoustries+=1;
						System.out.print("Please enter your password: ");
						String passwordentered=scanner.next();
						if(passwordentered.equals(password)) {//Checks if the password entered by the user is the same as the one we have in our database.
							System.out.println("Password was entered Successfully");
							tries=1;//Keeps track of the amount of times the user has entered their password wrong.Resets only when the correct password is entered.
							continuoustries=1;//Keeps track of the consecutive amounts of time the user has entered their password wrong. Resets if correct password is entered or it exceeds 3 times.
							again2=false;
							System.out.println("How many appliances do you wish to enter? ");
							if(scanner.hasNextInt()) { //Check if user input is an integer.
							int nbofappliancestobeentered=scanner.nextInt();//If the user has entered an integer. It stores it in this variable.
							if(nbofappliancestobeentered<=availableinventory) {//Checks to see if the number of appliance the user wants to enter is less than or equal to the available inventory.
								for(int i=0;i<nbofappliancestobeentered;i++) {//For loops that prompts the user brand,type and price of the appliances they wish to enter.
									System.out.print("Please enter the brand: ");
									String brand=scanner.next();
									scanner.nextLine();//Clear scanner buffer.
									System.out.print("Please enter the type of appliance: ");
									String type=scanner.nextLine();
									
									System.out.print("Please enter the price of the appliance: ");
									double price=scanner.nextDouble();
									Appliance appliance=new Appliance(type,brand,price);
									if(!appliance.checktype(type)) {
										i-=1;
									}else {
									Inventory[(maxinventory-availableinventory)]=appliance;
									System.out.println(Inventory[(maxinventory-availableinventory)].toString());
									System.out.println("Item has been successfully added!");
									availableinventory-=1;
								}
								}
							}else { //If the number of appliance the user wants to enter exceeds the available inventory. A message is displayed to the user informing them on the space available.
								System.out.println("The maximum remaining Place in the inventory is "+availableinventory);
							}
							}else {
								scanner.next();
								System.out.println("Please enter an integer!!!");
							}
							break;
						}else if(continuoustries<=3) {
							System.out.println("Wrong password!PLease try again!");
							again2=true;
						}else if(continuoustries>3 &&tries<=12) {//If the user has tried more than 3 times continuously. we want the program to stop asking for the password and show the menu 1.
							continuoustries=1;
							again2=false;
							again=true;
						}else if(tries>12) {//If the user enters the password wrong for more than 12 times. we display a message saying there is suspicious activity and terminate the program.
							System.out.println("Program detected suspicious activities and will terminate immediately!");
							System.exit(0);
						}
					}
			
					
					break;
				case 2:again2=true;
					//Just like in case 1. Same way we check the password. Only difference the system does not terminate at a specific number of tries. It keeps prompting the user until they enter the correct password.
					while(again2) {
					tries+=1;
					continuoustries+=1;
					System.out.print("Please enter your password: ");
					String passwordentered=scanner.next();
					if(passwordentered.equals(password)) {
						System.out.println("Password was entered Successfully");
						tries=0;
						continuoustries=0;
						again2=false;
						System.out.print("Please enter the serial number of the appliance you wish to update: ");
						if(scanner.hasNextLong()) {//Checks if user has entered a long number (integer). Since serial number is of type long.
						long serialnumbertoupdate=scanner.nextLong();
						boolean exists=false;
						for(Appliance appliance:Inventory) {
							if(appliance!=null)
							if(serialnumbertoupdate==appliance.getSerialnb()) {
								exists=true;
							}
						}
						if(!exists) {
							System.out.println("The Serial Number You Have Entered Is Incorrect. Please Try Again!");
						}
						for(int i=0;i<maxinventory;i++) {
							if(Inventory[i]==null) {
							continue;	
							}else {
							if(serialnumbertoupdate==Inventory[i].getSerialnb()) {//Fetches the Appliance with the corresponding serial number entered by the user.
								System.out.println(Inventory[i].toString());
								System.out.print(mainmenu2);// Displays a second menu which asks the user to specify what information they would like to change.
								if(scanner.hasNextInt()) {//Checks if the User has entered an integer.
								int secondchoice=scanner.nextInt();
								switch(secondchoice) { 
								
								case 1: scanner.nextLine();//Clear Scanner Buffer.
										System.out.println("To which Brand would you like to change? ");
										String brandchange=scanner.nextLine();
									    Inventory[i].ChangeBrand(brandchange);
									    System.out.println("Brand successfully changed to: "+brandchange);
									    break;
								case 2: scanner.nextLine();//Clear Scanner Buffer.
										System.out.println("To which type would you like to change? ");
										String typechange=scanner.nextLine();
										Inventory[i].ChangeType(typechange);
										if(Inventory[i].checktype(typechange))
										System.out.println("Type successfully changed to: "+typechange);
										break;
								case 3: System.out.println("To which price would you like to change? ");
										if(scanner.hasNextDouble()) {
										double pricechange=scanner.nextDouble();
										Inventory[i].ChangePrice(pricechange);
										System.out.println("Price successfully change to: "+pricechange);
										}else {
											scanner.nextLine();
											System.out.println("Please enter a number!!");
										}
										break;
								case 4: //System.exit(0);
										break;
								default:break;
								}
							}else {
								scanner.nextLine();
								System.out.println("Please enter an Integer!!!");
								i-=1;
								continue;
							}
							}
						}
						}
						}else {
							scanner.nextLine();//Clear scanner buffer.
							System.out.println("Please enter a valid serial number!!!");
						}
						break;
					}else if(continuoustries<=3) {
						System.out.println("Wrong password!PLease try again!");
						again2=true;
					}else if(continuoustries>3) {
						continuoustries=0;
						again2=false;
						again=true;
					}
				}
					break;
				//In this case. we take the brand name from the user and filter all the appliances that are from this brand. Then we display it to the user.
				case 3:System.out.print("Please enter the brand you would like to search for: ");
				Appliance.findAppliancesBy(scanner.next(),Inventory);
					break;
				//In this case. We take the maximum price from the user and then we search all the appliance in the inventory. Any appliance with a cheaper price than that entered by the user we display it.
				case 4:
				System.out.print("Please enter the price: ");
				findCheaperThan(scanner.nextDouble(),Inventory);
				break;
				case 5:System.out.println("Thank you for using our appliance inventory program!!");
					System.exit(0);
				break;
				default:break;
			}
		}else {
			scanner.nextLine();//Clear Scanner Buffer.
			System.out.println("\nPlease enter an integer!!!\n");
			continue;
		}
		}
	scanner.close();
	}



	}


