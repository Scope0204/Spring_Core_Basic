package hello.core.web;

import hello.core.common.MyLogger;

import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // Lombok의 기능 중 하나 : final을 파라미터로 받는 생성자를 만들어줌
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "OK";

    }
}
