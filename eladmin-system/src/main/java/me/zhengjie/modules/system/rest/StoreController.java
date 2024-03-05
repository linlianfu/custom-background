package java.me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 */
@Slf4j
@RestController
@RequestMapping("/api/store")
public class StoreController {

    /**
     * 测试 mvc
     * @return
     */
    @GetMapping(value = "/page")
    @PreAuthorize("@el.check('job:list','user:list')")
    public boolean page(){
        log.info("1");
        return false;
    }
}
