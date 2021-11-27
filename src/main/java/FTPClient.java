import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        for(int i=0; i < 8;i++) {
            dout.writeUTF("Hello server");
        }
        dout.flush();
        dout.close();
        socket.close();
    }
}
