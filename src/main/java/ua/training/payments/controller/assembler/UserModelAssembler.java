package ua.training.payments.controller.assembler;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ua.training.payments.controller.UserController;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public static final String GET_REL = "get";
    public static final String UPDATE_REL = "update";
    public static final String DELETE_REL = "delete";

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);

        Link getLink = linkTo(methodOn(UserController.class).getUser(new User())).withRel(GET_REL);
        Link updateLink = linkTo(methodOn(UserController.class).updateUser(null)).withRel(UPDATE_REL);
        Link deleteLink = linkTo(methodOn(UserController.class).deleteUser(new User())).withRel(DELETE_REL);

        userModel.add(deleteLink, updateLink, getLink);

        return userModel;
    }
}
