package by.dzmitry_lakisau.hw02;

public class Calculator implements ICalculator {

    @Override
    public String add(float... values) {
        float sum = 0;
        for (float value : values) {
            sum += value;
        }
        return format(sum);
    }

    private String format(float value) {
        if (value % 1 != 0 )
            return String.valueOf(value);
        else return String.valueOf((int) value);
    }

    @Override
    public String multiply(float... values) {
        float res = 1;
        for(float value : values){
            res = res * value;
        }
        return format(res);
    }
}
