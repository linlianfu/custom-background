package me.zhengjie.modules.characterReplaceRule.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleCriteria;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleRequest;
import me.zhengjie.modules.characterReplaceRule.service.ICharacterReplaceRuleService;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleDto;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eleven
 */
@Slf4j
@RestController
@Api(tags = "字符替换管理")
@RequestMapping("/api/characterReplaceRule")
public class CharacterReplaceRuleController {

    @Autowired
    private ICharacterReplaceRuleService service;


    @ApiOperation("字符替换分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<CharacterReplaceRuleDto>> queryAll(CharacterReplaceRuleCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.pageQueryAll(criteria, page), HttpStatus.OK);
    }

    @ApiOperation("字符替换")
    @GetMapping(value = "/getCharacterReplaceRule")
    public ResponseEntity<CharacterReplaceRuleDto> getCharacterReplaceRule(String id){
        return new ResponseEntity<>(service.getCharacterReplaceRuleById(id),HttpStatus.OK);
    }

    /**
     * 创建密钥
     * @param request
     * @return
     */
    @PostMapping
    @Log("新增字符替换")
    @ApiOperation("新增字符替换")
    public ResponseEntity<Object> createCharacterReplaceRule(@Validated @RequestBody CharacterReplaceRuleRequest request){
        service.createRule(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("新增字符替换")
    @ApiOperation("新增字符替换")
    public ResponseEntity<Object> updateCharacterReplaceRule(@Validated @RequestBody CharacterReplaceRuleRequest request){
        service.updateRule(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @Log("删除CharacterReplaceRule")
    @ApiOperation("删除CharacterReplaceRule")
    public ResponseEntity<Object> deleteCharacterReplaceRule(@RequestBody List<String> ids) {
        service.deleteById(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
