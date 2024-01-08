```markdown
# Remote Postgres Database Connection

This Java application allows users to connect to a remote PostgreSQL database and execute SQL queries. The project consists of three main components: database connection, file management, and a graphical user interface (GUI) built using JavaFX.

## File Management

The `FileManager` class handles file operations, including creating, writing, and reading files.

### Methods

- `create_file(String file_name)`: Creates a file if it doesn't exist.
- `write_file(String file_name, String content)`: Writes content to a file.
- `read_file(String file_name)`: Reads the content of a file.

## GUI Components

The GUI is implemented using JavaFX and consists of several text fields and buttons for providing database connection details.

### `homepageController` Class

- Manages the actions and events of the main GUI.
- Connects to the database using provided credentials.
- Saves connection details to a file (`credentials.data`).
- Navigates to the query interface upon successful connection.

### `Main` Class

- Launches the JavaFX application and displays the main GUI.

### `QueryController` Class

- Handles the query execution and displays the result in a TableView.
- Utilizes the `FileManager` class to read connection credentials from `credentials.data`.

## Running the Application

1. Ensure Java is installed on your system.
2. Run the `Main` class to launch the GUI.
3. Enter the database connection details and click the "Connect" button.
4. If the connection is successful, the query interface will be displayed.
5. Enter SQL queries in the text field and click the "Execute" button to see the results.

## Dependencies

- JavaFX for building the graphical user interface.
- PostgreSQL JDBC driver for connecting to the PostgreSQL database.

## Contributors

-Bipul Bimali