export class User{


	constructor($name: string, $nick: string, $lastName: string, $phoneNumber: string, $email: string, $password: string) {
		this.name = $name;
		this.nick = $nick;
		this.lastName = $lastName;
		this.phoneNumber = $phoneNumber;
		this.email = $email;
		this.password = $password;
	}
    
    name:string;
    nick:string;
    lastName:string;
    phoneNumber:string;
    email:string;
    password:string;

}