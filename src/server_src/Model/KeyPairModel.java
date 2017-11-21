//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//
// KeyPairModel.java
//

//
// Class description...
//

package Model;

import java.math.BigInteger;

public class KeyPairModel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------

    private BigInteger privateKey, publicKey;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public KeyPairModel() {
        privateKey = null;
        publicKey = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------

    public void setPrivateKey(BigInteger privateKey) {

        this.privateKey = privateKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    // FIXME: implement
    public BigInteger generateKey() {
        return new BigInteger("2");
    }
}
