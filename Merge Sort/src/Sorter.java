import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Sorter {
    public static int[] sortSmallToLarge(int[] a, JFrame frame, int width, int height, long waitTime) {
        LinkedList<LinkedList<Integer>> arrays = new LinkedList<>();
        for (int i : a) {
            LinkedList<Integer> x = new LinkedList<>();
            x.add(i);
            arrays.add(x);
        }
        while (arrays.size() > 1) {
            LinkedList<LinkedList<Integer>> tempArrays = new LinkedList<>();
            while (arrays.size() > 1) {
                try {
                    tempArrays.add(mergeSmallToLarge(arrays.get(0), arrays.get(1), waitTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                arrays.remove(0);
                arrays.remove(0);
                LinkedList<LinkedList<Integer>> displayArray = new LinkedList<>(tempArrays);
                displayArray.addAll(arrays);
                try {
                    displayStep(listToArray(displayArray), frame, width, height);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (arrays.size() > 0) tempArrays.add(arrays.get(0));
            arrays = tempArrays;
        }
        try {
            displayStep(listToArray(arrays), frame, width, height);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listToArray(arrays);
    }
    private static LinkedList<Integer> mergeSmallToLarge(LinkedList<Integer> a, LinkedList<Integer> b, long waitTime) throws InterruptedException {
        LinkedList<Integer> c = new LinkedList<>();
        while (a.size() > 0 && b.size() > 0) {
            if (a.get(0) < b.get(0)) {
                c.add(a.get(0));
                a.remove(0);
                Thread.sleep(waitTime);
            } else {
                c.add(b.get(0));
                b.remove(0);
                Thread.sleep(waitTime);
            }
        }
        if (a.size() > 0) {
            c.addAll(a);
        } else {
            c.addAll(b);
        }
        return c;
    }
    private static void drawArrayToGraph(int[] array, Graphics g, int width, int height) {
        g.setColor(Color.black);
        int largestNum = array[0];
        for (int i : array) if (i > largestNum) largestNum = i;
        for (int i = 0; i < array.length; i++) g.fillRect((int) ((float) (width) / (float) (array.length) * (float) (i)), (int) ((float) (500) - (float) (height) / (float) (largestNum) * (float) (array[i])), (int) ((float) (width) / (float) (array.length)), (int) ((float) (height) / (float) (largestNum) * (float) (array[i])));
    }
    public static int[] randomizeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int locationToSwapWith = getRandomNum(i, array.length);
            int swappedNum = array[locationToSwapWith];
            array[locationToSwapWith] = array[i];
            array[i] = swappedNum;
        }
        return array;
    }
    private static int getRandomNum(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
    private static int[] listToArray(LinkedList<LinkedList<Integer>> a) {
        LinkedList<Integer> usableArray = new LinkedList<>();
        for (LinkedList<Integer> x : a) usableArray.addAll(x);
        int[] output = new int[usableArray.size()];
        for (int i = 0; i < usableArray.size(); i++) output[i] = usableArray.get(i);
        return output;
    }
    private static void displayStep(int[] a, JFrame frame, int width, int height) throws InterruptedException {
        BufferStrategy bs = frame.getBufferStrategy();

        do {
            do {
                Graphics g = bs.getDrawGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

                drawArrayToGraph(a, g, width, height);

                g.dispose();
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }
    public static int[] createListOfLength(int length) {
        int[] output = new int[length];
        for (int i = 0; i < length; i++) {
            output[i] = i;
        }
        return output;
    }
}