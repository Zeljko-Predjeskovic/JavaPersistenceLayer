package spg.pos.predjeskovic.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spg.pos.predjeskovic.service.StudentDto;
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
    public String findAllStudents(Model model, @RequestParam int page){
        model.addAttribute("title","Schüler");

        Pageable pageable = PageRequest.of(page,SIZE);
        var students = studentService.findAllStudents(pageable);
        model.addAttribute("students",students);

        model.addAttribute("pageSize", studentService.getPages(SIZE));

        return "students";
    }


    @GetMapping(path="/create")
    public String CreateStudent(Model model){
        model.addAttribute("title", "Erstellen");

        model.addAttribute("student", new StudentDto());

        return "create-students";
    }

    @PostMapping(path="/create")
    public String creatAction(@ModelAttribute StudentDto studentDto){
        studentService.save(studentDto);
        return "redirect:/students?page=0";
    }
}
