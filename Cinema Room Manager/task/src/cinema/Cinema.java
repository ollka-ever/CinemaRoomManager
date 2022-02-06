package cinema;

import java.util.Scanner;

public class Cinema {

    static int rowsQuantity = 0;
    static int seatsQuantity = 0;
    static int selectedRow = 0;
    static int selectedSeat = 0;
    String[][] matrix;
    int purchasedSeats;
    int currentIncome;

    //initial code from main
    public static String[][] initial(){
        //creating matrix and filling it with S
        System.out.println("Enter the number of rows:");
        rowsQuantity = rows();
        System.out.println("Enter the number of seats in each row:");
        seatsQuantity = seats();

        String[][] matrix = new String[rowsQuantity][seatsQuantity];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = "S";
            }
        }

        //printing sample of matrix
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsQuantity; i++) {
            if (i != seatsQuantity) {
                System.out.print(i + " ");
            } else {
                System.out.println(i);
            }
        }

        int temp = 1;

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(temp + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != matrix[i].length - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else if (j == matrix[i].length - 1) {
                    System.out.println(matrix[i][j]);
                }
            }
            temp++;
        }
        return matrix;
    }

    //create cinema
    public static String[][] creation(){
        //creating matrix and filling it with S
        System.out.println("Enter the number of rows:");
        rowsQuantity = rows();
        System.out.println("Enter the number of seats in each row:");
        seatsQuantity = seats();

        String[][] matrix = new String[rowsQuantity][seatsQuantity];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = "S";
            }
        }

        return matrix;
    }

    //display cinema matrix with booked place
    public static void showCinema(String[][] matrix){
        System.out.println();
        matrix[selectedRow - 1][selectedSeat - 1] = "B";
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsQuantity; i++) {
            if (i != seatsQuantity) {
                System.out.print(i + " ");
            } else {
                System.out.println(i);
            }
        }

        int temp = 1;

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(temp + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != matrix[i].length - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else if (j == matrix[i].length - 1) {
                    System.out.println(matrix[i][j]);
                }
            }
            temp++;
        }
    }

    //display cinema matrix with seats
    public void showSeats(){
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsQuantity; i++) {
            if (i != seatsQuantity) {
                System.out.print(i + " ");
            } else {
                System.out.println(i);
            }
        }

        int temp = 1;

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(temp + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != matrix[i].length - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else if (j == matrix[i].length - 1) {
                    System.out.println(matrix[i][j]);
                }
            }
            temp++;
        }
    }

    //getting rows in cinema
    public static int rows(){
        Scanner scanner = new Scanner(System.in);
        int rowsQuantity = scanner.nextInt();
        return rowsQuantity;
    }

    //getting seats in row in cinema
    public static int seats(){
        Scanner scanner = new Scanner(System.in);
        int seatsQuantity = scanner.nextInt();
        return seatsQuantity;
    }

    //selecting row in cinema
    public static int rowNeeded(){
        Scanner scanner = new Scanner(System.in);
        int neededRow = scanner.nextInt();
        return neededRow;
    }

    //selecting seat in row in cinema
    public static int seatNeeded(){
        Scanner scanner = new Scanner(System.in);
        int seatNeeded = scanner.nextInt();
        return seatNeeded;
    }

    //counting cinema capacity
    public static int cinemaCapacity(String[][] matrix){
        //System.out.println("Enter the number of rows:");
        rowsQuantity = matrix.length;
        //System.out.println("Enter the number of seats in each row:");
        seatsQuantity = matrix[0].length;
        int capacity = rowsQuantity * seatsQuantity;
        return capacity;
    }

    //counting possible profit depending on cinema size
    public static int countProfit(String[][] matrix){
        int capacity = cinemaCapacity(matrix);
        int profit = 0;

        if (capacity < 61) {
            profit = capacity * 10;
        } else {
            int expensive = (rowsQuantity / 2) * seatsQuantity;
            int cheap = capacity - expensive;
            profit = expensive * 10 + cheap * 8;
        }
        System.out.println("Total income:");
        System.out.printf("$%d", profit);
        return profit;
    }

    public void askingForSeat(){
        System.out.println();
        System.out.println("Enter a row number:");
        selectedRow = rowNeeded();
        System.out.println("Enter a seat number in that row:");
        selectedSeat = seatNeeded();
    }

    public void validateSeat(){
        while (selectedRow < 1 || selectedRow > rowsQuantity || selectedSeat < 1 || selectedSeat > seatsQuantity) {
            System.out.println();
            System.out.println("Wrong input!");
            askingForSeat();
        }
    }

    //selecting a seat with price
    public void selectSeat(){
        askingForSeat();
        validateSeat();

        while (matrix[selectedRow - 1][selectedSeat - 1].equals("B")) {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            askingForSeat();
            validateSeat();
        }

        matrix[selectedRow - 1][selectedSeat - 1] = "B";
        purchasedSeats++;

        int price = 0;
        int capacity = cinemaCapacity(matrix);

        if (capacity < 61) {
            price = 10;
        } else {
            if (selectedRow <= matrix.length / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println();
        System.out.printf("Ticket price: $%d%n", price);
        currentIncome += price;
    }

    public void statistics(){
        System.out.println();
        System.out.println("Number of purchased tickets: " + purchasedSeats);
        double percent = percents();
        System.out.printf("Percentage: %.2f", percent);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome());
    }

    public int totalIncome(){
        int totalIncome = 0;
        int capacity = cinemaCapacity(matrix);

        if (capacity < 61) {
            totalIncome = capacity * 10;
        } else {
            totalIncome = (rowsQuantity / 2 * seatsQuantity * 10) + ((capacity - rowsQuantity / 2 * seatsQuantity) * 8);
        }

        return totalIncome;
    }

    public double percents(){
        return purchasedSeats * 100.00 / cinemaCapacity(matrix);
    }

    public void menuOptions() {
        menuItself();

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        while (i != 0) {
            if (i == 1) {
                showSeats();
                System.out.println();
                menuItself();
                i = scanner.nextInt();
            } else if (i == 2) {
                selectSeat();
                System.out.println();
                menuItself();
                i = scanner.nextInt();
            } else if (i == 3) {
                statistics();
                System.out.println();
                menuItself();
                i = scanner.nextInt();
            }
        }
    }

    public static void menuItself(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        // Write your code here
        Cinema cinema = new Cinema();
        cinema.matrix = creation();
        System.out.println();
        cinema.menuOptions();

    }
}