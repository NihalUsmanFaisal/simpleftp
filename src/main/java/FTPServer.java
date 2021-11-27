import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {

    private static final Logger logger = LoggerFactory.getLogger(FTPServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        boolean serverRunning = true;
        logger.info("Server is starting up");
        while(serverRunning){
            Socket s = socket.accept();
            logger.error("Server has accepted a connection request from a client");
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            boolean eof = true;
            while(eof){
                try{
                    String str = dataInputStream.readUTF();
                    logger.info("Input from cliend hase been read");
                    System.out.println("message: " + str);
                    dataOutputStream.writeUTF("message recieved");
                    logger.info("Response sent back to client");
                }
                catch (EOFException e){
                    logger.info("End of file  has been reached");
                    eof = false;
                }
            }
        }
        socket.close();
    }
}
