public class InterpreterImpl implements Interpreter {

    @Override
    public String someNumberSystemToDecimal(String currentNumber, int receivedRadix) {
        double resultDecimalNumber;
        int commaIndex = currentNumber.indexOf(',');

        if (commaIndex == -1) {
            resultDecimalNumber = countDecimalNumerals(currentNumber, receivedRadix, currentNumber.length());
        } else {
            resultDecimalNumber = countDecimalNumerals(currentNumber, receivedRadix, commaIndex);
        }

        return String.valueOf(resultDecimalNumber);
    }

    private double countDecimalNumerals(String requestedNumber, int radix, int commaIndex) {
        char[] numSequence = requestedNumber.toCharArray();
        double resultDecimalNumber = 0;

        for (int i = 0; i < commaIndex; i++) {
            int tempDecimalNumeral = someNumeralToDecimalNumeral(numSequence[i]);
            resultDecimalNumber += tempDecimalNumeral * Math.pow(radix, commaIndex - 1 - i);
        }

        int countOfNumbersAfterComma = requestedNumber.length() - commaIndex;

        for (int i = 1; i < countOfNumbersAfterComma; i++) {
            int tempDecimalNumber = someNumeralToDecimalNumeral(numSequence[commaIndex + i]);
            resultDecimalNumber += tempDecimalNumber * Math.pow(radix, -i);
        }
        return resultDecimalNumber;
    }

    private int someNumeralToDecimalNumeral(int charAsNumber) {
        if (charAsNumber >= 48 && charAsNumber <= 57) {
            return charAsNumber - 48;
        } else if (charAsNumber >= 65 && charAsNumber <= 90) {
            return charAsNumber - 55;
        } else throw new IllegalArgumentException();
    }

    @Override
    public String decimalNumberSystemToSome(String decimalNumber, int requiredRadix) {
        int commaIndex = decimalNumber.indexOf(',');
        String leftPartSomeNumber = countSomeNumerals(decimalNumber, requiredRadix, commaIndex);
        boolean isCommaHere = commaIndex != -1;
        String rightPartSomeNumber = "";


        if (isCommaHere) {
            String fractionalPart = "0." + decimalNumber.substring(commaIndex + 1);
            rightPartSomeNumber = countSomeFractionalPart(fractionalPart, requiredRadix);
        }

        return isCommaHere
                ? leftPartSomeNumber + '.' + rightPartSomeNumber
                : leftPartSomeNumber;
    }

    private String countSomeNumerals(String decimalNumber, int requiredRadix, int commaIndex) {
        long decimalLong;

        if (commaIndex != -1) {
            decimalLong = Long
                    .parseLong(decimalNumber
                            .substring(0, commaIndex));
        } else decimalLong = Long.parseLong(decimalNumber);

        StringBuilder result = new StringBuilder();

        while (decimalLong != 0) {
            long temp = decimalLong % requiredRadix;
            decimalLong /= requiredRadix;
            result.append(decimalNumeralToSomeNumeral((int) temp));
        }
        return String.valueOf(result.reverse());
    }

    private String countSomeFractionalPart(String fractionalPart, int requiredRadix) {
        double fractionalDouble = Double.parseDouble(fractionalPart);

        StringBuilder result = new StringBuilder();

        while (fractionalDouble != 0) {
            fractionalDouble *= requiredRadix;
            long temp = (long) fractionalDouble;
            result.append(decimalNumeralToSomeNumeral(temp));
            fractionalDouble %= 1;
        }

        return String.valueOf(result);
    }

    private char decimalNumeralToSomeNumeral(long charAsNumber) {
        if (charAsNumber >= 0 && charAsNumber <= 9) {
            return (char) (charAsNumber + 48);
        } else if (charAsNumber >= 10 && charAsNumber <= 36) {
            return (char) (charAsNumber + 55);
        } else throw new IllegalArgumentException();
    }

}
