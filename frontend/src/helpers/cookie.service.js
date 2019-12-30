const ID_TOKEN_KEY = "user";
import Cookies from 'js-cookie'
var date = new Date(new Date().getTime() + 4 * 60 * 60 * 1000);

export const getToken = (token_name) => {
  return Cookies.get(token_name);
  //return localStorage.getItem(ID_TOKEN_KEY);
};

export const saveToken = (token_name, token) => {
  Cookies.set(token_name, token, { expires: date, secure: false});
  //localStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyToken = (token_name) => {
  Cookies.remove(token_name);
  //localStorage.removeItem(ID_TOKEN_KEY);
};

export default { getToken, saveToken, destroyToken };
