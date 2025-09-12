import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleAlarm {
    private static boolean ringing = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Alarm App");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Set Alarm (HH:MM):");
        JTextField timeField = new JTextField(5);
        JButton setButton = new JButton("Set Alarm");
        JButton stopButton = new JButton("Stop Alarm");

        frame.add(label);
        frame.add(timeField);
        frame.add(setButton);
        frame.add(stopButton);

        // Set Alarm
        setButton.addActionListener(e -> {
            try {
                String input = timeField.getText();
                String[] parts = input.split(":");
                if (parts.length != 2) throw new Exception("Invalid format");

                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);

                long delay = cal.getTimeInMillis() - System.currentTimeMillis();
                if (delay < 0) delay += 24 * 60 * 60 * 1000; // next day

                new Timer().schedule(new TimerTask() {
                    public void run() {
                        JOptionPane.showMessageDialog(frame, "⏰ Alarm Ringing!");
                        startBeepingOrSound();
                    }
                }, delay);

                JOptionPane.showMessageDialog(frame, "Alarm set successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Use HH:MM format.");
            }
        });

        // Stop Alarm
        stopButton.addActionListener(e -> stopBeeping());

        frame.setVisible(true);
    }

    // Play WAV if exists, else system beep
    private static void startBeepingOrSound() {
        ringing = true;

        File wavFile = new File("clock.wav"); // put clock.wav in project root
        if (wavFile.exists()) {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(wavFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                System.out.println("Error playing WAV. Using beep instead.");
                startSystemBeep();
            }
        } else {
            startSystemBeep();
        }
    }

    private static void startSystemBeep() {
        new Thread(() -> {
            while (ringing) {
                Toolkit.getDefaultToolkit().beep();
                try { Thread.sleep(1000); } catch (InterruptedException ex) {}
            }
        }).start();
    }

    private static void stopBeeping() {
        ringing = false;
    }
}
