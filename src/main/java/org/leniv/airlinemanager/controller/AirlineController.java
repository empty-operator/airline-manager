package org.leniv.airlinemanager.controller;

import lombok.AllArgsConstructor;
import org.leniv.airlinemanager.entity.Airline;
import org.leniv.airlinemanager.service.AirlineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/airlines")
@AllArgsConstructor
public class AirlineController {

    private final AirlineService service;

    @GetMapping
    public String getAirlines(
            @RequestParam Optional<Long> totalMtowFrom, @RequestParam Optional<Long> totalMtowTo,
            @RequestParam Optional<Long> totalNumberOfSeatsFrom, @RequestParam Optional<Long> totalNumberOfSeatsTo,
            @RequestParam Optional<String> name, Model model
    ) {
        model.addAttribute("airlines", service.findAll(name, totalMtowFrom, totalMtowTo, totalNumberOfSeatsFrom,
                totalNumberOfSeatsTo));
        return "airlines";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("airline", new Airline());
        return "airlines/new";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("airline", service.findById(id));
        return "airlines/edit";
    }

    @PostMapping
    public String save(@ModelAttribute("airline") Airline airline) {
        service.save(airline);
        return "redirect:/airlines";
    }

    @PatchMapping
    public String update(@ModelAttribute("airline") Airline airline) {
        service.save(airline);
        return "redirect:/airlines";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/airlines";
    }

}
