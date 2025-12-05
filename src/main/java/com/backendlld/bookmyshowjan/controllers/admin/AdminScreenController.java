package com.backendlld.bookmyshowjan.controllers.admin;

import com.backendlld.bookmyshowjan.dtos.ScreenRequestDTO;
import com.backendlld.bookmyshowjan.dtos.ScreenResponseDTO;
import com.backendlld.bookmyshowjan.dtos.SeatBlockConfig;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.services.ScreenLayoutService;
import com.backendlld.bookmyshowjan.services.ScreenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/screen")
@PreAuthorize("hasRole('ADMIN') or hasRole('THEATER_OWNER')")
public class AdminScreenController {

    private final ScreenLayoutService screenLayoutService;
    private final ScreenService screenService;

    public AdminScreenController(ScreenLayoutService screenLayoutService,  ScreenService screenService) {
        this.screenLayoutService = screenLayoutService;
        this.screenService = screenService;
    }

    @PostMapping("/add")// admin/screens/add
    public ResponseEntity<ScreenResponseDTO> createScreen(
            @RequestBody ScreenRequestDTO request) {

        System.out.println("theaterId = " + request.getTheaterId());
        System.out.println("screenName = " + request.getScreenName());

        Screen screen = screenService.createScreen(request.getTheaterId(), request.getScreenName());



        ScreenResponseDTO response = new ScreenResponseDTO();
        response.setScreenId(screen.getId());
        response.setScreenName(screen.getName());
//
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{screenId}/layout")
    public ResponseEntity<Void> createLayout(
            @PathVariable String screenId,
            @RequestBody List<SeatBlockConfig> blocks) {

        screenLayoutService.generateLayout(screenId, blocks);
        return ResponseEntity.ok().build();
    }
}