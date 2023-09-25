public class Loader {
    public static void main(String[] args) {
//        System.out.println(args[0]);
        String redBlock = "\u001B[41m   ";      // Red background block
        String greenBlock = "\u001B[42m   ";    // Green background block
        String darkGreyBlock = "\u001B[100m   ";   // Yellow background block
        String lightGrey = "\u001B[47m   ";       // Reset color to default

        // Print colored blocks
        System.out.print(redBlock);
        System.out.print(greenBlock);
        System.out.print(darkGreyBlock);
        System.out.print(lightGrey);

    }
}
