package ServerModel;

import ClientModel.KeyPair;

import java.io.Serializable;

public class InitialClientInfoMsgModel implements Serializable {

    private String nickname;
    private String ip;
    private int port;
    private boolean nameAvailable;
    private KeyPair publicKey;

    public InitialClientInfoMsgModel (String nickname, String ip, int port, boolean nameAvailable,
                                      KeyPair publicKey) {
        this.nickname = nickname;
        this.ip = ip;
        this.port = port;
        this.nameAvailable = nameAvailable;
        this.publicKey = publicKey;
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

    public KeyPair getPublicKey() {
        return publicKey;
    }

    public boolean isNameAvailable() {
        return nameAvailable;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setNameAvailable(boolean nameAvailable) {
        this.nameAvailable = nameAvailable;
    }
}
