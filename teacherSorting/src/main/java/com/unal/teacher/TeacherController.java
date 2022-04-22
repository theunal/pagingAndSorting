package com.unal.teacher;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Log4j2
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // DB YE TEACHER EKLEME
    // http://localhost:8080/addTeacher
    @GetMapping("/addTeacher")
    @ResponseBody
    public String addTeacher() {

        Teacher teacher = Teacher
                .builder()
                .name("teacher name")
                .surname("teacher surname")
                .age(35)
                .build();

        teacherRepository.save(teacher);

        return "Eklendi: "+teacher;
    }





















//    // DB'DE OLAN TÜM VERİLERİ GETİRİR
//    // http://localhost:8080/getAll
//    @GetMapping("/getAll")
//    @ResponseBody
//    public String getAll() {
//
//        Iterable<Teacher> entities = teacherRepository.findAll();
//
//        if (entities.iterator().hasNext()) {
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("Tüm liste getirildi: <br/> <br/>");
//            for (var entity : entities) {
//                log.info(entity);
//                builder.append(entity+"<br/>");
//            }
//
//            return builder+"";
//
//        }
//
//        log.info("hata olustu");
//        return "hata olustu";
//    }
//
//
//    // PAGİNG
//
//
//
//
//    // DB'DEKİ VERİLERİ 0.SAYFADA SİZE'A GÖRE GETİRİR
//    // http://localhost:8080/paging/5
//    @GetMapping("/paging/{size}")
//    @ResponseBody
//    public String paging(@PathVariable int size) {
//
//        Pageable pageable = PageRequest.of(0,size);
//        Page<Teacher> entities = teacherRepository.findAll(pageable);
//
//        if (entities.iterator().hasNext()) {
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("Sayfa: ").append(0).append(", Size: ").append(size).append(" <br/> <br/>");
//            for (var entity : entities) {
//                log.info(entity);
//                builder.append(entity).append("<br/>");
//            }
//
//            return builder+"";
//
//        }
//
//        log.info("hata olustu");
//        return "hata olustu";
//    }
//
//
//
//
//
//
//    // DB'DEKİ VERİLERİ SAYFA NUMARASI VE SİZE DEĞERİNE GÖRE GETİRİR
//    // http://localhost:8080/paging/0/5
//    @GetMapping("/paging/{page}/{size}")
//    @ResponseBody
//    public String paging(@PathVariable int page,
//                         @PathVariable int size) {
//
//        Pageable pageable = PageRequest.of(page,size);
//        Page<Teacher> entities = teacherRepository.findAll(pageable);
//
//        if (entities.iterator().hasNext()) {
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("Sayfa: ").append(page).append(", Size: ").append(size).append(" <br/> <br/>");
//            for (var entity : entities) {
//                log.info(entity);
//                builder.append(entity).append("<br/>");
//            }
//
//            return builder+"";
//
//        }
//
//        log.info("hata olustu");
//        return "hata olustu";
//    }
//
//
//
//
//
//
//
//
//
//    // SORTİNG
//
//
//
//
//
//
//    // KÜÇÜKTEN BÜYÜĞE SIRALAMA >> ASC
//    // http://localhost:8080/getAsc
//    @GetMapping("/getAsc")
//    @ResponseBody
//    public String getAsc() {
//
//        Sort sort = Sort.by("id");
//        Iterable<Teacher> entities = teacherRepository.findAll(sort);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Küçükten büyüğe sıralandı: <br/><br/>");
//        for (var entity : entities) {
//            log.info(entity);
//            builder.append(entity+"<br/>");
//        }
//
//        return builder+"";
//    }
//
//    // BÜYÜKTEN KÜÇÜĞE SIRALAMA >> DESC
//    // http://localhost:8080/getDesc
//    @GetMapping("/getDesc")
//    @ResponseBody
//    public String getDesc() {
//
//        Sort sort = Sort.by("id").descending();
//        Iterable<Teacher> entities = teacherRepository.findAll(sort);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Büyükten küçüğe sıralandı: <br/><br/>");
//        for (var entity : entities) {
//            log.info(entity);
//            builder.append(entity+"<br/>");
//        }
//
//        return builder+"";
//    }
//
//
//    // // SAYFA, ELEMAN SAYISI VE SIRALAMA TÜRÜNE GÖRE SORTİNG >> ASC
//    // http://localhost:8080/getPagingAndSortingAsc/0/5/id
//    @GetMapping("getPagingAndSortingAsc/{page}/{size}/{sortValue}")
//    @ResponseBody
//    public String getPagingAndSortingAsc(
//            @PathVariable int page,
//            @PathVariable int size,
//            @PathVariable Object sortValue
//    ) {
//        Sort sort = Sort.by(sortValue.toString());
//        Pageable pageable =
//                PageRequest.of(page,size,sort);
//
//        var entities = teacherRepository.findAll(pageable);
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("Sayfa: ").append(page)
//                .append(", Size: ").append(size)
//                .append("<br/>Küçükten büyüğe sıralandı: <br/><br/>");
//        for (var entity : entities) {
//            log.info(entity);
//            builder.append(entity).append("<br/>");
//        }
//
//        return builder+"";
//    }
//
//
//
//



















































}
