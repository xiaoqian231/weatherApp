import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame{
    private JPanel mainPanel;
    private JButton loginButton;
    private JPanel panelLoginButton;
    private JPanel panelRegisterButton;
    private JButton registerButton;
    private JTextPane weatherTextPane;
    private JPanel panelTopText;

    public StartForm() throws HeadlessException {
        super("WeatherApp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,200));
        weatherTextPane.setEditable(false);


        //change of the background colors
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        panelLoginButton.setBackground(Color.WHITE);
        panelRegisterButton.setBackground(Color.WHITE);
        weatherTextPane.setBackground(Color.WHITE);
        panelTopText.setBackground(Color.WHITE);


        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm("Login");
                StartForm.super.dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                StartForm.super.dispose();
            }
        });
    }
}
