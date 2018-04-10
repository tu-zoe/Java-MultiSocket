import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//1.创建一个服务器端Socket，指定并监听8888端口
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = null;
		int count = 0;
		System.out.println("服务器启动，等待客户端的连接");
		//循环监听客户端的连接请求
		while(true){
			socket = serverSocket.accept();
			//创建并启动一个新的线程
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
			
			count++;
			System.out.println("当前连接的客户端数量：");
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端IP"+ address.getHostAddress());
		}
		
	}

}
