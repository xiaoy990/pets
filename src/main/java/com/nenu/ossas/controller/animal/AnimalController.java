package com.nenu.ossas.controller.animal;

import com.nenu.ossas.entity.animal.Animal;
import com.nenu.ossas.service.animal.AnimalService;
import com.nenu.ossas.util.ui.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    /**
     * 寻宠页面
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public List<Animal> listAll(Model model){
        List<Animal> list = null;
        try {
            list = animalService.listAll();
            model.addAttribute("animalList",list);
            //return "xunchong";
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping(value = "manageAm")
    public String manageAm(Model model){
        List<Button> list = new ArrayList<>();
        Button button = new Button();
        button.setHref("/delPetClinic?id=");
        button.setValue("删除");
        list.add(button);

        model.addAttribute("buttons",list);
        model.addAttribute("list",animalService.listAll());
        return "PAGES/manage";
    }


    /**
     * 跳转增加页面
     * @return
     */
    @RequestMapping(value = "/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "/addAnimal";
    }


    /**
     * 增加动物信息页面
     * @param animal
     * @return
     */
    @RequestMapping(value = "/addAnimal",method = RequestMethod.POST)
    public String addAnimal(Animal animal){
        try {
            animalService.addAnimal(animal);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 删除动物信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delAnimal",method = RequestMethod.GET)
    public String delAnimal(int id){
        try {
            animalService.delAnimal(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }


    /**
     * 跳转更新页面
     * @return
     */
    @RequestMapping(value = "/toUpdate",method =RequestMethod.GET)
    public String toUpdate(){
        return "updateAnimal";
    }


    /**
     * 更新动物信息
     * @param animal
     * @return
     */
    @RequestMapping(value = "/updateAnimal",method = RequestMethod.POST)
    public String updateAnimal(Animal animal){
        try {
            animalService.updateAnimal(animal);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 根据关键字查询动物信息
     * @param keyword
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectByKeyword",method = RequestMethod.GET)
    public String selectByKeyword(String keyword,Model model){
        try {
            List<Animal> resultList = null;
            resultList = animalService.selectByKeyword(keyword);
            model.addAttribute("resultList1",resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "resultList1";
    }
}
