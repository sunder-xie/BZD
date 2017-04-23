package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bizideal.whoami.signup.entity.DietInfo;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午10:44:06
 * @version 1.0
 */
public interface DietInfoMapper extends Mapper<DietInfo> {

	@Select("SELECT * FROM diet_info WHERE hall_id = #{hallId} AND mee_id = #{meeId}")
	List<DietInfo> selectByPageSelf(@Param("hallId") int hallId,
									@Param("meeId") int meeId);

	@Select("SELECT * FROM diet_info WHERE hall_id = #{hallId} AND mee_id = #{meeId} OR (hall_id =0 AND mee_id = 0)")
	List<DietInfo> selectByPage(@Param("hallId") int hallId,
								@Param("meeId") int meeId);

	@Select("SELECT COUNT(*) FROM diet_info WHERE hall_id = #{hallId} AND mee_id = #{meeId} OR (hall_id =0 AND mee_id = 0)")
	int selectCountByPage(@Param("hallId") int hallId,
						  @Param("meeId") int meeId);

	@Update("UPDATE diet_info SET diet_disp = #{dietDisp} WHERE diet_id = #{dietId}")
	int updateDiet(@Param("dietId") int dietId,
				   @Param("dietDisp") String dietDisp);

}
