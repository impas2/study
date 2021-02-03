package web.carservice;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarService {

    private List<Car> carList = initSomeCars();

    public List<Car> initSomeCars() {
        List<Car> carList;
        carList = new ArrayList<>();
        carList.add(new Car("1", "BMW", 4));
        carList.add(new Car("2", "TAZ", 4));
        carList.add(new Car("3", "UAZ", 4));
        carList.add(new Car("4", "VAZ", 4));
        carList.add(new Car("5", "ZAZ", 4));

        return carList;
    }

    public List<Car> getCarList(int count) {
        return carList.stream().limit(count).collect(Collectors.<Car>toList());
    }



}
