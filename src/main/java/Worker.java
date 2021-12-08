import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Worker  extends Thread implements WorkerService{

    private Socket controlSocket;

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    public Worker(Socket connectionSocket ){
        this.controlSocket = connectionSocket;
        logger.info("Initialized new worked thread");
    }

    @Override
    public void run() {
        logger.error("Server has accepted a connection request from a client");
        try{
            DataInputStream dataInputStream = new DataInputStream(controlSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(controlSocket.getOutputStream());
            boolean eof = true;
            while(eof){
                try{
                    String str = dataInputStream.readUTF();
                    logger.info("Input from client hase been read");
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
            controlSocket.close();
            return;
        }
        catch (IOException e){
           logger.error("There is an IO error");
           return;
        }

    }

    @Override
    public void executeCommand() {

    }

    @Override
    public void changeWorkingDirectory(String path) {

    }

    @Override
    public void RetrieveFile(String filePath) {

    }

    @Override
    public void storeFile() {

    }

    @Override
    public void appendFile(String filePath) {

    }
}
