package we_won.hackerton.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_won.hackerton.Interface.VocaRepository;
import we_won.hackerton.entity.Vocabulary;

import java.util.List;

@RestController
@RequestMapping("/api/voca")
@RequiredArgsConstructor
public class VocaController {

    private final VocaRepository vocaRepository;

    @GetMapping("") //모든 단어들을 불러옴
    public ResponseEntity<?> getAllVoca(){
        final List<Vocabulary> vocas = vocaRepository.findAll();
        return new ResponseEntity<>(vocas, HttpStatus.OK);
    }
}
