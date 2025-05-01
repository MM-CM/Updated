package FRONTENDADMIN;

import BACKENDLIB.arryList;
import BACKENDUSER.LLhistory;
import DSA.LinkedListAccounts;
import DSA.LinkedlistBook;
import FRONTENDLIB.ArchieveUI;
import FRONTENDLIB.BORROWINGUI;
import static FRONTENDLIB.DASHBOARDUI.arcs;
import static FRONTENDLIB.DASHBOARDUI.sharedTransac;
import FRONTENDLIB.RecordPayUI;
import FRONTENDLIB.ReturnUI;
import FRONTENDLIB.VIEWLISTUI;
import LogSigBackEnd.User;
import LogSigBackEnd.UserService;
import LoginAndSignUp.LoginSignup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class dashBoard extends parentComponent implements ActionListener {
    LinkedListAccounts acc;
    UserService userService;
    JPanel userPan = new JPanel();
    JPanel pan1 = new JPanel();
    JButton generateReports = new JButton("Generate Reports");
    JButton accountManagement = new JButton("Account Management");
    JLabel w = new JLabel("W");
    JLabel welcome = new JLabel("elcome to your dashboard, ");
    User user;
    JPanel red;
    LinkedlistBook book;
    arryList arr;

    public dashBoard(UserService userService, LinkedListAccounts acc, User user, LinkedlistBook book, arryList arr) {
        this.acc = acc;
        this.userService = userService;
        this.user = user;
        this.book = book;
        this.arr = arr;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon);
        this.setLayout(null); // Keep existing design intact
        this.setTitle("DashBoard");

        JPanel white = new JPanel(null);
        white.setBounds(0, 0, screenSize.width / 4, screenSize.height);
        white.setBackground(Color.white);

        red = new JPanel(null);
        red.setBounds(screenSize.width / 4, 0, screenSize.width * 3 / 4, screenSize.height);
        red.setBackground(new Color(0x6F1D1B));

        label = new JLabel();
        label.setText(user.getUsername());
        label.setBounds(148, 80, 248, 67);
        label.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        label.setForeground(Color.black);

        JLabel lab = new JLabel("Welcome to your DashBoard");
        lab.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        lab.setForeground(Color.white);
        lab.setBounds(300, 279, 879, 115);
        lab.setVisible(true);
        red.add(lab);

        JButton butt = new JButton("Home");
        butt.setBounds(30, 160, 409, 61);
        butt.setBackground(Color.white);
        butt.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        butt.setForeground(new Color(0x6F1D1B));
        butt.setBorder(null);
        butt.addActionListener(e -> {
            lab.setVisible(true);
            switchContent("Home");
            red.add(lab);
        });

        JButton in = new JButton("Inventory");
        in.setBounds(30, 236, 409, 61);
        in.setBackground(Color.white);
        in.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        in.setForeground(new Color(0x6F1D1B));
        in.setBorder(null);
        in.addActionListener(e -> {
            red.removeAll();
            VIEWLISTUI viewListUI = new VIEWLISTUI();
            viewListUI.designFrame();
            red.add(viewListUI.panel);
            revalidate();
            repaint();
        });

        JButton bb = new JButton("Account Management");
        bb.setBounds(30, 315, 409, 61);
        bb.setBackground(Color.white);
        bb.setFont(new Font("Bebas Neue", Font.BOLD, 32));
        bb.setForeground(new Color(0x6F1D1B));
        bb.setBorder(null);
        bb.addActionListener(e -> {
            red.removeAll();
            adminCOA borr = new adminCOA(userService, acc);
            red.add(borr.mainPanel);
            revalidate();
            repaint();
        });

        JButton rr = new JButton("Borrowed Reports");
        rr.setBounds(30, 390, 409, 61);
        rr.setBackground(Color.white);
        rr.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        rr.setForeground(new Color(0x6F1D1B));
        rr.setBorder(null);
        rr.addActionListener(e -> {
            red.removeAll();
            adminReports ret = new adminReports(acc, userService, user, book, arr);
            red.add(ret.mainPanel);
            revalidate();
            repaint();
        });

        JButton rp = new JButton("Book Return Reports");
        rp.setBounds(30, 470, 409, 61);
        rp.setBackground(Color.white);
        rp.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        rp.setForeground(new Color(0x6F1D1B));
        rp.setBorder(null);
        rp.addActionListener(e -> {
            red.removeAll();
            ReturnReport pay = new ReturnReport(acc, userService, user, book, arr);
            red.add(pay.mainPanel);
            revalidate();
            repaint();
        });

        JButton log = new JButton("Log-out");
        log.setBounds(30, 550, 409, 61);
        log.setBackground(Color.white);
        log.setFont(new Font("Bebas Neue", Font.BOLD, 40));
        log.setForeground(new Color(0x6F1D1B));
        log.setBorder(null);
        log.addActionListener(e -> {
            log();
            new LoginSignup();
        });

        white.add(label);
        white.add(butt);
        white.add(in);
        white.add(bb);
        white.add(rr);
        white.add(rp);
        white.add(log);

        this.add(white);
        this.add(red);
        this.setVisible(true);
    }

    public void log() {
        this.dispose();
    }

    private void switchContent(String viewType) {
        red.removeAll();
        if ("Inventory".equals(viewType)) {
            VIEWLISTUI viewListUI = new VIEWLISTUI();
            viewListUI.designFrame();
            red.add(viewListUI.panel);
        } else {
            JLabel homeLabel = new JLabel("Welcome to your DashBoard");
            homeLabel.setFont(new Font("Bebas Neue", Font.BOLD, 50));
            homeLabel.setForeground(Color.white);
            red.add(homeLabel);
        }
        red.revalidate();
        red.repaint();
    }

    public static void main(String[] args) {
        UserService service = new UserService();
        LinkedListAccounts acc = new LinkedListAccounts();
        User user = new User();
        LinkedlistBook book = new LinkedlistBook();
        arryList arr = new arryList();
        new dashBoard(service, acc, user, book, arr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Not used
    }
}