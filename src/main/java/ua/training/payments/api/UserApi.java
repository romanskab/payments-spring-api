package ua.training.payments.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.dto.validation.group.OnUpdate;
import ua.training.payments.model.User;

@Api(tags = "User management REST API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1/user")
public interface UserApi {

    @ApiOperation("Get user API")
    @ApiResponse(code = 200, message = "OK", response = UserModel.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    UserModel getUser(@AuthenticationPrincipal User user);

    @ApiOperation("Update user API")
    @ApiResponse(code = 200, message = "OK", response = UserModel.class)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    UserModel updateUser(@RequestBody @Validated(OnUpdate.class) UserDto userDto);

    @ApiOperation("Delete user API")
    @ApiResponse(code = 204, message = "No content")
    @DeleteMapping
    ResponseEntity<Void> deleteUser(@AuthenticationPrincipal User user);

}
