import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Core/login/login.component';
import { AddCuentaComponent } from './Cuenta/add-cuenta/add-cuenta.component';
import { EditCuentaComponent } from './Cuenta/edit-cuenta/edit-cuenta.component';
import { ListarCuentaComponent } from './Cuenta/listar-cuenta/listar-cuenta.component';
import { ConsignacionComponent } from './Operaciones/consignacion/consignacion.component';
import { ListarOperacionesComponent } from './Operaciones/listar-operaciones/listar-operaciones.component';
import { RetiroComponent } from './Operaciones/retiro/retiro.component';
import { TransferenciaComponent } from './Operaciones/transferencia/transferencia.component';
import { EditarUserComponent } from './user/editar-user/editar-user.component';
import { NewPasswordComponent } from './user/new-password/new-password.component';
import { RegistrarComponent } from './user/registrar/registrar.component';
import { AddClientComponent } from './Client/add-client/add-client.component';
import { EditClientComponent } from './Client/edit-client/edit-client.component';
import { ListarClientComponent } from './Client/listar-client/listar-client.component';
import { DeleterClientComponent } from './Client/deleter-client/deleter-client.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  // listar clientes
  {path:'listar', component:ListarClientComponent},
  // Buscar cliente por id
  // {path:'clienteid/:id',component:EditClienteComponent},
  // agregar cliente
  {path:'add', component:AddClientComponent},
  // editar cliente
  {path:'edit/:id', component:EditClientComponent},
  // eliminar cliente
  {path:'delete', component:DeleterClientComponent},
  // listar cuentas
  {path: 'listarCuentas/:id', component:ListarCuentaComponent},
  // nueva cuenta agregar
  {path: 'addCuenta/:id', component:AddCuentaComponent},
  //Edit cuenta
  {path: 'editCuenta/:id', component:EditCuentaComponent},
  // listar operaciones
  //{path: 'listarCuentas/:id/transactions/:id', component:ListarOperaciones}
  {path: 'listarOperaciones/:id', component:ListarOperacionesComponent},
  //operacion retiro
  {path: 'retiro/:id', component:RetiroComponent},
  //operacion consignacion
  {path: 'consignacion/:id', component:ConsignacionComponent},
  //operacion transferencia 
  {path: 'transferencia/:id', component:TransferenciaComponent},
  //Login
  {path: 'login', component:LoginComponent},
  //Singup
  {path: 'registrar', component:RegistrarComponent},
  //Editar usuario
  {path: 'editarUser', component:EditarUserComponent},
  //Editar usuario
  {path: 'editPassword', component:NewPasswordComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
