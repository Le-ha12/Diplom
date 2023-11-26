package data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Data
public class DataHelper {
    private Faker faker;

    @Value
    public static class NumberCardClass {
        private String numberCard;
    }

    public static NumberCardClass getNumberCardApproved() {
        return new NumberCardClass("4444 4444 4444 4441");
    }
    public static NumberCardClass getNumberCardDeclined() {
        return new NumberCardClass("4444 4444 4444 4442");
    }
    public static NumberCardClass getNumberCardSymbol() {
        return new NumberCardClass("As!@");
    }
    public static NumberCardClass getNumberCardLessQuantity() {
        return new NumberCardClass("4");
    }
    public static NumberCardClass getNumberCardMoreQuantity() {
        return new NumberCardClass("4444 4444 4444 4444 4");
    }
    public static NumberCardClass getNumberCardEmpty() {
        return new NumberCardClass("");
    }

     public static String getCardownerValid() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCardownerLessQuantity() {
        return "z";
    }
    public static String getCardownerMoreQuantity() {
        return "CardownerCardownerCard";
    }
    public static String getCardownerSymbol() {
        return "1,ю";
    }
    public static String getCardownerEmpty() {
        return "";
    }

    public static String getMonthVal() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getMonthNoVal() {
        return LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getMonthZero() {
        return "00";
    }
    public static String getMonthLessQuantity() {
        return "1";
    }
    public static String getMonthMoreQuantity() {
        return "011";
    }
    public static String getMonthSymbol() {
        return "s,";
    }
    public static String getMonthEmpty() {
        return "";
    }

    public static String getYearVal() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getYearNoVal() {
        return LocalDate.now().minusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getYearNow() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearZero() {
        return "00";
    }
    public static String getYearLessQuantity() {
        return "2";
    }
    public static String getYearMoreQuantity() {
        return "222";
    }
    public static String getYearSymbol() {
        return "w!";
    }
    public static String getYearEmpty() {
        return "";
    }

    public static String getCvcVal() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String cvcVal[] = {"121", "115", "243", "604"};
        return cvcVal[rand];
    }

    public static String getCvcLessQuantity() {
        return "5";
    }
    public static String getCvcMoreQuantity() {
        return "0001";
    }
    public static String getCvcSymbol() {
        return "r-";
    }
    public static String getCvcEmpty() {
        return "";
    }
}
