package edu.tucn.scd.serverapp.position;

import edu.tucn.scd.serverapp.terminal.TerminalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/positions") // maps the requests starting with '/positions' to this controller
public class PositionController {

    @Autowired // creates the injection of PositionService instance
    private PositionService positionService;

    @PostMapping // maps the '/positions' POST requests to this method
    @Operation(summary = "Save a new position") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public PositionDTO create(@RequestBody PositionDTO positionDTO) {
        return positionService.create(positionDTO);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update a position")
    @PutMapping("/{id}")
    public PositionDTO update(@PathVariable Integer id, @RequestBody PositionDTO positionlDTO) {
        return positionService.updatePosition(id, positionlDTO);
    }

    @GetMapping // maps the '/positions' GET requests to this method
    @Operation(summary = "Ia toate pozitiile unui terminal intre 2 date") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public List<PositionDTO> findPositions(@RequestParam String terminalId, @RequestParam Date startDate, @RequestParam Date endDate) {
        return positionService.findByTerminalIdStartDateEndDate(terminalId, startDate, endDate);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete a position by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        positionService.deletePosition(id);
    }
}
