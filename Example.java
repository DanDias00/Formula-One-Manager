import javax.swing.*;


public class Example extends JFrame {
    public static void main(String[] args) {
        JFrame j = new JFrame("New frame");
        j.setSize(475, 250);
        j.setLayout(null);
        JTextField t  = new JTextField("Enter value");
        t.setBounds(100,60,100,50);
        JTextField t2  = new JTextField("Enter value2");
        t2.setBounds(230,60,100,50);
        JButton b = new JButton("Click");
        b.setBounds(30,80,80,40);
        j.add(b);
        j.add(t);
        j.add(t2);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
