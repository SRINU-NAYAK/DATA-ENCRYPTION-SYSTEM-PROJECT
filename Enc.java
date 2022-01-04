import java.io.*;
import java.nio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.awt.FileDialog;

public class Enc extends Dialog {

    TextField textField1 = new TextField();
    Label label1 = new Label();

    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();
    Scrollbar scrollbar1 = new Scrollbar();
    Label label5 = new Label();
    Label label6 = new Label();
    Label label7 = new Label();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();

    public Enc(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            Enc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Enc() throws Exception {
        this.setSize(new Dimension(629, 629));
        this.setLayout(null);
        this.setFont(new Font("Dialog", 0, 16));
        this.setBackground(new Color(214, 255, 239));
        this.setBounds(new Rectangle(150, 10, 629, 400));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
        textField1.setBounds(new Rectangle(90, 60, 400, 30));
        textField1.setFont(new Font("Tahoma", 0, 14));
        textField2.setBounds(new Rectangle(90, 110, 400, 30));
        textField2.setFont(new Font("Tahoma", 0, 14));
        textField2.setVisible(true);
        textField3.setBounds(new Rectangle(90, 190, 400, 30));
        textField3.setFont(new Font("Tahoma", 0, 14));
        label1.setText("File:");
        label1.setBounds(new Rectangle(50, 60, 35, 30));
        label1.setFont(new Font("Tahoma", 0, 16));
        label6.setBounds(new Rectangle(50, 110, 30, 30));
        label6.setText("key");
        label7.setBounds(new Rectangle(90, 150, 250, 30));
        label7.setText("select file to store encrypted data");
        label6.setVisible(true);
        label6.setFont(new Font("Tahoma", 0, 16));
        button1.setLabel("Browse....");
        button1.setBounds(new Rectangle(493, 60, 110, 30));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1_actionPerformed(e);
            }
        });
        button2.setLabel("Encrypt");
        button2.setBounds(new Rectangle(240, 250, 110, 30));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button2_actionPerformed(e);
            }
        });
        button3.setLabel("Close");
        button3.setBounds(new Rectangle(493, 300, 110, 30));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button3_actionPerformed(e);
            }
        });
        button4.setLabel("Browse....");
        button4.setBounds(new Rectangle(493, 190, 110, 30));
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button4_actionPerformed(e);
            }
        });
        button5.setLabel("Browse....");
        button5.setBounds(new Rectangle(493, 110, 110, 30));
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button5_actionPerformed(e);
            }
        });
        scrollbar1.setBounds(new Rectangle(120, 340, 480, 30));
        scrollbar1.setOrientation(0);
        scrollbar1.setVisibleAmount(5);
        scrollbar1.setBlockIncrement(5);
        scrollbar1.setMaximum(255);
        scrollbar1.setVisible(false);
        label5.setText("Please wait....");
        label5.setBounds(new Rectangle(5, 290, 110, 30));
        label5.setFont(new Font("Tahoma", 0, 16));
        label5.setVisible(false);

        this.add(label6, null);
        this.add(label5, null);
        this.add(label7, null);
        this.add(scrollbar1, null);
        this.add(button3, null);
        this.add(button4, null);
        this.add(button2, null);
        this.add(button5, null);
        this.add(button1, null);
        this.add(label1, null);
        this.add(textField1, null);
        this.add(textField2, null);
        this.add(textField3, null);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            cancel();
        }
        super.processWindowEvent(e);
    }

    void cancel() {
    }

    private void button1_actionPerformed(ActionEvent e) {
        FileDialog f = new FileDialog(this, "Select a file");
        f.setVisible(true);
        this.textField1.setText(f.getDirectory() + f.getFile());
    }

    private void button2_actionPerformed(ActionEvent e) {
        try {
            Encryt();
        } catch (IOException io) {
            io.printStackTrace();
        }
        // Run Scrollbar
        this.scrollbar1.setVisible(true);
        this.label5.setVisible(true);
        runScrollbar();
        this.scrollbar1.setVisible(false);
        this.label5.setVisible(false);
    }

    void Encryt() throws IOException, FileNotFoundException {

        FileOutputStream ou = new FileOutputStream(textField3.getText(), false);
        FileInputStream io = new FileInputStream(textField1.getText());
        FileInputStream oi = new FileInputStream(textField2.getText());
        File file1 = new File(textField2.getText());
        byte[] data1 = new byte[(int) file1.length()];
        oi.read(data1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data2 = { (byte) 'L' };
        baos.write(data1);
        baos.write(data2);
        byte[] data3 = baos.toByteArray();

        // String s1 = new String(data1);
        // char a = (char)oi.read();
        // String d = Character.toString(a);
        // int s = Integer.parseInt(s1);
        // byte b1 = data1
        long s = ByteBuffer.wrap(data3).getInt();
        s = (s < 0 ? -s : s);
        if (data1.length >= 8) {
            int n = 0;
            while ((n = io.read()) != -1) {
                // n = io.read();
                if (n != -1) {

                    // System.out.println((char)n);
                    if (Character.isUpperCase(n)) {
                        char ch = (char) ((n + s - 65) % 26 + 65);
                        // System.out.println(ch);
                        int i = ch;

                        ou.write(i);
                    } else if (Character.isLowerCase(n)) {
                        char ch = (char) ((n + s - 97) % 26 + 97);
                        // System.out.println(ch);
                        int j = ch;
                        ou.write(j);
                    } else {
                        char ch = (char) ((n + s - 32) % 33 + 32);
                        // System.out.println(ch);
                        int j = ch;
                        ou.write(j);
                    }
                } else {
                    System.out.println("End of File");
                }

            }
            io.close();
            ou.close();
            oi.close();
        } else {

            MsgBox b = new MsgBox();
            b.setVisible(true);

        }
    }

    public void runScrollbar() {
        try {
            for (int i = 1; i <= 32; i++) {
                this.scrollbar1.setValue(i);
                Color c = new Color(i, 0, 0);
                this.scrollbar1.setBackground(c);
                Thread.sleep(10);
            }
        } catch (Exception ee) {
        }
    }

    void button3_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    void button4_actionPerformed(ActionEvent e) {
        FileDialog f = new FileDialog(this, "Select a file");
        f.setVisible(true);
        this.textField3.setText(f.getDirectory() + f.getFile());
    }

    void button5_actionPerformed(ActionEvent e) {
        FileDialog f = new FileDialog(this, "Select a file");
        f.setVisible(true);
        this.textField2.setText(f.getDirectory() + f.getFile());
    }
}
