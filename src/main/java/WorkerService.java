public interface WorkerService {
    public void executeCommand(String command); // Decodes the command from the client and calls the corresponding methods
    public void changeWorkingDirectory(String path); // change the current working directory
    public void RetrieveFile(String filePath); // shares a copy of the file to the client
    public void storeFile();  // stores data received as a file
    public void appendFile(String filePath); // Appends data to a preexisting file or creates a new file
}
