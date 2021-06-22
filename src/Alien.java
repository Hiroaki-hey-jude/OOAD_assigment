import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.*;

/**
 * implemented Singleton which is a creational design pattern that lets us
 * ensure that a class has only one instance to make sure files will be read only once.
 */
public class Alien {
    private String randWord;
    private Random rand = new Random();
    private Scanner scan1, scan2, scan3;

    private static Alien instance = new Alien();

    private Alien(){
        try {
            scan1 = new Scanner(new File("./res/fourWords.txt"));
            scan2 = new Scanner(new File("./res/eightWords.txt"));
            scan3 = new Scanner(new File("./res/twelveWords.txt"));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static Alien getInstance(){
        return instance;
    }

    public void getFile(String difficulty) {

        List<String> words = new ArrayList<>();

        if(difficulty == "easy"){
            while (scan1.hasNext()){
                words.add(scan1.nextLine());
            }
        }else if(difficulty == "medium"){
            while (scan2.hasNext()){
                words.add(scan2.nextLine());
            }
        }else{
            while (scan3.hasNext()){
                words.add(scan3.nextLine());
            }
        }

        randWord = words.get(rand.nextInt(words.size()));
    }

    public String getWord(){
        return randWord;
    }

}
