import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
class PromptWrapper {
    public static void reservationSystemMenu() {
        System.out.println("Reservation System Menu");
        System.out.println("[1] Economy Plus");
        System.out.println("[2] Economy");
        System.out.print("Type chosen menu: ");
    }

    public static void seatAssignment(int systemTypeChoice) {
        String systemType = null;
        if (systemTypeChoice == 1) {
            systemType = "Economy Plus";
        }
        else
            systemType = "Economy";

        System.out.println(systemType + " Seat Assignment");
        System.out.println("[1] Random");
        System.out.println("[2] Manual");
        System.out.print("Type chosen menu: ");
    }

    public static void seatFull(int systemTypeChoice) {
        String systemType = null;
        String viceVersa  = null;
        if (systemTypeChoice == 1) {
            systemType = "ECONOMY PLUS";
            viceVersa = "ECONOMY";
        }
        else {
            systemType = "ECONOMY";
            viceVersa = "ECONOMY PLUS";
        }
        System.out.println(systemType + " seats are FULL!");
        System.out.println("Change to " + viceVersa + "  seating [Y] Yes/[N] No: ");
    }

    public static void boardingPass(int reservationType, String seatNum, String bookingNum, String lastName, String firstName) {
        String reserve = null;
        if (reservationType == 1) {
            reserve = "ECONOMY PLUS";
        }
        else
            reserve = "ECONOMY";

        System.out.println("-----------------------------------BOARDING PASS----------------------------------------");
        System.out.println("SEAT TYPE: " + reserve);
        System.out.println("SEAT NUMBER: " + seatNum);

        System.out.println("\nBooking Number: " + bookingNum);
        System.out.println("Passenger's Last Name: " + lastName);
        System.out.println("Passenger's First Name: " + firstName);
        System.out.print("----------------------------------------------------------------------------------------");
    }

    public static void ExitSystem() {
        System.out.println("All passengers are on board!\nThank you! Next flight leaves in 3 hours.");
    }
}

public class AirlineReservation {
    public static final Random random = new Random();
    public static String[][] airplaneSeats = new String[3][18];
    public static final int[] economyPlusSeat = {0, 2, 11};
    public static final int[] economySeat = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17};
    public static void initializeAirplaneSeats(String mode) {
        //Takes mode parameter for setting different problem scenarios (Full Passenger, No Free Seat in Economy/Economy Plus)
        if (mode.equalsIgnoreCase("default")) {
            for(String[] airplaneRC : airplaneSeats) {
                Arrays.fill(airplaneRC, "A");
            }

            for(int row = 0; row < 2; row++) {
                for(int column = 0; column < 2; column++) {
                    airplaneSeats[row][column] = "N";
                }
            }
        }
        else if (mode.equalsIgnoreCase("full")) {
            for(String[] airplaneRC : airplaneSeats) {
                Arrays.fill(airplaneRC, "O");
            }

            for(int row = 0; row < 2; row++) {
                for(int column = 0; column < 2; column++) {
                    airplaneSeats[row][column] = "N";
                }
            }
        }

        else if (mode.equalsIgnoreCase("full-economy-plus")) {
            for(String[] airplaneRC : airplaneSeats) {
                Arrays.fill(airplaneRC, "A");
            }

            for(int row = 0; row < airplaneSeats.length; row++) {
                for(int column = 0; column < airplaneSeats[row].length; column++) {
                    if ((row == 2 && column == 0)|| (column == 2 && row != 2) || column == 11) {
                        airplaneSeats[row][column] = "O";
                    }
                }
            }

            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 2; column++) {
                    airplaneSeats[row][column] = "N";
                }
            }
        }

        else if (mode.equalsIgnoreCase("full-economy")) {
            for(String[] airplaneRC : airplaneSeats) {
                Arrays.fill(airplaneRC, "A");
            }

            for(int row = 0; row < airplaneSeats.length; row++) {
                for(int column = 0; column < airplaneSeats[row].length; column++) {
                    if (!((row == 2 && column == 0)|| (column == 2 && row != 2) || column == 11)) {
                        airplaneSeats[row][column] = "O";
                    }
                }
            }

            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 2; column++) {
                    airplaneSeats[row][column] = "N";
                }
            }
        }
    }
    public static boolean isAvailable(int row, int column) {
        return airplaneSeats[row][column].equalsIgnoreCase("A");
    }
    public static void changeSeatStatus(int row, int column, String status) {
        airplaneSeats[row][column] = status;
    }
    public static int[] randomSeatAssign(int reservationType) {
        boolean isOccupied = false;
        int rowIdx = 0;
        int columnIdx = 0;

        while (!isOccupied) {
            rowIdx = random.nextInt(3);
            if (reservationType == 1) {
                columnIdx = economyPlusSeat[random.nextInt(economyPlusSeat.length)];
            }
            else {
                columnIdx = economySeat[random.nextInt(economySeat.length)];
            }
            isOccupied = isAvailable(rowIdx, columnIdx);
        }

        return new int[]{rowIdx, columnIdx};
    }
    public static int[] getSeatAvailability() {
        int[] availability = {0, 0};

        for(int row = 0; row < airplaneSeats.length; row++) {
            for(int column = 0; column < airplaneSeats[row].length; column++) {
                if ((column == 0 || (column == 2 && (row == 0 || row == 1)) || column == 11) && airplaneSeats[row][column].equalsIgnoreCase("A")) {
                    availability[0]++;
                }
                else if (airplaneSeats[row][column].equalsIgnoreCase("A"))
                    availability[1]++;
            }
        }

        return availability;
    }
    public static void displayAirplaneSeats() {
        StringBuilder display = new StringBuilder("****************************** AIRLINE RESERVATION SYSTEM *******************************\n");

        int iter = 0;
        int[] availableSeat = getSeatAvailability();
        display.append(String.format("                Seats Availability: Economy Plus = %d Economy = %d\n\n", availableSeat[0], availableSeat[1]));
        display.append("     ");

        for(int columnLabel = 1; columnLabel <= airplaneSeats[0].length + 6; columnLabel++) {
            if (columnLabel == 10) {
                display.append(String.format("%d  ", columnLabel));
                continue;
            }
            else if (columnLabel > 11) {
                columnLabel = 18 + iter;
                iter++;
            }
            display.append(String.format("%d   ", columnLabel));
        }

        display.append("\n-----------------------------------------------------------------------------------------\n");

        for(int row = 0; row < airplaneSeats.length; row++) {
            String rowStr;

            if (row == 0)
                rowStr = "D";
            else if (row == 1)
                rowStr = "C";
            else {
                rowStr = "A";
                display.append("\n");
            }

            display.append(String.format("%s | ", rowStr));

            for(int column = 0; column < airplaneSeats[row].length; column++) {
                if (airplaneSeats[row][column] == null)
                    continue;
                else if (column >= 10)
                    display.append(String.format("  %s  ", airplaneSeats[row][column]));
                else
                    display.append(String.format("%s   ", airplaneSeats[row][column]));
            }

            display.append(String.format(" | %s\n", rowStr));
        }

        display.append("-----------------------------------------------------------------------------------------\n");

        display.append("\n             Legend: A = Available  O = Occupied  N = Not Applicable\n");

        display.append("*****************************************************************************************");
        System.out.println(display);
    }

    public static int rowInputVerify(String row) {
        switch (row) {
            case "A":
                return 2;
            case "C":
                return 1;
            default:
                return 0;
        }
    }

    public static int columnInputVerify(int column) {
        if (column < 11) {
            return column - 1;
        }
        switch (column) {
            case 11:
                return 18;
            case 12:
                return 19;
            case 13 :
                return 20;
            case 14:
                return 21;
            case 15:
                return 22;
            case 16:
                return 23;
            case 17:
                return 24;
            case 18:
                return 11;
            case 19:
                return 12;
            case 20:
                return 13;
            case 21:
                return 14;
            case 22:
                return 15;
            case 23:
                return 16;
            case 24:
                return 17;
            default:
                throw new IllegalStateException("Unexpected value: " + column);
        }
    }

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);

        initializeAirplaneSeats("default"); //Change here to test out the different situations
        // default, full, full-economy-plus, full-economy
        displayAirplaneSeats();

        PromptWrapper.reservationSystemMenu();

        int reservationType = entry.nextInt();
        entry.nextLine();

        int[] availability = getSeatAvailability();

        if (availability[0] == 0 && availability[1] == 0) {
            PromptWrapper.ExitSystem();
            System.exit(1);
        }

        int[] available = getSeatAvailability();

        if ((reservationType == 1 && available[0] == 0) || (reservationType == 2 && available[1] == 0)) {
            PromptWrapper.seatFull(reservationType);
            String choice = entry.nextLine();

            if (choice.equalsIgnoreCase("N")) {
                PromptWrapper.ExitSystem();
            }
            else {
                if (reservationType == 1) {
                    reservationType = 2;
                }
                else {
                    reservationType = 1;
                }
            }
        }

        System.out.println("******************************ENTER PASSENGER DETAILS************************************");
        System.out.print("Enter Booking Number: ");
        String bookingNum = entry.nextLine();
        System.out.print("Enter Passenger's Last Name: ");
        String lastName = entry.nextLine();
        System.out.print("Enter Passenger's First Name: ");
        String firstName = entry.nextLine();
        System.out.println("****************************************************************************************\n");

        PromptWrapper.seatAssignment(reservationType);

        int seatPlanChoice = entry.nextInt();
        entry.nextLine();

        String rowSeatChoice = null;
        int columnSeatChoice = 0;

        if (seatPlanChoice == 2) {
            String column = null;
            if (reservationType == 1) {
                column = "[1, 3, 18]";
            }
            else {
                column = "[2 - 11 and 19-24]";
            }

            System.out.println("-------------------------------MANUAL SEAT OCCUPATION-----------------------------------");
            System.out.print("Enter Row [A, C, D]: ");
            rowSeatChoice = entry.nextLine();

            System.out.printf("Enter Column %s: ", column);
            columnSeatChoice = entry.nextInt();

            boolean isOccupied = isAvailable(rowInputVerify(rowSeatChoice), columnInputVerify(columnSeatChoice));

            if (!isOccupied) {
                System.out.println("That seat is already occupied!");
                System.exit(0);
            }
            changeSeatStatus(rowInputVerify(rowSeatChoice), columnInputVerify(columnSeatChoice), "O");
        }

        else {
            int[] randomized = randomSeatAssign(reservationType);
            String[] rows = {"D", "C", "A"};
            rowSeatChoice = rows[randomized[0]];
            columnSeatChoice = columnInputVerify(randomized[1]);

            changeSeatStatus(rowInputVerify(rowSeatChoice), columnInputVerify(columnSeatChoice), "O");
        }

        displayAirplaneSeats();
        System.out.println();
        PromptWrapper.boardingPass(reservationType, String.format("%s%d", rowSeatChoice, columnSeatChoice), bookingNum, lastName, firstName);
    }
}
