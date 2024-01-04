package per.amazondb.database_connection;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.security.PublicKey;
import java.util.Scanner;

public class FileManager {
    public static boolean create_file(String file_name){
        try {
            File file = new File(file_name);
            if (file.exists()) {
                return true;
            }
            if (file.createNewFile()) {
                return true;
            }
            return false;
        }
        catch (IOException ioException){
            System.out.println("ERROR : "+ ioException);
        }
        return false;
    }
    public static boolean write_file(String file_name, String content){
        if(create_file(file_name)){
            try {
                FileWriter write_to_file = new FileWriter(file_name);
                write_to_file.write(content);
                write_to_file.close();
                return true;
            }
            catch (IOException IO){
                return false;
            }
        }
        return false;
    }
    public static String[] read_file(String file_name){
        String[]  file_content ={"","",""};
        try {
            File file = new File(file_name);
            Scanner file_scanner = new Scanner(file);
            for (int index = 0;file_scanner.hasNextLine();index++){
                file_content[index] = file_scanner.nextLine();
            }
            return file_content;
        }
        catch (FileNotFoundException file_exception){
            System.out.println(file_exception);
        }
        return file_content;
    }
}
