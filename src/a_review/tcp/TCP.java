package a_review.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class TCP {

    public static void main(String[] args) {

    }

    class Server implements Runnable {
        private int port;

        public Server(int port) {
            super();
            this.port = port;
        }

        @Override
        public void run() {
            ServerSocket server = null;
            try {
                server = new ServerSocket(port);
                server.getChannel();
                while(true){
                    Socket socket = server.accept();
                    new Thread(new TCPServerHandler(socket));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client implements Runnable {
        private int serverPort;

        @Override
        public void run() {
            try {
                Socket socket = new Socket("192.168.31.11", serverPort);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
