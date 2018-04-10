import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {
	static private Random r = new Random();

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//1.创建客户端Socket，指定服务器地址和连接端口
		Socket socket = new Socket("127.0.0.1",8888);
		//2.获取输出流，向服务器发送信息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		
		long name = Math.abs(r.nextLong());
		long pass = Math.abs(r.nextLong());
		
		pw.write("用户名" + name + ";密码：" + pass);
		pw.flush();
		socket.shutdownOutput();
		//3.获取输入流，读取服务器端的响应
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info = br.readLine())!=null) {
			System.out.println("我是客户端，服务器端返回的信息是："+info);
		}
		//4.关闭资源
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}

}
