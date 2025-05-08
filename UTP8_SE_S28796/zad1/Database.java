package zad1;

//operacje bzaodanowe

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Locale;

public class Database {

    private final String url;
    private final TravelData td;
    private Connection connection;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.td = travelData;

        try{
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Error connectiong: " + e.getMessage(), e);
        }
    }

    public void create(){
        //TODO utworzenie bazy danych i wpisanie do niej wsyztskich ofert, wczytanych z plików
        try {

            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE OFERTY");   //usunąć potem
            statement.executeUpdate("CREATE TABLE OFERTY(id INT PRIMARY KEY, loc VARCHAR(20), country VARCHAR(20), depart DATE, ret DATE, place VARCHAR(20), price DECIMAL(6, 2), currency VARCHAR(3))");


            String sql = "INSERT INTO OFERTY(id, loc, country, depart, ret, place, price, currency) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statment = connection.prepareStatement(sql)) {
                int id_count = 1;
                for (String offers : td.getOffersDescriptionsList("pl_PL", "yyyy-mm-dd")) {
                    String[] splited = offers.split("\t");

                    statment.setInt(1, id_count);
                    statment.setString(2, splited[0]);
                    statment.setString(3, splited[1]);
                    statment.setDate(4, Date.valueOf(splited[2]));
                    statment.setDate(5, Date.valueOf(splited[3]));
                    statment.setString(6, splited[4]);
                    statment.setBigDecimal(7, new java.math.BigDecimal(splited[5].replace(",", "")));
                    statment.setString(8, splited[6]);

                    statment.executeUpdate();
                    id_count++;
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //GUI :D fun T.T
    private JComboBox<String> languageBox;
    private JComboBox<String> countryBox;
    private JTable table;


    public void showGui() {

        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            JPanel panel = new JPanel(new GridLayout(4, 2));

            panel.add(new JLabel("Choose language: "));
            languageBox = new JComboBox<>(getLanguage());
            panel.add(languageBox);

            panel.add(new JLabel("Choose country: "));
            countryBox = new JComboBox<>(getCountry());
            panel.add(countryBox);

            JButton refreshButton = new JButton("Refresh");
            panel.add(refreshButton);
            refreshButton.addActionListener(e -> refresh());

            DefaultTableModel defultModel = new DefaultTableModel();
            table = new JTable(defultModel);
            JScrollPane scrollPane = new JScrollPane(table);

            frame.setTitle("Offers");

            panel.add(scrollPane);
            frame.add(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private String[] getLanguage() {
        Locale[] locales = Locale.getAvailableLocales();
        String[] languages = new String[locales.length];

        for (int i = 0; i < locales.length; i++) {
            languages[i] = locales[i].getDisplayLanguage();
        }

        return languages;
    }

    private String[] getCountry() {
        Locale[] locales = Locale.getAvailableLocales();
        String[] countries = new String[locales.length];

        for (int i = 0; i < locales.length; i++) {
            countries[i] = locales[i].getDisplayCountry();
        }

        return countries;
    }

    private void refresh() {
        String language = (String) languageBox.getSelectedItem();
        String country = (String) countryBox.getSelectedItem();

        assert language != null;
        Locale selectedLocale = new Locale(language, country);

        DefaultTableModel defultTable = (DefaultTableModel) table.getModel();
        defultTable.setRowCount(0);


            try {
                String sql = "SELECT * FROM OFERTY WHERE lok = ? AND kraj = ?";

                try (PreparedStatement statment = connection.prepareStatement(sql)) {
                    statment.setString(1, selectedLocale.getDisplayLanguage());
                    statment.setString(2, selectedLocale.getCountry());

                    ResultSet resultSet = statment.executeQuery();

                    while (resultSet.next()) {
                        String loc = resultSet.getString("loc");
                        String countryName = resultSet.getString("country");
                        Date departureDate = resultSet.getDate("depart");
                        Date returnDate = resultSet.getDate("ret");
                        String place = resultSet.getString("place");
                        double price = resultSet.getDouble("price");
                        String currency = resultSet.getString("currency");

                        defultTable.addRow(new Object[]{loc, countryName, departureDate, returnDate, place, price, currency});
                    }
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    /*private String[] getLanguage() {
        Set<String> res = new HashSet<>();

        try {
            String sql = "SELECT DISTINCT loc FROM OFERTY";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    res.add(resultSet.getString("loc").split("_")[0]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return res.toArray(new String[0]);
    }

    private String[] getCountry() {
        Set<String> res = new HashSet<>();

        try {
            String sql = "SELECT DISTINCT loc FROM OFERTY";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    res.add(resultSet.getString("loc").split("_")[1]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return res.toArray(new String[0]);
    }*/
}

    //TODO GUI z tabelą, pokazującą wczytane oferty
    //TODO wybór języka i ustawień regionalnych, w których pokazywane są oferty
