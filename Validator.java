import java.util.Arrays;
import java.util.Scanner;
public class Validator {

    /**
     * Return true if the card number is valid
     *
     * This function will take a credit card number as its parameter. It will
     * first determine if the card belongs to one of the given vendors
     *
     * Then it will compute the two sums needed for the mod 10 check.
     *
     * And, finally it will perform the mod 10 check.
     */
    public static boolean isValid(long number) {

        int addEven = 0, addOdd = 0,sum;
        boolean validC;

        // determines if the vendor is valid or invalid and determines what vendor it is
        validC = isValidVendor(number);
        //	System.out.println("vendor:"+ validC);
        if(validC = false){
            System.out.println("Invalid vendor.");
            return validC;

        }

        // adds up from sumOfDoubleEvenPlace and sumOfOddPlace
        addEven = sumOfDoubleEvenPlace(number);
        addOdd = sumOfOddPlace(number);

        sum = addEven + addOdd;

        // if the total of addEven and addOdd is not divisible by 10 then the credit card doesn't exist
        if (sum % 10 == 0) {
            return true;
        }
        else{
            return false;
        }


    }

    public static boolean isValidVendor(long number) {
        //4 for Visa cards
        //5 for Master cards
        //37 for American Express cards
        //6 for Discover cards


        boolean visa, master, AE, discover;
        boolean result;
        int cc_type = 0;


        visa = prefixMatched(number,4);
        master = prefixMatched(number,5);
        AE = prefixMatched(number,37);
        discover = prefixMatched(number,6);
        //a function to determine if the credit card is from a certain vendor if not it is not a valid credit card

//        if (visa == true)
//        {
//            System.out.println("This is a Visa card. ");
//            return visa;
//        }
//        else if(master == true){
//            System.out.println("This is a Mastercard. ");
//            return master;
//        }
//        else if(AE == true){
//            System.out.println("This is an American Express card. ");
//            return AE;
//        }
//        else if(discover == true) {
//            System.out.println("This is a Discover card. ");
//            return discover;
//        }
//            System.out.println("This credit card has no vendor.");
//        return false;
//

        //if credit card == visa
        //visa == true
        if (visa == true){
            cc_type = 1;
        }
        else if(master == true){
            cc_type = 2;
        }
        else if(AE == true){
            cc_type = 3;
        }
        else if(discover == true){
            cc_type = 4;
        }

        switch(cc_type){
            case 1:
                System.out.println("This is a Visa card. ");
                return result = visa;
            case 2:
                System.out.println("This is a Mastercard. ");
                return result = master;
            case 3:
                System.out.println("This is an American Express card. ");
                return result = AE;
            case 4:
                System.out.println("This is a Discover card. ");
                return result = discover;
            default:
                System.out.println("This credit card has no vendor. ");
                return result = false;
        }
    }


    /**
     * Get the result from Step 2
     */
    public static int sumOfDoubleEvenPlace(long number) {

        int result = 0;
        int x = 0;
        int x1 = 0;


        while(number > 0){
            //  find the second number from right to left
            x = (int)(((number % 100)  - (number  % 10))/10) * 2;
            //System.out.println("x =" + x);
            // determines if x is a single or double digit number
            x1 = getDigit(x);

            // totals x every time until the loops reaches to zero
            result += x1;
            // trim out every two numbers once x is finished
            number = number / 100;

        }
        return result;
    }



    /**
     * Return sum of odd place digits in number
     */
    public static int sumOfOddPlace(long number) {

        int result = 0, x = 0, z;
        //System.out.println("ins:" + number);
        while (number > 0){
            // finds the first number from right to left
            x = (int)(number % 10);

            // brings z to the getDigit function to determine if it is greater than 10
            z = getDigit(x);

            // adds up the odd numbers from right to left
            result += z;

            // trims the two numbers until the number becomes zero
            number = number / 100;
            //	   System.out.println("number:" + number);
        }

        return result;
    }

    /**
     * Return this number if it is a single digit, otherwise, return * the sum
     * of the two digits
     */
    public static int getDigit(int number) {
        int num_of_digit;
        int final_digit = 0;
        int total_sum = 0 ;

        // 	numofdigit = number/10;
        //gets the number of digits from the getSize program which will determine if it is a double digit or single digit number
        num_of_digit = getSize(number);
        //  	if (numofdigit > 0 ){
        //if it is double digits, it will trim off one number and add both of them together
        if (num_of_digit >= 2){
            while(number > 0){
                total_sum = total_sum + number % 10 ;
                number = number/10 ;
            }
            // returns the total of the double digit numbers
            final_digit = total_sum;
        }
        else{
            final_digit = number;
        }

        return final_digit;

    }

    /**
     * Return true if the digit d is a prefix for number
     */
    public static boolean prefixMatched(long number, int d) {
        int digits;
        int total_num_in_cc = 0;
        boolean type;
        // find how many digits in d
        digits = getSize(d);
        // finds how many digits in the credit card number
        total_num_in_cc = getSize(number);

//        System.out.println("startNumber: " + total_num_in_cc);
//        System.out.println("Total digits: " + digits);

        // while the number of the credit card prefix is greater than d , divides the credit card to different prefixes and if the number matches the prefix of d then it returns true
        while (total_num_in_cc >= digits + 1){
            //keeps subtracting numbers in credit card by 1
            number = number / 10;
            total_num_in_cc = getSize(number);
            //            System.out.println("current_number_in_CC: " + total_num_in_cc);
        }

//        System.out.println("Number: " + number);
//        System.out.println("d: " + d);

        if (number == d){
            type = true;
        }
        else{
            type = false;
        }
        return type;
    }



    /**
     * Return the number of digits in d
     */
    public static int getSize(long d) {
        long number = d;
        int counter = 0;

        while (number > 0){
            // add increment of the number to count the digits
            counter++;

            // trim off one number from the entire number set and continues to count until it trims to zero
            number =  number / 10;
        }
        return counter;
    }

    /**
     * Return the first k number of digits from number. If the number of digits
     * in number is less than k, return number.
     */
    public static long getPrefix(long number, int k) {
        // gets the size of the card and k is how many digits it is looking at
        int size = getSize(number);
        // if the required size is greater than the required k number of digits then it returns the number if not it goes through the loop and trimes out each number until it requires the k requirements
        if(size < k){
            return number;
        }

        int remove_count = size - k;
        while (remove_count > 0) {
            number /= 10;
            remove_count--;
        }
        return number;
    }

    public static void main(String[] args) {

        /**
         *  Begin testing for getSize
         *  Inputs: 213,452343,21312
         *  Outputs: 3,6,5
         */
        int[] ins = {213, 452343, 21312};
        int[] outs = {3, 6, 5};
        int size = -1;
        for (int num = 0; num < ins.length; num++) {
            size = getSize(ins[num]);
            if (size != outs[num]) {
                System.out.println("getSize test has Failed\nInput : " + ins[num] + "\nOutput: " + size + "\nExpected Output: " + outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getSize tests have passed");

        /**
         * Begin testing for getPrefix
         * Inputs: (213,1),(452343,3),(21312,6)
         * Outputs: 2,452,21312
         */
        int[][] t1_ins = {{213,1},{452343,3}, {21312,6}};
        int[] t1_outs = {2, 452, 21312};
        long pref = -1;
        for (int num = 0; num < t1_ins.length; num++) {
            pref = getPrefix(t1_ins[num][0],t1_ins[num][1]);
            if (pref != t1_outs[num]) {
                System.out.println("getPrefix test has Failed\nInput : " + Arrays.toString(t1_ins[num]) + "\nOutput: " + pref + "\nExpected Output: " + t1_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getPrefix tests have passed");

        /**
         * Begin testing for prefixMatched
         * Inputs: (213,1),(452343,3),(21312,6)
         * Outputs: 2,452,21312
         */
        int[][] t2_ins = {{213,21},{452343,452}, {21312,21312},{56547,23}};
        boolean[] t2_outs = {true, true, true,false};
        boolean boolMatched = false;
        for (int num = 0; num < t2_ins.length; num++) {
            boolMatched = prefixMatched(t2_ins[num][0],t2_ins[num][1]);
            if (boolMatched != t2_outs[num]) {
                System.out.println("prefixMatched test has Failed\nInput : " + Arrays.toString(t2_ins[num]) + "\nOutput: " + boolMatched + "\nExpected Output: " + t2_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All prefixMatched tests have passed");

        /**
         * Begin testing for getDigit
         * Inputs: 12,1,18,2,4
         * Outputs: 3,1,9,2,4
         */
        int[] t3_ins = {12,1,18,2,4};
        int[] t3_outs = {3,1,9,2,4};
        int d = -1;
        for (int num = 0; num < t3_ins.length; num++) {
            d = getDigit(t3_ins[num]);
            if (d != t3_outs[num]) {
                System.out.println("getDigit test has Failed\nInput : " + t3_ins[num] + "\nOutput: " + d + "\nExpected Output: " + t3_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getDigit tests have passed");

        /**
         * Begin testing for sumofOddPlace
         * Inputs: 46546556465465l,42568559665995l,46543333465465l,465465789358565l
         * Outputs: 36,42,31,46
         */
        long[] t4_ins = {46546556465465l,42568559665995l,46543333465465l,465465789358565l};
        int[] t4_outs = {36,42,31,46};
        int sum_odd = -1;
        for (int num = 0; num < t4_ins.length; num++) {
            sum_odd = sumOfOddPlace(t4_ins[num]);
            if (sum_odd != t4_outs[num]) {
                System.out.println("sumOfOddPlace test has Failed\nInput : " + t4_ins[num] + "\nOutput: " + sum_odd + "\nExpected Output: " + t4_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All sumOfOddPlace tests have passed");

        /**
         * Begin testing for sumofOddPlace
         * Inputs: 46546556465465l,42568559665995l,46543333465465l,465465789358565l
         * Outputs: 36,42,31,46
         */
        long[] t5_ins = {46546556465465l,42568559665995l,46543333465465l,465465789358565l};
        int[] t5_outs = {25,30,33,35};
        int sum_even = -1;
        for (int num = 0; num < t5_ins.length; num++) {
            sum_even = sumOfDoubleEvenPlace(t5_ins[num]);
            if (sum_even != t5_outs[num]) {
                System.out.println("sumOfDoubleEvenPlace test has Failed\nInput : " + t5_ins[num] + "\nOutput: " + sum_even + "\nExpected Output: " + t5_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All sumOfDoubleEvenPlace tests have passed");

        //Valid CC Number
        long validTest = 4388576018410707L;
        //invalid CC number
        long inValidTest = 4388576018402626L;

        //test3
        long testCard = 5305050184759385L;

        System.out.println("Credit Card Number " + validTest + (isValid(validTest) ? " is Valid" : " is Invalid"));
        System.out.println("Credit Card Number: " + inValidTest + (isValid(inValidTest) ? " is Valid" : " is Invalid"));
        System.out.println("Credit Card Number: " + testCard + (isValid(testCard) ? " is Valid" : " is Invalid"));
    }
}
