/******************************
 * 
 * @author Patrick Atkins
 * CS 350 Project 5
 * April 3 2015
 * CWID: 11495287
 * CDriver.java
 * Creates a test driver, storing all the information associated with a test driving client
 *
 *****************************/

@SuppressWarnings("serial")
public class CDriver implements java.io.Serializable{ //Serializable makes the CDriver object able to wirte to file
	
	private int customerNumber;
	private int yearsDriving;
	private String name;
	private String models;
	private String transmission;
	private int jeepOwner; //0 for YES, 1 for NO, -1 for no entry
	
	public CDriver (int n, String name, int y, int o, String m, String t){
		customerNumber = n;
		yearsDriving = y;
		this.name = name;
		models = m;
		transmission = t;
		jeepOwner = o;
	}
	
	public int getCustomerNumber(){return customerNumber;}
	public int getYearsDriving(){return yearsDriving;}
	public String getName(){return name;}
	public int getJeepOwner(){return jeepOwner;}
	public String getModels(){return models;}
	
	public void setCustomerNumber(int n){customerNumber = n;}
	public void setYearsDriving(int n){yearsDriving = n;}
	public void setName(String n){name = n;}
	public void setJeepOwner(int n){jeepOwner = n;}
	public void setTransmission(String n){transmission = n;}
	public void setModels(String n){models = n;}
	
	public int getTransmission(){
		if (transmission.equals("5-Manual")){return 0;}
		else if (transmission.equals("6-Manual")){return 1;}
		else if (transmission.equals("8-Auto")){return 2;}
		else if (transmission.equals("Don't Care")){return 3;}
		else {return 4;}
	}
	
	public String toString(){ //outputs all variables of CDriver in a string, of specific length to fit the list size
		String o = "   ";
		//format customer number with preceeding 0's
		String num = "" + customerNumber;
		int numZero = 4-num.length();
		for (int i = 0; i<numZero;i++){
			o+= "0";
		}
		o+="" + customerNumber;
		while (o.length() < 20) {o+=" ";}
		o+=name;
		while (o.length() < 40) {o+=" ";}
		o+=""+yearsDriving;
		while (o.length() < 59) {o+=" ";}
		if (jeepOwner==0) {o += "YES";}
		else{o += "NO";}
		while (o.length() < 79) {o+=" ";}
		o+= models;
		while (o.length() < 98) {o+=" ";}
		o+= transmission;
		return o;
	}
}
