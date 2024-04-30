package edu.tucn.scd.serverapp.terminal;

import edu.tucn.scd.serverapp.position.Position;
import edu.tucn.scd.serverapp.position.PositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class TerminalMapper {

    public TerminalDTO terminaltoDTO(Terminal terminal) {
        TerminalDTO terminalDTO = new TerminalDTO();
        terminalDTO.setId(terminal.getId());
        terminalDTO.setTerminalName(terminal.getTerminalName());
        return terminalDTO;
    }

    public Terminal DTOtoterminal(TerminalDTO terminalDTO) {
        Terminal terminal = new Terminal();
        terminal.setId(terminalDTO.getId());
        terminal.setTerminalName(terminalDTO.getTerminalName());

        return terminal;
    }
}
