package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import we_won.hackerton.Interface.LiteratureRepository;
import we_won.hackerton.Interface.SentenceRepository;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.dao.UserSentenceScrapDAO;
import we_won.hackerton.dto.DeleteDTO;
import we_won.hackerton.entity.Literature;
import we_won.hackerton.entity.Sentence;
import we_won.hackerton.entity.UserSentenceScrapID;
import we_won.hackerton.entity.User_;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sentence")
public class SentenceController {


    private final LiteratureRepository literatureRepository;
    private final UserSentenceScrapDAO userSentenceScrapDAO;
    private final UserRepository userRepository;
    private final SentenceRepository sentenceRepository;

    @GetMapping("/{title}")
    public ResponseEntity<?> getLiteratureInfo(@PathVariable("title") String title){
        Literature literature = literatureRepository.findByTitle(title);
        return new ResponseEntity<>(literature,HttpStatus.CREATED);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteLiterature(@RequestBody DeleteDTO deleteDTO){
        Optional<User_> user = userRepository.findByUsername(deleteDTO.getUsername());

        Optional<Sentence> sentence = sentenceRepository.findById(deleteDTO.getId());
        UserSentenceScrapID id = new UserSentenceScrapID(user.get().getId(),sentence.get().getId());
        userSentenceScrapDAO.deleteById(id);
        HashMap<String,String> result = new HashMap<>();
        result.put("status","200");
        result.put("message","문장 스크랩 목록에서 삭제 됐습니다.");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
