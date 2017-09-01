package micki;

import org.kociemba.twophase.Search;
import org.kociemba.twophase.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgScrambler {

    //THIS CLASS IS MADE BY TAO YU, I HAVE NOT CODED ANY OF IT

    private static final String[] solved = { "U", "U", "U", "U", "U", "U", "U",
            "U", "U", "R", "R", "R", "R", "R", "R", "R", "R", "R", "F", "F",
            "F", "F", "F", "F", "F", "F", "F", "D", "D", "D", "D", "D", "D",
            "D", "D", "D", "L", "L", "L", "L", "L", "L", "L", "L", "L", "B",
            "B", "B", "B", "B", "B", "B", "B", "B" };
    private String[] cube = { "U", "U", "U", "U", "U", "U", "U", "U", "U", "R",
            "R", "R", "R", "R", "R", "R", "R", "R", "F", "F", "F", "F", "F",
            "F", "F", "F", "F", "D", "D", "D", "D", "D", "D", "D", "D", "D",
            "L", "L", "L", "L", "L", "L", "L", "L", "L", "B", "B", "B", "B",
            "B", "B", "B", "B", "B" };

    private void updateArray(int... indexes) {
        String[] cubeCopy = cube.clone();
        for (int i = 0; i < 54; i++) {
            cube[i] = cubeCopy[indexes[i]];
        }

    }

    public AlgScrambler(String alg) {
        doAlgorithm(alg);
    }

    public AlgScrambler() {
    }

    public void reset() {
        this.cube = solved;
    }

    private void doU(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(6, 3, 0, 7, 4, 1, 8, 5, 2, 45, 46, 47, 12, 13, 14, 15,
                    16, 17, 9, 10, 11, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                    31, 32, 33, 34, 35, 18, 19, 20, 39, 40, 41, 42, 43, 44, 36,
                    37, 38, 48, 49, 50, 51, 52, 53);
        }

    }

    private void doR(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(0, 1, 20, 3, 4, 23, 6, 7, 26, 15, 12, 9, 16, 13, 10,
                    17, 14, 11, 18, 19, 29, 21, 22, 32, 24, 25, 35, 27, 28, 51,
                    30, 31, 48, 33, 34, 45, 36, 37, 38, 39, 40, 41, 42, 43, 44,
                    8, 46, 47, 5, 49, 50, 2, 52, 53);
        }

    }

    private void doF(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(0, 1, 2, 3, 4, 5, 44, 41, 38, 6, 10, 11, 7, 13, 14, 8,
                    16, 17, 24, 21, 18, 25, 22, 19, 26, 23, 20, 15, 12, 9, 30,
                    31, 32, 33, 34, 35, 36, 37, 27, 39, 40, 28, 42, 43, 29, 45,
                    46, 47, 48, 49, 50, 51, 52, 53);
        }

    }

    private void doD(int times) {

        for (int i = 0; i < times; i++) {
            updateArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 24,
                    25, 26, 18, 19, 20, 21, 22, 23, 42, 43, 44, 33, 30, 27, 34,
                    31, 28, 35, 32, 29, 36, 37, 38, 39, 40, 41, 51, 52, 53, 45,
                    46, 47, 48, 49, 50, 15, 16, 17);
        }

    }

    private void doL(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(53, 1, 2, 50, 4, 5, 47, 7, 8, 9, 10, 11, 12, 13, 14,
                    15, 16, 17, 0, 19, 20, 3, 22, 23, 6, 25, 26, 18, 28, 29,
                    21, 31, 32, 24, 34, 35, 42, 39, 36, 43, 40, 37, 44, 41, 38,
                    45, 46, 33, 48, 49, 30, 51, 52, 27);
        }

    }

    private void doB(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(11, 14, 17, 3, 4, 5, 6, 7, 8, 9, 10, 35, 12, 13, 34,
                    15, 16, 33, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                    30, 31, 32, 36, 39, 42, 2, 37, 38, 1, 40, 41, 0, 43, 44,
                    51, 48, 45, 52, 49, 46, 53, 50, 47);
        }

    }

    private void doE(int times) {

        for (int i = 0; i < times; i++) {
            updateArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 21, 22, 23, 15,
                    16, 17, 18, 19, 20, 39, 40, 41, 24, 25, 26, 27, 28, 29, 30,
                    31, 32, 33, 34, 35, 36, 37, 38, 48, 49, 50, 42, 43, 44, 45,
                    46, 47, 12, 13, 14, 51, 52, 53);
        }

    }

    private void doM(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(0, 52, 2, 3, 49, 5, 6, 46, 8, 9, 10, 11, 12, 13, 14,
                    15, 16, 17, 18, 1, 20, 21, 4, 23, 24, 7, 26, 27, 19, 29,
                    30, 22, 32, 33, 25, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
                    45, 34, 47, 48, 31, 50, 51, 28, 53);
        }

    }

    private void doS(int times) {
        for (int i = 0; i < times; i++) {
            updateArray(0, 1, 2, 43, 40, 37, 6, 7, 8, 9, 3, 11, 12, 4, 14, 15,
                    5, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 16,
                    13, 10, 33, 34, 35, 36, 30, 38, 39, 31, 41, 42, 32, 44, 45,
                    46, 47, 48, 49, 50, 51, 52, 53);
        }

    }

    private void doX(int times) {
        for (int i = 0; i < times; i++) {
            doR(1);
            doM(3);
            doL(3);
        }
    }

    private void doY(int times) {
        for (int i = 0; i < times; i++) {

            doU(1);
            doE(3);
            doD(3);
        }
    }

    private void doZ(int times) {
        for (int i = 0; i < times; i++) {

            doF(1);
            doS(1);
            doB(3);
        }
    }

    private void doUw(int times) {
        for (int i = 0; i < times; i++) {
            doE(3);
            updateArray(6, 3, 0, 7, 4, 1, 8, 5, 2, 45, 46, 47, 12, 13, 14, 15,
                    16, 17, 9, 10, 11, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                    31, 32, 33, 34, 35, 18, 19, 20, 39, 40, 41, 42, 43, 44, 36,
                    37, 38, 48, 49, 50, 51, 52, 53);

        }

    }

    private void doRw(int times) {

        for (int i = 0; i < times; i++) {
            doM(3);
            updateArray(0, 1, 20, 3, 4, 23, 6, 7, 26, 15, 12, 9, 16, 13, 10,
                    17, 14, 11, 18, 19, 29, 21, 22, 32, 24, 25, 35, 27, 28, 51,
                    30, 31, 48, 33, 34, 45, 36, 37, 38, 39, 40, 41, 42, 43, 44,
                    8, 46, 47, 5, 49, 50, 2, 52, 53);
        }

    }

    private void doFw(int times) {
        for (int i = 0; i < times; i++) {
            doS(1);
            updateArray(0, 1, 2, 3, 4, 5, 44, 41, 38, 6, 10, 11, 7, 13, 14, 8,
                    16, 17, 24, 21, 18, 25, 22, 19, 26, 23, 20, 15, 12, 9, 30,
                    31, 32, 33, 34, 35, 36, 37, 27, 39, 40, 28, 42, 43, 29, 45,
                    46, 47, 48, 49, 50, 51, 52, 53);
        }

    }

    private void doDw(int times) {

        for (int i = 0; i < times; i++) {
            doE(1);
            updateArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 24,
                    25, 26, 18, 19, 20, 21, 22, 23, 42, 43, 44, 33, 30, 27, 34,
                    31, 28, 35, 32, 29, 36, 37, 38, 39, 40, 41, 51, 52, 53, 45,
                    46, 47, 48, 49, 50, 15, 16, 17);
        }

    }

    private void doLw(int times) {

        for (int i = 0; i < times; i++) {
            doM(1);
            updateArray(53, 1, 2, 50, 4, 5, 47, 7, 8, 9, 10, 11, 12, 13, 14,
                    15, 16, 17, 0, 19, 20, 3, 22, 23, 6, 25, 26, 18, 28, 29,
                    21, 31, 32, 24, 34, 35, 42, 39, 36, 43, 40, 37, 44, 41, 38,
                    45, 46, 33, 48, 49, 30, 51, 52, 27);
        }

    }

    private void doBw(int times) {
        for (int i = 0; i < times; i++) {
            doS(3);
            updateArray(11, 14, 17, 3, 4, 5, 6, 7, 8, 9, 10, 35, 12, 13, 34,
                    15, 16, 33, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                    30, 31, 32, 36, 39, 42, 2, 37, 38, 1, 40, 41, 0, 43, 44,
                    51, 48, 45, 52, 49, 46, 53, 50, 47);
        }

    }

    public void printCube() {

        int u = 1;
        int r = 2;
        int f = 3;
        int d = 4;
        int l = 5;
        int b = 6;

        pspace(3);
        printIndexes(u, 1, 3);
        System.out.println();
        pspace(3);
        printIndexes(u, 4, 3);
        System.out.println();
        pspace(3);
        printIndexes(u, 7, 3);
        System.out.println();

        printIndexes(l, 1, 3);
        printIndexes(f, 1, 3);
        printIndexes(r, 1, 3);
        printIndexes(b, 1, 3);
        System.out.println();
        printIndexes(l, 4, 3);
        printIndexes(f, 4, 3);
        printIndexes(r, 4, 3);
        printIndexes(b, 4, 3);
        System.out.println();
        printIndexes(l, 7, 3);
        printIndexes(f, 7, 3);
        printIndexes(r, 7, 3);
        printIndexes(b, 7, 3);

        System.out.println();
        pspace(3);
        printIndexes(d, 1, 3);
        System.out.println();
        pspace(3);
        printIndexes(d, 4, 3);
        System.out.println();
        pspace(3);
        printIndexes(d, 7, 3);
        System.out.println();

    }

    private void printIndexes(int face, int start, int stickers) {

        start = (face - 1) * 9 + start;
        for (int i = start; i < start + stickers; i++) {

            System.out.print(cube[i - 1]);
            System.out.print(" ");
        }
    }

    private void pspace(int spaces) {
        for (int i = 0; i < spaces * 2; i++) {
            System.out.print(" ");
        }
    }

    public String toString() {

        StringBuffer sb = new StringBuffer();

        for (String s : cube) {
            sb.append(s);
        }
        return sb.toString();
    }

    public void doAlgorithm(String alg) {
        String[] moveArr = alg.split("(?=[A-Za-z])");

        for (String move : moveArr) {

            Pattern p = Pattern.compile("([RUFBLDrufbldxyzEMS])(\\d*)('?)");
            move = move.trim();
            Matcher m = p.matcher(move);
            if (m.matches()) {

                String side = m.group(1);

                int times = 1;
                if (!m.group(2).equals("")) {
                    times = Integer.parseInt(m.group(2)) % 4;
                }

                if (m.group(3).equals("'")) {
                    times = (4 - times) % 4;
                }

                switch (side) {
                    case "R":
                        doR(times);
                        break;
                    case "U":
                        doU(times);
                        break;
                    case "F":
                        doF(times);
                        break;
                    case "B":
                        doB(times);
                        break;
                    case "L":
                        doL(times);
                        break;
                    case "D":
                        doD(times);
                        break;
                    case "r":
                        doRw(times);
                        break;
                    case "u":
                        doUw(times);
                        break;
                    case "f":
                        doFw(times);
                        break;
                    case "b":
                        doBw(times);
                        break;
                    case "l":
                        doLw(times);
                        break;
                    case "d":
                        doDw(times);
                        break;
                    case "x":
                        doX(times);
                        break;
                    case "y":
                        doY(times);
                        break;
                    case "z":
                        doZ(times);
                        break;
                    case "E":
                        doE(times);
                        break;
                    case "M":
                        doM(times);
                        break;
                    case "S":
                        doS(times);
                        break;

                }
            } else {

                System.out.println("BAD ALG");

            }

        }

    }

    public static String invert(String alg) {

        StringBuffer sb = new StringBuffer();
        String[] moveArr = alg.split("(?=[A-Za-z])");

        for (int i = moveArr.length - 1; i != -1; i--) {
            String move = moveArr[i];

            Pattern p = Pattern.compile("([RUFBLDrufbldxyzEMS])(\\d*)('?)");
            move = move.trim();
            Matcher m = p.matcher(move);
            if (m.matches()) {

                String side = m.group(1);

                int times = 1;
                if (!m.group(2).equals("")) {
                    times = Integer.parseInt(m.group(2)) % 4;
                }

                if (m.group(3).equals("'")) {
                    times = (4 - times) % 4;
                }

                times = (4 - times) % 4;// invert

                switch (times) {
                    case 0:
                        break;
                    case 1:
                        sb.append(side);
                        sb.append(" ");
                        break;
                    case 2:
                        sb.append(side);
                        sb.append(2);
                        sb.append(" ");
                        break;
                    case 3:
                        sb.append(side);
                        sb.append("'");
                        sb.append(" ");
                        break;
                }
            }

        }
        return sb.toString();

    }

    public String solution() {
        return Search.solution(toString(), 21, 3, false);
    }

    public String inverseSolution() {
        return AlgScrambler.invert(solution());
    }

    public String wcaOrient() {
        // u-r--f--d--l--b
        // 4 13 22 31 40 49

        StringBuffer moves = new StringBuffer();
        if (cube[13].equals("U")) {//R face
            doAlgorithm("z'");
            moves.append("z'");
        } else if (cube[22].equals("U")) {//on F face
            doAlgorithm("x");
            moves.append("x");
        } else if (cube[31].equals("U")) {//on D face
            doAlgorithm("x2");
            moves.append("x2");
        } else if (cube[40].equals("U")) {//on L face
            doAlgorithm("z");
            moves.append("z");
        } else if (cube[49].equals("U")) {//on B face
            doAlgorithm("x'");
            moves.append("x'");
        }

        if (cube[13].equals("F")) {//R face
            doAlgorithm("y");
            moves.append("y");
        } else if (cube[40].equals("F")) {//on L face
            doAlgorithm("y'");
            moves.append("y'");
        } else if (cube[49].equals("F")) {//on B face
            doAlgorithm("y2");
            moves.append("y2");
        }

        return moves.toString();
    }

    public static String obfusticate(String alg){
        AlgScrambler rc = new AlgScrambler(alg);
        String orient = AlgScrambler.invert(rc.wcaOrient());
        return rc.inverseSolution() + orient;
    }


}
