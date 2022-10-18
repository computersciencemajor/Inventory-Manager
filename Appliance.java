/*
 * @NAME:Jason Achkar Diab 
 * @ID: 40227239
 * @NAME: William Harpin
 * @ID:40212540
 * @COMP 249
 * @Assignment: #1(#0)
 * @Due Date: 07 October 2022
 * */
/*
 * This Program lets their User Input Information about their Appliance Inventory. 
 * They Can Change, Display a specific Brand or Filter by price depending on User Preference.
 * */

public class Appliance {

		String type;
		static int maxinventory=0;
		String brand;
		long serialnb=1000000;
		double price;
		static int objectscreated=0; // To keep track of the number of Appliance Objects Created.
		String checktypes[] = {"fridge","air conditioner","washer","dryer","freezer","stove","dishwasher","water heaters","microwave"};
public Appliance(String type,String brand,double price) { //Constructor which checks the type and price before creating an appliance object to make sure all entries from the user are correct.
	if(price<1) {
		System.out.println("Error:Price of an appliance cannot be less than 1$");
	}else if(!checktype(type)){
		System.out.println("The Type entered was incorrect! Please try again!");
	}else {
		this.type=type;
		this.brand=brand;
		this.serialnb+=objectscreated;
		this.price=price;
		objectscreated+=1;
	}
	}
public void ChangeType(String type) {//Method that allows the User to change the Type of an Appliance.
	if(checktype(type)) {
	 this.type=type;
	}else {
		System.out.println("The type entered was Incorrect! Please try again!");
	}
}
public void ChangeBrand(String brand) {//Method that allows the User to change the Brand of an Appliance.
	this.brand=brand;
}
public void ChangePrice(double price) {//Method that allows the User to change the Price of an Appliance.
	this.price=price;
}
public String getType() {
	return this.type;
}
public String getBrand() {
	return this.brand;
}
public long getSerialnb() {
	return this.serialnb;
}
public double getPrice() {
	return this.price;
}
@Override
public String toString() {
	return "Appliance Serial # "+this.getSerialnb()+"\n"+"Brand: "+this.getBrand()+"\n"+"Type: "+this.getType()+"\n"+"Price: "+this.getPrice(); 
}
public static int findNumberOfCreatedAppliances() {//Method that returns the number of Appliance objects created by the user.
	return objectscreated;
}
public boolean checktype(String type) { //Method that checks the type of Appliance object to make sure it is corresponding with the limited types allowed.
	String checktype=type.toLowerCase();
	boolean check=false;
	for(int i=0;i<9;i++) {
		if(checktypes[i].equals(checktype)) {
			check=true;
		}
	}
	return check;
}
public boolean equals(Appliance object2) {//Method that checks if two objects are equal.
	if((this.type.equalsIgnoreCase(object2.type))&&(this.brand.equalsIgnoreCase(object2.brand))&&(this.price==object2.price)) {
		return true;
	}else {
		return false;
	}
}
public static void findAppliancesBy(String brand,Appliance Inventory[]) {//Method that allows filtering by brand.
	boolean found=false;
	for(int i=0;i<Inventory.length;i++) {
		if(Inventory[i]==null) {
			continue;
		}else if(Inventory[i].getBrand().equalsIgnoreCase(brand)) {
		System.out.println(Inventory[i].toString());
		found=true;
		}
	}
	if(!found) {
		System.out.println("This brand could not be found in our inventory!!");
	}
	}

public static void findCheaperThan(double price,Appliance Inventory[]) {//Method that allows filtering by Price.
	for(int i=0;i<Inventory.length;i++) {
		if(Inventory[i]==null) {
			continue;
		}else if(Inventory[i].getPrice()<price) {
		System.out.println(Inventory[i].toString());
	}else {
		System.out.println("There is not item in the inventory cheaper than: "+price);
	}
	}
}


}