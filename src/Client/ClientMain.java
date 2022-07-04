package Client;

import java.net.SocketOption;
import java.security.spec.RSAOtherPrimeInfo;

public class ClientMain {

    public static void main(String[] args) {

        ClientChat cm = new ClientChat();
        cm.setIoStreams();
    }
}
