import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JTextField loginField;
    private JTextField cityField;
    private JButton checkCityButton;
    private JTextField countryTextField;


    public RegisterForm() throws HeadlessException {
        super("Register");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 300));

        this.setContentPane(mainPanel);
        this.pack();

        // use Enter to click this button
        this.getRootPane().setDefaultButton(registerButton);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        FindCity findCity = new FindCity();
        UserDAO userDAO = new UserDAO();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartForm startForm = new StartForm();
                RegisterForm.super.dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                String country = countryTextField.getText();

                String login = loginField.getText();
                String password = passwordField.getText();
                String password2 = passwordField2.getText();
                Integer cityID = findCity.findCityId(city, country);

                if (city.length() > 0 && cityID == null) {
                    JOptionPane.showMessageDialog(null, "Please input valid city name", "Alert", JOptionPane.ERROR_MESSAGE);
                } else if (country.length() != 2) {
                    JOptionPane.showMessageDialog(null, "Please input 2 characters country name", "Alert", JOptionPane.ERROR_MESSAGE);
                } else if (login.length() < 3) {
                    JOptionPane.showMessageDialog(null, "Login should be 4 characters minimum", "Alert", JOptionPane.ERROR_MESSAGE);
                } else if (password.length() < 3) {
                    JOptionPane.showMessageDialog(null, "Password should be 4 characters minimum", "Alert", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(password2)) {
                    JOptionPane.showMessageDialog(null, "Passwords are not equal!", "Alert", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (userDAO.addUser(login, password, cityID)) {
                        User user = null;
                        user = userDAO.logginIn(login, password);
                        if (user.getId() != null) {
                            JOptionPane.showMessageDialog(null, "Registered successfully!", "Information", JOptionPane.PLAIN_MESSAGE);
                            WeatherForm weatherForm = new WeatherForm(user);
                            RegisterForm.super.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User already exists", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        checkCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer cityID = findCity.findCityId(cityField.getText(), countryTextField.getText());

                if (cityID == null) {
                    JOptionPane.showMessageDialog(null, "City not found", "Alert", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Valid city", "Information", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
    }
}
