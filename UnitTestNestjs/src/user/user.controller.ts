import { Body, Controller, Get, Param, Post, ValidationPipe } from "@nestjs/common";
import { UserService } from "./user.service";
import { UserCreateDto } from "./user.create.dto";

@Controller("user")
export class UserController {
    constructor(private userService: UserService) { }
    @Get("all")
    all() {
        return this.userService.getUsers()
    }

    @Post("create")
    async create(@Body(new ValidationPipe()) user: UserCreateDto) {
        return await this.userService.createUser(user)
    }
}