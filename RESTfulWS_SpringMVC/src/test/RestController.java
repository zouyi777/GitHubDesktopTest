package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * GET 方法用于读取、检索、查询、过滤一个资源
 * PUT 用于修改、更新资源，创建客户端维护主键信息的资源
 * DELETE 方法用于删除资源
 * POST 方法用于创建资源
 */
@Controller
@RequestMapping("/rest")
public class RestController {
	
     String localHost="http://192.168.0.115:8080/RESTfulWS_SpringMVC/";
    //存储学生信息
    private List<Student> sList = new ArrayList<Student>();
   
    //构造3个学生信息
    public RestController(){
        Student s1 = new Student(2017001, "张一", 23, "女", "java",localHost+"images/head_pic/a.jpg");
        Student s2 = new Student(2017002, "李二", 23, "女", "js",localHost+"images/head_pic/b.jpg");
        Student s3 = new Student(2017003, "王三", 23, "女", "安卓",localHost+"images/head_pic/c.jpg");
        Student s4 = new Student(2017004, "王四", 23, "女", "安卓",localHost+"images/head_pic/d.jpg");
        Student s5 = new Student(2017005, "王五", 23, "女", "安卓",localHost+"images/head_pic/e.jpg");
        Student s6 = new Student(2017006, "王六", 23, "女", "安卓",localHost+"images/head_pic/f.jpg");
        Student s7 = new Student(2017007, "王七", 23, "女", "安卓",localHost+"images/head_pic/g.jpg");
        Student s8 = new Student(2017008, "王八", 23, "女", "安卓",localHost+"images/head_pic/h.jpg");
        Student s9 = new Student(2017009, "王九", 23, "女", "安卓",localHost+"images/head_pic/i.jpg");
        Student s10 = new Student(20170010, "王十", 23, "女", "安卓",localHost+"images/head_pic/j.jpg");
//        Student s11 = new Student(20170011, "王十一", 23, "女", "安卓",localHost+"images/head_pic/k.jpg");
//        Student s12 = new Student(20170012, "王十二", 23, "女", "安卓",localHost+"images/head_pic/l.jpg");
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);
        sList.add(s4);
        sList.add(s5);
        sList.add(s6);
        sList.add(s7);
        sList.add(s8);
        sList.add(s9);
        sList.add(s10);
//        sList.add(s11);
//        sList.add(s12);
    }

    //获取所有学生信息
    @ResponseBody
    @RequestMapping(value="/student",method=RequestMethod.GET)
    public Object getAll(){
        System.out.println("GET:ALL"); 
        return sList;
    }

    //根据id获取某个学生信息
    @ResponseBody
    @RequestMapping(value="/student/{id}",method=RequestMethod.GET)
    public Object getOne(@PathVariable("id") Integer id){
        System.out.println("GET:"+id);
        List<Student> selectList = new ArrayList<Student>();
        for(Student s : sList){
            if(s.getId()==id){
                selectList.add(s);
            } 
        }
        return selectList;
    }
    //新增加一个学生信息
    @ResponseBody
    @RequestMapping(value="/student",method=RequestMethod.POST)
    public Object post(@RequestBody Student student){
        System.out.println("POST:"+student.getId());
        sList.add(student);
        return sList;
    }
    //根据Id更新一个学生信息
    @ResponseBody
    @RequestMapping(value="/student/{id}",method=RequestMethod.PUT)
    public Object put(@PathVariable("id") Integer id,@RequestBody Student student){
        System.out.println("PUT:"+id);
        List<Student> removeList = new ArrayList<Student>();
        for (Student s : sList) {
            if(s.getId()==id){
                student.setId(s.getId());
                removeList.add(s);  
            }
        }
        sList.removeAll(removeList);
        sList.add(student);
        return sList;
    }
    //删除所有学生信息
    @ResponseBody
    @RequestMapping(value="/student",method=RequestMethod.DELETE)
    public Object delete(){
        System.out.println("DELETE:ALL");
        sList.clear();
        return sList;
    }

    //根据Id删除一个学生信息
    @ResponseBody
    @RequestMapping(value="/student/{id}",method=RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Integer id){
        System.out.println("DELETE:"+id);
        List<Student> removeList = new ArrayList<Student>();
        for (Student s : sList) {
            if(s.getId()==id){
                removeList.add(s);  
            }
        }
        sList.removeAll(removeList);
        return sList;
    }
}