package org.leniv.airlinemanager.controller;

import lombok.AllArgsConstructor;
import org.leniv.airlinemanager.entity.Plane;
import org.leniv.airlinemanager.service.AirlineService;
import org.leniv.airlinemanager.service.PlaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/planes")
@AllArgsConstructor
public class PlaneController {

    private final PlaneService planeService;
    private final AirlineService airlineService;

    @GetMapping
    public String getPlanes(
            @RequestParam Optional<Integer> rangeFrom, @RequestParam Optional<Integer> rangeTo,
            @RequestParam Optional<Integer> numberOfSeatsFrom, @RequestParam Optional<Integer> numberOfSeatsTo,
            @RequestParam Optional<Integer> fuelCapacityFrom, @RequestParam Optional<Integer> fuelCapacityTo,
            @RequestParam Optional<Integer> speedFrom, @RequestParam Optional<Integer> speedTo,
            @RequestParam Optional<Integer> mtowFrom, @RequestParam Optional<Integer> mtowTo,
            @RequestParam Optional<String> name, @RequestParam Optional<Long> airline, Model model
    ) {
        model.addAttribute("airlines", airlineService.findAll());
        model.addAttribute("planes", planeService.findAll(name, rangeFrom, rangeTo, numberOfSeatsFrom, numberOfSeatsTo,
                fuelCapacityFrom, fuelCapacityTo, speedFrom, speedTo, mtowFrom, mtowTo, airline));
        return "planes";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("plane", new Plane());
        model.addAttribute("airlines", airlineService.findAll());
        return "planes/new";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plane", planeService.findById(id));
        model.addAttribute("airlines", airlineService.findAll());
        return "planes/edit";
    }

    @PostMapping
    public String save(@ModelAttribute("plane") Plane plane) {
        planeService.save(plane);
        return "redirect:/planes";
    }

    @PatchMapping
    public String update(@ModelAttribute("plane") Plane plane) {
        planeService.save(plane);
        return "redirect:/planes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        planeService.deleteById(id);
        return "redirect:/planes";
    }

}
