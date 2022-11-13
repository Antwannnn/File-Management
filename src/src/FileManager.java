import java.io.*;
import java.util.Scanner;

public class FileManager {

    private File file;

    private String absolutePath;
    private Scanner reader;

    public FileManager(File file){
        this.file = file;
    }

    protected void create(){

        absolutePath = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder(absolutePath);
        String reversedPath = sb.reverse().toString();
        int charCount = absolutePath.length();

        for (char ch : reversedPath.toCharArray()) {
            sb.deleteCharAt(charCount);
            charCount--;
            if(ch == '\\')
                break;
        }

        file = new File(sb.toString());

        if (file.mkdir()) {
            System.out.println("Directory created successfully");
        }

        file = new File(absolutePath);

        try{
            if(file.createNewFile()){
                String fileName = file.getName();
                System.out.println(fileName + " has been successfully created !");
            }
            else{
                System.out.println("File already exists, try another name.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during the creation of the file.");
        }
    }

    protected void writeLine(){

        BufferedWriter bufferedWriter = null;
        String lineContent;

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            lineContent = typeLine(bufferedWriter);

        }catch(IOException e){
            System.out.println("An error occurred during the writing of the file.");

        }finally {
            try{
                bufferedWriter.flush();
                bufferedWriter.close();
            }catch (IllegalStateException | IOException | NullPointerException e){
                System.out.println("Couldn't close the buffer");
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
        }finally{
            try{
                reader.close();
            }catch(IllegalStateException e){
                System.out.println("Couldn't close the reader");
            }
        }
    }

    protected void readPrint(int line){
        String data;
        try {
            reader = new Scanner(file);
            int lineCount = 0;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                if (lineCount < line) {
                    ++lineCount;
                    System.out.println("Line " + lineCount + ": " + data);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File wasn't found.");
        }finally{
            try{
                reader.close();
            }catch(IllegalStateException e){
                System.out.println("Couldn't close the reader");
            }

        }
    }



    public void getFileInfos(){
        try{
            System.out.println("File name : " + file.getName());
            System.out.println("--------------------------------");
            System.out.println("Absolute path : " + file.getAbsolutePath());
            System.out.println("Readable : " + file.canRead());
            System.out.println("Writable : " + file.canWrite());
            System.out.println("Executable : " + file.canExecute());
            System.out.println("Size in Bytes : " + file.length());
        }catch(Exception e){
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
                System.out.println("Can't access to the next line");
            }
            System.out.println("Line added : " + "\"" + value + "\"");
        }

        return value;
    }



}
