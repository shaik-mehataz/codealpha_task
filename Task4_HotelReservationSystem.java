import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category; // Standard, Deluxe, Suite
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - " + (isBooked ? "Booked" : "Available");
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String category;
    String paymentStatus;

    Booking(String customerName, int roomNumber, String category, String paymentStatus) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.paymentStatus = paymentStatus;
    }

    public String toString() {
        return "Name: " + customerName + ", Room: " + roomNumber + " (" + category + "), Payment: " + paymentStatus;
    }
}

public class Task4_HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {
        initializeRooms();
        loadBookingsFromFile();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayAvailableRooms();
                    break;
                case 2:
                    bookRoom(sc);
                    break;
                case 3:
                    cancelBooking(sc);
                    break;
                case 4:
                    displayBookings();
                    break;
                case 5:
                    saveBookingsToFile();
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    static void initializeRooms() {
        for (int i = 1; i <= 10; i++) {
            String type = (i <= 4) ? "Standard" : (i <= 7 ? "Deluxe" : "Suite");
            rooms.add(new Room(i, type));
        }
    }

    static void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (!room.isBooked)
                System.out.println(room);
        }
    }

    static void bookRoom(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        displayAvailableRooms();
        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();
        sc.nextLine(); // consume newline

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && !room.isBooked) {
                room.isBooked = true;
                System.out.print("Enter payment amount (simulate): ₹");
                double amt = sc.nextDouble();
                sc.nextLine();

                bookings.add(new Booking(name, room.roomNumber, room.category, "Paid ₹" + amt));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available or invalid number.");
    }

    static void cancelBooking(Scanner sc) {
        System.out.print("Enter your name to cancel booking: ");
        String name = sc.nextLine();
        Iterator<Booking> it = bookings.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Booking booking = it.next();
            if (booking.customerName.equalsIgnoreCase(name)) {
                it.remove();
                for (Room r : rooms) {
                    if (r.roomNumber == booking.roomNumber) {
                        r.isBooked = false;
                        break;
                    }
                }
                found = true;
                System.out.println("Booking cancelled.");
                break;
            }
        }
        if (!found) System.out.println("No booking found with that name.");
    }

    static void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("\n--- Current Bookings ---");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    static void saveBookingsToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Booking b : bookings) {
                out.println(b.customerName + "," + b.roomNumber + "," + b.category + "," + b.paymentStatus);
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    static void loadBookingsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                String name = data[0];
                int roomNo = Integer.parseInt(data[1]);
                String category = data[2];
                String payment = data[3];

                for (Room r : rooms) {
                    if (r.roomNumber == roomNo) {
                        r.isBooked = true;
                        break;
                    }
                }

                bookings.add(new Booking(name, roomNo, category, payment));
            }
        } catch (Exception e) {
            System.out.println("Error loading previous bookings.");
        }
    }
}
