import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddUsuarioComponent } from './Usuario/add-usuario/add-usuario.component';
import { ListarUsuarioComponent } from './Usuario/listar-usuario/listar-usuario.component';
import { DeleterUsuarioComponent } from './Usuario/deleter-usuario/deleter-usuario.component';
import { EditUsuarioComponent } from './Usuario/edit-usuario/edit-usuario.component';
import { AddCuentaComponent } from './Cuenta/add-cuenta/add-cuenta.component';
import { ListarCuentaComponent } from './Cuenta/listar-cuenta/listar-cuenta.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from './Usuario/Services/usuario.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ListarOperacionesComponent } from './Operaciones/listar-operaciones/listar-operaciones.component';
import { ConsignacionComponent } from './Operaciones/consignacion/consignacion.component';
import { RetiroComponent } from './Operaciones/retiro/retiro.component';
import { TransferenciaComponent } from './Operaciones/transferencia/transferencia.component';
import { EditCuentaComponent } from './Cuenta/edit-cuenta/edit-cuenta.component';
import { LoginComponent } from './Core/login/login.component';
import { HeaderComponent } from './Core/header/header.component';
import { HttpConfigInterceptor } from './Core/HttpConfigInterceptor';
import { RegistrarComponent } from './user/registrar/registrar.component';
import { EditarComponent } from './user/editar/editar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddUsuarioComponent,
    ListarUsuarioComponent,
    DeleterUsuarioComponent,
    EditUsuarioComponent,
    AddCuentaComponent,
    ListarCuentaComponent,
    ListarOperacionesComponent,
    ConsignacionComponent,
    RetiroComponent,
    TransferenciaComponent,
    EditCuentaComponent,
    LoginComponent,
    HeaderComponent,
    RegistrarComponent,
    EditarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS,useClass: HttpConfigInterceptor, multi:true}],
  //providers: [UsuarioService],
  bootstrap: [AppComponent]
})
export class AppModule { }
