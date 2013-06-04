package metric.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoader {

    private File file;
    private FileReader fileReader;
    private BufferedReader buffReader;
    private String filePath;

    public FileLoader(String filePath) {
        this.filePath = filePath;
    }

    public void openFile() {
        try {
            file = new File(filePath);
            fileReader = new FileReader(file);
            buffReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getLine() {
        try {
            return buffReader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void closeFile() {
        try {
            buffReader.close();
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
