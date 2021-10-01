
public class ExchangeRateMath {

    public static double calculateRate(double inputValue , double inputRate , double outputRate){

        //calculates the exchange rate and returns the final value.
        double exchangeRate = outputRate / inputRate;
        double outputValue =  inputValue * exchangeRate;

        return Math.round(outputValue*100.0)/100.0;
    }
}
