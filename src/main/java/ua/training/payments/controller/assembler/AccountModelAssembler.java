package ua.training.payments.controller.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ua.training.payments.controller.UserAccountController;
import ua.training.payments.controller.model.AccountModel;
import ua.training.payments.dto.AccountDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AccountModelAssembler extends RepresentationModelAssemblerSupport<AccountDto, AccountModel> {

    public static final String GET_REL = "get";
    public static final String UPDATE_REL = "update";
    public static final String DELETE_REL = "delete";

    public AccountModelAssembler() {
        super(UserAccountController.class, AccountModel.class);
    }

    @Override
    public AccountModel toModel(AccountDto entity) {
        AccountModel accountModel = new AccountModel(entity);

//        Link getLink = linkTo(methodOn(AccountController.class).getAccount(new Account())).withRel(GET_REL);
        return accountModel;
    }
}
