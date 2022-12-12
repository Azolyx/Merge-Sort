import javax.swing.*;

public class Main {
    boolean running = false;
    int windowWidth = 500;
    int windowHeight = 500;
    long waitTime = (long) 1;

    Thread thread;
    JFrame frame;

    int[] listToSort = Sorter.randomizeArray(Sorter.createListOfLength(500));

    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        frame = new CreateWindow("Merge Sort", windowWidth, windowHeight);
        start();
    }
    public void start() {
        if (thread != null) {
            stop();
        }
        thread = new Thread(this::running);
        thread.start();
        running = true;
    }
    public void stop() {
        try {
            running = false;
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread = null;
    }
    public void running() {
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 100000000 / fps;
        double delta = 0;

        Sorter.sortSmallToLarge(listToSort, frame, windowWidth, windowHeight, waitTime);

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta > 1) {
                tick();
            }

            render();
        }
        stop();
    }

    public void tick() {
    }
    public void render() {

    }
}