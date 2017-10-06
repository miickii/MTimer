package micki.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import micki.AlgScrambler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Controller{

    @FXML private BorderPane borderPane;
    @FXML private Label curScrambleLabel;
    @FXML private Hyperlink nextScrambleButton;
    @FXML private Label numberOfTimes;
    @FXML private Label curTime;
    @FXML private Label bestTime;
    @FXML private Label worstTime;
    @FXML private Label curAvg5;
    @FXML private Label bestAvg5;
    @FXML private Label curAvg12;
    @FXML private Label bestAvg12;
    @FXML private Label curAvg100;
    @FXML private Label bestAvg100;
    @FXML private Label curAvg1000;
    @FXML private Label bestAvg1000;
    @FXML private Label sessionAvg;
    @FXML private Label timerLabel;
    @FXML private Hyperlink resetSessionButton;
    @FXML private Label timesLabel;
    @FXML private TextField ollTextField;
    @FXML private Hyperlink solutionButton;
    @FXML private Label solutionLabel;
    @FXML private HBox topBox;
    @FXML private VBox leftBox;
    @FXML private VBox rightBox;
    @FXML private HBox bottomBox;

    private float bestTimeTracker = 999999999;
    private float lastBestTime = 999999999;
    private float worstTimeTracker = 0;
    private float lastWorstTime = 0;
    private float timerTracker;

    private int[] ollsIKnow = {9, 29, 30, 35, 37, 55, 56};

    private boolean timing = false;
    private boolean resetClickedOnce = false;

    private String[] auf = {"", "U' ", "U ", "U2 "};
    private String allTimes = "";
    private String curScramble = "";
    private String lastScamble = "";

    private AlgScrambler algScrambler;
    private File file;
    private Scanner in;
    private Random random;
    private Timeline time;
    Scene scene;

    private Map<String, Integer> pllChecker;

    //todo: avg of 50 instead of 1000

    public void initializeScene(Scene scene)
    {
        this.scene = borderPane.getScene();
        this.scene.setOnKeyPressed(event -> {

        });
        this.scene.setOnKeyReleased(event -> {

            switch(event.getCode())
            {
                case SPACE:
                     timerRunning(timing);
                    break;
                case A:
                    reset();
                    break;
                case N:
                    setScramble();
                    break;
                case Q:
                    deleteLastTime();
                    break;
                case P:
                    lastScrambleMethod();
                    break;
            }
        });
    }

    public Controller()
    {
        algScrambler = new AlgScrambler();
        file = new File("allAlgs.txt");
        random = new Random();
        pllChecker = new HashMap<>();

    }

    public void resetSession(ActionEvent actionEvent)
    {
        reset();
    }

    public void getSolution(ActionEvent actionEvent)
    {
        setSolution(true);
        borderPane.requestFocus();
    }

    public void getNextScramble(ActionEvent actionEvent)
    {
        setScramble();
        borderPane.requestFocus();
        /*int num = 1000;
        while(num > 1)
        {
            setScramble();
            borderPane.requestFocus();
            num--;
            System.out.println(num);
        }
        int num2 = 1;
        for(String pll : pllChecker.keySet())
        {
            System.out.println(pll + "   " + num2 + ": " + pllChecker.get(pll));
            num2++;
        }*/
    }

    public void getLastScramble(ActionEvent actionEvent)
    {
        lastScrambleMethod();
    }

    public void lastScrambleMethod()
    {
        curScramble = lastScamble;

        curScrambleLabel.setText(lastScamble);
        borderPane.requestFocus();
    }

    public void reset()
    {
        if(!resetClickedOnce)
        {
            resetSessionButton.setText("SURE?");
            resetClickedOnce = true;
        }
        else {
            resetSessionButton.setText("RESET");
            updateEverything();
            timesLabel.setText("");
            resetClickedOnce = false;
        }
        borderPane.requestFocus();
    }

    public void deleteLastTime()
    {
        String[] tAllTimes = splitTimes();
        String updatedTimeLabel = "";

        for(int i = 0; i < tAllTimes.length-1; i++)
        {
            if(i == tAllTimes.length-2) //third to last time
            {
                worstTimeTracker = lastWorstTime;
                bestTimeTracker = lastBestTime;

                timesLabel.setText(allTimes = updatedTimeLabel);
                updateEverything(Float.parseFloat(tAllTimes[i]));
                return;
            }
            else
            {
                updatedTimeLabel += tAllTimes[i] + ", ";
            }
        }

    }

    public void updateEverything()
    {
        String[] allTimesInLabel = timesLabel.getText().split(",");

        setScramble();
        updateTimes("DNF");
    }

    public void updateEverything(float curTime)
    {
        updateTimes(curTime);
    }

    private String randomAuf(int type)
    {
        switch(type)
        {
            case 0:
                return auf[random.nextInt(4)];
            case 1:
                return auf[random.nextInt(2)];
            case 2:
                return "";
            default:
                return "not a pll";
        }
    }

    private void setScramble()
    {
        int selectedOll;
        int oll;
        int pll;

        lastScamble = curScramble;

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] ollA = ollTextField.getText().replaceAll(" ", "").split("/"); //splitting the OLL algs in textfield
        String randomOll = ollA[random.nextInt(ollA.length)].toLowerCase();

        try
        {
            oll = Integer.parseInt(randomOll); //getting a random one of the selected oll's and parse it to int
        }
        catch (NumberFormatException e)
        {
            switch(randomOll)
            {
                case "1lll":
                    oll = ollsIKnow[random.nextInt(ollsIKnow.length)];
                    break;
                case "zbll":
                    oll = random.nextInt(7) + 21;
                    break;
                default:
                    oll = random.nextInt(57)+1;
                    break;
            }
        }

        pll = random.nextInt(21)+1;

        String ollAlg = "";
        String pllAlg = "";

        while(oll > 0)
        {
            oll--;
            ollAlg = in.nextLine().split(":")[1];
        }
        in.close();

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        char curAlgName = ' '; //we use it to check for pll's and if they are symmetrical
        while(pll > 0)
        {
            String[] arr = in.nextLine().split(":");

            curAlgName = arr[0].toLowerCase().toCharArray()[0];
            if(curAlgName >= 97 && curAlgName <=122)
            {
                pll--;
                pllAlg = arr[1];
            }
        }
        if(curAlgName == 'z' || curAlgName == 'e')
        {
            ollAlg = randomAuf(1) + ollAlg;
        }
        else if(curAlgName == 'h' || curAlgName == 'n')
        {
            ollAlg = randomAuf(2) + ollAlg;
        }
        else
        {
            ollAlg = randomAuf(0) + ollAlg;
        }
        pllAlg = randomAuf(0) + pllAlg;

        String fullScramble = pllAlg + ollAlg + randomAuf(0);
        /*if(pllChecker.containsKey(fullScramble))
        {
            pllChecker.put(fullScramble, pllChecker.get(fullScramble)+1); //THIS IS TO CHECK IF EVERY PLL GETS SCRAMBLED
        }
        else
        {
            pllChecker.put(fullScramble, 1);
        }*/
        String scramble = AlgScrambler.obfusticate(fullScramble);
        curScrambleLabel.setText(scramble);

        in.close();

        /*if(oll == 0) selectedOll = 57-oll-1;
        else selectedOll = 57-oll;

        pll = random.nextInt(21);

        while(oll >= 2)
        {
            oll--;
            in.nextLine();
        }
        String ollAlg = auf[random.nextInt(3)] + in.nextLine().split(":")[1];
        while(pll >= 1-selectedOll)
        {
            pll--;
            in.nextLine();
        }
        String pllAlg = auf[random.nextInt(3)] + in.nextLine().split(":")[1];
        String scramble = AlgScrambler.obfusticate(pllAlg + ollAlg);
        curScrambleLabel.setText(scramble);

        in.close();*/

        curScramble = scramble;
        setSolution(false);
    }

    private void setSolution(boolean isOn)
    {
        //depending on is on update solution from nothing to a solution from algScrambler
        if(isOn)
        {
            solutionLabel.setText(algScrambler.invert(curScrambleLabel.getText()));
        }
        else
        {
            solutionLabel.setText("");
        }
    }

    public void timerRunning(boolean timing)
    {
        if(timing)
        {
            time.stop();

            timerStopped(timerTracker);

            this.timing = false;

        }
        else {

            time = new Timeline();

            this.timing = true;
            setVisibility(false);
            timerLabel.setTextFill(Paint.valueOf("#00e600"));

            time.setCycleCount(Timeline.INDEFINITE);
            if (time != null) {
                time.stop();
            }
            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timerTracker += 0.01;
                    timerLabel.setText(String.format("%.2f", timerTracker));
                }
            });

            time.getKeyFrames().add(keyFrame);
            time.play();
        }

    }

    private void timerStopped(float curTime)
    {
        timerLabel.setTextFill(Paint.valueOf("#ffffff"));
        resetClickedOnce = false;
        resetSessionButton.setText("RESET");

        lastWorstTime = worstTimeTracker;
        lastBestTime = bestTimeTracker;

        updateEverything(curTime);
        setVisibility(true);
    }

    private void setVisibility(boolean visible)
    {
        topBox.setVisible(visible);
        leftBox.setVisible(visible);
        rightBox.setVisible(visible);
        bottomBox.setVisible(visible);
    }

    private float calculateAvg(int amount, String[] allTimes)
    {
        float totalSum = 0;
        float best = 999999999;
        float worst = 0;

        for(int i = allTimes.length-1; i >= allTimes.length-amount; i--)
        {
            if(Float.parseFloat(allTimes[i]) < best)
                best = Float.parseFloat(allTimes[i]);
            if(Float.parseFloat(allTimes[i]) > worst)
                worst = Float.parseFloat(allTimes[i]);
        }

        for(int i = allTimes.length-1; i >= allTimes.length-amount; i--)
        {
            if(Float.parseFloat(allTimes[i]) != best && Float.parseFloat(allTimes[i]) != worst)
                totalSum += Float.parseFloat(allTimes[i]);
        }

        return Float.parseFloat(String.format(Locale.US,"%.2f", (totalSum/(amount-2))));
    }

    private void updateAvgs(String[] allTimes)
    {
        float avg;
        float curBestAvg;

        if(allTimes.length >= 5)
        {
            avg = calculateAvg(5, allTimes);
            try {
                curBestAvg = Float.parseFloat(bestAvg5.getText().replaceAll("\\s", "").split(":")[1]); //parsing the String from curAvg5 then removing all " " and then splitting by : and then selecting number 2 item in array
            }
            catch (NumberFormatException e)
            {
                curBestAvg = avg;
            }

            if(avg <= curBestAvg) bestAvg5.setText("best avg5: " + avg);
            curAvg5.setText("current avg5: " + avg);
        }
        else
        {
            curAvg5.setText("current avg5: DNF");
            bestAvg5.setText("best avg5: DNF");
        }

        if(allTimes.length >= 12)
        {
            avg = calculateAvg(12, allTimes);
            try {
                curBestAvg = Float.parseFloat(bestAvg12.getText().replaceAll("\\s", "").split(":")[1]); //parsing the String from curAvg5 then removing all " " and then splitting by : and then selecting number 2 item in array
            }
            catch (NumberFormatException e)
            {
                curBestAvg = avg;
            }

            if(avg <= curBestAvg) bestAvg12.setText("best avg12: " + avg);
            curAvg12.setText("current avg12: " + avg);
        }
        else
        {
            curAvg12.setText("current avg12: DNF");
            bestAvg12.setText("best avg12: DNF");
        }

        if(allTimes.length >= 3)
        {
            avg = calculateAvg(allTimes.length, allTimes);

            sessionAvg.setText("session avg: " + avg);
        }
        else
        {
            sessionAvg.setText("session avg: DNF");
        }

        if(allTimes.length >= 100)
        {
            avg = calculateAvg(100, allTimes);
            try {
                curBestAvg = Float.parseFloat(bestAvg100.getText().replaceAll("\\s", "").split(":")[1]); //parsing the String from curAvg5 then removing all " " and then splitting by : and then selecting number 2 item in array
            }
            catch (NumberFormatException e)
            {
                curBestAvg = avg;
            }

            if(avg <= curBestAvg) bestAvg100.setText("best avg100: " + avg);
            curAvg100.setText("current avg100: " + avg);
        }
        else
        {
            curAvg100.setText("current avg100: DNF");
            bestAvg100.setText("best avg100: DNF");
        }

        if(allTimes.length >= 1000)
        {
            avg = calculateAvg(1000, allTimes);
            try {
                curBestAvg = Float.parseFloat(bestAvg1000.getText().replaceAll("\\s", "").split(":")[1]); //parsing the String from curAvg5 then removing all " " and then splitting by : and then selecting number 2 item in array
            }
            catch (NumberFormatException e)
            {
                curBestAvg = avg;
            }

            if(avg <= curBestAvg) bestAvg1000.setText("best avg1000: " + avg);
            curAvg1000.setText("current avg1000: " + avg);
        }
        else
        {
            curAvg1000.setText("current avg1000: DNF");
            bestAvg1000.setText("best avg1000: DNF");
        }

    }

    private void updateBestTimes(float recentTime)
    {
        curTime.setText("current time: " + String.valueOf(recentTime));
        if(recentTime <= bestTimeTracker)
        {
            bestTimeTracker = recentTime;
        }
        if(recentTime >= worstTimeTracker)
        {
            worstTimeTracker = recentTime;
        }
        bestTime.setText("best time: " + bestTimeTracker);
        worstTime.setText("worst time: " + worstTimeTracker);
    }

    private void updateTimes(float curTime)
    {
        String formattedTime = String.format(Locale.US,"%.2f", curTime);
        timesLabel.setText(allTimes += formattedTime + ", ");

        String[] allTimesInLabel = splitTimes();

        updateBestTimes(Float.parseFloat(formattedTime));
        updateAvgs(allTimesInLabel);
        setScramble();

        timerLabel.setText(formattedTime);
        numberOfTimes.setText("number of times: " + String.valueOf(allTimesInLabel.length));

        timerTracker = 0.00f;
    }

    private String[] splitTimes()
    {
        String timesLabelNoSpace = timesLabel.getText().replaceAll("\\s", ""); // removing spaces
        return timesLabelNoSpace.split(",");
    }

    private void updateTimes(String time)
    {
        numberOfTimes.setText("number of times: " + time);
        curTime.setText("current time: " + time);
        bestTime.setText("best time: " + time);
        worstTime.setText("worst time: " + time);
        bestTimeTracker = 999999999;
        worstTimeTracker = 0;
        curAvg5.setText("current avg5: " + time);
        bestAvg5.setText("best avg5: " + time);
        curAvg12.setText("current avg12: " + time);
        bestAvg12.setText("best avg12: " + time);
        curAvg100.setText("current avg100: " + time);
        bestAvg100.setText("best avg100: " + time);
        curAvg1000.setText("current avg1000: " + time);
        bestAvg1000.setText("best avg1000: " + time);
        sessionAvg.setText("session avg: " + time);
        timesLabel.setText("");
        timerLabel.setText("0.00");

        allTimes = "";
        timing = false;

    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
