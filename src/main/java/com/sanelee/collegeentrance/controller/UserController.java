package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.UserMapper;
import com.sanelee.collegeentrance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private UserMapper userMapper;
    //定义页面
    @RequestMapping("/userBasicInformation")
    public String userBasicInformation(){
        return "userBasicInformation";
    }

    @RequestMapping(value="/adduserBasicInformation",method=RequestMethod.POST)
    public String userBasicInformation(Map<String,Object> map,
                                       @RequestParam(name="userRealname",required = false) String userRealname,
                                       @RequestParam(name="userGender",required = false) String userGender,
                                       @RequestParam(name="userPhone",required = false) String userPhone,
                                       @RequestParam(name="userWechat",required = false) String userWechat,
                                       @RequestParam(name="userScore",required = false) Integer userScore,
                                       @RequestParam(name="userRank",required = false) Integer userRank,
                                       @RequestParam(name="userArea",required = false) String userArea,
                                       @RequestParam(name="userSort",required = false) String userSort){
        User userIdentify = userMapper.findByUserNameAndUserPhone(userRealname,userPhone);
        if (userIdentify==null&&userRealname!=("")&&userPhone!=("")&&userWechat!=("")&&userScore!=null&&userRank!=null){
            User user= new User();
            user.setUserRealname(userRealname);
            user.setUserGender(userGender);
            user.setUserPhone(userPhone);
            user.setUserWechat(userWechat);
            user.setUserScore(userScore);
            user.setUserRank(userRank);
            user.setUserArea(userArea);
            user.setUserSort(userSort);

            userMapper.saveUser(user);
            return "reportZhiyuan";
        }
        else if (userIdentify!=null){
            map.put("msg","信息数据已存在，若需要修改请联系管理员！");
            return "userBasicInformation";
        }
        else if(userRealname.equals("")){
            map.put("msg","姓名不能为空！");
            return "userBasicInformation";
        }
        else if(userPhone.equals("")){
            map.put("msg","电话不能为空！");
            return "userBasicInformation";
        }
        else if(userWechat.equals("")){
            map.put("msg","微信号不能为空！");
            return "userBasicInformation";
        }
        else if(userScore==null){
            map.put("msg","分数不能为空！");
            return "userBasicInformation";
        }
        else if(userRank==null){
            map.put("msg","位次不能为空！");
            return "userBasicInformation";
        }
        else {
            map.put("msg","信息填写错误，请检查无误后重新提交！");
            return "userBasicInformation";
        }


    }

}
