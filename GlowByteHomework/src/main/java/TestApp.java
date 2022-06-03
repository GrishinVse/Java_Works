import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestApp {
    // Путь к выходному файлу
    static final private String pathToSave = "C:/Users/vsevo/IdeaProjects/result.txt";

    // Параметры базы данных
    static final private String JDBC_DRIVER = "org.h2.Driver";
    static final private String DB_URL = "jdbc:h2:file:D:/H2_database/glowbyte";

    // Параметры для аутентификации
    static final private String USER = "vsevo";
    static final private String PASS = "1234";

    /**
     * Метод возвращает результат запроса к таблице, указаной в параметре
     * @param tableName - название таблицы
     * @return - вывод информации из таблицы базы данных
     */
    public static ArrayList<ArrayList<String>> selectFromTable(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            if (tableName.strip().toLowerCase().equals("table_list")) {
                String sql = "SELECT TABLE_NAME, PK FROM TABLE_LIST";
                ResultSet rs = stmt.executeQuery(sql);

                ArrayList<ArrayList<String>> table_list = new ArrayList<ArrayList<String>>();

                while(rs.next()) {
                    List<String> line = new ArrayList<String>();

                    String TABLE_NAME  = rs.getString("TABLE_NAME").strip().toLowerCase();
                    String PK = rs.getString("PK").strip().toLowerCase();

                    String[] words = PK.split(",");
                    if (words.length > 1) {
                        for (String word: words) {
                            line.add(TABLE_NAME);
                            line.add(word.strip().toLowerCase());
                            table_list.add((ArrayList<String>) line);
                            line = new ArrayList<String>();
                        }
                    } else {
                        line.add(TABLE_NAME);
                        line.add(PK);
                        table_list.add((ArrayList<String>) line);
                    }
                }
                rs.close();
                return table_list;
            } else if (tableName.strip().toLowerCase().equals("table_cols")) {
                String sql = "SELECT TABLE_NAME, COLUMN_NAME, COLUMN_TYPE FROM TABLE_COLS";
                ResultSet rs = stmt.executeQuery(sql);

                ArrayList<ArrayList<String>> table_cols = new ArrayList<ArrayList<String>>();

                while(rs.next()) {
                    List<String> line = new ArrayList<String>();

                    String TABLE_NAME  = rs.getString("TABLE_NAME").strip().toLowerCase();
                    String COLUMN_NAME = rs.getString("COLUMN_NAME").strip().toLowerCase();
                    String COLUMN_TYPE = rs.getString("COLUMN_TYPE").strip().toLowerCase();

                    line.add(TABLE_NAME);
                    line.add(COLUMN_NAME);
                    line.add(COLUMN_TYPE);

                    table_cols.add((ArrayList<String>) line);
                }
                rs.close();
                return table_cols;
            }
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Метод выводит результат обработки в командную строку и записывает в файл pathToSave
     * @param table1 - первая таблица
     * @param table2 - вторая таблица
     */
    public static void saveRes(ArrayList<ArrayList<String>> table1, ArrayList<ArrayList<String>> table2) {
        String result = "";
        for (ArrayList<String> el_1 : table1) {
            for (ArrayList<String> el_2 : table2) {
                if (el_1.equals(el_2.subList(0, 2))) {
                    ArrayList<String> line = el_1;
                    line.add(el_2.get(2));
                    result += String.join(", ", line) + "\n";
                    break;
                }
            }
        }
        System.out.println(result);

        // Запись в файл
        try {
            FileWriter myWriter = new FileWriter(pathToSave);
            myWriter.write(result);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> res1 = selectFromTable("TABLE_LIST");
        System.out.println(res1);

        ArrayList<ArrayList<String>> res2 = selectFromTable("TABLE_COLS");
        System.out.println(res2);

        saveRes(res1, res2);
    }
}
