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
    public String createAction(@ModelAttribute StudentDto studentDto){
        studentService.save(studentDto);
        return "redirect:/students?page=0";
    }

    @GetMapping(path = "/edit")
    public String editStudent(Model model, @RequestParam Long id){
        model.addAttribute("title", "edit");

        var student = studentService.findById(id);

        model.addAttribute("student",student);

        return "edit-student";
    }

    @PostMapping(path = "/edit")
    public String editStudent(@ModelAttribute StudentDto studentDto){
        studentService.replace(studentDto);

        return "redirect:/students?page=0";
    }

    @GetMapping(path = "/delete")
    public String removeStudent(@RequestParam Long id){
        studentService.remove(id);

        return "redirect:/students?page=0";
    }
}
