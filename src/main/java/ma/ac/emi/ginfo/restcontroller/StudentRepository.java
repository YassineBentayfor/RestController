package ma.ac.emi.ginfo.restcontroller;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNomAndPrenom(String nom, String Prenom);

    List<Student> findByNom(String nom);
}
