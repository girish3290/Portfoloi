public class Main {

    public static void main(String[] args) {
        // Input data to be bit-stuffed
        String inputData = "01111110 0011110 11111110";
        System.out.println("Original Data: " + inputData);

        String stuffedData = bitStuffing(inputData);//Calling bitStuffing Function 
        System.out.println("Stuffed Data: " + stuffedData);
    }

    public static String bitStuffing(String input) {
        StringBuilder stuffedData = new StringBuilder();//Creates a mutable string object named "stuffedData"

        int count = 0;
        for (char bit : input.toCharArray()) {//Converts String to a array of characters
            stuffedData.append(bit);//Add to stuffedData
            if (bit == '1') {
                count++;
            } else {
                count = 0;
            }

            // Inserting '0' after five consecutive '1's
            if (count == 5) {
                stuffedData.append('0');
                count = 0;
            }
        }

        return stuffedData.toString();
    }
}
