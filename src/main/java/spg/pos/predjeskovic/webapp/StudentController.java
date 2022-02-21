package spg.pos.predjeskovic.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spg.pos.predjeskovic.service.StudentService;



@Controller
@RequestMapping(path="/students")
public class StudentController {

    private static int SIZE = 10;

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String findAllStudents(Model model,@RequestParam(value="page") int page){

        if(page==0){
            page = 1;
        }

        Pageable pageable = PageRequest.of(page,SIZE);

        var students = studentService.findAllStudents(pageable);

        model.addAttribute("students",students);

        return "students";
    }

}