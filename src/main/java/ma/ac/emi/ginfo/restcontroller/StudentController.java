package ma.ac.emi.ginfo.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public List<Student> studentList(){
        return studentRepository.findAll();
    }

    @PostMapping("/")
    public Student studentList(@RequestBody Student s){
        return studentRepository.save(s);
    }

    @PutMapping("/{matricule}")
    public void studentList(@RequestBody Student s, @PathVariable Long matricule){
        studentRepository.findById(matricule)
                .map(student -> {
                    student.setNom(s.getNom());
                    student.setPrenom(s.getPrenom());
                    return studentRepository.save(student);
                })
                .orElseGet(()-> studentRepository.save(s));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Student student(@PathVariable Long id){
        return studentRepository.findById(id).get();
    }

    @GetMapping("/{nom}/{prenom}")
    public List<Student> student(@PathVariable String nom, @PathVariable String prenom){
        return studentRepository.findByNomAndPrenom(nom, prenom);
    }

    @GetMapping("/search")
    public List<Student> student(@RequestParam String nom){
        return studentRepository.findByNom(nom);
    }

    @GetMapping(value = "/SiteEMI")
    public ModelAndView emi(){
        return new ModelAndView("redirect:/api/v1/students/search?nom=KABBAJ");
    }

}

