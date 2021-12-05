import java.net.Socket;

public class WorkerFactory {

    private static WorkerFactory instance;

    private WorkerFactory(){}

    public static WorkerFactory getInstance(){
        if(instance == null){
            instance = new WorkerFactory();
        }
        return instance;
    }

    public Worker getWorker(Socket connectionSocket){
        Worker newWorker = new Worker(connectionSocket);
        newWorker.start();
        return newWorker;
    }
}
