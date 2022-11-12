import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManager {

    private File file;
    private FileOutputStream fileOutputStream;
    private Scanner reader;

    public FileManager(File file){
        this.file = file;
    }

    protected void create(){
        try{
            if(file.createNewFile()){
                System.out.println(file.getName() + " has been successfully created !");
            }
            else{
                System.out.println(file.getName() + " already exists, try another name.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during the creation of the file.");
            e.printStackTrace();
        }
    }

    protected void writeLine(){

        BufferedWriter bufferedWriter = null;
        String lineContent;

        try{
            fileOutputStream = new FileOutputStream(file);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            lineContent = typeLine(bufferedWriter);

        }catch(IOException e){
            System.out.println("An error occurred during the writing of the file.");
            e.printStackTrace();

        }finally {
            try{
                bufferedWriter.close();
            }catch (Exception e){
                System.out.println("Couldn't close the buffer");
                e.printStackTrace();
            }
        }
    }

    protected void readPrint(){
        String data;
        try {
            reader = new Scanner(file);
            int lineCount = 0;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                ++lineCount;
                System.out.println("Line " + lineCount + ": " + data);
            }
        }catch(FileNotFoundException e){
            System.out.println("File wasn't found.");
            e.printStackTrace();
        }finally{
            try{
                reader.close();
            }catch(Exception e){
                System.out.println("Couldn't close the reader");
                e.printStackTrace();
            }
        }
    }

    protected void readPrint(int headLine){
        String data;
        try {
            reader = new Scanner(file);
            int lineCount = 0;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                if (lineCount < headLine) {
                    ++lineCount;
                    System.out.println("Line " + lineCount + ": " + data);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File wasn't found.");
            e.printStackTrace();
        }finally{
            try{
                reader.close();
            }catch(Exception e){
                System.out.println("Couldn't close the reader");
                e.printStackTrace();
            }

        }
    }

    public void getFileInfos(){
        if(file.exists()){
            System.out.println("File name : " + file.getName());
            System.out.println("--------------------------------");
            System.out.println("Absolute path : " + file.getAbsolutePath());
            System.out.println("Readable : " + file.canRead());
            System.out.println("Writable : " + file.canWrite());
            System.out.println("Executable : " + file.canExecute());
            System.out.println("Size in Bytes : " + file.length());
        }
        else{
            System.out.println("Specified file doesn't exist.");
        }
    }

    private String typeLine(BufferedWriter bw){
        Scanner keyboard = new Scanner(System.in);
        String value = "";
        System.out.println("You can now type everything you want to add in the file. Type /exit to exit the program.");
        while(!value.equals("/exit")){
            value = keyboard.nextLine();
            try{
                bw.write(value);
                bw.newLine();
            }catch(IOException e){
                System.out.println("Can't access to next line");
                e.printStackTrace();
            }
            System.out.println("Line added : " + "\"" + value + "\"");
        }

        return value;
    }


}
