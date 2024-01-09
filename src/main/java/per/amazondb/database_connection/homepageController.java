// homepageController class controls the actions and events of the homepage.fxml
package per.amazondb.database_connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import per.amazondb.database_connection.FileManager;

public class homepageController {
// FXML elements representing UI components
    @FXML
    private Button debugkey;

    @FXML
    private Button Test_button;

    @FXML
    private Button Password_visibility;

    @FXML
    private TextField Address;

    @FXML
    private TextField Database_name;

    @FXML
    private TextField Username;

    @FXML
    private TextField Port;

    @FXML
    private Button Connect_button;

    @FXML
    private Text databaseURL;

    @FXML
    private TextField Password;
    String url;
    String username;
    String password;
// Event handler for Enter Key pressed 
    // Still developing
    @FXML
    void enter_pressed(){
        Address.setOnKeyPressed( evt ->{
            if(evt.getCode().equals(KeyCode.ENTER)){
                System.out.println("entered");
                Port.requestFocus();
            }
        }
        );
    }
    // Method to test the connection with the database
    @FXML
    boolean Test() {
        urlgenerator();
        try{
            Connection connection = DriverManager.getConnection(url, Username.getText(), Password.getText());
            System.out.println("connection Successful");
            return true;
        }
        catch (SQLException Sql_exception){
            System.out.println(Sql_exception);
            return false;
        }
    }
    // Method to connect to the database and open Query.fxml window
    @FXML
    void Connect() {
        if(Test()){
            FileManager.create_file("credentials.data");
            FileManager.write_file("credentials.data",url+"\n"+username+"\n"+password);
            System.out.println("Error storing credentials");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Query.fxml"));
                Stage childstage = new Stage();
                childstage.setTitle("Query");
                childstage.setScene(new Scene(root));
                childstage.show();
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
    // Method to change visibility of password Not implemented yet
    @FXML
    void togglevisibility() {

    }
    // Method to generate url to connect to the database
    private void urlgenerator(){
        username = Username.getText();
        password = Password.getText();
        if(Port.getText().equals("")){
            url = "jdbc:postgresql://"+Address.getText()+"/"+Database_name.getText();
            return;
        }
        url = "jdbc:postgresql://"+Address.getText()+":"+"/"+Database_name.getText();
    }

}
