package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.carservice.CarService;
import web.model.Car;
import java.util.List;


@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String doSomeCars(@RequestParam int count, Model model) {
        List<Car> carList = carService.getCarList(count);
        model.addAttribute("car", carList);
        return "cars";
    }

}
