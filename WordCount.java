// this program counts the number of words that are found in a given paragraphs
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class WordCount implements ActionListener{
    public JFrame frame;
    private JScrollPane scrollPane;
    public JPanel panel, panel1;
    public JTextArea textArea;    
    public JButton button;    
    Color darkTeal = Color.decode("#0d4d5e");
    public void initialize(){
        frame = new JFrame("Word Count");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setFont(new Font("Arial", Font.BOLD, 20));
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setBackground(Color.decode("#0d4d5e"));
        textArea.setForeground(Color.decode("#9bd770"));
        textArea.setCaretColor(Color.decode("#9bd770"));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setBounds(20, 20, 400, 250);
        textArea.setEditable(true);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 450, 350);

        button = new JButton("Count Words");
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setForeground(Color.decode("#FFD700"));
        button.setBackground(darkTeal);
        button.setBounds(150,400,150,50);

        panel = new JPanel();
        panel.setBackground(Color.decode("#0d4d5e"));
        panel.setLayout(null);
        panel.setBounds(0, 0, 470, 400);
        panel.add(scrollPane);
        frame.add(panel);

        panel1 = new JPanel();
        panel1.setBackground(Color.decode("#0d4d5e"));
        panel1.setLayout(null);
        panel1.setBounds(200, 420, 200, 100);
        panel1.add(button);
        frame.add(panel1);
        button.addActionListener(this);

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        WordCount count = new WordCount();
        count.initialize();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String paragraph = textArea.getText();
        int wordCount = paragraph.split("\s+").length;
        if(textArea.getText().isEmpty()){
            wordCount = 0;
        }
        JOptionPane.showMessageDialog(null, "Number of words: " + wordCount);
        
    }
}