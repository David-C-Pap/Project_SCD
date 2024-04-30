package edu.tucn.scd.serverapp.position;

import edu.tucn.scd.serverapp.exceptions.NotFoundException;
import edu.tucn.scd.serverapp.terminal.Terminal;
import edu.tucn.scd.serverapp.terminal.TerminalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Radu Miron
 * @version 1
 */
@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class PositionService {

    @Autowired // injects the PositionRepository instance
    private PositionRepository positionRepository;
    @Autowired // injects the PositionMapper instance
    private PositionMapper positionMapper;
    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public PositionDTO create(PositionDTO positionDTO) {
        PositionDTO createdPositionDTO =
                positionMapper.positionToDto(positionRepository.save(positionMapper.dtoToPosition(positionDTO)));
        log.info("Salveaza o noua pozitie pentru terminalul cu Id " + createdPositionDTO.getTerminalId());
        return createdPositionDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true) // the method is executed in a read-only transaction
    public List<PositionDTO> findByTerminalIdStartDateEndDate(String terminalId, Date startDate, Date endDate) {
        List<PositionDTO> positions = positionRepository.findPositions(terminalId, startDate, endDate)
                .stream()
                .map(p -> positionMapper.positionToDto(p))
                .collect(Collectors.toList());
        log.info(String.format("Gaseste %d pozitii pentru: terminalId=%s, startDate=%s, endDate=%s",
                positions.size(), terminalId, startDate, endDate));
        return positions;
    }
    @Transactional(rollbackFor = Exception.class)
    public PositionDTO updatePosition(Integer id, PositionDTO updatedPosition) {
        Optional<Position> optionalPosition = positionRepository.findById(id);
        if (optionalPosition.isPresent()) {
            Position pozitie = optionalPosition.get();
              pozitie.setLatitude(updatedPosition.getLatitude());
              pozitie.setLongitude(updatedPosition.getLongitude());
            return positionMapper.positionToDto(positionRepository.save(pozitie));
        } else throw new NotFoundException("Terminal negasit!");
    }
    @Transactional(rollbackFor = Exception.class)
    public void deletePosition(Integer id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if (positionOptional.isPresent()) {
            positionRepository.deleteById(id);
        } else {
            throw new NotFoundException("Pozitia cu ID " + id + " negasita");
        }
    }
}
