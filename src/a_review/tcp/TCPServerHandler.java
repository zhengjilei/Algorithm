package a_review.tcp;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class TCPServerHandler implements Runnable {

    private Socket socket;

    public TCPServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        SocketChannel channel = socket.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


    }
}
