package com.vvg.krivanek.warehouserental.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vvg.krivanek.warehouserental.dao.service.UserDaoService;
import com.vvg.krivanek.warehouserental.domain.AuctionBid;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;
import com.vvg.krivanek.warehouserental.service.WarehouseAuctionService;

@Controller
public class WarehouseAuctionController {

	@Autowired
	WarehouseAuctionService warehouseAuctionService;

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/getAuctions")
	public String getAuction(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model) {
		model.addAttribute("warehouseAuctions", warehouseAuctionService.getAuctionWarehouses(PageRequest.of(page, 6)));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return "auction";
	}

	@GetMapping("/getWarehouseAuction")
	public String getAuctionWarehouse(
			@RequestParam(name = "warehouseAuctionId", required = true) String warehouseAuctionId, Model model,
			WarehouseAuction warehouseAuction) {
		model.addAttribute("warehouseAuction", warehouseAuctionService.getWarehouseAuctionById(warehouseAuctionId));
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("user",
					userDaoService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		return "warehouseAuction";
	}

	@GetMapping("/start")
	public void start(Model model) {
		warehouseAuctionService.warehouseAuctionScheduler();
	}

	@PostMapping("/warehouseAuctionBid")
	public String warehouseAuctionBid(Model model, AuctionBid auctionBid) {
		warehouseAuctionService.warehouseAuctionBid(auctionBid);
		return "redirect:/getWarehouseAuction?warehouseAuctionId=" + auctionBid.getWarehouseAuctionId();

	}
}
