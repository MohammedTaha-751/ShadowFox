package chat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChatClient {

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;
    private Socket socket;
    private String username;

    public ChatClient(String serverAddress, int port) {
        // Ask for username
        username = JOptionPane.showInputDialog(null, "Enter your name:", "Username", JOptionPane.PLAIN_MESSAGE);
        if (username == null || username.trim().isEmpty()) {
            username = "User" + (int)(Math.random() * 1000);
        }

        // Setup GUI
        frame = new JFrame("Chat - " + username);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> sendMessage());

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Connect to server
        try {
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            new MessageListener(socket).start();
        } catch (IOException e) {
            showError("Could not connect to server.");
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            out.println(username + ": " + message);
            inputField.setText("");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    // Thread to receive messages
    private class MessageListener extends Thread {
        private BufferedReader in;

        public MessageListener(Socket socket) {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                showError("Error setting up input stream.");
            }
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    chatArea.append(message + "\n");
                }
            } catch (IOException e) {
                chatArea.append("Disconnected from server.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClient("localhost", 12345));
    }
}
