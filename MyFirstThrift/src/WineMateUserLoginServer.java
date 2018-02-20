import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;


public class WineMateUserLoginServer {
	
	public static void main(String[] args) {
		try {
			WineMateUserLoginProcessor service = new WineMateUserLoginProcessor();
			WineMateServices.Processor processor = new WineMateServices.Processor(service);
			
			TServerTransport serverTransport = new TServerSocket(7890);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			
			System.out.println("Starting server...");
			server.serve();
			
		} catch (Exception x) {
		      x.printStackTrace();
	    }
	}
}
