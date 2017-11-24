package ServerModel;

import java.io.Serializable;

public class InitialClientInfoMsgModel implements Serializable {

    private String nickname;
    private String ip;
    private int port;

    public InitialClientInfoMsgModel (String nickname, String ip, int port) {
        this.nickname = nickname;
        this.ip = ip;
        this.port = port;
    }


    public String getNickname() {
        return nickname;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
