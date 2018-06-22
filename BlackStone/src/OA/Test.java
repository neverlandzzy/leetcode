package OA;


public class Test {
	
	static class AccountType {
		String accountType;
		public AccountType(String type) {
			this.accountType = type;
		}
		
		public String getName() {
			return this.accountType;
		}
	}
	
	static class BankAccount {
		String currency;
		int unit;
		AccountType type;
				
		public BankAccount(String currency, int unit) {
			this.currency = currency;
			this.unit = unit;
			this.type = new AccountType(this.getClass().getSimpleName());
		}
		
		public int getUnits() {
			return this.unit;
		}
		
		public String getCurrency() {
			return this.currency;
		}

		
		public AccountType getAccountType() {
			return this.type;
		}
	}
	
	static class SavingsAccount extends BankAccount {

		public SavingsAccount(String currency, int unit) {
			super(currency, unit);
			this.type = new AccountType("Savings");
		}
		
	}
	
	static class CheckingAccount extends BankAccount {

		public CheckingAccount(String currency, int unit) {
			super(currency, unit);
			this.type = new AccountType("Checking");
		}
		
	}
	
	static class BrokerageAccount extends BankAccount {

		public BrokerageAccount(String currency, int unit) {
			super(currency, unit);
			this.type = new AccountType("Brokerage");
		}
		
	}
	
    private static final String TEXT =  "I am a {0} account with {1,number,#} units of {2} currency";

    public static void main(String args[] ) throws Exception {
    	/*
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        accounts.add(new SavingsAccount("USD",3));//Savings
        accounts.add(new SavingsAccount("EUR",2));//Savings
        accounts.add(new CheckingAccount("HUF",100));//Checking
        accounts.add(new CheckingAccount("COP",10000));//Checking
        accounts.add(new BrokerageAccount("GBP",2));//Brokerage
        accounts.add(new BrokerageAccount("INR",600));//Brokerage
        

        accounts.stream().forEach(
                                    account -> System.out.println(
                                        MessageFormat.format(TEXT,
                                            new Object[]{
                                            account.getAccountType().getName(),//make this work
                                            account.getUnits(),//make this work
                                            account.getCurrency()//make this work
                                                          }))); */
        SavingsAccount test1 = new SavingsAccount("EUR",2);                                          
        System.out.println(test1.getAccountType().getName());
        System.out.println(test1.getUnits());
        System.out.println(test1.getCurrency());
        
        CheckingAccount test2 = new CheckingAccount("GBP",2);                                          
        System.out.println(test2.getAccountType().getName());
        System.out.println(test2.getUnits());
        System.out.println(test2.getCurrency());
    }
}
