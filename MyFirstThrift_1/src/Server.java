import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;


public class Server {


	public static void main(String[] args) {
		
		try {
			HelloWorldServer helloWorldServer = new HelloWorldServer();
			HelloWorldService.Processor processor = new HelloWorldService.Processor(helloWorldServer);
			
			TServerTransport serverTransport = new TServerSocket(9093);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			
			System.out.println("Starting server...");
			server.serve();
			
		} catch (Exception x) {
		      x.printStackTrace();
	    }
	}
}
