package com.flipkart.bean;
import com.flipkart.utils.DateTimeUtil;

/*
 * CLASS DESCRIPTION
 * 
 * For the Payment of Semester Fees . Assumed that Fees is same for all so no attribute for Amount
 * 
 * ATTRIBUTES
 * 		-	TransactionID 	: Unique for all Transactions related to Fees
 * 		-	Status 			: Stores whether fees is paid by the Student or not 
 * 		- 	timeStamp		: Time Stamp of the payment
 * 		- 	RegNo			: Registration Number for which fees is being paid
 */

public class Payment {
	private int TransactionID;
	private String Status;
	private String timeStamp;
	private int RegNO ;
	private int amount;
	private String Mode;
	
	
	//To Get time Stamp
	private DateTimeUtil DTUtil = new DateTimeUtil();
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRegNO() {
		return RegNO;
	}
	public void setRegNO(int regNO) {
		RegNO = regNO;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int ID) {
		
		TransactionID = DTUtil.getUniqueNumber(ID);
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}

}
