
public class Share {

	private int sNumber;
	private double sPrice;
	private String sCompnay;
	private String sDate;
	
	
	public Share(int sNumber, double sPrice, String sCompnay, String sDate) {
		super();
		setsNumber(sNumber);
		setsPrice(sPrice) ;
		setsCompnay(sCompnay);
		setsDate(sDate);
	}
	
	
	public int getsNumber() {
		return sNumber;
	}
	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}
	public double getsPrice() {
		return sPrice;
	}
	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}
	public String getsCompnay() {
		return sCompnay;
	}
	public void setsCompnay(String sCompnay) {
		this.sCompnay = sCompnay;
	}


	public String getsDate() {
		return sDate;
	}


	public void setsDate(String sDate) {
		this.sDate = sDate;
	}


	@Override
	public String toString() {
		return "Amount Of Shares = " + sNumber + ", Share Price = " + sPrice + ", Share Compnay Name = " + sCompnay + ", Share Prush Date = " + sDate+"\n" ;
	}
	
	
	
	
}
