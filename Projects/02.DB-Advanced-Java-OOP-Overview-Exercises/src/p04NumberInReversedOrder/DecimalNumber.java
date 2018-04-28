package p04NumberInReversedOrder;

public class DecimalNumber {
    private double number;

    public DecimalNumber(double number) {
        this.number = number;
    }

    public String numberInReversedOrder(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.number);
        String reversedNumber = sb.reverse().toString();
        if (reversedNumber.startsWith("0.")){
            reversedNumber = reversedNumber.substring(2);
        }
        return reversedNumber;
    }
}
