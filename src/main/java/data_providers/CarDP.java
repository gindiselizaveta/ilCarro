package data_providers;

import dto.NewCar;
import org.testng.annotations.DataProvider;
import utils.PropertiesReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarDP {

    @DataProvider
    public Iterator<NewCar> dataProviderCarFile() {

        List<NewCar> list = new ArrayList<>();
        String fileName = PropertiesReader.getProperty("base.properties", "file_car_csv");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/data_csv" + File.separator + fileName))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(NewCar.builder().serialNumber(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .pricePerDay(Double.parseDouble(splitArray[7]))
                        .about(splitArray[8])
                        .city(splitArray[9]).build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }
}
