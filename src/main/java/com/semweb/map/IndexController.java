package com.semweb.map;

import java.util.ArrayList;

import com.semweb.map.model.Coordinate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {

        ArrayList<Coordinate> coordList = new ArrayList<>();

        coordList.add(new Coordinate(45.447102, 4.386077));
        coordList.add(new Coordinate(45.448178, 4.386066));
        coordList.add(new Coordinate(45.448758, 4.384296));
        coordList.add(new Coordinate(45.446974, 4.384382));
        coordList.add(new Coordinate(45.448486, 4.385906));

        Coordinate[] coords = {
            new Coordinate(45.447102, 4.386077)
        };

        model.addAttribute("name", "A tous ceux qui detestent Ubisoft, voici les endroits où vous trouverez son plus grand fan, Altaïr");

        
            model.addAttribute("coords", coordList);
        

        return "index";
    }

}
