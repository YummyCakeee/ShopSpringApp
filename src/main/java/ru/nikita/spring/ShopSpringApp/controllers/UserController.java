package ru.nikita.spring.ShopSpringApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.ShopSpringApp.dto.UserDTO;
import ru.nikita.spring.ShopSpringApp.models.User;
import ru.nikita.spring.ShopSpringApp.services.UsersService;
import ru.nikita.spring.ShopSpringApp.util.FieldErrorsData;
import ru.nikita.spring.ShopSpringApp.util.ItemNotFoundException;
import ru.nikita.spring.ShopSpringApp.util.ResponseData;
import ru.nikita.spring.ShopSpringApp.util.UserSortMode;

import javax.validation.Valid;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllUsers(
            @RequestParam(name = "sort",
                    required = false, defaultValue = "NONE") String sortMode) {
        UserSortMode userSortMode = UserSortMode.NONE;
        if (Objects.nonNull(sortMode)) {
            userSortMode = UserSortMode.valueOf(sortMode.toUpperCase());
        }
        ResponseData response = new ResponseData(
                usersService.findAll(userSortMode).stream().map(this::convertToUserDTO).collect(Collectors.toList()),
                true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") int id) {
        User foundUser = usersService.findById(id);
        if (Objects.isNull(foundUser)) {
            throw new ItemNotFoundException();
        }

        return new ResponseEntity<>(
                convertToUserDTO(usersService.findById(id)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseData> addUser(@RequestBody @Valid UserDTO userDTO,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorsData = FieldErrorsData.getErrorsData(bindingResult.getFieldErrors());
            return  new ResponseEntity<>(new ResponseData(false, errorsData), HttpStatus.BAD_REQUEST);
        }
        usersService.save(convertToUser(userDTO));
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteUser(@PathVariable("id") int id) {
        usersService.deleteById(id);
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateUser(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult,
                                                   @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            String errorsData = FieldErrorsData.getErrorsData(bindingResult.getFieldErrors());
            return  new ResponseEntity<>(new ResponseData(false, errorsData), HttpStatus.BAD_REQUEST);
        }
        User user = convertToUser(userDTO);
        user.setId(id);
        usersService.save(user);
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseData> userNotFoundExceptionHandler(ItemNotFoundException e) {
        return new ResponseEntity<>(
                new ResponseData(false, "Такого пользователя нет"),
                HttpStatus.NOT_FOUND);
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
