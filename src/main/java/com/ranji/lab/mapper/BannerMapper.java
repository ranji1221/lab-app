package com.ranji.lab.mapper;

import com.ranji.lab.entity.Banner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 幻灯片Mapper
 *  1.插入图片id
 *  2.查询最新插入幻灯片的id
 *  3.通过幻灯片的id查询幻灯片
 *  4.查询所有的幻灯片
 */
public interface BannerMapper {
    @Insert("insert into banner (imageId) valules (#{imageId})")
    int insertBanner(int imageId);
    @Select("select Max(id) from banner")
    int latestBannerData();
    @Select("select b.id,i.img_name name,i.img_addr addr,i.img_description from banner b join img i on b.image_id = i.id where b.id = #{bannerId};")
    Banner findBannerById(int bannerId);
    @Select("select b.id,i.img_name name,i.img_addr addr,i.img_description from banner b join img i on b.image_id = i.id;")
    List<Banner> findAllBanner();
}
