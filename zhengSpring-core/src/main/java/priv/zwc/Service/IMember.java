package priv.zwc.Service;

import priv.zwc.Module.Member;
import priv.zwc.Page.PageModel;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface IMember {
    public PageModel<Member> queryListPage(String keyword, int currpage, int pagesize);
    public Member queryById(long id);
    public Member loginQuery(String loginName);
    public boolean canAdd(String username);
    public void addMember(Member member);
    public void updateById(Member member);
    public void deleteById(long id);
}
