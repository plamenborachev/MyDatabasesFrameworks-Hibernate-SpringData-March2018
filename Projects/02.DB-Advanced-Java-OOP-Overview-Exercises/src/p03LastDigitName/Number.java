package p03LastDigitName;

public class Number {
    private int number;

    public Number(int number) {
        this.number = number;
    }

    public String lastDigitName(){
        int lastDigit = this.number % 10;
        String lastDigitName = null;
        switch (lastDigit){
            case 0:
                lastDigitName = "zero";
                break;
            case 1:
                lastDigitName = "one";
                break;
            case 2:
                lastDigitName = "two";
                break;
            case 3:
                lastDigitName = "three";
                break;
            case 4:
                lastDigitName = "four";
                break;
            case 5:
                lastDigitName = "five";
                break;
            case 6:
                lastDigitName = "six";
                break;
            case 7:
                lastDigitName = "seven";
                break;
            case 8:
                lastDigitName = "eight";
                break;
            case 9:
                lastDigitName = "nine";
                break;
        }
        return lastDigitName;
    }
}
