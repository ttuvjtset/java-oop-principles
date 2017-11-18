package kodutoo08;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;


class WriteToFile {

    private BufferedWriter bufferedWriter;

    WriteToFile(String filename) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (NoSuchFileException e) {
            System.out.println("No such file");
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
    }

    void writeData(String data) {
        try {
            bufferedWriter.write(data);
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("written");
    }

    void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
    }
}


