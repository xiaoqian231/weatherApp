import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton loginButton;

    public LoginForm(String title) throws HeadlessException {
        // prepare frame
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,200));

        this.setContentPane(mainPanel);
        this.pack();

        // use Enter to click this button
        this.getRootPane().setDefaultButton(loginButton);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        UserDAO userDAO = new UserDAO();

        // cancel button logic
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartForm startForm = new StartForm();
                LoginForm.super.dispose();
            }
        });

        // login button logic
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = passwordField.getText();
                User user = null;

                if (login == null || login.length() == 0){
                    JOptionPane.showMessageDialog(null, "Login field is empty", "Alert", JOptionPane.ERROR_MESSAGE);
                }else if (password == null || password.length() == 0){
                    JOptionPane.showMessageDialog(null, "Password field is empty", "Alert", JOptionPane.ERROR_MESSAGE);
                }else {
                    user = userDAO.logginIn(login, password);
                    if (user.getId() == null){
                        JOptionPane.showMessageDialog(null, "Wrong password or login", "Alert", JOptionPane.ERROR_MESSAGE);
                    }else{
                        WeatherForm weatherForm = new WeatherForm(user);
                        LoginForm.super.dispose();
                    }
                }
            }
        });
    }
}
