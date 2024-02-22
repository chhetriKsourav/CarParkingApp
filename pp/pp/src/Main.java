import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;
        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "create_parking_lot":
                    int capacity = Integer.parseInt(parts[1]);
                    parkingLot = new ParkingLot(capacity);
                    System.out.println("Created a parking lot with " + capacity + " slots");
                    break;
                case "park":
                    parkingLot.park(parts[1], parts[2]);
                    break;
                case "leave":
                    parkingLot.leave(Integer.parseInt(parts[1]));
                    break;
                case "status":
                    parkingLot.status();
                    break;
                case "registration_numbers_for_cars_with_colour":
                    parkingLot.registrationNumbersForCarsWithColour(parts[1]);
                    break;
                case "slot_number_for_registration_number":
                    parkingLot.slotNumberForRegistrationNumber(parts[1]);
                    break;
                case "slot_numbers_for_cars_with_colour":
                    parkingLot.slotNumbersForCarsWithColour(parts[1]);
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
