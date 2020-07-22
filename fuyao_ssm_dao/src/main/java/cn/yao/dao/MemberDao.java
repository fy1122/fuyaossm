package cn.yao.dao;

import cn.yao.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {
    @Select("select * from member where id=#{id}")
    Member findById(String id);
}
