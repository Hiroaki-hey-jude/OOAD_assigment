import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AlienGUI extends JFrame implements MouseListener, ActionListener{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    public static JPanel mainPanel, highestPanel, secondHighestPanel, bottomPanel, thirdHighest;
    public static JLabel insideHighest, hiroImageLabel, maryamImageLabel;
    public static JLabel insideThird = new JLabel();
    public static JLabel insideThird2 = new JLabel();
    public static JButton answerButton;
    public String answer;
    public ArrayList<String> wordsOnScreen = new ArrayList<>();
    public static int correctCount;
    public static int wrongCount;
    public String challenge;
    private String playerGuess;

    public void setPlayerGuess(String playerGuess){
        this.playerGuess = playerGuess;
    }

    public String getPlayerGuess(){
        return playerGuess;
    }

    public boolean guessWord(){
        String listStringOutput = "";
        for(int i = 0; i < answer.length(); i++){
            if(wordsOnScreen.get(i) != "-"){

            }
            else if(playerGuess.equals(answer.substring(i, i+1))){
                wordsOnScreen.set(i, playerGuess);
                correctCount++;
            }
            else{
                wordsOnScreen.set(i, "-");
            }
        }
        for (String s : wordsOnScreen)
        {
            listStringOutput += s + "\t";
        }
        insideHighest.setText(listStringOutput);
        win();
        return listStringOutput.contains(playerGuess);
    }

    public void getLengthOfAnswer(String word){
        if(answer.length() == 4)
            for(int i = 0; i < 4; i++)
                wordsOnScreen.add("-");
        else if(answer.length() == 8)
            for(int i = 0; i < 8; i++)
                wordsOnScreen.add("-");
        else
            for(int i = 0; i < 12; i++)
                wordsOnScreen.add("-");
    }

    public void win(){
        if(answer.length() == correctCount) {
            JOptionPane.showMessageDialog(null, "you win!  you saved the world!", "Congrats!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void answerAllOnce(){
        challenge = JOptionPane.showInputDialog(null,
                    "if you fail, you lose", null);

        if(challenge == null){
        }
        else if(challenge.equals(answer)){
            JOptionPane.showMessageDialog(null, "you win! you saved the world!", "Congrats!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "you lose!", "sorry!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public AlienGUI(String word){
        ImageIcon hiro = new ImageIcon("./res/Hiro.PNG");
        ImageIcon Maryam = new ImageIcon("./res/Maryam.PNG");

        answer = word;
        setSize(WIDTH, HEIGHT);
        setTitle("Alien game!" );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(answer);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 0));
        mainPanel.setBackground(Color.white);

        if(answer.length() == 4)
            insideHighest = new JLabel("----");
        else if(answer.length() == 8)
            insideHighest = new JLabel("--------");
        else
            insideHighest = new JLabel("------------");

        insideHighest.setFont(new Font("Century", Font.ITALIC, 85));

        answerButton = new JButton("answer all at once chance");
        answerButton.addMouseListener(this);

        highestPanel = new JPanel();
        highestPanel.add(insideHighest);
        highestPanel.add(answerButton);

        hiroImageLabel = new JLabel(hiro);
        maryamImageLabel = new JLabel(Maryam);

        secondHighestPanel = new JPanel();
        secondHighestPanel.setBackground(Color.white);
        secondHighestPanel.add(hiroImageLabel);
        secondHighestPanel.add(maryamImageLabel);

        highestPanel.setBackground(Color.WHITE);

        thirdHighest = new JPanel();
        thirdHighest.setBackground(Color.white);
        thirdHighest.add(insideThird);
        thirdHighest.add(insideThird2);

        mainPanel.add(highestPanel);
        mainPanel.add(secondHighestPanel);
        mainPanel.add(thirdHighest);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 3));
        bottomPanel.setBackground(Color.GRAY);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);

        createButtons(bottomPanel);


    }

    public static void main(String args[]){
        Difficulty diff = new Difficulty();
        diff.difficultySelect("select difficulty level");
        diff.setVisible(true);

    }


    public void appearAlien(){
        ImageIcon alien = new ImageIcon("./res/Alien.PNG");
        ImageIcon egg = new ImageIcon("./res/egg.PNG");
        ImageIcon halfConstructed = new ImageIcon("./res/constructed1.PNG");
        ImageIcon aboutBeDone = new ImageIcon("./res/constructed2.PNG");
        ImageIcon constructed = new ImageIcon("./res/constructed.PNG");
        ImageIcon transparent = new ImageIcon("./res/transparent.PNG");
        switch (wrongCount){
            case 1:
                insideThird.setIcon(egg);
                break;
            case 2:
                insideThird.setIcon(alien);
                break;
            case 3:
                hiroImageLabel.setIcon(alien);
                break;
            case 4:
                insideThird2.setIcon(halfConstructed);
                break;
            case 5:
                insideThird2.setIcon(aboutBeDone);
                break;
            case 6:
                insideThird2.setIcon(constructed);
                maryamImageLabel.setIcon(transparent);
                JOptionPane.showMessageDialog(null, "Maryam is being probed, Hiro got eaten...", "maryam is taken away, lose!!",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                break;


        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Sound sound = new Sound();
        playerGuess = e.getActionCommand();
        sound.setSound(sound.getClickSound());
        sound.play();

        if(!guessWord()){
            wrongCount++;
        }
        appearAlien();

    }

    public void createButtons(JPanel bottomPanel) {

        JButton[] buttons = new JButton[26];
        String[] allAlphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(allAlphabet[i]);
            buttons[i].setSize(45, 45);

            buttons[i].setActionCommand(allAlphabet[i]);
            buttons[i].addActionListener(this);

            bottomPanel.add(buttons[i]);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        answerAllOnce();
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
