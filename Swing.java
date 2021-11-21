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
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.Object;


import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class Swing extends Formula1ChampionshipManager {
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
    static HashMap<Integer, String> driverStartingPositions = new HashMap<>();


    public static BufferedImage background;



    public static void main(String[] args) {

        new Swing();


        Collections.sort(championDrivers, new TotalComparator());// by default sorting the list in ascending order by total

        JTable t = new JTable(); //creating table
        t.setForeground(Color.WHITE);
        t.setOpaque(false);
        t.setBackground(new Color(255, 255, 255, 0));

        t.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver name", "Driver team", "Driver location", "First positions", "Second positions ", "Third positions", "Driver total points ", "Total races"}));
        t.setDefaultEditor(Object.class, null);//makes  the cells non edit

        addToMainTable(championDrivers, t);//calling the add to table method to add data
        t.getColumn("Driver name").setPreferredWidth(100);

        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewport(new ImageViewport());
        scrollPane.setViewportView(t);
        panel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(800, 500));

        //button to sort by total points
        JButton sortByDescTotalPointsBtn = new JButton("Sort by total points");
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
        JButton currentRaceBtn = new JButton("Check  current race");
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


        //add everything to panel
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
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Dialog frame to search driver implementation
        JFrame jFrame = new JFrame();

        JDialog driverDialog = new JDialog(jFrame);
        jFrame.setLayout(null);

        driverDialog.setBounds(500, 100, 400, 300);

        JTextField text;

        text = new JTextField(" Enter driver name");
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


                DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new TotalComparatorReversed());// reversing the order by total
                addToMainTable(championDrivers, t);
                System.out.println("total sort works");
            }
        });

        sortByFirstPositionBtn.addActionListener(new ActionListener() { //action listener for no of points sort
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new FirstComparator());// sorting according to first places
                addToMainTable(championDrivers, t);
                System.out.println("sort by first works");
            }
        });

        randomRaceBtn.addActionListener(new ActionListener() { //action listener for generating random race
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
                tableModel.setRowCount(0);

                try {
                    manager.race();
                    Collections.sort(championDrivers, new TotalComparator()); //sorting them in ascending order
                    addToMainTable(championDrivers, t);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }


            }
        });

        refreshBtn.addActionListener(new ActionListener() { //refresh button to refresh the championship table
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
                tableModel.setRowCount(0);
                Collections.sort(championDrivers, new TotalComparator()); //sorting using ascending order
                addToMainTable(championDrivers, t);//adding drivers to the main table
                System.out.println("updated");

            }
        });

        checkRacePosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.showRaces(); //calling method to show all races
                showRaceHistory(); // adding the frame to show all races


            }
        });

        currentRaceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showIndividualRaceFrame();
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                driverDialog.setVisible(true);


            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text.getText().toUpperCase();
                SearchDriverResultFrame(name);
            }
        });

        probability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
                tableModel.setRowCount(0);
                randomPositionAllocation();
                Collections.sort(championDrivers, new TotalComparator());// the order by total
                addToMainTable(championDrivers, t);


            }
        });


    }

    //Methods used in Swing implementation

    public static void searchRaceDriver(String name, JTable jTable) {  // method to search a driver
        for (int i = 0; i < races.size(); i++) {
            for (int j = 0; j < championDrivers.size(); j++) {
                if (races.get(i).getRaceMap().get(j).equals(name)) {
                    ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{" Race on " + races.get(i).getDate(), "Position " + (j + 1), races.get(i).getRaceMap().get(j)});

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


    public static void addToRaceTable(JTable jTable) { //adding the drivers to the table
        Collections.sort(races, new dateSorting()); //sorting according to the date

        for (int i = 0; i < races.size(); i++) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{"Race " + " On " + races.get(i).getDate(), " ", " "});
            for (int j = 0; j < championDrivers.size(); j++) {
                ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{(j + 1), races.get(i).getRaceMap().get(j)});
            }


        }

    }

    public static void individualRacePositions(JTable jTable) { //Getting individual positions of each race
        for (int j = 0; j < manager.perRaceMap.size(); j++) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{(j + 1), manager.perRaceMap.get(j)});

        }

    }


    public static void randomPositionAllocation() {
        Date date;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
        System.out.println(formatter.format(date));

        HashMap<Integer, String> positionMap = new HashMap<>();


        int[] percentages = {40, 30, 10, 10, 2, 2, 2, 2, 2};//adding the probabilities as a percentage


        Collections.shuffle(championDrivers); //shuffling the drivers names
        System.out.println("after shuffle " + championDrivers);

        for (int i = 0; i < championDrivers.size(); i++) {
            driverStartingPositions.put(i, championDrivers.get(i).getDriverName()); //adding the random driver names to a temp per race hashmap
        }

      //  System.out.println(driverStartingPositions);


        int winner = pickWinner(percentages);
        System.out.println(winner);


        String winnerDriver = "";


       for (int i = 0; i < championDrivers.size(); i++) {
            if (championDrivers.get(i).getDriverName().equals(championDrivers.get(winner).getDriverName())) {
                winnerDriver = championDrivers.get(winner).getDriverName();
                System.out.println(winnerDriver);


            }
        }


       // System.out.println(driverStartingPositions);
        Collections.shuffle(championDrivers);
        System.out.println("After final " +championDrivers);
        for(int i=0;i<championDrivers.size();i++){
            if(championDrivers.get(i).getDriverName().equals(winnerDriver)){
                Collections.swap(championDrivers,0,i);
            }
        }
        System.out.println(championDrivers);


        for (int i = 0; i < championDrivers.size(); i++) {
           positionMap.put(i, championDrivers.get(i).getDriverName());//adding the positions to the position hashmap
        }

      /*  for (int i = 0; i < championDrivers.size(); i++) {
            perRaceMap.put(i, championDrivers.get(i).getDriverName()); //
        }*/
        perRaceMap.putAll(positionMap);

       // System.out.println("Hashmap " + positionMap);

        races.add(new Race(date, positionMap)); //adding the race positions to the race class
       // System.out.println("race added");

        manager.statUpdate();

    }
    public static int pickWinner(int[] percentages) {



            int randomNumber = (int) (Math.random() * 100);
            int countdown = randomNumber;
            for (int i = 0; i < percentages.length; i++) {
                countdown -= percentages[i];
                if (countdown < 0) {
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

        addToRaceTable(table);

        JButton previousBtn = new JButton("Close");
        previousBtn.setBounds(100, 100, 100, 50);
        previousBtn.setSize(100, 50);

        JPanel historyPanel = new JPanel();
        JScrollPane historyScrollPane = new JScrollPane();
        historyScrollPane.setViewport(new ImageViewport());
        historyScrollPane.setViewportView(table);
        historyPanel.add(historyScrollPane);
        historyScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame historyFrame = new JFrame("Race table");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        historyFrame.setIconImage(icon);

        historyFrame.add(historyPanel);
        historyPanel.add(previousBtn);

        historyFrame.setSize(900, 700);
        historyFrame.setVisible(true);
        historyFrame.setLayout(null);
        historyFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        previousBtn.addActionListener(new ActionListener() {
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
        individualRacePositions(tableRace);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(100, 100, 100, 50);
        closeBtn.setSize(100, 50);

        JPanel racePanel = new JPanel();
        JScrollPane raceScrollPane = new JScrollPane();
        raceScrollPane.setViewport(new ImageViewport());
        raceScrollPane.setViewportView(tableRace);
        racePanel.add(raceScrollPane);
        raceScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame raceFrame = new JFrame("Race tableRace");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        raceFrame.setIconImage(icon);

        raceFrame.add(racePanel);
        racePanel.add(closeBtn);

        raceFrame.setSize(900, 700);
        raceFrame.setVisible(true);
        raceFrame.setLayout(null);
        raceFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


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
                new String[]{"Race date ", "Driver position","Driver name"}));
        searchTable.setDefaultEditor(Object.class, null);//makes  the cells non edit
        searchRaceDriver(name,searchTable);//calling the method to search for driver

        JButton closeSearchBtn = new JButton("Close");
        closeSearchBtn.setBounds(100, 100, 100, 50);
        closeSearchBtn.setSize(100, 50);

        JPanel searchPanel = new JPanel();
        JScrollPane searchScrollPane = new JScrollPane();
        searchScrollPane.setViewport(new ImageViewport());
        searchScrollPane.setViewportView(searchTable);
        searchPanel.add(searchScrollPane);
        searchScrollPane.setPreferredSize(new Dimension(850, 500));

        JFrame searchFrame = new JFrame("Race tableRace");//creating frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Formula1CW\\src\\f1.png");
        searchFrame.setIconImage(icon);

        searchFrame.add(searchPanel);
        searchPanel.add(closeSearchBtn);

        searchFrame.setSize(900, 700);
        searchFrame.setVisible(true);
        searchFrame.setLayout(null);
        searchFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        closeSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFrame.setVisible(false);
            }
        });

    }

    //https://www.oracle.com/java/technologies/painting.html

    public Swing() {
        try {
            background = ImageIO.read(new File("D:\\Formula1CW\\src\\F1_2022_Aston_Side_Left.jpg"));//adding a background picture
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public static class ImageViewport extends JViewport {

        public ImageViewport() {
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









