import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			//获取输入流并读取客户端发送来的信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			while((info=br.readLine())!=null) {
				System.out.println("我是服务器，客户端说"+info);
			}
			socket.shutdownInput();
			
			//获取输出流，相应客户端请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("欢迎您");
			pw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {

		
	}

}
