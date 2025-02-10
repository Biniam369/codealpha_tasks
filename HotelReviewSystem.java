import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// Review class to store individual reviews
class Review {
    private String user;
    private int rating;
    private String comment;
    private Date date;

    public Review(String user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.date = new Date(); // Automatically sets the review date
    }

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "User: " + user + " | Rating: " + rating + "/5 | " + comment + " | Date: " + date;
    }
}

// Hotel class to store hotel details and associated reviews
class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    public void addReview(String user, int rating, String comment) {
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }
        reviews.add(new Review(user, rating, comment));
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }

    public List<Review> getSortedReviewsByRating() {
        return reviews.stream()
                .sorted(Comparator.comparingInt(Review::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> getSortedReviewsByDate() {
        return reviews.stream()
                .sorted(Comparator.comparing(Review::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> filterReviewsByMinRating(int minRating) {
        return reviews.stream()
                .filter(review -> review.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Hotel: " + name + " | Total Reviews: " + reviews.size();
    }
}

// Main System to manage hotels and user interaction
public class HotelReviewSystem {
    private static final Map<String, Hotel> hotels = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHotel Review System");
            System.out.println("1. Add Hotel");
            System.out.println("2. Add Review");
            System.out.println("3. View Reviews (Sorted by Rating)");
            System.out.println("4. View Reviews (Sorted by Date)");
            System.out.println("5. Filter Reviews by Minimum Rating");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter hotel name: ");
                    String hotelName = scanner.nextLine();
                    hotels.put(hotelName, new Hotel(hotelName));
                    System.out.println("Hotel added successfully!");
                    break;

                case 2:
                    System.out.print("Enter hotel name: ");
                    hotelName = scanner.nextLine();
                    if (!hotels.containsKey(hotelName)) {
                        System.out.println("Hotel not found!");
                        break;
                    }
                    System.out.print("Enter your name: ");
                    String user = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter comment: ");
                    String comment = scanner.nextLine();
                    hotels.get(hotelName).addReview(user, rating, comment);
                    System.out.println("Review added successfully!");
                    break;

                case 3:
                    System.out.print("Enter hotel name: ");
                    hotelName = scanner.nextLine();
                    if (!hotels.containsKey(hotelName)) {
                        System.out.println("Hotel not found!");
                        break;
                    }
                    System.out.println("Reviews sorted by rating:");
                    hotels.get(hotelName).getSortedReviewsByRating().forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter hotel name: ");
                    hotelName = scanner.nextLine();
                    if (!hotels.containsKey(hotelName)) {
                        System.out.println("Hotel not found!");
                        break;
                    }
                    System.out.println("Reviews sorted by date:");
                    hotels.get(hotelName).getSortedReviewsByDate().forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter hotel name: ");
                    hotelName = scanner.nextLine();
                    if (!hotels.containsKey(hotelName)) {
                        System.out.println("Hotel not found!");
                        break;
                    }
                    System.out.print("Enter minimum rating: ");
                    int minRating = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Filtered reviews:");
                    hotels.get(hotelName).filterReviewsByMinRating(minRating).forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}