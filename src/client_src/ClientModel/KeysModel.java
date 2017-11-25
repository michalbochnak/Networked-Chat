package ClientModel;

import java.math.BigInteger;

public class KeysModel {

    private int E;
    private int D;
    private int N;


    public KeysModel(int p, int q){
        this.N = calculateN(p, q);
        this.E = calculateE(p, q);
        this.D = calculateD(this.E, p, q);
    }

    public int getE() {
        return E;
    }

    public int getN() {
        return N;
    }

    public int getD() {
        return D;
    }



    private int calculateD(int e, int p, int q) {

        int phi = (p-1)*(q-1);
        BigInteger E = new BigInteger(Integer.toString(e));
        BigInteger PHI = new BigInteger(Integer.toString(phi));
        BigInteger D = E.modInverse(PHI);

        return D.intValue();
    }

    //based on geekforgeeks website
    private int calculateE(int p, int q) {
        int e=2;
        int phi = (p-1)*(q-1);
        while (e < phi)
        {
            // e must be co-prime to phi and
            // smaller than phi.
            if (gcd(e, phi)==1)
                break;
            else
                e++;
        }
        return e;
    }

    // Returns gcd of a and b
    //based on geekforgeeks website
    private int gcd(int a, int h)
    {
        int temp;
        while (true)
        {
            temp = a%h;
            if (temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    private int calculateN(int p, int q) {
        return p*q;
    }
}
