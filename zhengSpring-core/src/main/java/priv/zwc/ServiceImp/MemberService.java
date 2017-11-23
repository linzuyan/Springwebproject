package priv.zwc.ServiceImp;

import priv.zwc.Page.PageModel;
import priv.zwc.Service.IMember;
import priv.zwc.Mapper.MemberMapper;
import priv.zwc.Module.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class MemberService implements IMember {
    @Autowired
    private MemberMapper memberMapper;
    public PageModel<Member> queryListPage(String keyword,int currpage,int pagesize) {
        int startindex=0;
        int endindex=0;
        int count;
        int pagecount;
        List<Member> members=new ArrayList<Member>();
        startindex=(currpage-1)*pagesize;
        members=memberMapper.queryListPage(keyword,startindex,pagesize);
        count=memberMapper.queryListPageCount(keyword);
        pagecount=count%pagesize>0?count/pagesize+1:count/pagesize;
        PageModel<Member> pageModel=new PageModel<Member>(members,currpage,pagecount,pagesize);
        return  pageModel;
    }

    public Member queryById(long id) {
        return memberMapper.queryById(id);
    }

    public Member loginQuery(String loginName) {
        return memberMapper.loginQuery(loginName);
    }

    public boolean canAdd(String username) {
        if (memberMapper.canAdd(username)>0){
            return false;
        }
        return true;
    }

    public void addMember(Member member) {
        memberMapper.addMember(member);
    }

    public void updateById(Member member) {
        memberMapper.updateById(member);
    }

    public void deleteById(long id) {
        memberMapper.deleteById(id);
    }
}
