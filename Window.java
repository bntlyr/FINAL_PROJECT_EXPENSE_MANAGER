
import javax.swing.JFrame;

class Window extends JFrame {
    Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(getTitle());
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(getSize());
    }
}
