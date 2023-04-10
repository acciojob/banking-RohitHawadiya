package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
         super(name,balance,5000);
         this.tradeLicenseId=tradeLicenseId;
         if(balance<5000)
             throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isNumberValid(tradeLicenseId)){
            String rearrangedId=arrangedString(tradeLicenseId);

            if(rearrangedId.equals("")){
                throw new Exception("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId=rearrangedId;
            }
        }

    }

    public String arrangedString(String S){
        int N=S.length();
        int[]count=new int[26];
        String SS=S.toUpperCase();
        for(char ch:SS.toCharArray()){
            count[(int)ch-(int)'A']++;
        }

        char ch_max=getCountChar(count);

        int maxCount=count[(int)ch_max-(int)'A'];

        if(N%2==0) {
            if (maxCount > N/2)
                return "";
        }
            else{
                if(maxCount>(N/2)+1){
                    return "";
                }
            }

        char[]ans=new char[N];
        int index;
        for(index=0;index<N;index=index+2){
            if(count[(int)ch_max-(int)'A']>0){
                ans[index]=ch_max;
                count[(int)ch_max-(int)'A']--;
            }
            else{
                break;
            }
        }

        for(int i=0;i<26;i++){
            char ch=(char)('A'+i);

            while(count[(int)ch-(int)'A']>0){
                if(index>=N){
                    index=1;
                }
                ans[index]=ch;
                index=index+2;
                count[(int)ch-(int)'A']--;
            }
        }
        return ans.toString();
    }

    public char getCountChar(int[]count){
        int max=0;
        char ch=0;

        for(int i=0;i<26;i++){
            if(count[i]>max){
                max=count[i];
                ch=(char)((int)'A'+i);
            }
        }
        return ch;
    }

    public boolean isNumberValid(String tradeLicenseId)
    {
        for(int i=0;i<tradeLicenseId.length()-1;i++)
        {
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1))
                return false;
        }
        return true;
    }

}
