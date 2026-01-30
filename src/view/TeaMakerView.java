package view;

import controller.TeaMakerController;
import model.*;
import model.observer.TeaMakerObserver;
import model.states.State;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TeaMakerView extends JFrame implements TeaMakerObserver, ActionListener {

    private TeaMakerModel model;
    private TeaMakerController controller;

    private JTextField txtCupInput;
    private JButton btnFill;
    private JButton btnStart;
    private JButton btnBoil;
    private JButton btnReset;

    private JLabel lblStateIdle;
    private JLabel lblStateMakingTea;
    private JLabel lblStateBoilingWater;
    private JLabel lblStateDone;

    private JLabel lblTotalCupsValue;
    private JLabel lblMessageArea;
    private JLabel lblDayArea;
    private JLabel lblDateArea;

    private JLabel lblImage;

    public TeaMakerView(TeaMakerController controller, TeaMakerModel model) {
        this.controller = controller;
        this.model = model;
        model.registerObserver(this);
    }

    public void createView() {
        setTitle("Tea Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        JPanel mainGrid = new JPanel(new GridBagLayout());
        mainGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;

        btnFill = createStyledButton("FILLED");
        btnFill.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 0.1;
        mainGrid.add(btnFill, gbc);

        txtCupInput = new JTextField("");
        txtCupInput.setHorizontalAlignment(JTextField.CENTER);
        txtCupInput.setFont(new Font("Arial", Font.BOLD, 20));

        txtCupInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        txtCupInput.setToolTipText("Enter cup amount");
        txtCupInput.setCaretColor(Color.BLACK);

        gbc.gridx = 1; gbc.gridy = 0;
        mainGrid.add(txtCupInput, gbc);

        JPanel leftMiddlePanel = new JPanel(new BorderLayout());
        leftMiddlePanel.setBorder(new LineBorder(Color.BLACK));

        lblImage = new JLabel("☕", SwingConstants.CENTER);
        lblImage.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        leftMiddlePanel.add(lblImage, BorderLayout.CENTER);

        btnStart = createStyledButton("START");
        btnStart.setBorder(null);
        btnStart.addActionListener(this);
        leftMiddlePanel.add(btnStart, BorderLayout.SOUTH);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 4;
        gbc.weighty = 0.4;
        mainGrid.add(leftMiddlePanel, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1; gbc.weighty = 0.1;

        lblStateIdle = createStatusLabel("IDLE");
        gbc.gridy = 1; mainGrid.add(lblStateIdle, gbc);

        lblStateMakingTea = createStatusLabel("MAKING TEA");
        gbc.gridy = 2; mainGrid.add(lblStateMakingTea, gbc);

        lblStateBoilingWater = createStatusLabel("BOILING WATER");
        gbc.gridy = 3; mainGrid.add(lblStateBoilingWater, gbc);

        lblStateDone = createStatusLabel("DONE");
        gbc.gridy = 4; mainGrid.add(lblStateDone, gbc);

        JLabel lblTotalCupsTitle = new JLabel("Total Cups", SwingConstants.CENTER);
        lblTotalCupsTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalCupsTitle.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 0.1;
        mainGrid.add(lblTotalCupsTitle, gbc);

        lblTotalCupsValue = new JLabel("0", SwingConstants.CENTER);
        lblTotalCupsValue.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalCupsValue.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = 1; gbc.gridy = 5;
        mainGrid.add(lblTotalCupsValue, gbc);

        btnBoil = createStyledButton("BOIL WATER");
        btnBoil.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        mainGrid.add(btnBoil, gbc);

        lblMessageArea = new JLabel("Messages/Warnings/Notifications", SwingConstants.CENTER);
        lblMessageArea.setForeground(Color.RED);
        lblMessageArea.setFont(new Font("Arial", Font.BOLD, 14));
        lblMessageArea.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        mainGrid.add(lblMessageArea, gbc);

        lblDayArea = new JLabel("Day", SwingConstants.CENTER);
        lblDayArea.setFont(new Font("Arial", Font.BOLD, 14));
        lblDayArea.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 1;
        gbc.weighty = 0.1;
        mainGrid.add(lblDayArea, gbc);

        lblDateArea = new JLabel("Date", SwingConstants.CENTER);
        lblDateArea.setFont(new Font("Arial", Font.BOLD, 14));
        lblDateArea.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = 1; gbc.gridy = 8;
        mainGrid.add(lblDateArea, gbc);

        btnReset = createStyledButton("Reset");
        btnReset.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        mainGrid.add(btnReset, gbc);

        add(mainGrid, BorderLayout.CENTER);
        setVisible(true);

        txtCupInput.requestFocusInWindow();

        updateView();
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBorder(new LineBorder(Color.BLACK));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        return btn;
    }

    private JLabel createStatusLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setBorder(new LineBorder(Color.BLACK));
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        return lbl;
    }

    private void updateDateDisplay() {
        LocalDate date = LocalDate.now();
        lblDayArea.setText(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        lblDateArea.setText(date.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFill) {
            try {
                String text = txtCupInput.getText();
                if (text == null || text.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a number!");
                    return;
                }
                int cups = Integer.parseInt(text);
                if(cups > 0){
                    controller.fill(cups);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        } else if (e.getSource() == btnStart) {
            controller.start();
        } else if (e.getSource() == btnBoil) {
            controller.boil();
        } else if (e.getSource() == btnReset) {
            controller.reset();
        }
    }

    @Override
    public void updateView() {
        String rawMessage = model.getMessage();
        if (rawMessage != null) {
            String htmlMessage = "<html><center>" + rawMessage.replace(" | ", "<br>") + "</center></html>";
            lblMessageArea.setText(htmlMessage);
        } else {
            lblMessageArea.setText("");
        }

        State currentState = model.getState();
        updateStatusLights(currentState);

        lblTotalCupsValue.setText(String.valueOf(controller.fetchTotalCups()));

        updateDateDisplay();
    }

    private void updateStatusLights(State state) {
        lblStateIdle.setBackground(Color.WHITE);
        lblStateMakingTea.setBackground(Color.WHITE);
        lblStateBoilingWater.setBackground(Color.WHITE);
        lblStateDone.setBackground(Color.WHITE);

        boolean inputEnabled = false;
        boolean controlsEnabled = true;

        if (state == model.getEmptyState()) {
            inputEnabled = true;
        } else if (state == model.getIdleState()) {
            lblStateIdle.setBackground(Color.YELLOW);
            inputEnabled = false;
        } else if (state == model.getTeaState()) {
            lblStateMakingTea.setBackground(Color.YELLOW);
            controlsEnabled = false;
        } else if (state == model.getBoilingWaterState()) {
            lblStateBoilingWater.setBackground(Color.YELLOW);
            controlsEnabled = false;
        } else if (state == model.getDoneState()) {
            lblStateDone.setBackground(Color.YELLOW);
        }

        txtCupInput.setEnabled(inputEnabled);
        btnFill.setEnabled(inputEnabled || state == model.getDoneState());

        btnStart.setEnabled(controlsEnabled);
        btnBoil.setEnabled(controlsEnabled);
        btnReset.setEnabled(true);
    }
}