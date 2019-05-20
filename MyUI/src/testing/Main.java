package testing;

import com.danielmehlber.myui.*;
import com.danielmehlber.myui.MyTextEntry.MY_TEXT_ENTRY_MODE;

import javax.swing.*;
import java.awt.*;

public class Main extends MyFrame {

    //Component Space
    MyPage pgFront;
    MyPage pgLogin;
    MyPage pgHome;
    MyScrollPane pgScrollHome;
    MyButton btnToLoginPage;
    MyButton btnLogin;
    MyButton btnBack;
    MyButton btnLogout;
    MyTextEntry username;
    MyTextEntry password;
    MyProgessChart pc;
    Runnable rlogin;
    Runnable rBack;
    public Main() {
        super(MyDesign.PINK_NEON);
        getDesign().frameTopDesign = MyDesign.FRAME_DESIGN.BAR;
        getDesign().apply();
        setSize(600, 600);
        setResizable(false);
        setTitle("Testing Area");
        runnables();
        pgFront = new MyPage(getDesign());
        btnToLoginPage = new MyButton(getDesign(), "Enter");
        btnToLoginPage.setLocation(220, 206);
        btnToLoginPage.addRunnable(() -> changePage(pgLogin, MyDirection.NORTH));
        pgFront.add(btnToLoginPage);
        setContentPage(pgFront);

        JLabel lblWelcomeToThe = new JLabel("Welcome to the Testing App !");
        lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeToThe.setBounds(200, 181, 186, 14);
        pgFront.add(lblWelcomeToThe);
        lblWelcomeToThe.setFont(getDesign().font);
        lblWelcomeToThe.setForeground(getDesign().textColor);

        pgLogin = new MyPage(getDesign());
        btnLogin = new MyButton(getDesign(), "Login");
        btnLogin.setLocation(321, 344);
        btnLogin.addRunnable(rlogin);
        pgLogin.add(btnLogin);

        username = new MyTextEntry(getDesign(), MY_TEXT_ENTRY_MODE.NORMAL);
        username.setSize(345, 50);
        username.setLocation(126, 121);
        username.setSubtext("Enter 'user' here");
        password = new MyTextEntry(getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
        password.setSize(345, 50);
        password.setLocation(126, 185);
        password.setSubtext("Enter 'password' here");
        pgLogin.add(username);
        pgLogin.add(password);

        btnBack = new MyButton(getDesign(), "Back");
        btnBack.setLocation(126, 344);
        btnBack.addRunnable(rBack);
        pgLogin.add(btnBack);

        pgHome = new MyPage(getDesign());
        pc = new MyProgessChart(getDesign());
        pc.setLocation(196, 135);
        pc.add(new MyChartEntry(50, MyColor.RED));
        pc.add(new MyChartEntry(20, MyColor.BLUE));
        pc.add(new MyChartEntry(90, MyColor.GREEN));
        pgHome.add(pc);
        //setContentPage(pgHome);

        pgScrollHome = new MyScrollPane(getDesign(), false, true);
        MyGradient gradientPane = new MyGradient(MyDirection.SOUTH, MyColor.BLUE, MyColor.RED);
        gradientPane.setSize(200, 1000);
        pgScrollHome.setContentPane(gradientPane);
        super.scene.removeAll();
        super.scene.add(pgScrollHome, BorderLayout.CENTER);

        go();

    }

    public static void main(String[] args) {
        new Main();
    }

    private void runnables() {
        rlogin = () -> {
            String susername = username.getText();
            String spassword = password.getText();
            if (susername.equals("user") && spassword.equals("password")) {
                changePage(pgHome, MyDirection.EAST);
                pc.go();
            } else if (!susername.equals("user")) {
                username.error("Just type 'user' goddamnit!", 3);
            } else {
                password.error("Just type 'password' goddamnit!", 3);
            }
        };

        rBack = () -> changePage(pgFront, MyDirection.SOUTH);

    }
}


