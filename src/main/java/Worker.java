import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Worker  extends Thread implements WorkerService{

    private Socket controlSocket;

    private static final Logger logger = LogManager.getLogger(Worker.class);

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
            BufferedReader clientCommandReader = new BufferedReader(new InputStreamReader(dataInputStream));
            boolean eof = true;
            while(eof){
                try{
                    String command = clientCommandReader.readLine();
                    logger.info("Command from client has been read :"+ command);
                    executeCommand(command);
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
    public void executeCommand(String command) {
        switch (command){
            case "CWD" :
                logger.info("Execute change working directory command");
                break;
            case "RETR" :
                logger.info("Execute Retrieve file ");
                break;
            case "STORE":
                logger.info("Execute store file");
                break;
            case "APPEND":
                logger.info("Execute append file");
                break;
            case "DEL":
                logger.info("Execute delete file");
                break;
            case "RMD":
                logger.info("Execute remove directory");
                break;
            case "LIST":
                logger.info("Execute list All Files");
                break;
            case "CLOSE":
                logger.info("Execute close Connection");
                break;
            default:
                logger.error("Invalid command");
                return;

        }
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
