package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import we_won.hackerton.Interface.LiteratureRepository;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.dao.UserLiteratureScrapDAO;
import we_won.hackerton.dto.DeleteDTO;
import we_won.hackerton.entity.Literature;
import we_won.hackerton.entity.UserLiteratureScrapId;
import we_won.hackerton.entity.User_;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/literatures")
public class LiteratureController {
    private final LiteratureRepository literatureRepository;
    private final UserLiteratureScrapDAO userLiteratureScrapDAO;
    private final UserRepository userRepository;
    @GetMapping("/random")
    public ResponseEntity<?> getAllLiter() {
        List<Literature> literature = literatureRepository.findAll();
        System.out.println(literature);
        try{
            return new ResponseEntity<>(literature,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("에러입니다.",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{category}")
    public ResponseEntity<?> getLiteratureById(@PathVariable("category") long category) {
        List<Literature> literature = literatureRepository.findByCategory(category);
        try{
            return new ResponseEntity<>(literature,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("에러입니다.",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteLiterature(@RequestBody DeleteDTO deleteDTO){
        Optional<User_> user = userRepository.findByUsername(deleteDTO.getUsername());

        Optional<Literature> literature = literatureRepository.findById(deleteDTO.getId());
        UserLiteratureScrapId id = new UserLiteratureScrapId(user.get().getId(),literature.get().getId());
        userLiteratureScrapDAO.deleteById(id);
        HashMap<String,String> result = new HashMap<>();
        result.put("status","200");
        result.put("message","글 스크랩 목록에서 삭제 됐습니다.");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
