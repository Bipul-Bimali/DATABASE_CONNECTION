// Query Controller class manages the actions and events of the Query.fxml file
package per.amazondb.database_connection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.Comparator;
import per.amazondb.database_connection.FileManager;

public class QueryController {
    // FXML elements representing UI components
    @FXML
    private TableView display_table;
    @FXML
    private TextField query;
    private final ObservableList<ObservableList<String>> data_matrix = FXCollections.observableArrayList();
    // Method to generate columns based on Resultset Metadata
    public  void Column_generator(ResultSet result){
        try {
            int column_count = result.getMetaData().getColumnCount();
            System.out.println(column_count);
            ResultSetMetaData meta_data = result.getMetaData();
            display_table.getColumns().clear();
                // Create a new column
            for (int column_index = 0; column_index <= meta_data.getColumnCount(); column_index++) {
                TableColumn<ObservableList<String>, String> new_column;
                final int ColumnIndex = column_index;
                if (ColumnIndex ==0) {
                    new_column = new TableColumn<>("S.N.");
                    new_column.setComparator(Comparator.comparingInt(Integer::parseInt));
                }
                else {
                    new_column = new TableColumn<>(meta_data.getColumnName(column_index));
                    new_column.setSortable(false);
                }
                    // Set the cell value factory for the column
                    new_column.setCellValueFactory(cellData ->
                            new SimpleStringProperty(cellData.getValue().get(ColumnIndex)));
                    // Add the new column to the TableView
                    display_table.getColumns().add(new_column);
            }
            }
        catch (SQLException SQL_exception) {
            throw new RuntimeException(SQL_exception);
        }

    }
//    This method establishes a connection to database and executes SQL query
    public  void database_connection( ) {
        String[] credentials=FileManager.read_file("credentials.data");

        data_matrix.clear();
        try {
            Connection connection_bool = DriverManager.getConnection(credentials[0],credentials[1],credentials[2]);
            System.out.println("connection Successful");
            PreparedStatement prepared_statement = connection_bool.prepareStatement(query.getText());
            System.out.println(query.getText());
            ResultSet result_set = prepared_statement.executeQuery();
            Column_generator(result_set);
            int row_index = 1;
            while(result_set.next()) {
                ObservableList<String> row_data = FXCollections.observableArrayList();
                for(int i = 0; result_set.getMetaData().getColumnCount()>=i ; i++){
                    if(i!=0){
                        row_data.add(result_set.getString(i));
                    }
                    else {
                        row_data.add(row_index +"");
                        row_index++;
                    }
                }
                if (row_data.isEmpty()){
                    continue;
                }
                else {
                    data_matrix.add(row_data);
                }
            }
            display_table.setItems(data_matrix);
            display_table.getColumns().sorted();
        }

        catch (SQLException SQL_exception){
            System.out.println("SQL error"+ SQL_exception);
        }
        catch (Exception exceptions){
            System.out.println(exceptions + "exception ERROR");
        }
    }
}

