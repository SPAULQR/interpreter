public class InterpreterTest {
    static InterpreterImpl interpreterImpl = new InterpreterImpl();
    static String expectedNum1 = "1496.671875";
    static String expectedNum2 = "22.625";
    static String expectedNum3 = "1001011";
    static String expectedNum4 = "11000010.001";


    public static void main(String[] args) {
        String actualNum1 = interpreterImpl.someNumberSystemToDecimal("5D8,AC", 16);
        String actualNum2 = interpreterImpl.someNumberSystemToDecimal("10110,101", 2);
        String actualNum3 = interpreterImpl.decimalNumberSystemToSome("75", 2);
        String actualNum4 = interpreterImpl.decimalNumberSystemToSome("194,125", 2);

        System.out.println(expectedNum1.equals(actualNum1));
        System.out.println(expectedNum2.equals(actualNum2));
        System.out.println(expectedNum3.equals(actualNum3));
        System.out.println(expectedNum4.equals(actualNum4));
    }
}
