package OA;

public class Payment {
	
	public static String payment(String input) {
		String[] str = input.split("~");
		double loan = Double.parseDouble(str[0]);
		double years = Double.parseDouble(str[1]);
		double monthlyRate = Double.parseDouble(str[2]) / 100 / 12;
		double downpay = Double.parseDouble(str[3]);
		
		double monthlypayment = (monthlyRate * (loan - downpay)) / (1 - Math.pow((1 + monthlyRate), -(years * 12)));		
		double interests = (monthlypayment * 12 * years) + downpay - loan;
		
		String monthlypaymentStr = String.format("%.2f", monthlypayment);
		String interestsStr = String.format("%.0f", interests);
	
		return "$" + monthlypaymentStr + "~" + "$" + interestsStr;
	}
	
	public static void main(String[] args) {
		String test1 = "6000~5~6~0";
		String test2 = "30000~10~6~5000";
		
		System.out.println(payment(test1));
		System.out.println(payment(test2));
	}
}
