import java.io.Serializable;

public class AccountHolderBanking implements Serializable {

        private static final long serialVersionUID =7509767273601259359L;
        private String acctyp,pass;
        String accNum;
        private double balance;

        public AccountHolderBanking (String accNum, String acctyp, double balance,String pass) {

            this.accNum = accNum;
            this.acctyp = acctyp;
            this.balance = balance;
            this.pass=pass;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return ("Account type: " + this.acctyp+'\n');
        }

}

