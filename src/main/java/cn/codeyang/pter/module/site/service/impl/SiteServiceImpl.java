package cn.codeyang.pter.module.site.service.impl;

import cn.codeyang.pter.module.site.entity.Site;
import cn.codeyang.pter.module.site.mapper.SiteMapper;
import cn.codeyang.pter.module.site.service.SiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

}
