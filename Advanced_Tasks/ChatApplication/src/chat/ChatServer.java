package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("🔵 Chat server started on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("✅ New client connected: " + clientSocket);
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("❌ Server error: " + e.getMessage());
        }
    }

    // Thread to handle each connected client
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("📨 Received: " + message);
                    broadcast("💬 " + message);
                }

            } catch (IOException e) {
                System.out.println("⚠️ Client disconnected: " + socket);
            } finally {
                if (out != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(out);
                    }
                }
                try {
                    socket.close();
                } catch (IOException ignored) {}
            }
        }

        // Send message to all connected clients
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
