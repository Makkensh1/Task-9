package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users1", userService.listUsers());
        return "users";
    }

    @RequestMapping("")
    public String adminMain() {
        return "admin";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        user.setId(id);
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("allRoles", roleList);
        return "update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/addNew")
    public String addNewUser(Model model) {
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("allRoles", roleList);
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }
}
