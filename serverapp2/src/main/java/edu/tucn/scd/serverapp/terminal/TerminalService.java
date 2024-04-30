package edu.tucn.scd.serverapp.terminal;

import edu.tucn.scd.serverapp.exceptions.AlreadyExistsException;
import edu.tucn.scd.serverapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TerminalMapper terminalMapper;
    @Transactional(rollbackFor = Exception.class)
    public TerminalDTO create(TerminalDTO terminalDTO) {
        String terminalId = terminalDTO.getId();
        Optional<Terminal> existingTerminal = terminalRepository.findById(terminalId);
        if (existingTerminal.isPresent()) {
            throw new AlreadyExistsException("Terminalul cu ID " + terminalId + " exista deja");
        } else {
            Terminal noulTerminal = terminalRepository.save(terminalMapper.DTOtoterminal(terminalDTO));
            return terminalMapper.terminaltoDTO(noulTerminal);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public TerminalDTO getTerminalById(String terminalId) {
        Optional<Terminal> optionalTerminal = terminalRepository.findById(terminalId);
        if (optionalTerminal.isPresent())
            return terminalMapper.terminaltoDTO(optionalTerminal.get());
        else throw new NotFoundException("Terminal negasit!");
    }

    @Transactional(rollbackFor = Exception.class)
    public TerminalDTO updateTerminal(String id, TerminalDTO updatedTerminal) {
        Optional<Terminal> optionalTerminal = terminalRepository.findById(id);
        if (optionalTerminal.isPresent()) {
            Terminal terminal = optionalTerminal.get();
            terminal.setTerminalName(updatedTerminal.getTerminalName());
            return terminalMapper.terminaltoDTO(terminalRepository.save(terminal));
        } else throw new NotFoundException("Terminal negasit!");
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTerminal(String id) {
        Optional<Terminal> terminalOptional = terminalRepository.findById(id);
        if (terminalOptional.isPresent()) {
            terminalRepository.deleteById(id);
        } else {
            throw new NotFoundException("Terminalul cu Id" + id + "negasit");
        }
    }

}
