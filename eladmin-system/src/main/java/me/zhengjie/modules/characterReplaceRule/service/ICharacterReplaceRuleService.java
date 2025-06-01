package me.zhengjie.modules.characterReplaceRule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.characterReplaceRule.domain.CharacterReplaceRule;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleCriteria;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleRequest;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleBaseDto;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleDto;
import me.zhengjie.utils.PageResult;

import java.util.List;

/**
 * @author eleven
 */
public interface ICharacterReplaceRuleService {

    /**
     * 获取指定网站的字符替换规则
     * @param websiteId
     * @return
     */
    List<CharacterReplaceRuleBaseDto> getCharacterReplaceRuleByWebsiteId(String websiteId);
    /**
     * 获取指定的字符替换规则
     * @param id
     * @return
     */
    CharacterReplaceRuleDto getCharacterReplaceRuleById(String id);

    /**
     * 获取字符替换分页
     * @param criteria
     * @param page
     * @return
     */
    PageResult<CharacterReplaceRuleDto> pageQueryAll(CharacterReplaceRuleCriteria criteria, Page<Object> page);

    /**
     * 创建字符替换规则
     * @param request
     * @return
     */
    CharacterReplaceRule createRule(CharacterReplaceRuleRequest request);
    /**
     * 更新字符替换规则
     * @param request
     * @return
     */
    CharacterReplaceRule updateRule(CharacterReplaceRuleRequest request);

    /**
     * 删除id
     * @param id
     * @return
     */
    boolean deleteById(List<String> id);


}
