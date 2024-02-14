
import { IsNotEmpty, IsNumber, IsString } from "class-validator";

export class UserCreateDto {
    @IsString()
    @IsNotEmpty()
    name: string;
}