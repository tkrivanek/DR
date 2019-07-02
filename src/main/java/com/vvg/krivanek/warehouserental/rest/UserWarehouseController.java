package com.vvg.krivanek.warehouserental.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvg.krivanek.warehouserental.dao.service.UserDaoService;
import com.vvg.krivanek.warehouserental.domain.UserWarehouse;
import com.vvg.krivanek.warehouserental.service.UserWarehouseService;
import com.vvg.krivanek.warehouserental.service.WarehouseService;

@Controller
public class UserWarehouseController {

	@Autowired
	UserWarehouseService userWarehouseService;
	@Autowired
	UserDaoService userDaoService;
	@Autowired
	WarehouseService warehouseService;

	@GetMapping("/rentWarehouse")
	public String findWarehouse(@RequestParam(name = "warehouseIds", required = true) String warehouseId,
			UserWarehouse userWarehouse, Model model) {
		model.addAttribute("warehouse", warehouseService.getWarehouse(warehouseId));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return "rentWarehouse";
	}

	@GetMapping("/getUserWarehouses")
	public String getUserWarehouses(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model) {
		model.addAttribute("userWarehouses",
				userWarehouseService.getUserWarehouses(Long.valueOf(userId), PageRequest.of(page, 6)));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return "userWarehouses"; // view
	}
	
	@GetMapping("/getAllUserWarehouses")
	public String getAllUserWarehouses(	@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model) {
		model.addAttribute("userWarehouses",
				userWarehouseService.getAllUserWarehouses(PageRequest.of(page, 6)));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return "rentedWarehouses"; // view
	}

	@PostMapping("/saveWarehouseRent")
	public String saveWarehouseRent(UserWarehouse userWarehouse) {
		userWarehouseService.saveWarehouseRent(userWarehouse);
		return "redirect:/getWarehouses";
	}

	@DeleteMapping("/cancelWarehouseRent")
	public String cancelWarehouseRent(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "warehouseId", required = true) String warehouseId) {
		userWarehouseService.cancelWarehouseRent(userId, warehouseId);
		return "redirect:/getUserWarehouses?userId="+userId;
	}
}
