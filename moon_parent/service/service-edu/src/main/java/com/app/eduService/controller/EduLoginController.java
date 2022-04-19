package com.app.eduService.controller;

import com.app.eduService.entity.EduTeacher;
import com.app.eduService.utils.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public R login() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("token", "admin");
        return R.success().data(map);
    }

    @GetMapping("info")
    public R info() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", " http://121.4.149.171/File/picture/sakura.jpg");
        return R.success().data(map);
    }
}
