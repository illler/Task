import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TicketsHandler {
    private static final String FILE_PATH = "C:\\Users\\tezer\\IdeaProjects\\test\\src\\main\\java\\tickets.json";
    private static final String ORIGIN_CITY = "Владивосток";
    private static final String DESTINATION_CITY = "Тель-Авив";
    private static final int UTC = 7;

    public static void main(String[] args) throws IOException {
        List<Ticket> tickets = readTicketsFromFile();

        Map<String, Integer> minTimeMap = new HashMap<>();
        List<Integer> prices = new ArrayList<>();
        int totalPrices = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getOrigin_name().equals(ORIGIN_CITY) && ticket.getDestination_name().equals(DESTINATION_CITY)) {
                int price = ticket.getPrice();
                int time = timeFinder(ticket);

                prices.add(price);
                totalPrices += price;

                minTimeMap.compute(ticket.getCarrier(), (t, existingTime) -> existingTime == null ? time : Math.min(existingTime, time));
            }
        }

        printMinFlightTimes(minTimeMap);

        System.out.println("<------------->");
        prices.sort(Integer::compareTo);
        int median = calculateMedian(prices);
        int avg = totalPrices / prices.size();
        System.out.println("Разница между средней ценой и медианой: " + (avg - median));
    }

    private static void printMinFlightTimes(Map<String, Integer> minTimeMap) {
        minTimeMap.forEach((ticket, timeInMinutes) -> {
            int hours = timeInMinutes / 60;
            int minutes = timeInMinutes % 60;
            String formattedTime = String.format("Минимальное время полета для авиакомпании %s: %02d:%02d", ticket, hours, minutes);
            System.out.println(formattedTime);
        });
    }

    private static List<Ticket> readTicketsFromFile() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        ObjectMapper objectMapper = new ObjectMapper();
        TicketWrapper ticketWrapper = objectMapper.readValue(json, TicketWrapper.class);
        return ticketWrapper.getTickets();
    }

    private static int calculateMedian(List<Integer> prices) {
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2) + prices.get(size / 2 - 1)) / 2;
        } else {
            return prices.get((size - 1) / 2);
        }
    }

    private static int timeFinder(Ticket ticket) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime departureTime = LocalTime.parse(ticket.getDeparture_time(), formatter);
        LocalTime arrivalTime = LocalTime.parse(ticket.getArrival_time(), formatter);

        Duration timeDifference = Duration.between(departureTime, arrivalTime).plusHours(UTC);
        return (int) timeDifference.toMinutes();
    }

}

