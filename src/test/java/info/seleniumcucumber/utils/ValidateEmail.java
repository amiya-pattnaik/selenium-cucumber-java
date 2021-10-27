package info.seleniumcucumber.utils;

import net.thucydides.core.annotations.Step;

public class ValidateEmail {
    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
