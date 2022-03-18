public class Solution {

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();

        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);

        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);

        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));

        undergroundSystem.checkIn(10, "Leyton", 24);
        undergroundSystem.checkOut(10, "Waterloo", 38);

        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }
}
