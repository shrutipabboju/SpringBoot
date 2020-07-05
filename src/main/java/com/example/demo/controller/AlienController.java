package com.example.demo.controller;

import com.example.demo.dao.AlienDao;
import com.example.demo.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

//@RestController This is if all the methods are REST oriented and you can remove ResponseBody everywhere
@Controller
public class AlienController {

    @Autowired
    AlienDao alienDao;

    // All this is MVC Spring
//    @RequestMapping("/")
//    public String home() {
//        return "home.jsp";
//    }
//
//    @RequestMapping("/addAlien")
//    public String addAlien(Alien alien){
//        alienDao.save(alien);
//        return "home.jsp";
//    }
//
//    @RequestMapping("/getAlien")
//    public ModelAndView getAlien(@RequestParam Integer aid){
//        ModelAndView mv = new ModelAndView("showAlien.jsp");
//        Alien alien = alienDao.findById(aid).orElse(new Alien());
//        mv.addObject(alien);
//        return mv;
//    }
//
//    @RequestMapping("/updateAlien")
//    public ModelAndView updateAlien(@RequestParam("aid1") Integer aid1,Alien alien){
//        ModelAndView mv = new ModelAndView("showAlien.jsp");
//        alienDao.deleteById(aid1);
//        alienDao.save(alien);
//        System.out.println(alienDao.findByTech("Java"));
//        System.out.println(alienDao.findByAidGreaterThan(102));
//        System.out.println(alienDao.findByTechSorted("Test"));
//        mv.addObject(alien);
//        return mv;
//    }

    //only supports XML in the commented case
    //@RequestMapping(path = "/aliens", produces ={"application/xml"})
    @GetMapping("/aliens")
    @ResponseBody
    public List<Alien> getAliens(){
       //return alienDao.findAll().toString();
        return alienDao.findAll();
    }

    //@GetMapping - You can use this, although default for Request Mapping is GET
    @RequestMapping("/alien/{aid}")
    @ResponseBody
    public Optional<Alien> getSpecificAlien(@PathVariable("aid") Integer aid){
        //return alienDao.findById(aid).toString();
        return alienDao.findById(aid);
    }

    //@PostMapping(path="/aliens",consumes = {"application/json"}) - Can be used to collect only JSON form data
    @PostMapping("/alien")
    @ResponseBody
    public Alien addRESTAlien(@RequestBody Alien alien){
        //@RequestBody is for collecting raw data
        alienDao.save(alien);
        return alien;
    }

    @PutMapping("/alien")
    @ResponseBody
    public Alien saveOrUpdate(@RequestBody Alien alien){
        alienDao.save(alien);
        return alien;
    }

    @DeleteMapping("/alien/{aid}")
    @ResponseBody
    public String deleteAlien(@PathVariable Integer aid){
        Alien a = alienDao.getOne(aid);
        alienDao.delete(a);
        //return a; - Can't return a after you delete it, can you? You can't basically
        return "deleted";
    }
}
