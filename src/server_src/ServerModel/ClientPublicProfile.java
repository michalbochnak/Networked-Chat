package ServerModel;

import ClientModel.Pair;

import java.io.Serializable;

public class ClientPublicProfile implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String nickname;
    private Pair publicKey;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientPublicProfile (String name, Pair publicKey) {
        nickname = name;
        this.publicKey = publicKey;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public Pair getPublicKey() {
        return publicKey;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPublicKey(Pair publicKey) {
        this.publicKey = publicKey;
    }


}


