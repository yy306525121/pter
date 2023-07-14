package cn.codeyang.pter.module.site.mapper;

import cn.codeyang.pter.module.site.entity.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author yangzy
 */
@Mapper
public interface SiteMapper extends BaseMapper<Site> {
}
