import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherForm extends JFrame {
    private JLabel cityLabel;
    private JLabel welcomeLabel;
    private JButton logoutButton;
    private JLabel imageLabel;
    private JPanel mainPanel;
    private JLabel weatherTodayTextLabel;
    private JLabel degreesLabel;
    private JLabel cloudsLabel;
    private JLabel windLabel;
    private JLabel t1Label;
    private JLabel t2Label;
    private JLabel t3Label;
    private JLabel w1Label;
    private JLabel w2Label;
    private JLabel w3Label;
    private JLabel c1Label;
    private JLabel c2Label;
    private JLabel c3Label;
    private JLabel n1Label;
    private JLabel n2Label;
    private JLabel n3Label;
    private JLabel cloudsLabel1;
    private JPanel weatherIconBg;

    private void setWeather(WeatherDay day, JLabel t, JLabel w, JLabel c, JLabel n) {
        t.setText(day.getTemperature() + "°C");

        if (day.getWind() != null) {
            w.setText(day.getWind() + "km/h");
        }
        if (day.getClouds() != null) {
            c.setText(day.getClouds() + "% clouds");
        }
        n.setText(day.getWeather());
    }


    public WeatherForm(User user) {
        // prepare frame
        super("WeatherDay");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 500));


        this.setContentPane(mainPanel);
        mainPanel.setOpaque(true);
        weatherIconBg.setBackground(new Color(174, 238, 238));


        this.pack();

        String cityName;
        ArrayList<WeatherDay> list = new ArrayList<>();


        {
            WeatherApi weatherApi = new WeatherApi(user.getCity());
            cityName = weatherApi.getCityFromJson();
            try {
                list = weatherApi.getWeatherList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        URL url = null;
        try {
            url = new URL("http://openweathermap.org/img/wn/" + list.get(0).getIcon() + "@4x.png");
            Image image = ImageIO.read(url);

            ImageIcon weatherIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));

            imageLabel.setIcon(weatherIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        cityLabel.setText(cityName);
        setWeather(list.get(0), degreesLabel, windLabel, cloudsLabel1, weatherTodayTextLabel);
        weatherTodayTextLabel.setText("Weather today: " + list.get(0).getWeather());

        setWeather(list.get(1), t1Label, w1Label, c1Label, n1Label);
        setWeather(list.get(2), t2Label, w2Label, c2Label, n2Label);
        setWeather(list.get(3), t3Label, w3Label, c3Label, n3Label);

        welcomeLabel.setText("Welcome " + user.getLogin());


        this.setLocationRelativeTo(null);
        this.setVisible(true);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartForm startForm = new StartForm();
                WeatherForm.super.dispose();
            }
        });
    }
}
