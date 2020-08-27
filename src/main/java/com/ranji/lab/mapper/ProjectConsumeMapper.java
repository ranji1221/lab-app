package com.ranji.lab.mapper;

import com.ranji.lab.dto.ProjectConsumeDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 实验项目mapper
 */
public interface ProjectConsumeMapper {
    //插入
    @Insert("insert into project_consume (experiment_consume_id,arrange_project_id,consume_num,status) values (#{experimentConsumeId},#{arrangeProjectId},#{consumeNum},0)")
    int insertProjectConsume(ProjectConsumeDto projectConsumeDto);
    //projectId查询全部
    @Select("select pc.*,ci.`name` consumeName,ci.unit_name unitName from project_consume pc LEFT JOIN consume_inform ci on pc.experiment_consume_id = ci.id where pc.arrange_project_id = #{arrangeProjectId} and status != 1")
    List<ProjectConsumeDto> projectIdFindAllProjectConsume(int arrangeProjectId);
    //分类查询
    @Select("select pc.*,ci.`name` consumeName,ci.unit_name unitName from project_consume pc LEFT JOIN consume_inform ci on pc.experiment_consume_id = ci.id where pc.status = #{status}")
    List<ProjectConsumeDto> typeProjectIdFindAllProjectConsume(int status);
    //修改信息
    @Update("update project_consume set consume_num = #{consumeNum}, status = #{status} where id = #{id}")
    int updProjectConsume(ProjectConsumeDto projectConsumeDto);
}
