import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Car[] slots;
    private Map<String, Integer> registrationToSlotMap;
    private Map<String, List<String>> colorToRegistrationMap;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        slots = new Car[capacity];
        registrationToSlotMap = new HashMap<>();
        colorToRegistrationMap = new HashMap<>();
    }

    public void park(String registrationNumber, String color) {
        if (isFull()) {
            System.out.println("Sorry, parking lot is full");
            return;
        }

        int availableSlot = getAvailableSlot();
        slots[availableSlot - 1] = new Car(registrationNumber, color);
        registrationToSlotMap.put(registrationNumber, availableSlot);
        colorToRegistrationMap.computeIfAbsent(color, k -> new ArrayList<>()).add(registrationNumber);
        System.out.println("Allocated slot number: " + availableSlot);
    }

    public void leave(int slotNumber) {
        if (isValidSlot(slotNumber)) {
            slots[slotNumber - 1] = null;
            System.out.println("Slot number " + slotNumber + " is free");
        } else {
            System.out.println("Invalid slot number");
        }
    }

    public void status() {
        System.out.println("Slot No. Registration No Colour");
        for (int i = 0; i < capacity; i++) {
            if (slots[i] != null) {
                System.out.println((i + 1) + " " + slots[i].getRegistrationNumber() + " " + slots[i].getColor());
            }
        }
    }

    public void registrationNumbersForCarsWithColour(String color) {
        List<String> registrationNumbers = colorToRegistrationMap.getOrDefault(color, new ArrayList<>());
        if (!registrationNumbers.isEmpty()) {
            System.out.println(String.join(", ", registrationNumbers));
        } else {
            System.out.println("Not found");
        }
    }

    public void slotNumberForRegistrationNumber(String registrationNumber) {
        Integer slotNumber = registrationToSlotMap.get(registrationNumber);
        if (slotNumber != null) {
            System.out.println(slotNumber);
        } else {
            System.out.println("Not found");
        }
    }

    public void slotNumbersForCarsWithColour(String color) {
        List<String> registrationNumbers = colorToRegistrationMap.getOrDefault(color, new ArrayList<>());
        if (!registrationNumbers.isEmpty()) {
            List<String> slotNumbers = new ArrayList<>();
            for (String regNumber : registrationNumbers) {
                Integer slotNumber = registrationToSlotMap.get(regNumber);
                if (slotNumber != null) {
                    slotNumbers.add(String.valueOf(slotNumber));
                }
            }
            System.out.println(String.join(", ", slotNumbers));
        } else {
            System.out.println("Not found");
        }
    }

    private boolean isFull() {
        for (Car slot : slots) {
            if (slot == null) {
                return false;
            }
        }
        return true;
    }

    private int getAvailableSlot() {
        for (int i = 0; i < capacity; i++) {
            if (slots[i] == null) {
                return i + 1;
            }
        }
        return -1;
    }

    private boolean isValidSlot(int slotNumber) {
        return slotNumber >= 1 && slotNumber <= capacity && slots[slotNumber - 1] != null;
    }
}