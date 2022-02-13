import { User } from "./User";

export class UserDto{
    user: User = new User();
    newPassword: string;
}