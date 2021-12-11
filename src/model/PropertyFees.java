package model;

import java.util.Calendar;
import java.sql.Date;

//PropertyFee Model
//SingleTon Design Pattern
public class PropertyFees {
	
	//field include:
	//fee and listing period defaults
	//listing period start and end
	private int fee = 100;
	private int listingPeriod = 60; //60 days
	private String listingStart;
	private String listingEnd;
	
	private static PropertyFees instance;
	
	public static PropertyFees getInstance()
	{
		if(instance == null) 
		{
			instance = new PropertyFees();
		}
		return instance;
	}
	
	//Makes use of calendar and Date libraries to set an accurate and real date to each listing
    public PropertyFees(){
        this.setFeePeriodStart(new Date(Calendar.getInstance().getTimeInMillis()).toString());
        Calendar date = Calendar.getInstance();
        date.setTime(new Date(Calendar.getInstance().getTimeInMillis()));;
        date.add(Calendar.DAY_OF_MONTH, listingPeriod);
        this.setFeePeriodEnd(new Date(date.getTimeInMillis()).toString());
    }

    //Functional getters and setters
    public int getFee() {
        return fee;
    }

    public void setFee(int amount) {
        this.fee = amount;
    }

    public int getPeriod() {
        return listingPeriod;
    }

    public void setPeriod(int period) {
        this.listingPeriod = period;
        Calendar cal = Calendar.getInstance();
        //cal.setTime(new java.util.Date(feePeriodStart));
        cal.add(Calendar.DAY_OF_MONTH, period);
        this.setFeePeriodEnd(new Date(cal.getTimeInMillis()).toString());
    }
    
    public String getFeePeriodStart() {
        return listingStart;
    }

    public void setFeePeriodStart(String feePeriodStart) {
        this.listingStart = feePeriodStart;
    }

    public String getFeePeriodEnd() {
        return listingEnd;
    }

    public void setFeePeriodEnd(String date) {
        this.listingEnd = date;
    }
}
