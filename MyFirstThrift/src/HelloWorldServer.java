import org.apache.thrift.TException;


public class HelloWorldServer implements HelloWorldService.Iface {
	@Override
	public String hello(String name) throws TException {
		
		String returnString = name + " Hello from JAVA server!\n";
		
		System.out.println("Received " + name);
		
		return returnString;
	}

}
