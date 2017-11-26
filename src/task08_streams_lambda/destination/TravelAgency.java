package task08_streams_lambda.destination;

public class TravelAgency {
    void playWithDistination() {
        Destination dest1 = new Destination("Tallinn", 305);

        Destination dest2 = new Destination("Hong Kong", 281);
        Destination dest3 = new Destination("London", 270);

        System.out.println(dest1.getCelsius());
        //System.out.println(dest1.getFahrenheit());
        System.out.println(dest2.getFahrenheit());

        // ei kaivita!!
     //   System.out.println(dest1.getAvgWeather(t -> t - 273.15));

        System.out.println(dest1.getAvgWeather(t -> t-273.15));
        System.out.println(dest1.getAvgWeather(t -> t-273.15));
    }

//    public static double getCelsius(double tempKelvin) {
//        return tempKelvin - 273.15;
//    }

    public static void main(String[] args) {
        new TravelAgency().playWithDistination();
    }
}
