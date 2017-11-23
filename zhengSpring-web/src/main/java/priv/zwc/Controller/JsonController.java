package priv.zwc.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import priv.zwc.Module.Member;
import priv.zwc.Page.PageModel;
import priv.zwc.Service.IMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
@Controller
@RequestMapping(value = "/Json")
public class JsonController extends BaseController {
    @Autowired
    private IMember iMember;
    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public ModelAndView Index(){
        ModelAndView modelAndView=new ModelAndView();
        PageModel<Member> list=iMember.queryListPage("",1,2);
        JSONArray jsonArray= JSONArray.fromObject(list.getModel());
        modelAndView.addObject("index",jsonArray);
        modelAndView.setViewName("form1");
        return  modelAndView;
    }
    @RequestMapping(value = "/index1",method = {RequestMethod.GET})
    public ModelAndView Index1(String id){
        ModelAndView modelAndView=new ModelAndView();
        List<Member> list=new ArrayList<Member>();
        Member member= iMember.queryById(Integer.parseInt(id));
        JSONArray jsonArray= JSONArray.fromObject(member);
        String jsonstr= jsonArray.toString();

        JSONArray newjson= JSONArray.fromObject(jsonstr);
        for (int i=0;i<newjson.size();i++){
            JSONObject jsonmap= newjson.getJSONObject(i);

            Member homeFloor1= (Member) JSONObject.toBean(jsonmap ,Member.class);
            list.add(homeFloor1);
        }
        modelAndView.addObject("list",list);
        modelAndView.setViewName("json");
        return  modelAndView;
    }
}
