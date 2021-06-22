import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Difficulty extends JFrame implements ActionListener, MouseListener{
    private String difficulty;
    private Alien alien;

    public String getDifficulty(){
        return difficulty;
    }

    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        if(btn.equals("easy")){
            difficulty = "easy";
        }else if(btn.equals("medium")){
            difficulty = "medium";
        }else{
            difficulty = "difficult";
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void difficultySelect(String title){
        setTitle(title);
        setBounds(500, 300, 300, 100);
        setBackground(Color.red);

        JButton radio1 = new JButton("easy");
        JButton radio2 = new JButton("medium");
        JButton radio3 = new JButton("difficult");

        JPanel p = new JPanel();
        p.add(radio1);
        p.add(radio2);
        p.add(radio3);


        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        radio1.addActionListener(this);
        radio2.addActionListener(this);
        radio3.addActionListener(this);
        radio1.addMouseListener(this);
        radio2.addMouseListener(this);
        radio3.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Alien a = new Alien();
        alien = Alien.getInstance();
        try {
            alien.getFile(getDifficulty());
        }catch (Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        AlienGUI GUI = new AlienGUI(alien.getWord());
        GUI.getLengthOfAnswer(alien.getWord());
        GUI.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
