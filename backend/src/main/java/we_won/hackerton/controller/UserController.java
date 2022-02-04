package we_won.hackerton.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import we_won.hackerton.Interface.LiteratureRepository;
import we_won.hackerton.Interface.SentenceRepository;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.Interface.WordRepository;
import we_won.hackerton.dao.UserLiteratureScrapDAO;
import we_won.hackerton.dao.UserSentenceScrapDAO;
import we_won.hackerton.dao.UserWordScrapDAO;
import we_won.hackerton.dto.UserFormDTO;
import we_won.hackerton.dto.UserScrapDTO;
import we_won.hackerton.dto.UserSentenceDTO;
import we_won.hackerton.dto.UserWordDTO;
import we_won.hackerton.entity.*;
import we_won.hackerton.response.SuccessResponse;
import we_won.hackerton.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final LiteratureRepository literatureRepository;
    private final UserLiteratureScrapDAO userLiteratureScrapDAO;
    private final UserSentenceScrapDAO userSentenceScrapDAO;
    private final SentenceRepository sentenceRepository;
    private final WordRepository wordRepository;
    private final UserWordScrapDAO userWordScrapDAO;
//    @PostMapping("/{username}")
//    public ResponseEntity<?> checkUsernameDuplicate(@PathVariable String username){
//        //System.out.println(user);
//        System.out.println(username);
//
//    }

    @PostMapping("")
    public ResponseEntity<?> insertAccount(
            @Valid @RequestBody UserFormDTO dto,
            BindingResult bindingResult
    ){

        if(bindingResult.hasErrors()){
            System.out.println("중복처리인가?");
            return new ResponseEntity<>("hello world~",HttpStatus.BAD_REQUEST);
        }

        boolean user = userRepository.existsByUsername(dto.getUsername());
        System.out.println(user);
        if(user){
            HashMap<String,String> result = new HashMap<>();
            result.put("status","400");
            result.put("message","아이디가 중복되었습니다.");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        else{
            userService.saveOrUpdateAccount(dto.toEntity());
            HashMap<String,String> result = new HashMap<>();
            result.put("status","200");
            result.put("message","회원가입 성공입니다!.");
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }

    }

    @GetMapping("")
    public ResponseEntity<?> viewAccount() {

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @GetMapping("/scrap/literature/{username}")
    public ResponseEntity<?> scrapList(@PathVariable("username") String username){
        final User_ user = userRepository.getByUsername(username);
        final List<UserLiteratureScrap> scrapList = userLiteratureScrapDAO.findAllByUser_id(user.getId());
        final List<Literature> literature_list = new ArrayList<>();
        for(int i = 0;i<scrapList.size();i++){
            literature_list.add(scrapList.get(i).getLiterature());
        }
        return new ResponseEntity<>(literature_list, HttpStatus.OK);

    }

    @PostMapping("scrap/literature")
    public ResponseEntity<?> userLiteratureScrap(@RequestBody UserScrapDTO userScrapDTO){
        final UserLiteratureScrap newScrap = new UserLiteratureScrap();
        final User_ user = userRepository.getByUsername(userScrapDTO.getUsername());
        final Literature literature = literatureRepository.getByTitle(userScrapDTO.getLiteratureTitle());
        final SuccessResponse result = new SuccessResponse<>();

        newScrap.setUser(user);
        newScrap.setLiterature(literature);

        userLiteratureScrapDAO.save(newScrap);
        result.setSuccess(true);
        result.setData("success");
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
    @PostMapping("scrap/sentence") //글을 스크랩할 때
    public ResponseEntity<?> userSentenceScrap(@RequestBody UserSentenceDTO userSentenceDTO){
        sentenceRepository.save(userSentenceDTO.toEntity()); //sentence DB에 저장
        final UserSentenceScrap newScrap = new UserSentenceScrap();
        final User_ user = userRepository.getByUsername(userSentenceDTO.getUsername());
        final Sentence sentence = sentenceRepository.getById(userSentenceDTO.getSentenceId());
        final SuccessResponse result = new SuccessResponse<>();

        newScrap.setUser(user);
        newScrap.setSentence(sentence);

        userSentenceScrapDAO.save(newScrap);
        result.setSuccess(true);
        result.setData("success");
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @GetMapping("/scrap/sentence/{username}")
    public ResponseEntity<?> scrapSentenceList(@PathVariable("username") String username){
        final User_ user = userRepository.getByUsername(username);
        final List<UserSentenceScrap> scrapList = userSentenceScrapDAO.findAllByUser_id(user.getId());
        final List<Sentence> sentences_list = new ArrayList<>();
        for(int i = 0;i<scrapList.size();i++){
            sentences_list.add(scrapList.get(i).getSentence());
        }
        return new ResponseEntity<>(sentences_list, HttpStatus.OK);

    }

    @PostMapping("scrap/word") //문장을 스크랩할 때
    public ResponseEntity<?> userWordScrap(@RequestBody UserWordDTO userWordDTO){
        wordRepository.save(userWordDTO.toEntity()); //sentence DB에 저장
        final UserWordScrap newScrap = new UserWordScrap();
        final User_ user = userRepository.getByUsername(userWordDTO.getUsername());
        final Word word = wordRepository.getById(userWordDTO.getWordId());
        final SuccessResponse result = new SuccessResponse<>();

        newScrap.setUser(user);
        newScrap.setWord(word);

        userWordScrapDAO.save(newScrap);
        result.setSuccess(true);
        result.setData("success");
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @GetMapping("/scrap/word/{username}")
    public ResponseEntity<?> scrapWordList(@PathVariable("username") String username){
        final User_ user = userRepository.getByUsername(username);
        final List<UserWordScrap> scrapList = userWordScrapDAO.findAllByUser_id(user.getId());
        final List<Word> word_list = new ArrayList<>();
        for(int i = 0;i<scrapList.size();i++){
            word_list.add(scrapList.get(i).getWord());
        }
        return new ResponseEntity<>(word_list, HttpStatus.OK);

    }

}
