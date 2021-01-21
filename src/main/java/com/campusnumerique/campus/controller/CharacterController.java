package com.campusnumerique.campus.controller;

import java.util.List;

import com.campusnumerique.campus.form.CharacterForm;
import com.campusnumerique.campus.model.Character;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
class MainController {

    @Autowired
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/characterList"}, method = RequestMethod.GET)
    public List<Character> characterList() {

        List<Character> characterList = restTemplate.getForObject(
                "http://localhost:8081/character", List.class);

        return characterList;
    }

    @RequestMapping(value = {"/character/{id}"}, method = RequestMethod.GET)
    public Character characterById() {

        return restTemplate.getForObject(
                "http://localhost:8081/character/{id}", Character.class);
    }

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.GET)
    public String showAddCharacter(Model model) {

        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);

        return "addCharacter";
    }

    @RequestMapping(value = {"/addCharacter"}, method = RequestMethod.POST)
    public String saveCharacter(Model model, //
                                @ModelAttribute("characterForm") CharacterForm characterForm) throws JSONException {

        String name = characterForm.getName();
        String type = characterForm.getType();

        String url = "http://localhost:8081/character";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject characterJsonObject = new JSONObject();
        characterJsonObject.put("id", characterList().size()+1);
        characterJsonObject.put("name", name);
        characterJsonObject.put("type", type);
        HttpEntity<String> request = new HttpEntity<>(characterJsonObject.toString(), headers);

        if (name != null && name.length() > 0 //
                && type != null && type.length() > 0) {

            restTemplate.postForObject(url, request, Character.class);

            return "redirect:/characterList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCharacter";
    }

}