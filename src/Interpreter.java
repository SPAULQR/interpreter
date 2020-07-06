public interface Interpreter {
    String someNumberSystemToDecimal(String currentNumber, int receivedRadix);

    String decimalNumberSystemToSome(String decimalNumber, int requiredRadix);
}
