package edu.tucn.scd.serverapp.terminal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terminals")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    @Operation(summary = "Save a new terminal")
    @PostMapping // maps the '/terminals' POST requests to this method
    public TerminalDTO create(@RequestBody TerminalDTO terminalDTO) {

        return terminalService.create(terminalDTO);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get a terminal by Id")
    @GetMapping("/{id}")
    public TerminalDTO read(@PathVariable String id) {

        return terminalService.getTerminalById(id);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update a terminal")
    @PutMapping("/{id}")
    public TerminalDTO update(@PathVariable String id, @RequestBody TerminalDTO terminalDTO) {
        return terminalService.updateTerminal(id, terminalDTO);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete a terminal by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        terminalService.deleteTerminal(id);
    }
}
