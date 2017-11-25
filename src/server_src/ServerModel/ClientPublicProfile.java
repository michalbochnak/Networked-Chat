package ServerModel;

import ClientModel.KeyPair;

import java.io.Serializable;

public class ClientPublicProfile implements Serializable {

    private String nickname;
    private KeyPair publicKey;


    public ClientPublicProfile (String name, KeyPair publicKey) {
        nickname = name;
        this.publicKey = publicKey;
    }

    public String getNickname() {
        return nickname;
    }

    public KeyPair getPublicKey() {
        return publicKey;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPublicKey(KeyPair publicKey) {
        this.publicKey = publicKey;
    }
}
