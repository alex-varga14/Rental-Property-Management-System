
import java.util.Calendar;
import java.sql.Date;

public class PropertyFees {
	
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


    public PropertyFees(){
        this.setFeePeriodStart(new Date(Calendar.getInstance().getTimeInMillis()).toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Calendar.getInstance().getTimeInMillis()));;
        cal.add(Calendar.DAY_OF_MONTH, listingPeriod);
        this.setFeePeriodEnd(new Date(cal.getTimeInMillis()).toString());
    }

    public PropertyFees(String feePeriodStart, String feePeriodEnd) {
        this.setFeePeriodStart(feePeriodStart);
        this.setFeePeriodEnd(feePeriodEnd);
    }

    public double getFee() {
        return fee;
    }

    public void setFee(int amount) {
        this.fee = amount;
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
}
