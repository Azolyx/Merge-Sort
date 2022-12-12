import javax.swing.*;
import java.awt.*;

public class CreateWindow extends JFrame {
    public CreateWindow(String windowName, int windowWidth, int windowHeight) {
        setTitle(windowName);
        setSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        createBufferStrategy(3);
    }
}
