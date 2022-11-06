public class validator {
    private static final String VALID_CHARACTERS = "0123456789_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private validator() {

    }

    public static boolean check(String login,
                                String password,
                                String confirmPassword) {
        try {
            validate(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static void validate(String login,
                                 String password,
                                 String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (login == null || login.length() > 20) {
            throw new WrongLoginException("Длина логина должна быть меньше или равна 20");
        }
        if (password == null || password.length() >= 20) {
            throw new WrongPasswordException(" Длина пароля должна быть меньше 20");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли должны совпадать");
        }
        validateCharacters(login, true);
        validateCharacters(password, false);
    }


    private static void validateCharacters(String s,
                                           boolean isLogin) throws WrongLoginException, WrongPasswordException {
        for (int i = 0; i < s.length(); i++) {
            if (!VALID_CHARACTERS.contains(String.valueOf(s.charAt(i)))) {
                if (isLogin) {
                    throw new WrongLoginException(" В логине содержится некорректный символ'" + s.charAt(i) + "'!");
                } else {
                    throw new WrongPasswordException(" В логине содержится некорректный символ'" + s.charAt(i) + "'!");

                }

            }
        }
    }
}

