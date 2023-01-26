package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping("")
    public String adminMain(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("user", new User());
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, Model model) {
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("allRoles", roleList);
        User user = new User();
        model.addAttribute("user", user);
        user.setId(id);
        return "update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("/addUser")
    public String saveUser(@Validated(User.class) @ModelAttribute("user") User user,@RequestParam("authorities") List<String> values, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = roleService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("allRoles", roleList);
        return "newUser";
    }
}
