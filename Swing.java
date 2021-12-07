import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.lang.Object;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Swing extends Formula1ChampionshipManager {
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
    public static BufferedImage background;
    static JFrame frame;

    public Swing() {
        try {
            background = ImageIO.read(new File("D:\\Formula1CW\\src\\F1_2022_Aston_Side_Left.jpg"));//adding a background picture
        } catch (IOException ex) {
            System.out.println("Error in image");

        }

        Collections.sort(championDrivers, new TotalComparator());// by default sorting the list in descending order by total

        JTable championTable = new JTable(); //creating table
        championTable.setForeground(Color.WHITE);
        championTable.setOpaque(false);
        championTable.setBackground(new Color(255, 255, 255, 0));

        championTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver name", "Driver team", "Location", "First positions", "Second positions ", "Third positions", "Total points ", "Total races"}));
        championTable.setDefaultEditor(Object.class, null);//makes  the cells non edit

        addToMainTable(championDrivers, championTable);//calling the add to table method to add data
        championTable.getColumn("Driver name").setPreferredWidth(150);
        championTable.getColumn("Driver team").setPreferredWidth(150);

        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewport(new image());
        scrollPane.setViewportView(championTable);
        panel.add(scrollPane);//adding scroll panel to the panel
        scrollPane.setPreferredSize(new Dimension(800, 500));

        //button to sort by total points
        JButton sortByDescTotalPointsBtn = new JButton("Sort by total points ASC");
        sortByDescTotalPointsBtn.setBounds(200, 100, 100, 50);
        sortByDescTotalPointsBtn.setSize(100, 50);

        //button to sort by first positions
        JButton sortByFirstPositionBtn = new JButton("Sort by first position");
        sortByFirstPositionBtn.setBounds(100, 100, 100, 50);
        sortByFirstPositionBtn.setSize(100, 50);

        //button to generate random race
        JButton randomRaceBtn = new JButton("Race");
        randomRaceBtn.setBounds(50, 100, 100, 50);
        randomRaceBtn.setSize(100, 50);

        //button to check race positions
        JButton checkRacePosition = new JButton("Check race positions");
        checkRacePosition.setBounds(25, 100, 100, 50);
        checkRacePosition.setSize(100, 50);

        //button to refresh table
        JButton refreshBtn = new JButton("Refresh table");
        refreshBtn.setBounds(10, 100, 100, 50);
        refreshBtn.setSize(100, 50);

        //button to check current race positions
        JButton currentRaceBtn = new JButton("Check current race");
        currentRaceBtn.setBounds(5, 100, 100, 50);
        currentRaceBtn.setSize(100, 50);

        //button to search for a driver
        JButton searchBtn = new JButton("Search Driver");
        searchBtn.setBounds(0, 100, 100, 50);
        searchBtn.setSize(100, 50);

        //button to probability race
        JButton probability = new JButton("Probability");
        probability.setBounds(0, 100, 100, 50);
        probability.setSize(100, 50);


        //add everything to panel and frame
        JFrame frame = new JFrame("Driver championship table");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        frame.setIconImage(icon);
        frame.add(panel);
        panel.add(sortByDescTotalPointsBtn);
        panel.add(sortByFirstPositionBtn);
        panel.add(randomRaceBtn);
        panel.add(checkRacePosition);
        panel.add(refreshBtn);
        panel.add(currentRaceBtn);
        panel.add(searchBtn);
        panel.add(probability);

        frame.setSize(900, 700);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Dialog frame to search driver implementation
        JFrame jFrame = new JFrame();

        JDialog driverDialog = new JDialog(jFrame);
        jFrame.setLayout(null);

        driverDialog.setBounds(500, 100, 400, 300);

        JTextField text;

        text = new JTextField("Enter driver name");
        text.setBounds(150, 90, 200, 30);

        JButton closeSearchDialog = new JButton("Close");
        closeSearchDialog.setBounds(200, 200, 200, 30);
        closeSearchDialog.setSize(100, 25);

        JButton search = new JButton("Search");
        search.setBounds(100, 200, 200, 30);
        search.setSize(100, 25);



        closeSearchDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverDialog.setVisible(false); //hiding the dialog box by default
            }
        });

        driverDialog.add(closeSearchDialog);
        driverDialog.add(search);
        driverDialog.add(text);
        driverDialog.setVisible(false);


        /*------------------------------------------------------------------------------------------------------------------*/
        sortByDescTotalPointsBtn.addActionListener(new ActionListener() { //action listener for descending order sort
            @Override
            public void actionPerformed(ActionEvent e) {


                DefaultTableModel tableModel = (DefaultTableModel) championTable.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new TotalComparatorReversed());// reversing the order by total
                addToMainTable(championDrivers, championTable);
            }
        });

        sortByFirstPositionBtn.addActionListener(new ActionListener() { //action listener for no of points sort
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) championTable.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new FirstComparator());// sorting according to first places
                addToMainTable(championDrivers, championTable);
            }
        });

        randomRaceBtn.addActionListener(new ActionListener() { //action listener for generating random race
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (championDrivers.size() < 10) {
                        errorMessage();
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) championTable.getModel();
                        tableModel.setRowCount(0);
                        LocalDate date1 = RandomDate(2021, 2021);
                        //creating date obj
                        manager.race(date1); //passing date obj to race method making it not null
                        Collections.sort(championDrivers, new TotalComparator()); //sorting them in ascending order
                        addToMainTable(championDrivers, championTable);
                    }
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

            }
        });

        refreshBtn.addActionListener(new ActionListener() { //refresh button to refresh the championship table
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) championTable.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new TotalComparator()); //sorting using ascending order
                addToMainTable(championDrivers, championTable);//adding drivers to the main table

            }
        });

        checkRacePosition.addActionListener(new ActionListener() { // check all races and its positions
            @Override
            public void actionPerformed(ActionEvent e) {
                if (championDrivers.size() < 10) {
                    errorRaceMessage();

                } else {
                    manager.showRaces(); //calling method to show all races
                    showRaceHistory(); // adding the frame to show all races
                }

            }
        });

        currentRaceBtn.addActionListener(new ActionListener() { //check current race positions
            @Override
            public void actionPerformed(ActionEvent e) {
                if (championDrivers.size() < 10) {
                    errorRaceMessage();
                } else {
                    showIndividualRaceFrame();
                }
            }
        });

        searchBtn.addActionListener(new ActionListener() { //search button to search for driver
            @Override
            public void actionPerformed(ActionEvent e) {
                if (championDrivers.size() < 10) {
                    errorSearchMessage();

                } else {
                    driverDialog.setVisible(true);
                }
            }
        });

        search.addActionListener(new ActionListener() { //search button after entering driver name
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text.getText().toUpperCase();
                SearchDriverResultFrame(name);//calling the search driver method with name as input
            }
        });

        probability.addActionListener(new ActionListener() { //race win according to probability
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel tableModel = (DefaultTableModel) championTable.getModel();
                tableModel.setRowCount(0);
                randomPositionAllocation();
                Collections.sort(championDrivers, new TotalComparator());// the order by total
                addToMainTable(championDrivers, championTable);
            }
        });
    }

    //Methods

    public static void searchRaceDriver(String name, JTable jTable) {  // method to search a driver

        for (Race race : races) {
            for (int j = 0; j < championDrivers.size(); j++) {
                if (race.getRaceMap().get(j).equals(name)) {
                    ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{" Race on " + race.getDate(), "Position " + (j + 1), race.getRaceMap().get(j)});
                }
            }
        }

    }

    public static void addToMainTable(ArrayList<Formula1Driver> f1, JTable jTable) { //adding the drivers to the table

        for (Formula1Driver driver : f1) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{
                    driver.getDriverName(), driver.getDriverTeam(), driver.getLocation(), driver.getFirstPosition(), driver.getSecondPosition(), driver.getThirdPosition(), driver.getTotalPoints(), driver.getTotalRaces()});
        }

    }


    public static void addToRaceHistoryTable(JTable jTable) { //adding the drivers to the history table
        Collections.sort(races, new dateSorting()); //sorting according to the date

        for (Race race : races) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{"Race " + " On " + race.getDate(), " ", " "});
            for (int j = 0; j < championDrivers.size(); j++) {
                ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{(j + 1), race.getRaceMap().get(j)});
            }
        }
    }

    public static void perRacePositions(JTable jTable) { //Getting individual positions of each race
        for (int j = 0; j < perRaceMap.size(); j++) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{(j + 1), perRaceMap.get(j)});
        }
    }


    public static void randomPositionAllocation() {
        if (!(championDrivers.size() < 10)) {
            LocalDate date;
            date = RandomDate(2021, 2021);

            HashMap<Integer, String> positionMap = new HashMap<>();//hashmap to add positions of the current race
            int[] probability = {40, 30, 10, 10, 2, 2, 2, 2, 2};//adding the probabilities as a percentage

            Collections.shuffle(championDrivers); //shuffling the drivers names randomly

            int winner = firstPositionProbability(probability);//getting the winner index

            String winnerDriver = "";
            for (Formula1Driver championDriver : championDrivers) {
                if (championDriver.getDriverName().equals(championDrivers.get(winner).getDriverName())) { //checking driver who has won
                    winnerDriver = championDrivers.get(winner).getDriverName(); //storing winning driver name
                     break;

                }
            }

            Collections.shuffle(championDrivers); //shuffling the drivers again randomly
            for (int i = 0; i < championDrivers.size(); i++) {
                if (championDrivers.get(i).getDriverName().equals(winnerDriver)) {
                    Collections.swap(championDrivers, 0, i);//swapping the winning driver to the first position
                }
            }


            for (int i = 0; i < championDrivers.size(); i++) {
                positionMap.put(i, championDrivers.get(i).getDriverName());//adding the positions to the position hashmap
            }

            perRaceMap.putAll(positionMap);
            races.add(new Race(date, positionMap)); //adding the race positions to the race class
            manager.statUpdate();//updating the stats
        } else {

            errorMessage();
        }

    }


    public static int firstPositionProbability(int[] probability) {

        int number = (int) (Math.random() * 100);
        for (int i = 0; i < probability.length; i++) {
            number -= probability[i];
            if (number < 0) {
                return i;
            }
        }
        return -1;
    }


    public static void showRaceHistory() { //shows the history of all races and its positions

        JTable table = new JTable(); //creating table
        table.setForeground(Color.WHITE);
        table.setOpaque(false);
        table.setBackground(new Color(255, 255, 255, 0));


        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver position", "Driver name"}));
        table.setDefaultEditor(Object.class, null);//makes  the cells non edit

        addToRaceHistoryTable(table);//calling the add to table method

        JButton previousBtn = new JButton("Close");
        previousBtn.setBounds(100, 100, 100, 50);
        previousBtn.setSize(100, 50);

        JPanel historyPanel = new JPanel();
        JScrollPane historyScrollPane = new JScrollPane();
        historyScrollPane.setViewport(new image());
        historyScrollPane.setViewportView(table);
        historyPanel.add(historyScrollPane);
        historyScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame historyFrame = new JFrame("Race Results Table");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        historyFrame.setIconImage(icon);

        historyFrame.add(historyPanel);
        historyPanel.add(previousBtn);

        historyFrame.setSize(900, 700);
        historyFrame.setVisible(true);
        historyFrame.setLayout(null);
        historyFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        previousBtn.addActionListener(new ActionListener() { //making the frame not visible on button click
            @Override
            public void actionPerformed(ActionEvent e) {
                historyFrame.setVisible(false);
            }
        });

    }

    public static void showIndividualRaceFrame() { //shows the latest race with its positions and drivers

        JTable tableRace = new JTable(); //creating tableRace
        tableRace.setForeground(Color.WHITE);
        tableRace.setOpaque(false);
        tableRace.setBackground(new Color(255, 255, 255, 0));


        tableRace.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver position", "Driver name"}));
        tableRace.setDefaultEditor(Object.class, null);//makes  the cells non edit
        perRacePositions(tableRace);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(100, 100, 100, 50);
        closeBtn.setSize(100, 50);

        JPanel racePanel = new JPanel();
        JScrollPane raceScrollPane = new JScrollPane();
        raceScrollPane.setViewport(new image());
        raceScrollPane.setViewportView(tableRace);
        racePanel.add(raceScrollPane);
        raceScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame raceFrame = new JFrame("Latest Race Results");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        raceFrame.setIconImage(icon);

        raceFrame.add(racePanel);
        racePanel.add(closeBtn);

        raceFrame.setSize(900, 700);
        raceFrame.setVisible(true);
        raceFrame.setLayout(null);
        raceFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceFrame.setVisible(false);
            }
        });

    }


    public static void SearchDriverResultFrame(String name) { //frame to show driver details from search option

        JTable searchTable = new JTable(); //creating tableRace
        searchTable.setForeground(Color.WHITE);
        searchTable.setOpaque(false);
        searchTable.setBackground(new Color(255, 255, 255, 0));


        searchTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Race date ", "Driver position", "Driver name"}));
        searchTable.setDefaultEditor(Object.class, null);//makes  the cells non edit
        searchRaceDriver(name, searchTable);//calling the method to search for driver

        JButton closeSearchBtn = new JButton("Close");
        closeSearchBtn.setBounds(100, 100, 100, 50);
        closeSearchBtn.setSize(100, 50);

        JPanel searchPanel = new JPanel();
        JScrollPane searchScrollPane = new JScrollPane();
        searchScrollPane.setViewport(new image());
        searchScrollPane.setViewportView(searchTable);
        searchPanel.add(searchScrollPane);
        searchScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame searchFrame = new JFrame("Driver Race Results");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        searchFrame.setIconImage(icon);

        searchFrame.add(searchPanel);
        searchPanel.add(closeSearchBtn);

        searchFrame.setSize(900, 700);
        searchFrame.setVisible(true);
        searchFrame.setLayout(null);
        searchFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        closeSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFrame.setVisible(false);
            }
        });

    }

    //Methods for error handling pop ups
    public static void errorMessage() {

        JOptionPane.showMessageDialog(frame, "Cannot run race without at least 10 drivers.", "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static void errorRaceMessage() {

        JOptionPane.showMessageDialog(frame, "No race found.", "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static void errorSearchMessage() {

        JOptionPane.showMessageDialog(frame, "Cannot search drivers without running a race.", "Alert", JOptionPane.WARNING_MESSAGE);
    }
    //https://www.oracle.com/java/technologies/painting.html

    public static class image extends JViewport {

        public image() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                Rectangle bounds = getViewRect();
                int x = Math.max(0, (bounds.width - background.getWidth()) / 2);
                int y = Math.max(0, (bounds.height - background.getHeight()) / 2);
                g.drawImage(background, x, y, this);
            }
        }
    }
}









