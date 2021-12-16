import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.slf4j.Log4jLoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FTPClient {

    private static final Logger logger = LogManager.getLogger(FTPClient.class);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        PrintWriter dout = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner commandLineReader = new Scanner(new InputStreamReader(System.in));
        boolean clientRunning = true;
        while(clientRunning){
            logger.info("Waiting for input command from the user");
            String command = commandLineReader.nextLine();
            logger.info("Read the command from the user");
            dout.println(command);
            logger.info("Command has been written to the output stream for the server to read");
        }
//        for(int i=0; i < 8;i++) {
//            dout.writeUTF("Hello server");
//            String str = din.readUTF();
//            System.out.println(str);
//        }
        dout.flush();
        dout.close();
        socket.close();
    }
}
