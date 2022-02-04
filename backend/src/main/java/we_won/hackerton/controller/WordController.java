package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.Interface.WordRepository;
import we_won.hackerton.dao.UserWordScrapDAO;
import we_won.hackerton.dto.DeleteDTO;
import we_won.hackerton.entity.UserWordScrapID;
import we_won.hackerton.entity.User_;
import we_won.hackerton.entity.Word;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words")
public class WordController {

    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final UserWordScrapDAO userWordScrapDAO;

    @DeleteMapping("")
    public ResponseEntity<?> deleteLiterature(@RequestBody DeleteDTO deleteDTO){
        Optional<User_> user = userRepository.findByUsername(deleteDTO.getUsername());

        Optional<Word> word = wordRepository.findById(deleteDTO.getId());
        UserWordScrapID id = new UserWordScrapID(user.get().getId(),word.get().getId());
        userWordScrapDAO.deleteById(id);
        HashMap<String,String> result = new HashMap<>();
        result.put("status","200");
        result.put("message","단어 스크랩 목록에서 삭제 됐습니다.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
