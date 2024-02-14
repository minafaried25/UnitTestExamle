import { Injectable } from "@nestjs/common";
import { UserModel } from "./user.model";
import { InjectModel } from "@nestjs/mongoose";
import { Model } from "mongoose";
import { UserCreateDto } from "./user.create.dto";

@Injectable({})
export class UserService {
    constructor(@InjectModel('users') private userMongo) { }
    getUsers() {
        return this.userMongo.find().exec();
    }
    async createUser(user: UserCreateDto) {
        const newUser = await new this.userMongo(user);
        return newUser.save();
    }
}
