package ua.training.payments.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.training.payments.controller.model.AccountModel;
import ua.training.payments.model.User;

@Api(tags = "User's accounts management REST API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1/user/accounts")
public interface UserAccountApi {

    @ApiOperation("Create account API")
    @ApiResponse(code = 201, message = "Created", response = AccountModel.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountModel create(@AuthenticationPrincipal User user);

    @ApiOperation("Get list of all user's accounts")
    @ApiResponse(code = 200, message = "OK", response = CollectionModel.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<AccountModel> getAllAccountsOfUser(@AuthenticationPrincipal User user);

}
