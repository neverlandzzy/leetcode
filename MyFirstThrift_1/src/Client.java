import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class Client {

	public static void main(String[] args) {
		
		try{
		 TTransport transport = new TSocket("localhost", 9093);
		 transport.open();
		 
		 TProtocol protocol = new  TBinaryProtocol(transport);
		 HelloWorldService.Client client = new HelloWorldService.Client(protocol);
		 
		 System.out.println(client.hello("Hi, from client!\n"));
		 
		 
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
