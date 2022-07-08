import Base from "./base";

class User extends Base<InputRegister, UpdateUser> {

  register = async (url: string, variables: InputRegister) => {
    return this.http<InputRegister>(url, "post", variables);
  };

  login = async (url: string, variables: InputRegister) =>{
    return this.http<InputRegister>(url, "post", variables);
  }
}

export default new User();
