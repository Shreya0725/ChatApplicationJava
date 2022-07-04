package Server;

import javax.imageio.spi.ServiceRegistry;

public class ServerMain {

    public static void main(String[] args) {

        ServerChat sc = new ServerChat();
        sc.waitingForClient();
        sc.setIoStreams();
    }
}
