// FileManager class handles file-related operations such as creating, writing and reading files
package per.amazondb.database_connection;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.security.PublicKey;
import java.util.Scanner;

public class FileManager {
// Method to create a file with given filename
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

    // Method to write content to the file with given file_name
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
    // Method to read content from the file with the given file_name
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
