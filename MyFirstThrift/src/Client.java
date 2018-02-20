import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class Client {

	public static void main(String[] args) {
		
		User user = new User();
		user.userName = "zzy";
		user.password = "123";
		
		try {
			
			TTransport transport = new TSocket("54.67.111.151", 7890);
			//TTransport transport = new TSocket("172.22.167.67", 9093);
			transport.open();
			LoginResult result;
			
			TProtocol protocol = new TBinaryProtocol(transport);
			WineMateServices.Client client = new WineMateServices.Client(protocol);
			
			
			result = client.login(user);
			System.out.println("result = "+ result);
			
			transport.close();
		 
		 
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
