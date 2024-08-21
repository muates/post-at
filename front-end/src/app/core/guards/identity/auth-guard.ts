import { Injectable } from "@angular/core";
import { AuthService } from "../../services/identity/auth.service";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router
    ) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {
        const isLoggedIn = this.authService.isLoggedIn();
        if (isLoggedIn) {
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
    }
}
