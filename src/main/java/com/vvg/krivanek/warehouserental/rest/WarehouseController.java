package com.vvg.krivanek.warehouserental.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvg.krivanek.warehouserental.dao.WarehouseDao;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.service.WarehouseAuctionService;
import com.vvg.krivanek.warehouserental.service.WarehouseService;

@Controller
public class WarehouseController {
	@Autowired
	WarehouseService warehouseService;

    @GetMapping("/getWarehouses")
    public String mainWithParam( @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);        
        model.addAttribute("warehouses", warehouseService.getWarehouses());

        return "welcome"; //view
    }

}
