package com.unal.teacher;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@Log4j2
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // baslangic sayfasi
    // http://localhost:8080/index
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    // DB YE TEACHER EKLEME
    // http://localhost:8080/addTeacher
    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("addKey", new Teacher());
        return "add";
    }

    // DB YE TEACHER EKLEME
    // http://localhost:8080/addTeacher
    @PostMapping("/addTeacher")
    public String postTeacher(@Valid @ModelAttribute("addKey") Teacher teacher, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            Teacher entity = Teacher
                    .builder()
                    .name(teacher.getName())
                    .surname(teacher.getSurname())
                    .age(teacher.getAge())
                    .build();

            teacherRepository.save(entity);
            model.addAttribute("teacher",entity);
            return "addSuccess";
        }

        return "add";
    }

    // PAGİNG
//    // DB'DEKİ VERİLERİ 0.SAYFADA SİZE'A GÖRE GETİRİR
//    // http://localhost:8080/paging/5
//    @GetMapping("/paging/{size}")
//    public String getPaging(@PathVariable(name = "size") int size, Model model) {
//
//        Pageable pageable = PageRequest.of(0, size);
//        Page<Teacher> entities = teacherRepository.findAll(pageable);
//
//
//        model.addAttribute("getSizeKey",entities);
//        return "size";
//    }
             //page and size pathvariable
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


//    // SORTİNG
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

    // ****************************************************************************
    // DB'DE OLAN TÜM VERİLERİ GETİRİR
    // http://localhost:8080/getAll
    @GetMapping("/getAll")
    public String getAll(Model model) {

        Iterable<Teacher> entities = teacherRepository.findAll();

        model.addAttribute("getAllKey", entities);
        return "getAll";
    }


    // ***************************************************************************
    // PAGİNG
    // DB'DEKİ VERİLERİ SAYFA NUMARASI VE SİZE DEĞERİNE GÖRE GETİRİR
    // http://localhost:8080/paging/0/5
    @GetMapping("/paging1")
    public String paging1(@RequestParam(value = "size") int size, Model model) {

        if (size <= 0) {
            return "index";
        }

        Pageable pageable = PageRequest.of(0,size);
        Page<Teacher> entities = teacherRepository.findAll(pageable);

        model.addAttribute("sizeKey",entities);
        return "size";

    }

    @GetMapping("/paging2")
    public String paging2(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model) {

        if (size <= 0) {
            return "index";
        }else if(page < 0) {
            return "index";
        }

        Pageable pageable = PageRequest.of(page,size);
        Page<Teacher> entities = teacherRepository.findAll(pageable);

        model.addAttribute("pageAndSizeKey",entities);
        return "pageAndSize";

    }

    // *****************************************************
    // SORTİNG LİSTESİ
    // KÜÇÜKTEN BÜYÜĞE SIRALAMA >> ASC
    // http://localhost:8080/asc
    @GetMapping("/ascId")
    public String ascid(Model model) {

        Sort sort = Sort.by("id");
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "asc";
    }
    @GetMapping("/ascName")
    public String ascname(Model model) {

        Sort sort = Sort.by("name");
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "asc";
    }
    @GetMapping("/ascSurname")
    public String ascnsurname(Model model) {

        Sort sort = Sort.by("surname");
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "asc";
    }
    @GetMapping("/ascAge")
    public String ascage(Model model) {

        Sort sort = Sort.by("age");
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "asc";
    }
    @GetMapping("/ascDate")
    public String ascdate(Model model) {

        Sort sort = Sort.by("date");
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "asc";
    }

    //      DESC
    //   http://localhost:8080/desc
    @GetMapping("/descId")
    public String descid(Model model) {

        Sort sort = Sort.by("id").descending();
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "desc";
    }
    @GetMapping("/descName")
    public String descname(Model model) {

        Sort sort = Sort.by("name").descending();
        var entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "desc";
    }
    @GetMapping("/descSurname")
    public String descsurname(Model model) {

        Sort sort = Sort.by("surname").descending();
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "desc";
    }
    @GetMapping("/descAge")
    public String descage(Model model) {

        Sort sort = Sort.by("age").descending();
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "desc";
    }
    @GetMapping("/descDate")
    public String descdate(Model model) {

        Sort sort = Sort.by("date").descending();
        Iterable<Teacher> entities = teacherRepository.findAll(sort);

        model.addAttribute("ascKey",entities);
        return "desc";
    }


    // ****************************************************************************
    //  SORTİNG
      // SAYFA, ELEMAN SAYISI VE SIRALAMA TÜRÜNE GÖRE SORTİNG >> ASC
     // http://localhost:8080/pagesizesort
    @GetMapping("ascIdps")
    public String ascIdps(@RequestParam(value = "page") final int page, @RequestParam(value = "size") final int size, Model model
    ) {
        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);


        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "ascPss";
    }

    @GetMapping("ascNameps")
    public String ascNameps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "ascPss";
    }

    @GetMapping("ascSurnameps")
    public String ascSurnameps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("surname");
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "ascPss";
    }

    @GetMapping("ascAgeps")
    public String ascAgeps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("age");
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "ascPss";
    }

    @GetMapping("ascDateps")
    public String ascDateps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("date");
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "ascPss";
    }

    // DESC
    @GetMapping("descIdps")
    public String descIdps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "descPss";
    }

    @GetMapping("descNameps")
    public String descNameps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "descPss";
    }

    @GetMapping("descSurnameps")
    public String descSurnameps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("surname").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "descPss";
    }

    @GetMapping("descAgeps")
    public String descAgeps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("age").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "descPss";
    }

    @GetMapping("descDateps")
    public String descDateps(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, Model model
    ) {
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        var entities = teacherRepository.findAll(pageable);

        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("pageSizeSortKey",entities);
        return "descPss";
    }

}
