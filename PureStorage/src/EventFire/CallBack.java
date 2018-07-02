package EventFire;

public class CallBack {
    String name;
    
    public CallBack() {
    
    }
    
    public CallBack(String name) {
    	this.name = name;
    }
    
    public void call() {
    	System.out.println("CallBack Event " + this.name + " is running now");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
    
    
}
