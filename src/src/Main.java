import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\jeanp\\OneDrive\\Desktop\\File\\file.txt");
        FileManager fm = new FileManager(file);
        fm.create();
        fm.writeLine();
        fm.getFileInfos();
    }
}