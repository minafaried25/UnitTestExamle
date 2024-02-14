import { Prop, Schema, SchemaFactory } from "@nestjs/mongoose";
import mongoose from "mongoose";
@Schema({ versionKey: false })
export class UserModel {
    @Prop({ type: mongoose.Types.ObjectId })
    id: string
    @Prop()
    name: string;
}
export const UserSchema = SchemaFactory.createForClass(UserModel);