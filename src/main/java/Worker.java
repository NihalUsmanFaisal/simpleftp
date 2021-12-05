import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Worker  extends Thread{

    private Socket connectionSocket;

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    public Worker(Socket connectionSocket ){
        this.connectionSocket = connectionSocket;
        logger.info("Initialized new worked thread");
    }

    @Override
    public void run() {
        logger.error("Server has accepted a connection request from a client");
        try{
            DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            boolean eof = true;
            while(eof){
                try{
                    String str = dataInputStream.readUTF();
                    logger.info("Input from cliend hase been read");
                    logger.info("Message :"+str);
                    dataOutputStream.writeUTF("message recieved");
                    logger.info("Response sent back to client");
                }
                catch (EOFException e){
                    logger.info("End of file  has been reached");
                    eof = false;
                }
            }
            logger.info("Closing the socket connection");
            connectionSocket.close();
            return;
        }
        catch (IOException e){
           logger.error("There is an IO error");
           return;
        }

    }
}
