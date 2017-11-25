package ClientModel;

import java.io.Serializable;

public class KeyPair implements Serializable {

    private int key;
    private int n;

    public KeyPair(int key, int n) {
        this.key = key;
        this.n = n;
    }

    public int getKey() {
        return key;
    }

    public int getN() {
        return n;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setN(int n) {
        this.n = n;
    }
}
