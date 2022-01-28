import { ListarUsuarioComponent } from "../Usuario/listar-usuario/listar-usuario.component";

export interface Usuario{
    id: number;
    tipoId: String;
    identificacion: number;
    name: String;
    apellido: String;
    email: String;
    fecha_nac: String;
    fecha_crea: String;
    estado: Boolean;
    usuario_codigo: String;
}