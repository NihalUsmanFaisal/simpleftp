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
            WorkerFactory.getInstance().getWorker(s);
        }
        socket.close();
    }
}
