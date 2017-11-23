package priv.zwc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import priv.zwc.Module.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface MemberMapper {
    public List<Member> queryListPage(@Param("keyword") String keyword,@Param("startindex") int startindex,@Param("pagesize") int pagesize);
    public int queryListPageCount(String keyword);
    public Member queryById(long id);
    public Member loginQuery(String loginname);
    public int canAdd(String loginname);
    public void addMember(Member member);
    public void updateById(Member member);
    public void deleteById(long id);
}
