export class Usuario {
  id: number;
  username: string;
  password: string;
  nombres: string;
  apellidos: string;
  email: string;
  entidad: any;
  enabled: boolean;
  roles: string[] = [];
}
