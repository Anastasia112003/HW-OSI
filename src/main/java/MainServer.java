import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        int port = 8621;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.printf("New connection accepted. Write your login\n");
                    final String login = in.readLine();
                    System.out.println(login);
                    out.println(String.format("Nice to meet you, %s Are you adult? Write yes or no", login));
                    final String answer = in.readLine();
                    if (answer.equals("yes")) {
                        out.println("You are in an adult version, dear " + login);
                    } else if (answer.equals("no")) {
                        out.println("You are in a kids version, dear" + login);
                    } else {
                        out.println("Your answer is incorrect, try again " + login);
                    }
                    final String bye = in.readLine();
                    System.out.println(bye);
                    out.println(bye);
                }
            }
        }
    }
}