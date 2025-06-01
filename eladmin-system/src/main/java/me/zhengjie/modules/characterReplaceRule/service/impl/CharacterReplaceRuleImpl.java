package me.zhengjie.modules.characterReplaceRule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.characterReplaceRule.domain.CharacterReplaceRule;
import me.zhengjie.modules.characterReplaceRule.domain.CharacterReplaceRuleItem;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleCriteria;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleItemRequest;
import me.zhengjie.modules.characterReplaceRule.domain.vo.CharacterReplaceRuleRequest;
import me.zhengjie.modules.characterReplaceRule.mapper.CharacterReplaceRuleItemMapper;
import me.zhengjie.modules.characterReplaceRule.mapper.CharacterReplaceRuleMapper;
import me.zhengjie.modules.characterReplaceRule.service.ICharacterReplaceRuleService;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleBaseDto;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleDto;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleItemDto;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class CharacterReplaceRuleImpl extends ServiceImpl<CharacterReplaceRuleMapper, CharacterReplaceRule> implements ICharacterReplaceRuleService {


    @Autowired
    private CharacterReplaceRuleMapper mapper;
    @Autowired
    private CharacterReplaceRuleItemMapper characterReplaceRuleItemMapper;

    @Autowired
    private WebsiteMapper websiteMapper;

    @Override
    public List<CharacterReplaceRuleBaseDto> getCharacterReplaceRuleByWebsiteId(String websiteId) {
        List<CharacterReplaceRule> characterReplaceRules = mapper.selectList(
                Wrappers.lambdaQuery(CharacterReplaceRule.class).in(CharacterReplaceRule::getWebsiteId, websiteId)
        );
        List<CharacterReplaceRuleBaseDto> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(characterReplaceRules)){

            for (CharacterReplaceRule characterReplaceRule : characterReplaceRules) {
                CharacterReplaceRuleBaseDto baseDto = new CharacterReplaceRuleBaseDto();
                baseDto.setRuleId(characterReplaceRule.getId());
                baseDto.setRuleName(characterReplaceRule.getRuleName());
                List<CharacterReplaceRuleItem> characterReplaceRuleItems = characterReplaceRuleItemMapper.selectList(
                        Wrappers.lambdaQuery(CharacterReplaceRuleItem.class)
                                .eq(CharacterReplaceRuleItem::getRuleId,characterReplaceRule.getId())
                );
                if (CollectionUtils.isNotEmpty(characterReplaceRuleItems)){
                    List<CharacterReplaceRuleItemDto> characterReplaceGroup = new ArrayList<>();
                    for (CharacterReplaceRuleItem characterReplaceRuleItem : characterReplaceRuleItems) {
                        CharacterReplaceRuleItemDto itemDto = new CharacterReplaceRuleItemDto();
                        itemDto.setOriginCharacter(characterReplaceRuleItem.getOriginCharacter());
                        itemDto.setNewCharacter(characterReplaceRuleItem.getNewCharacter());
                        characterReplaceGroup.add(itemDto);
                    }
                    baseDto.setCharacterReplaceGroup(characterReplaceGroup);
                }
                result.add(baseDto);
            }
        }
        return result;
    }


    @Override
    public CharacterReplaceRuleDto getCharacterReplaceRuleById(String id) {
        CharacterReplaceRule characterReplaceRule = mapper.selectById(id);

        CharacterReplaceRuleDto ruleDto = new CharacterReplaceRuleDto();
        ruleDto.setId(characterReplaceRule.getId());
        ruleDto.setRuleName(characterReplaceRule.getRuleName());
        ruleDto.setWebsiteId(characterReplaceRule.getWebsiteId());
        ruleDto.setEnable(characterReplaceRule.isEnable());
        List<CharacterReplaceRuleItem> characterReplaceRuleItems = characterReplaceRuleItemMapper.selectList(
                Wrappers.lambdaQuery(CharacterReplaceRuleItem.class)
                        .eq(CharacterReplaceRuleItem::getRuleId,characterReplaceRule.getId())
        );
        if (CollectionUtils.isNotEmpty(characterReplaceRuleItems)){
            List<CharacterReplaceRuleItemDto> characterReplaceGroup = new ArrayList<>();
            for (CharacterReplaceRuleItem characterReplaceRuleItem : characterReplaceRuleItems) {
                CharacterReplaceRuleItemDto itemDto = new CharacterReplaceRuleItemDto();
                itemDto.setOriginCharacter(characterReplaceRuleItem.getOriginCharacter());
                itemDto.setNewCharacter(characterReplaceRuleItem.getNewCharacter());
                characterReplaceGroup.add(itemDto);
            }
            ruleDto.setCharacterReplaceGroup(characterReplaceGroup);
        }
        return ruleDto;
    }

    @Override
    public PageResult<CharacterReplaceRuleDto> pageQueryAll(CharacterReplaceRuleCriteria criteria, Page<Object> page) {

        Page<CharacterReplaceRule> dbPage = new Page<>(page.getCurrent(), page.getSize());

        LambdaQueryWrapper<CharacterReplaceRule> wrapper = Wrappers.lambdaQuery(CharacterReplaceRule.class);
        if (StringUtils.isNotBlank(criteria.getWebsiteId())){
            wrapper.in(CharacterReplaceRule::getWebsiteId,criteria.getWebsiteId());
        }
        if (StringUtils.isNotBlank(criteria.getRuleName())){
            wrapper.eq(CharacterReplaceRule::getRuleName,criteria.getRuleName());
        }

        dbPage = mapper.selectPage(dbPage, wrapper.orderByDesc(CharacterReplaceRule::getCreateTime));

        List<CharacterReplaceRuleDto> list = ModelMapperUtils.mapList(dbPage.getRecords(),CharacterReplaceRuleDto.class);

        if (CollectionUtils.isNotEmpty(list)){
            Set<String> websiteIdList = list.stream().map(CharacterReplaceRuleDto::getWebsiteId).collect(Collectors.toSet());
            List<Website> websites = websiteMapper.selectBatchIds(websiteIdList);
            Map<String, String> websiteMap = websites.stream().collect(Collectors.toMap(Website::getId, Website::getSiteName));

            List<String> ruleIdList = list.stream().map(CharacterReplaceRuleDto::getId).collect(Collectors.toList());
            List<CharacterReplaceRuleItem> characterReplaceRuleItems = characterReplaceRuleItemMapper.selectList(Wrappers.lambdaQuery(CharacterReplaceRuleItem.class)
                    .in(CharacterReplaceRuleItem::getRuleId, ruleIdList));
            for (CharacterReplaceRuleDto characterReplaceRule : list) {
                characterReplaceRule.setWebsite(websiteMap.get(characterReplaceRule.getWebsiteId()));
                List<CharacterReplaceRuleItem> itemList = characterReplaceRuleItems.stream().filter(p -> p.getRuleId().equals(characterReplaceRule.getId()))
                        .collect(Collectors.toList());
                List<CharacterReplaceRuleItemDto> characterReplaceGroup = new ArrayList<>();
                for (CharacterReplaceRuleItem item : itemList) {
                    CharacterReplaceRuleItemDto ruleItemDto = new CharacterReplaceRuleItemDto();
                    ruleItemDto.setNewCharacter(item.getNewCharacter());
                    ruleItemDto.setOriginCharacter(item.getOriginCharacter());
                    characterReplaceGroup.add(ruleItemDto);
                }
                characterReplaceRule.setCharacterReplaceGroup(characterReplaceGroup);
            }
        }

        return PageUtil.toPage(list, dbPage.getTotal());
    }

    @Override
    public CharacterReplaceRule createRule(CharacterReplaceRuleRequest request) {
        Assert.notEmpty(request.getRuleName(),"规则名称不能为空");
        CharacterReplaceRule rule = new CharacterReplaceRule();
        rule.setRuleName(request.getRuleName());
        rule.setEnable(request.isEnable());
        rule.setWebsiteId(request.getWebsiteId());
        rule.setCreateTime(new Date());
        rule.setCreateId(SecurityUtils.getCurrentUserId());
        save(rule);

        createCharacterReplaceRuleItem(rule.getId(),request.getRuleItem());
        return rule;
    }


    @Override
    public CharacterReplaceRule updateRule(CharacterReplaceRuleRequest request) {
        Assert.notEmpty(request.getId(),"规则id不能为空");
        CharacterReplaceRule characterReplaceRule = mapper.selectById(request.getId());
        characterReplaceRule.setRuleName(request.getRuleName());
        characterReplaceRule.setEnable(request.isEnable());
        characterReplaceRule.setWebsiteId(request.getWebsiteId());
        mapper.updateById(characterReplaceRule);

        characterReplaceRuleItemMapper.delete(Wrappers.lambdaQuery(CharacterReplaceRuleItem.class)
                .eq(CharacterReplaceRuleItem::getRuleId, request.getId()));

        createCharacterReplaceRuleItem(request.getId(),request.getRuleItem());

        return characterReplaceRule;
    }

    @Override
    public boolean deleteById(List<String> ids) {
        for (String id : ids) {
            mapper.deleteById(id);
            characterReplaceRuleItemMapper.delete(
                    Wrappers.lambdaQuery(CharacterReplaceRuleItem.class).eq(CharacterReplaceRuleItem::getRuleId,id));
        }
        return false;
    }

    private void createCharacterReplaceRuleItem(String ruleId, List<CharacterReplaceRuleItemRequest> ruleItemRequest){
        for (CharacterReplaceRuleItemRequest item : ruleItemRequest) {
            CharacterReplaceRuleItem ruleItem = new CharacterReplaceRuleItem();
            ruleItem.setRuleId(ruleId);
            ruleItem.setOriginCharacter(item.getOriginCharacter());
            ruleItem.setNewCharacter(item.getNewCharacter());
            ruleItem.setCreateTime( new Date());
            ruleItem.setCreateId(SecurityUtils.getCurrentUserId());
            characterReplaceRuleItemMapper.insert(ruleItem);
        }
    }
}
