package i.solonin.pca.controller;

import i.solonin.pca.services.Asterisk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class RestController {
    private final Asterisk asterisk;

    @RequestMapping(value = "/dial", method = RequestMethod.GET)
    public ResponseEntity<Boolean> delete(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(asterisk.origin(from, to));
    }
}
