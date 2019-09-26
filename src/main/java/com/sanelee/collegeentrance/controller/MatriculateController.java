package com.sanelee.collegeentrance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Controller
public class MatriculateController {
        @RequestMapping("/matriculate")
        public String index(ModelMap map){
            map.addAttribute("expression",new Expression());
            return "matriculate";
        }
    @PostMapping("/mathoper")
    public String greetingSubmit(@ModelAttribute Expression expression, Map<String, Object> map) throws Exception {

        System.out.println(expression.getExpr());

        // 定义传入shell脚本的参数，将参数放入字符串数组里
        String expr = expression.getExpr();
        String file_path = "D://javademo/test_argv.py";

        String command = String.format("python %s %s", file_path, expr);

        // 执行CMD命令
        System.out.println("\nExecuting python script file now ......");
        Process pcs = Runtime.getRuntime().exec(command);
        pcs.waitFor();

        // 定义shell返回值
        String result = null;

        // 获取shell返回流
        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        // 字符流转换字节流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        // 这里也可以输出文本日志

        String lineStr = null;
        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }
        // 关闭输入流
        br.close();
        in.close();

        System.out.println(result);

        if(result.indexOf("Error") == -1)
            map.put("answer", "录取概率为 "+result);
        else
            map.put("answer", "<mark>"+result+"</mark>");

        return "matriculate";
    }
}
