/*
 * Milan Gulati
 * 
 * This class is used as the value for a key-value pair in the hash map
 * Each value in the hash map will be an instance of this class
 */

package Tickets;

public class Values 
{
	String password;		//stores password for each value
	LinkedOrders orders;	//stores order for each value
	
	Values() {}
	
	Values(String pass, LinkedOrders o)
	{
		password = pass;
		orders = o;
	}

	//accessors
	public String getPassword() {return password;}
	
	public LinkedOrders getOrder() {return orders;}
	
	//mutator
	public void setPassword(String text) {password = text;}
	
	public void setOrder(LinkedOrders o) {orders = o;}
}
