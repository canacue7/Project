import { Injectable } from "@angular/core";
import { User } from "src/app/user/Models/User";

@Injectable({
    providedIn: 'root'
})

export class GlobalService {
    public user: User = new User();

    constructor() {}
}