package priv.zwc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/3/17.
 */
@Controller
@RequestMapping(value = "/Redis")
public class RedisController {
    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public ModelAndView Index(){
        ModelAndView modelAndView=new ModelAndView();
        return modelAndView;
    }
}
