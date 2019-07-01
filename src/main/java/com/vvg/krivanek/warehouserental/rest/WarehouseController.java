package com.vvg.krivanek.warehouserental.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvg.krivanek.warehouserental.dao.service.UserDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.service.UserWarehouseService;
import com.vvg.krivanek.warehouserental.service.WarehouseService;
import com.vvg.krivanek.warehouserental.service.WarehouseStatusService;
import com.vvg.krivanek.warehouserental.service.WarehouseTypeService;

@Controller
public class WarehouseController {
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	UserWarehouseService userWarehouseService;
	@Autowired
	UserDaoService userDaoService;
	@Autowired
	WarehouseTypeService warehouseTypeService;
	@Autowired
	WarehouseStatusService warehouseStatusService;

	@GetMapping("/getWarehouses")
	public String getWarehouses(@RequestParam(name = "notRented", required = false) boolean notRented,
			@RequestParam(name = "rented", required = false) boolean rented,
			@RequestParam(name = "auction", required = false) boolean auction,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model) {
		model.addAttribute("warehouses",
				warehouseService.getWarehouses(notRented, rented, auction, PageRequest.of(page, 6)));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		model.addAttribute("currentPage", page);
		return "warehouses"; // view
	}

	@PostMapping("/saveWarehouse")
	public String saveWarehouse(Warehouse warehouse) {
		warehouseService.saveWarehouse(warehouse);
		return "redirect:/getWarehouses";
	}

	@PostMapping("/updateWarehouse")
	public String updateWarehouse(Warehouse warehouse) {
		warehouseService.updateWarehouse(warehouse);
		return "redirect:/getWarehouses";
	}

	@DeleteMapping("/deleteWarehouse")
	public String deleteWarehouse(@RequestParam(name = "warehouseId", required = true) String warehouseId) {
		warehouseService.deleteWarehouse(warehouseId);
		return "redirect:/getWarehouses";
	}

	@GetMapping("/findWarehouse")
	public String findWarehouse(@RequestParam(name = "warehouseId", required = true) String warehouseId, Model model) {
		model.addAttribute("warehouse", warehouseService.getWarehouse(warehouseId));
		model.addAttribute("warehouseTypes", warehouseTypeService.getWarehouseTypes());
		model.addAttribute("warehouseStatuses", warehouseStatusService.getWarehouseStatuses());
		return "editWarehouse";
	}

	@GetMapping("/createWarehouse")
	public String createWarehouse(Model model, Warehouse warehouse) {
		model.addAttribute("warehouseTypes", warehouseTypeService.getWarehouseTypes());
		model.addAttribute("warehouseStatuses", warehouseStatusService.getWarehouseStatuses());
		return "createWarehouse";
	}
}
