import { ListarClientComponent } from "../listar-client/listar-client.component";

export class Client{
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