import java.io.BufferedReader;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServSocket {
    public static void main(String[] args) throws Exception {
        System.out.println("Server is started ");
        ServerSocket ss= new ServerSocket(9999);
        System.out.println("server is waiting for client request");
        Socket s= ss.accept();
        System.out.println("Client Connected");
        BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Client Data: " + line);
        }
        s.close();
        ss.close();

    }
}
