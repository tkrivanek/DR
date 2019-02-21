package com.vvg.krivanek.warehouserental.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvg.krivanek.warehouserental.service.UserWarehouseService;
import com.vvg.krivanek.warehouserental.service.WarehouseService;

@Controller
public class WarehouseController {
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	UserWarehouseService userWarehouseService;

	@GetMapping("/getWarehouses")
	public String mainWithParam(@RequestParam(name = "id", required = false) String id, Model model) {
		model.addAttribute("warehouses", warehouseService.getWarehouses(id));

		return "welcome"; // view
	}

}
